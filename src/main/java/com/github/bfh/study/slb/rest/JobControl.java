package com.github.bfh.study.slb.rest;

import com.github.bfh.study.slb.imports.job.PrepareStep;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST interface for control a batch job
 *
 * @author Samuel Ackermann
 */
@Path("job")
public class JobControl {

  @POST
  @Path("/{jobName}/{source}")
  public Response startJob(@PathParam("jobName") String jobName, @PathParam("source") String source) {
    JobOperator operator =  BatchRuntime.getJobOperator();
    Properties jobProperties = new Properties();
    jobProperties.setProperty(PrepareStep.SOURCE_NAME_PROPERTY, source);

    operator.start(jobName, jobProperties);

    return Response.noContent()
        .status(Status.CREATED)
        .build();
  }
}
