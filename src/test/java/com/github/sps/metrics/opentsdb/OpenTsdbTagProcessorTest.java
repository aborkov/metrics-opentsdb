package com.github.sps.metrics.opentsdb;

import com.google.common.collect.ImmutableMap;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

/**
 * Created by oiatsenko on 8/7/2015.
 */
@RunWith(JUnitParamsRunner.class)
public class OpenTsdbTagProcessorTest {

    @Test
    @Parameters(method = "parameters")
    public void addTagsToMetricName(String metricName, Map<String, String> tags, String nameWithTags) {
        assertEquals(nameWithTags, OpenTsdbTagProcessor.addTagsToMetricName(metricName, tags));
    }

    @Test
    @Parameters(method = "parameters")
    public void extractTagsFromMetricName(String metricName, Map<String, String> tags, String nameWithTags) {
        assertEquals(tags, OpenTsdbTagProcessor.extractTags(nameWithTags));
    }

    @Test
    @Parameters(method = "parameters")
    public void extractOriginalMetricName(String metricName, Map<String, String> tags, String nameWithTags) {
        assertEquals(metricName, OpenTsdbTagProcessor.extractOriginalName(nameWithTags));
    }

    private Object[] parameters() {
        return $(
                $("name", ImmutableMap.of("id", "1"), "name.tags:id=1"),
                $("name", ImmutableMap.of("id", "1", "foo", "bar"), "name.tags:id=1&foo=bar"),
                $("name", ImmutableMap.of(), "name")
        );
    }
}