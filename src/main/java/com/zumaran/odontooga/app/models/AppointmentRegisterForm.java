package com.zumaran.odontooga.app.models;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class AppointmentRegisterForm {
    
    private int workId;
    private int providerId;
    private int customerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end;

    public AppointmentRegisterForm(int workId, int providerId, LocalDateTime start, LocalDateTime end) {
        this.workId = workId;
        this.providerId = providerId;
        this.start = start;
        this.end = end;
    }
    
}
