package com.github.bfh.study.slb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("allowed")
public class SearchPersonService {

    @GET
    @Path("/{firstName}/{lastName}")
    public Response isPersonAllowed(
            @PathParam("firstName") String firstName,
            @PathParam("lastName") String lastName) {

        if (firstName.toUpperCase().contains("KIM")
                && lastName.toUpperCase().contains("JONG-UN")) {
            return Response
                    .status(Status.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(Boolean.FALSE)
                    .build();
        } else {
            return Response
                    .status(Status.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(Boolean.TRUE)
                    .build();
        }
    }
}
