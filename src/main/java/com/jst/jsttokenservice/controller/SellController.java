package com.jst.jsttokenservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sell")
public class SellController {
    @GetMapping
    public String docuemnts(){
        return "document list";
    }
}
