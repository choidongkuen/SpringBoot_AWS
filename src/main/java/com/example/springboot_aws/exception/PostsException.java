package com.example.springboot_aws.exception;

import com.example.springboot_aws.type.ErrorCode;

public class PostsException extends RuntimeException{

    private ErrorCode errorCode;

    private String message;


    public PostsException(ErrorCode errorCode) {

        this.errorCode = errorCode;
        this.message = errorCode.getMessage();

    }
}
