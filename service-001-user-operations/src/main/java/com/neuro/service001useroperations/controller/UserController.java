package com.neuro.service001useroperations.controller;

import com.neuro.service001useroperations.dto.UserDto;
import com.neuro.service001useroperations.wrapper.RequestContainer;
import com.neuro.service001useroperations.wrapper.ResponseContainer;
import com.neuro.service001useroperations.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user-operations")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("hello1")
    public String postHello1() {
        return "Get Hello";
    }

    @PostMapping("hello")
    public String postHello() {
        return "Post Hello";
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping(value = "/mutate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseContainer<UserDto>> mutate(@RequestBody RequestContainer<UserDto> request) {
        log.info("request: {}", request);
        ResponseContainer<UserDto> response = userService.mutate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}