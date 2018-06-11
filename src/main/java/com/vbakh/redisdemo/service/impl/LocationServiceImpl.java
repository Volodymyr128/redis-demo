package com.vbakh.redisdemo.service.impl;

import com.vbakh.redisdemo.domain.Location;
import com.vbakh.redisdemo.repository.LocationRepository;
import com.vbakh.redisdemo.service.LocationService;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import static com.vbakh.redisdemo.utils.NumberUtils.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by volodymyr.bakhmatiuk on 6/11/18.
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Resource
    private LocationRepository repository;
    @Resource
    private RedisTemplate<String, String> template;

    @Override
    public Location create(Integer x, Integer y) {
        Location location = repository.save(
                Location.builder()
                        .x(x).y(y)
                        .id(UUID.randomUUID().toString())
                        .build()
        );
        template.opsForZSet().add(INDEX_NAME, toLexIndex(x, y, location.getId()), 0);
        return location;
    }

    @Override
    public void init() {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                create(x, y);
            }
        }
    }

    @Override
    public Set<Location> get(int fromX, int fromY, int toX, int toY) {
        return getLocations(
                toMinLexIndex(fromX, fromY),
                toMaxLexIndex(toX, toY)
        );
    }

    @Override
    public Set<Location> get(String from, String to) {
        return getLocations(
                from + ":",
                to + ":\\xff"
        );
    }

    private Set<Location> getLocations(String from, String to) {
        return template.opsForZSet()
                .rangeByLex(INDEX_NAME, RedisZSetCommands.Range.range().gte(from).lte(to))
                .stream()
                .map(str -> str.split(":")[1])
                .map(repository::findById)
                .map(o -> o.orElse(null))
                .collect(toSet());
    }
}
