package com.vbakh.redisdemo.utils;

import com.vbakh.redisdemo.domain.User;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by volodymyr.bakhmatiuk on 5/31/18.
 */
public class InitializationUtils {

    public static final String NAMES_URL = "http://deron.meranda.us/data/census-dist-male-first.txt";
    public static final Integer SUPPOSED_NAMES_AMOUNT = 1219;

    public static List<String> getNames() {
        List<String> names = new ArrayList<>(SUPPOSED_NAMES_AMOUNT);
        URL url = null;
        try {
            url = new URL(NAMES_URL);
            Scanner s = new Scanner(url.openStream());
            while (s.hasNext()) {
                names.add(s.next());
                // skip unneeded columns
                s.next();
                s.next();
                s.next();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read names", e);
        }
        return names;
    }

    public static User fromName(String name) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .category(randomCategory())
                .build();
    }

    public static User.Category randomCategory() {
        return Arrays.stream(User.Category.values())
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }
}
