package com.vbakh.redisdemo.service;

import com.vbakh.redisdemo.domain.User;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
public interface UserService {

    User create(String name);
    User get(String id);
}
