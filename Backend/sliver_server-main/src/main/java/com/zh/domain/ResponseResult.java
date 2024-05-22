package com.zh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    public final static int WrongUserOrPassword = 301;
    public final static int Unauthenticated = 302;
    public final static int IllegalPhoneNum = 303;
    public final static int IllegalPassword = 304;
    public final static int WrongAuthCode = 305;
    public final static int AuthCodeOutdated = 306;
    public final static int IllegalAction = 307;
    public final static int TokenOutdated = 308;
    public final static int Error = 309;
    public final static int IllegalNickname = 310;
    public final static int WaitForAuthCode = 201;
    public final static int LoginOk = 101;
    public final static int RegisterOk = 102;
    public final static int AccessOk = 103;


    private int code;
    private String message;
    private T data;
}
