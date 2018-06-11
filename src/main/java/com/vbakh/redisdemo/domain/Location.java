package com.vbakh.redisdemo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

/**
 * Created by volodymyr.bakhmatiuk on 6/11/18.
 */
@Builder
@Data
@RedisHash("Location")
public class Location {

    private String id;
    private Integer x;
    private Integer y;
}
