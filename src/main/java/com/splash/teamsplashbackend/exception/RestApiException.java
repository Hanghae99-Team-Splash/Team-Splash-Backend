package com.splash.teamsplashbackend.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException {
    private String errorMsg;

    private HttpStatus httpStatus;

}
