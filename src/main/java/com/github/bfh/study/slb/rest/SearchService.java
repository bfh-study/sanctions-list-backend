package com.github.bfh.study.slb.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("search")
public class SearchService {

    @GET
    @Path("/{for}")
    public Response search(@PathParam("for") String searchTxt){
        return Response
                .status(Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .entity(searchTxt)
                .build();
    }
}
