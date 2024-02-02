package com.jst.jsttokenservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/document/")
public class DocumentController {
    @GetMapping
    public String docuemnts(){
        return "document list";
    }
    @PutMapping("/{id}")
    public String updateDocuemnt(@PathVariable Long id){
        return " update document id = "+id;
    }

    @PostMapping
    public String createdDcuemnt(){
        return "create document";
    }

    @DeleteMapping("/{id}")
    public String deleteDocuemnt(@PathVariable Long id){
        return " delete document id = "+id;
    }
}
