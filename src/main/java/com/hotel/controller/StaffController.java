package com.hotel.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping
    public ResponseEntity<List<String>> getStaffs(){
        List<String> list = Arrays.asList("Raman", "Ketan", "Ratan", "resham");

        //return  ResponseEntity.ok(list);
        return ResponseEntity.status(200).body(list);

    }
}
