package fr.uge.lootin.back.exception;


import fr.uge.lootin.back.dto.ErrorResponse;

public class BadRequestException extends RuntimeException {

    private final int errorCode;

    BadRequestException(String message, int errorCode) {
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
