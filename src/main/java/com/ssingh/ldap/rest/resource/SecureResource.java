package com.ssingh.ldap.rest.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api", produces = "application/json")
public class SecureResource {

    @GetMapping("/whoami")
    public ResponseEntity<String> askWhoAmI() {
        return ResponseEntity.ok().body("Now you see me!!!.");
    }
}
