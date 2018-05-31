package com.vbakh.redisdemo.repository;

import com.vbakh.redisdemo.converters.DTOConverter;
import com.vbakh.redisdemo.domain.Message;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.support.collections.DefaultRedisSet;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

/**
 * Created by volodymyr.bakhmatiuk on 5/31/18.
 */
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    @Resource
    private StringRedisTemplate template;
    HashMapper<Message, String, String> mapper = new DTOConverter<>(Message.class);

    @Override
    public Set<String> getMessageIdsByUserId(String userId) {
        return new DefaultRedisSet<>("user:" + userId + ":messages", template);
    }

    @Override
    public void addUserMessage(String userId, String messageId) {
        new DefaultRedisSet<>("user:" + userId + ":messages", template).add(messageId);
    }

    @Override
    public Set<Message> getMessages(Set<String> ids) {
        template.setEnableDefaultSerializer(false);
        List<Object> messages = template.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection conn = (StringRedisConnection) connection;
            ids.stream()
                    .map(id -> "Message:" + id)
                    .forEach(conn::hGetAll);
            return null;
        });
        return convert(messages);
    }

    private Set<Message> convert(List<Object> messages) {
        return messages.stream()
                .map(o -> (Map<String, String>) o)
                .map(o -> mapper.fromHash(o))
                .collect(toSet());
    }
}
