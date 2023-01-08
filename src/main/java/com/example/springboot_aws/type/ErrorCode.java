package com.example.springboot_aws.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Getter
@RequiredArgsConstructor
@Slf4j
public enum ErrorCode {

    POST_NOT_FOUND("해당하는 게시글이 존재하지 않습니다.");
    private final String message;
}
