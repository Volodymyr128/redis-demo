package com.vbakh.redisdemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by volodymyr.bakhmatiuk on 5/29/18.
 */
@RestController
@RequestMapping(path = "api/")
public class MainController {

    @GetMapping(path = "test")
    public String test() {
        return "I'm here! [1.1]";
    }
}
