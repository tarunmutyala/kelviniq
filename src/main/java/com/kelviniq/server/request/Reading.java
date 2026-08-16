package com.kelviniq.server.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reading {
    private long ts;
    private String ch;
    private double c;
}
