package com.github.bfh.study.slb.rest;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
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
 * REST interface for file uploader
 */
@Path("file")
public class FileUploaderService {

    private static final Logger _log = LoggerFactory.getLogger(FileUploaderService.class);
    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/Dario/Desktop/tmp/";

    /**
     * Test REST interface
     *
     * @return "Hellllllo jööööööööööööggu"
     */
    @GET
    @Path("test")
    public Response sayHello(){
        return Response.status(Status.OK).entity("Hellllllo Jööööööööggu").build();
    }

    @OPTIONS
    @Path("upload")
    public Response uploadFile(){
        return Response
                .status(Status.OK)
                .header("Access-Control-Allow-Origin", "http://localhost:4200")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) {

        Map<String, List<InputPart>> formData = input.getFormDataMap();
        List<InputPart> inputParts = formData.get("file");
        inputParts.addAll(formData.get("slSource"));

        for (InputPart inputPart : inputParts) {
            try{
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                if(headers.containsKey("Content-Type")){
                    String fileName = parseFileName(headers);

                    InputStream inputStream = inputPart.getBody(InputStream.class, null);

                    fileName = SERVER_UPLOAD_LOCATION_FOLDER + fileName;

                    saveFile(inputStream, fileName);
                } else{
                    String slSource = getSlSource(headers);
                }
            } catch(IOException ex){
                _log.error(ex.getMessage());
            }
        }

        return Response
                .status(Status.OK)
                .build();
    }

    private String parseFileName(MultivaluedMap<String, String> headers){

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader){
            if((name.trim().startsWith("filename"))){
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"", "");

                return fileName;
            }
        }

        return "randomName";
    }

    private String getSlSource(MultivaluedMap<String, String> headers){

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader){
            if((name.trim().startsWith("name="))){
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"", "");

                return fileName;
            }
        }

        return "NO FOUND!";
    }

    private void saveFile(InputStream inputStream, String fileName){
        try{
            OutputStream outputStream = new FileOutputStream(new File(fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            outputStream = new FileOutputStream(new File(fileName));
            while ((read = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, read);
            }

            outputStream.flush();
            outputStream.close();
        }catch (IOException ex){
            _log.error(ex.getMessage());
        }
    }
}
