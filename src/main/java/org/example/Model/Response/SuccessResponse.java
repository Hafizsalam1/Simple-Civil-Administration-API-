package org.example.Model.Response;

import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends CommonResponse{

    private T data;

    public SuccessResponse(String Message, T data) {
        super.setCode("00");
        super.setMessage(Message);
        super.setStatus(HttpStatus.OK.name());
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
