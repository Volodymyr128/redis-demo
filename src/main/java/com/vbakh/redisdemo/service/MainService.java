package com.vbakh.redisdemo.service;

import com.vbakh.redisdemo.response.UserResponse;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
public interface MainService {

    void initialize();
    UserResponse getUserResponse(String userId);
}