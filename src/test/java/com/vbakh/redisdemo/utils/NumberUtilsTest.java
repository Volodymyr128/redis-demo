package com.vbakh.redisdemo.utils;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * Created by volodymyr.bakhmatiuk on 6/11/18.
 */
@RunWith(JUnit4ClassRunner.class)
public class NumberUtilsTest {

    @Test
    public void toIndexPrefix_test() {
        assertEquals("00000010011001", NumberUtils.toIndexPrefix(10, 5));
        assertEquals("00001001001110", NumberUtils.toIndexPrefix(19, 10));
    }
}
