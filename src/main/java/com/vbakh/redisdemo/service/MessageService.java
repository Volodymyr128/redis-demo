package com.vbakh.redisdemo.service;

import com.vbakh.redisdemo.domain.Message;

import java.util.Set;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
public interface MessageService {

    Message create(String id, String text, String authorId);
    Set<Message> getMessages(String userId);
    Set<Message> getMessagesPipelined(String userId);
}
