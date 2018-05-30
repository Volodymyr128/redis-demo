package com.vbakh.redisdemo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@Builder
@Data
@RedisHash("User")
public class User implements Serializable {

    public enum Category {
        A, B, C
    }

    @Id
    private String id;
    private String name;
    private Category category;
}
