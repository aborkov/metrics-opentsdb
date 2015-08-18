package com.github.sps.metrics.opentsdb;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class OpenTsdbTagProcessor {

    public static Map<String, String> extractTags(String metricName) {
        String tagsString = substringAfter(metricName, "tags:");
        return StringUtils.isNotBlank(tagsString)
                ? Maps.newHashMap(Splitter.on("&").withKeyValueSeparator("=").split(tagsString))
                : Maps.<String, String>newHashMap();
    }

    public static String extractOriginalName(String metricName) {
        String originalName = substringBefore(metricName, ".tags:");
        return StringUtils.isNotBlank(originalName) ? originalName : metricName;
    }

    public static String addTagsToMetricName(String metricName, Map<String, String> tags) {
        return tags.isEmpty()
                ? metricName
                : String.format("%s.tags:%s", metricName, Joiner.on("&").withKeyValueSeparator("=").join(tags));
    }
}
