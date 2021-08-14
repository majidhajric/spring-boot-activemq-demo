package dev.demo.demomq.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class DemoMessage implements Serializable {

    private final LocalDateTime time = LocalDateTime.now();

    private String title;

    private String message;
}
