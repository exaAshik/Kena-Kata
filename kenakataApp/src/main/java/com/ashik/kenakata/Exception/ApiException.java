package com.ashik.kenakata.Exception;

public class ApiException extends Exception{

    private String status;

    private String message;

    public ApiException(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiException(String message, String status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String status, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = message1;
    }

    public ApiException(Throwable cause, String status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public ApiException(String message, Throwable cause, String status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
