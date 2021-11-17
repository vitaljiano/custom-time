package com.example.time.demo.demo.exampleJavaTime;

import java.time.Duration;
import java.time.Instant;

public class MyTimeInstance {
    public static void main(String[] args) {
        Instant timeStamp = Instant.now();
        System.out.println("Time now: " + timeStamp);

        Instant specificTime = Instant.ofEpochMilli(timeStamp.toEpochMilli());
        System.out.println("Instant fot timestamp: " + specificTime);

        Duration duration = Duration.ofDays(60);
        System.out.println("Duration: "+ duration);

        System.out.println(timeStamp.plusSeconds(300).getEpochSecond());
    }
}
