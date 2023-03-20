package org.example.Model.Response;

public class ErrorResponse  extends CommonResponse{
    public ErrorResponse(String Code, String Message) {
        super.setCode(Code);
        super.setMessage(Message);
        super.setStatus("Failed");
    }

}
