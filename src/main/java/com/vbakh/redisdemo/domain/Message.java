package com.vbakh.redisdemo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@Builder
@Data
@RedisHash("Message")
public class Message implements Serializable {

    @Id
    private String id;
    private String text;
    private LocalDateTime posted;
    private Integer upvotes;
    private String authorId;
}
