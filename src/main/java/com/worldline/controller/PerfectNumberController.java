package com.worldline.controller;

import com.worldline.exception.PerfectNumberException;
import com.worldline.service.PerfectNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bruno Andrade
 * Dia: 20/11/2019
 **/
@RestController
@RequestMapping("/v1/perfectNumber")
@Api(value = "Perfect Number", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PerfectNumberController {

    @Autowired
    private PerfectNumberService perfectNumberService;

    @GetMapping("/{number}")
    @ApiOperation(value = "Check if a given number is perfect", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Integer> check(@PathVariable String number) throws PerfectNumberException {
        String response;
        if (perfectNumberService.isPerfect(Integer.valueOf(number))) {
            response = number + "is a perfect number";
        } else {
            response = number + "isn't a perfect number";
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all perfect numbers for a given range", response = Integer.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity findNumbersInRange(@PathVariable String min, @PathVariable String max) throws PerfectNumberException {
        List<Integer> perfectNumbers = perfectNumberService.findNumbersInRange(Integer.valueOf(min), Integer.valueOf(max));
        return new ResponseEntity(perfectNumbers, HttpStatus.OK);
    }

}
