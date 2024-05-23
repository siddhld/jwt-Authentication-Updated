package com.jwt.springsecurity.controller;

import com.jwt.springsecurity.model.UserInfo;
import com.jwt.springsecurity.service.JwtService;
import com.jwt.springsecurity.service.UserServiceInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceInfoImpl userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Api is working fine";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo){
        System.err.println("Inside ---------------- Login");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
        if(authentication.isAuthenticated()){
            System.err.println("Inside ---------------- Authenticated");
            return jwtService.generateToken(userInfo.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @PostMapping("/addUser")
    public UserInfo addUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }

    @GetMapping("/user/{id}")
    public UserInfo getUser(@PathVariable("id") Integer id){
        return userService.getById(id);
    }

    @GetMapping("/users")
    public List<UserInfo> getAll(){
        return userService.getAll();
    }
}
