package org.demo.exception;

public record ApiError(
        int status,
        String message
) {
}
