package com.example.time.demo.demo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "time")
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomTime  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;
    @Column(name = "local_time", columnDefinition = "TIME")
    private LocalTime localTime;
    @Column(name = "offset_time", columnDefinition = "TIMESTAMP")
    private OffsetDateTime offsetDateTime;
    @Column(name = "zoned_date_time", columnDefinition = "TIMESTAMP")
    private ZonedDateTime zonedDateTime;
    @Column(name = "instant_time", columnDefinition = "TIMESTAMP")
    private Instant instantTime;
}
