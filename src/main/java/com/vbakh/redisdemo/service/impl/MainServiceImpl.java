package com.vbakh.redisdemo.service.impl;

import com.vbakh.redisdemo.domain.Message;
import com.vbakh.redisdemo.domain.User;
import com.vbakh.redisdemo.response.UserResponse;
import com.vbakh.redisdemo.service.MainService;
import com.vbakh.redisdemo.service.MessageService;
import com.vbakh.redisdemo.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import static com.vbakh.redisdemo.utils.InitializationUtils.getNames;
import static java.util.stream.Collectors.toSet;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@Service
public class MainServiceImpl implements MainService {

    @Resource
    private UserService userService;
    @Resource
    private MessageService messageService;

    @Override
    public void initialize() {
        Set<User> users = getNames().stream()
                .map(userService::create)
                .collect(toSet());
        users.forEach(u -> {
            for (int i = 0; i < 10; i++) {
                messageService.create(UUID.randomUUID().toString(), "some random text " + UUID.randomUUID().toString(), u.getId());
            }
        });
    }

    @Override
    public UserResponse getUserResponse(String userId) {
        User user = userService.get(userId);
        Set<Message> messages = messageService.getMessagesPipelined(userId);
        return new UserResponse(user, messages);
    }
}
