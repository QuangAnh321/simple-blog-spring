package com.simple.blog.ultilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CommonFunction {

    public static final long DEFAULT_FAKE_ID_FOR_AUTO_GENERATED_CLASS = 1000;
    
    public LocalDateTime generateTimestampForNewObject() {
        var dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var dateTimeString = LocalDateTime.now().format(dateTimePattern);
        var dateTime = LocalDateTime.parse(dateTimeString, dateTimePattern);

        return dateTime;
    }
}
