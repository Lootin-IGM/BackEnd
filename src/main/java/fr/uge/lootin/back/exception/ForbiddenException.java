package fr.uge.lootin.back.exception;


import fr.uge.lootin.back.dto.ErrorResponse;

public class ForbiddenException extends RuntimeException {

    private final int errorCode;

    ForbiddenException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(errorCode, getMessage());
    }
}
