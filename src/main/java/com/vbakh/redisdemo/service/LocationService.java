package com.vbakh.redisdemo.service;

import com.vbakh.redisdemo.domain.Location;
import java.util.Set;

/**
 * Created by volodymyr.bakhmatiuk on 6/11/18.
 */
public interface LocationService {

    String INDEX_NAME = "i-locations";

    Location create(Integer x, Integer y);

    void init();

    Set<Location> get(int fromX, int fromY, int toX, int toY);

    Set<Location> get(String from, String to);
}
