package org.example.imooc_oa.service.error;

public class LoginError extends RuntimeException{
    public LoginError(String msg) {
        super(msg);
    }
}
