package com.vbakh.redisdemo.service.impl;

import com.vbakh.redisdemo.domain.User;
import com.vbakh.redisdemo.repository.UserRepository;
import com.vbakh.redisdemo.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import static com.vbakh.redisdemo.utils.InitializationUtils.fromName;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository repository;

    @Override
    public User create(String name) {
        return repository.save(fromName(name));
    }

    @Override
    public User get(String id) {
        return repository.findById(id).orElse(null);
    }
}
