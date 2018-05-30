package com.vbakh.redisdemo.rest;

import com.vbakh.redisdemo.response.UserResponse;
import com.vbakh.redisdemo.service.MainService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by volodymyr.bakhmatiuk on 5/29/18.
 */
@RestController
@RequestMapping(path = "api/")
public class MainController {

    @Resource
    private MainService mainService;

    @GetMapping(path = "test")
    public String test() {
        return "I'm here! [1.1]";
    }

    @PostMapping(path = "init")
    public void initialize() {
        mainService.initialize();
    }

    @GetMapping(path = "user/{id}")
    public UserResponse getUserResponse(@PathVariable("id") String id) {
        return mainService.getUserResponse(id);
    }
}