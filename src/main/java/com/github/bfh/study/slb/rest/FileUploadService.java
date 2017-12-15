package com.github.bfh.study.slb.rest;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.*;
import java.util.List;
import java.util.Map;

@Path("file")
public class FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/dario/Desktop/tmp/";

    @GET
    @Path("hello")
    public Response sayHello(){

        return Response.status(Status.OK).entity("Hello Dario duuu löu").build();
    }

    @OPTIONS
    @Path("upload")
    public Response fileUpload() {

        return Response.status(Status.METHOD_NOT_ALLOWED).header("Access-Control-Allow-Methods", "POST").header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("upload")
    public Response fileUpload(MultipartFormDataInput input) {

        String fileName = "";

        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inputParts = formParts.get("file");

        for (InputPart inputPart : inputParts) {

            try {

                // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                fileName = parseFileName(headers);

                // Handle the body of that part with an InputStream
                InputStream istream = inputPart.getBody(InputStream.class,null);

                fileName = SERVER_UPLOAD_LOCATION_FOLDER + fileName;

                saveFile(istream,fileName);

            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }

        String output = "File saved to server location : " + fileName;

        return Response.status(200).entity(output).build();
    }

    // Parse Content-Disposition header to get the original file name
    private String parseFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {

            if ((name.trim().startsWith("filename"))) {

                String[] tmp = name.split("=");

                String fileName = tmp[1].trim().replaceAll("\"","");

                return fileName;
            }
        }
        return "randomName";
    }

    // save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream,
                          String serverLocation) {

        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
