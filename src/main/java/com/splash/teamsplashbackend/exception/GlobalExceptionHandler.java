//package com.splash.teamsplashbackend.exception;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    // 페이지 에러가 필요한 Handler
//    @ExceptionHandler(value = {IllegalArgumentException.class})
//    public ResponseEntity<Object> exceptionHandler(Exception ex) {
//        RestApiException restApiException = new RestApiException();
//        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
//        restApiException.setErrorMsg(ex.getMessage());
//        return new ResponseEntity<>(restApiException, HttpStatus.BAD_REQUEST);
//    }
//    // 페이지 에러가 없도록 해주는 Handler
//    @ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<Object> exceptionMessageHandler(Exception ex) {
//        RestApiException restApiException = new RestApiException();
//        restApiException.setHttpStatus(HttpStatus.OK);
//        restApiException.setErrorMsg(ex.getMessage());
//        return new ResponseEntity<>(restApiException, HttpStatus.OK);
//    }
//
//}
