# logback-sms-appender

[![Build Status](https://travis-ci.org/mitaketw/logback-sms-appender.svg?branch=master)](https://travis-ci.org/mitaketw/logback-sms-appender) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/tw.com.mitake/logback-sms-appender/badge.svg)](https://maven-badges.herokuapp.com/maven-central/tw.com.mitake/logback-sms-appender)

![demo](https://cloud.githubusercontent.com/assets/795839/17597677/8691972a-6029-11e6-89f7-2fc556e0256b.gif)

## Dependency

### Maven

```xml
<dependency>
  <groupId>tw.com.mitake</groupId>
  <artifactId>logback-sms-appender</artifactId>
  <version>0.3.0</version>
</dependency>
```

### Gradle

```groovy
compile 'tw.com.mitake:logback-sms-appender:0.3.0'
```

## How to use

```xml
<appender name="SMS" class="tw.com.mitake.logback.appender.SmsAppender">
  <username>my_username</username> <!-- Your Mitake SMS username -->
  <password>my_password</password> <!-- Your Mitake SMS password -->
  <to>0912345678</to> <!-- Your Mitake SMS destination phone number -->
  <title>Logback SMS Appender</title> <!-- Your Mitake SMS title -->
</appender>
```
