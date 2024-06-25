package com.reactive.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {
    boolean success;
    String message;
    Map<String, T> data;
}
