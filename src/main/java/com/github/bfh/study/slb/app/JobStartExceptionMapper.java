package com.github.bfh.study.slb.app;

import io.crnk.core.engine.document.ErrorData;
import io.crnk.core.engine.error.ErrorResponse;
import io.crnk.core.engine.error.ExceptionMapper;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.batch.operations.JobStartException;
import javax.ws.rs.core.Response.Status;

/**
 * Mapper for JobStartException.
 *
 * @author Samuel Ackermann
 */
class JobStartExceptionMapper implements ExceptionMapper<JobStartException> {

    @Override
    public ErrorResponse toErrorResponse(JobStartException exception) {
        ErrorData errorData = ErrorData.builder()
            .setId(String.valueOf(Status.BAD_REQUEST.getStatusCode()))
            .setTitle("Can not start job")
            .setDetail(exception.getMessage())
            .setStatus(Status.BAD_REQUEST.getReasonPhrase())
            .build();

        return ErrorResponse.builder()
            .setSingleErrorData(errorData)
            .setStatus(Status.BAD_REQUEST.getStatusCode())
            .build();
    }

    @Override
    public JobStartException fromErrorResponse(ErrorResponse errorResponse) {
        throw new NotImplementedException();
    }

    @Override
    public boolean accepts(ErrorResponse errorResponse) {
        return errorResponse.getHttpStatus() == Status.BAD_REQUEST.getStatusCode();
    }
}
