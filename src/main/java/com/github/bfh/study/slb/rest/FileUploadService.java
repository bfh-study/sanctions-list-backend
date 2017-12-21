package com.github.bfh.study.slb.rest;

import com.github.bfh.study.slb.imports.job.JobProperties;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 * File Upload Service.
 *
 * @author Dario Carosella
 */
@Path("file")
public class FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    private static final String TMP_PATH_PROPERTY = "sanction-list.tmp-dir-path";

    /**
     * Test REST interface.
     *
     * @return "API is up and running"
     */
    @GET
    @Path("test")
    public Response isRunning() {
        logger.info("Calling API Tester");
        return Response.status(Status.OK).entity("API is up and running").build();
    }

    /**
     * Handle Cross-Origin Resource Sharing (CORS).
     *
     * @return Status.OK
     */
    @OPTIONS
    @Path("upload")
    public Response uploadFile() {
        logger.info("Calling the method, 'OPTIONS' and response with CORS");
        return Response
                .status(Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST")
                .build();
    }

    /**
     *  File uploader.
     *
     * @param input file information
     * @param slSource the provider
     * @return Status.OK
     */
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            MultipartFormDataInput input,
            @QueryParam("slSource") String slSource) {

        logger.info("Starting fileUploader for " + slSource + " source");

        String tmpPath = System.getProperty(TMP_PATH_PROPERTY);
        if (tmpPath == null) {
            return Response.serverError().build();
        }

        Map<String, List<InputPart>> formData = input.getFormDataMap();
        List<InputPart> inputParts = formData.get("file");
        InputStream inputStream = null;

        ResponseBuilder responseBuilder = Response.status(Status.OK)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST");
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> headers = inputPart.getHeaders();

                String fileName = parseFileName(headers);

                inputStream = inputPart.getBody(InputStream.class, null);

                logger.info("Uploading file, " + fileName + " to server");

                fileName = tmpPath + fileName;
                saveFile(inputStream, fileName);

                JobOperator operator =  BatchRuntime.getJobOperator();
                Properties jobProperties = new Properties();
                jobProperties.setProperty(JobProperties.SOURCE_NAME_PROPERTY, slSource);
                jobProperties.setProperty(JobProperties.PATH_NAME_PROPERTY, fileName);

                operator.start(JobProperties.JOB_NAME, jobProperties);
                logger.info("upload was successful");
            } catch (IOException ex) {
                logger.error(ex.getMessage());
                responseBuilder.status(Status.INTERNAL_SERVER_ERROR);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        logger.error(ex.getMessage());
                    }
                }
            }
        }

        return responseBuilder.build();
    }

    /**
     * Get the fileName.
     *
     * @param headers information from the UploadForm
     * @return fileName
     */
    private String parseFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        String fileName = "randomFileName.xml";

        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                fileName = tmp[1].trim().replaceAll("\"", "");

                return fileName;
            }
        }

        logger.warn("No fileName found. Set file name to randomFileName.xml");
        return fileName;
    }

    /**
     * Save file.
     *
     * @param inputStream the Data of the file
     * @param fileName the name of the file
     */
    private void saveFile(InputStream inputStream, String fileName) {
        OutputStream outputStream = null;
        try {
            int read;
            byte[] bytes = new byte[1024 * 1024 * 8];

            outputStream = new FileOutputStream(new File(fileName));
            while (-1 != (read = inputStream.read(bytes))) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.flush();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
