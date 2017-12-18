package com.github.bfh.study.slb.rest;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * File Upload Service
 *
 * @author Dario Carosella
 */
@Path("file")
public class FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "src/main/resources/uploads/";

    /**
     * Test REST interface
     *
     * @return "API is up and running"
     */
    @GET
    @Path("test")
    public Response isRunning() {
        logger.info("Caling API Tester");
        return Response.status(Status.OK).entity("API is up and running").build();
    }

    /**
     * Handle Cross-Origin Resource Sharing (CORS)
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
     * Uploading file to server
     *
     * @param input
     * @return Status.OK
     */
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            MultipartFormDataInput input,
            @QueryParam("slSource") String slSource) {

        logger.info("Starting fileUploader for " + slSource + " source");

        Map<String, List<InputPart>> formData = input.getFormDataMap();
        List<InputPart> inputParts = formData.get("file");
        InputStream inputStream = null;

        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> headers = inputPart.getHeaders();

                String fileName = parseFileName(headers);

                inputStream = inputPart.getBody(InputStream.class, null);

                logger.info("Uploading file, " + fileName + " to server");

                fileName = SERVER_UPLOAD_LOCATION_FOLDER + fileName;
                saveFile(inputStream, fileName);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
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

        logger.info("upload was successful");

        return Response
                .status(Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST")
                .build();
    }

    /**
     * Get the fileName
     *
     * @param headers
     * @return fileName
     */
    private String parseFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"", "");

                return fileName;
            }
        }

        logger.warn("No fileName found. Set file name to randomFileName.xml");
        return "randomFileName.xml";
    }

    /**
     * Save file
     *
     * @param inputStream
     * @param fileName
     */
    private void saveFile(InputStream inputStream, String fileName) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            outputStream = new FileOutputStream(new File(fileName));
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                if (outputStream != null) outputStream.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}

