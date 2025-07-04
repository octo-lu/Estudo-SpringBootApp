package com.example.nobsv2.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/open")
    public String open(){
        return "OPEN";
    }

    @GetMapping("/closed")
    public String closed(){
        return "CLOSED";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/special")
    public String special(){
        return "SPECIAL";
    }

    @PreAuthorize("hasRole('admin') or hasRole('common')")
    @GetMapping("/basic")
    public String basic(){
        return "BASIC";
    }
}
