package com.example.SprintBootAppWithSQL.config;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MaskingPatternLayout extends PatternLayout {
    private Pattern multilinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);
        multilinePattern = Pattern.compile(maskPatterns.stream().collect(Collectors.joining("|")), Pattern.MULTILINE);
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(sb);
        StringBuilder result = new StringBuilder(); // Temporary StringBuilder for masked message
        int previousEnd = 0; // Track the end index of the last match
        while (matcher.find()) {
            for (int group = 1; group <= matcher.groupCount(); group++) {
                if (matcher.group(group) != null) {
                    int start = matcher.start(group);
                    int end = matcher.end(group);
                    // Append the unmasked portion of the original message
                    result.append(sb, previousEnd, start);
                    // Append the masked text
                    result.append("*****");
                    // Update the end index of the last match
                    previousEnd = end;
                }
            }
        }
        // Append any remaining unmasked text after the last match
        result.append(sb, previousEnd, sb.length());
        return result.toString();
    }
}
