package com.vbakh.redisdemo.repository;

import com.vbakh.redisdemo.domain.Message;
import java.util.Set;

/**
 * Created by volodymyr.bakhmatiuk on 5/31/18.
 */
public interface MessageRepositoryCustom {

    Set<String> getMessageIdsByUserId(String userId);
    void addUserMessage(String userId, String messageId);
    Set<Message> getMessages(Set<String> ids);
}