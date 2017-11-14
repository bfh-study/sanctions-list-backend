package com.github.bfh.study.slb.app;

import io.crnk.core.engine.document.ErrorData;
import io.crnk.core.engine.error.ErrorResponse;
import io.crnk.core.engine.error.ExceptionMapper;
import javax.batch.operations.JobStartException;
import javax.ws.rs.core.Response.Status;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by samuel on 14.11.17.
 */
class JobStartExceptionMapper implements ExceptionMapper<JobStartException> {

    @Override
    public ErrorResponse toErrorResponse(JobStartException e) {
        ErrorData errorData = ErrorData.builder()
            .setId(String.valueOf(Status.BAD_REQUEST.getStatusCode()))
            .setTitle("Can not start job")
            .setDetail(e.getMessage())
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
