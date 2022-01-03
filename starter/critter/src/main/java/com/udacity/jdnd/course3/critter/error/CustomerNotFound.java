package com.udacity.jdnd.course3.critter.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such customer")
public class CustomerNotFound extends RuntimeException{
}
