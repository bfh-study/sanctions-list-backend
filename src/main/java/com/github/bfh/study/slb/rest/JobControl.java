package com.github.bfh.study.slb.rest;

import com.github.bfh.study.slb.imports.job.PrepareStep;

import com.github.bfh.study.slb.imports.job.XmlReader;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST interface for control a batch job.
 *
 * @author Samuel Ackermann
 */
@Path("job")
public class JobControl {

    /**
     * Starts a job.
     *
     * @param jobName Name of the job
     * @param source Name of the source
     * @param path path or URL where xml can be found
     *
     * @return Response with no content and status code 201
     */
    @POST
    @Path("/{jobName}/{source}")
    public Response startJob(
        @PathParam("jobName") String jobName,
        @PathParam("source") String source,
        @QueryParam("path") String path
    ) {
        JobOperator operator =  BatchRuntime.getJobOperator();
        Properties jobProperties = new Properties();
        jobProperties.setProperty(PrepareStep.SOURCE_NAME_PROPERTY, source);
        jobProperties.setProperty(XmlReader.PATH_NAME_PROPERTY, path);

        operator.start(jobName, jobProperties);

        return Response.noContent()
            .status(Status.CREATED)
            .build();
    }
}
