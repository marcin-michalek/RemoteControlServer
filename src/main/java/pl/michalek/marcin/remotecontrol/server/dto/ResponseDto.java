package pl.michalek.marcin.remotecontrol.server.dto;

/**
 * Base response from server.
 */
public class ResponseDto {
    private boolean success;
    private String message;

    private ResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseDto success() {
        return new ResponseDto(true, "Success");
    }
}
