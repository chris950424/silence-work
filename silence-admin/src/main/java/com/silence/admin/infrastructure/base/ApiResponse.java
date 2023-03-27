package com.silence.admin.infrastructure.base;



import java.io.Serializable;
import java.util.Base64;

/**
 * ApiResponse
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/27
 */
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -1L;
    private String code;
    private String message;
    private T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> success() {
        return success(null, null);
    }

    public static <T> ApiResponse<T> success(String message) {
        return success(message, null);
    }

    public static <T> ApiResponse<T> success(T result) {
        return success(null, result);
    }

    public static <T> ApiResponse<T> success(String message, T result) {
        return getInstance("0", message, result);
    }

    public static <T> ApiResponse<T> fail() {
        return fail(null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return getInstance("9999", message, null);
    }


    private static <T> ApiResponse<T> getInstance(String code, String message, T result) {
        return new ApiResponse<T>(code, message, result);
    }


    public static void main(String[] args) {
        final Base64.Decoder decoder = Base64.getDecoder();
        final byte[] hhTks = decoder.decode("hhTk");
        System.out.println(hhTks);

    }
}
