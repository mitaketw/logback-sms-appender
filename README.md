# logback-sms-appender

[![Build Status](https://travis-ci.org/mitaketw/logback-sms-appender.svg?branch=master)](https://travis-ci.org/mitaketw/logback-sms-appender)

## Dependency

### Maven

```xml
<dependency>
  <groupId>tw.com.mitake</groupId>
  <artifactId>logback-sms-appender</artifactId>
  <version>0.2.2</version>
</dependency>
```

### Gradle

```groovy
compile 'tw.com.mitake:logback-sms-appender:0.2.2'
```

## How to use

```xml
<appender name="SMS" class="tw.com.mitake.logback.appender.SmsAppender">
  <username>my_username</username> <!-- Your Mitake SMS username -->
  <password>my_password</password> <!-- Your Mitake SMS password -->
  <to>0912345678</to> <!-- Your Mitake SMS destination phone number -->
  <title>Logback SMS Appender</title> <!-- Your Mitake SMS title -->
  <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
    <pattern>${PATTERN}</pattern>
    <charset>${CHARSET}</charset>
  </encoder>
</appender>
```
