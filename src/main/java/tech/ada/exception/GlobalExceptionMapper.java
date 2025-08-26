package tech.ada.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof IsbnAlreadyExistsException) {
            return buildResponse("Conflict", exception.getMessage(), Response.Status.CONFLICT.getStatusCode());
        }

        if (exception instanceof TitleAlreadyExistsException) {
            return buildResponse("Conflict", exception.getMessage(), Response.Status.CONFLICT.getStatusCode());
        }

        if (exception instanceof TitleNotExistsException) {
            return buildResponse("Not Found", exception.getMessage(), Response.Status.NOT_FOUND.getStatusCode());
        }

        if (exception instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) exception).getConstraintViolations()
                    .stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.joining("; "));
            return buildResponse("Validation Error", message, Response.Status.BAD_REQUEST.getStatusCode());
        }

        if (exception instanceof NoIdFound) {
            return buildResponse("Not Found", exception.getMessage(), Response.Status.NOT_FOUND.getStatusCode());
        }

        return buildResponse("Internal Server Error", exception.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    private Response buildResponse(String error, String message, int status) {
        return Response.status(status)
                .entity(new ErrorResponse(error, message, status))
                .build();
    }
}
