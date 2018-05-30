package com.vbakh.redisdemo.service.impl;

import com.vbakh.redisdemo.domain.Message;
import com.vbakh.redisdemo.repository.MessageRepository;
import com.vbakh.redisdemo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisSet;
import org.springframework.data.redis.support.collections.RedisSet;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@Service
public class MessageServiceImpl implements MessageService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MessageRepository repository;
    @Resource
    private RedisTemplate<String, String> template;

    @Override
    public Message create(String id, String text, String authorId) {
        Message message = Message.builder()
                .id(id)
                .text(text)
                .posted(LocalDateTime.now())
                .upvotes(0)
                .authorId(authorId)
                .build();
        message = repository.save(message);
        String key = "user:" + authorId + ":messages";
        RedisSet<String> user2Message = new DefaultRedisSet<>(key, template);
        user2Message.add(message.getId());
        return message;
    }

    @Override
    public Set<Message> getMessages(String userId) {
        String key = "user:" + userId + ":messages";
        return new DefaultRedisSet<>(key, template).stream()
                .map(id -> repository.findById(id).orElseGet(() -> {
                    logger.warn("There is no message for key: " + id);
                    return null;
                }))
                .collect(toSet());
    }
}