package org.dto;

public class ResultadoDTO {
    private boolean success;
    private String message;

    public ResultadoDTO(){};

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess(){
        return  success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
