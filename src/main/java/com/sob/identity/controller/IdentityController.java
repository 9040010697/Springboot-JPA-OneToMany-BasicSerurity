package com.sob.identity.controller;

import com.sob.identity.service.IdentityService;
import com.sob.identity.model.AuthToken;
import com.sob.identity.repo.models.Identity;
import com.sob.identity.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class IdentityController {

    @Autowired
    private IdentityService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthToken login(@RequestHeader(name= "Authorization", defaultValue = "Basic YWRtaW46cGFzc3dvcmQ=") String authorization,
                           @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthToken register(@RequestHeader(name = "Authorization", defaultValue = "Basic YWRtaW46cGFzc3dvcmQ=") String authorization,
                              @RequestBody Identity identity) {

        return userService.register(identity);
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public Identity getIdentity(@RequestParam(defaultValue = "test@gmail.com") String userName) {
        return userService.getIdentity(userName);
    }
}
