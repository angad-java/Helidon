package io.helidon.exception;

public class HelidonbarException extends Exception {
    int code;

    public HelidonbarException() { super(); }

    public HelidonbarException(String message) {
        super(message);
    }

    public HelidonbarException(int code, String message) {
        this(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
