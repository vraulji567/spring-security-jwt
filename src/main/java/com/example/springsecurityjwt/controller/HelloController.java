package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.model.AuthRequest;
import com.example.springsecurityjwt.model.AuthResponse;
import com.example.springsecurityjwt.service.MyUserDetailsService;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping("/hello")
    public ResponseEntity hello(){
        return ResponseEntity.ok("Hello Spring Security JWT!!!");
    }

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> token(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException badCredentialsException){
            throw new Exception("Incorrect Username or Password", badCredentialsException);
        }

        final UserDetails user = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
