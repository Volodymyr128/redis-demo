package com.vbakh.redisdemo.response;

import com.vbakh.redisdemo.domain.Message;
import com.vbakh.redisdemo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Set;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@AllArgsConstructor
@Data
public class UserResponse {

    private User user;
    private Set<Message> messages;
}
