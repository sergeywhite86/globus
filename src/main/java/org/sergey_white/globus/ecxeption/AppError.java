package org.sergey_white.globus.ecxeption;

public record AppError(int status, String message, Object details) {
    public AppError(int status, String message) {
        this(status, message, null);
    }
}
