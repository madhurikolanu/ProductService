package com.apis.fakestore.exceptionhandler;

import com.apis.fakestore.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // whatever the output returned by controller will be passed to this class
public class ExceptionHandlers {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setDescription("Pass the proper values");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,
                HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundsException(){
        return new ResponseEntity<>(
                HttpStatus.BAD_REQUEST);

    }
}
