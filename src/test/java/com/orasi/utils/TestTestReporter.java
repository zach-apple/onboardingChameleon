package com.orasi.utils;

import java.util.List;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestTestReporter {
    private final static String BASE_LOG = "com.orasi.utils.TestTestReporter#testLog > ";

    @Test
    public void testLog() {
        TestReporter.log("blah");
        Assert.assertTrue(logHelper(Reporter.getOutput(), "blah"));
    }

    private boolean logHelper(List<String> logs, String log) {
        return logs.parallelStream().anyMatch(str -> str.contains(BASE_LOG + log));
    }
}
