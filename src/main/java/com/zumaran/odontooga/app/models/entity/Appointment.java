package com.zumaran.odontooga.app.models.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zumaran.odontooga.app.models.AppointmentSerializer;
import com.zumaran.odontooga.app.models.AppointmentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "appointments")
@JsonSerialize(using = AppointmentSerializer.class)
public class Appointment implements Comparable<Appointment>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "start")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "end")
    private LocalDateTime end;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @OneToOne
    @JoinColumn(name = "id_canceler")
    private Client canceler;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    // CUSTOMER => CLIENT
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    // PROVIDER => DENTIST
    @ManyToOne
    @JoinColumn(name = "id_dentist")
    private Dentist dentist;

    // WORK => SERVICE
    @ManyToOne
    @JoinColumn(name = "id_work")
    private Work work;

    // CHAT MESSAGES

    // INVOICE

    // EXCHANGE REQUEST

    public Appointment(LocalDateTime start, LocalDateTime end, Client client, Dentist dentist, Work work) {
        this.start = start;
        this.end = end;
        this.client = client;
        this.dentist = dentist;
        this.work = work;
    }

    @Override
    public int compareTo(Appointment o) { return this.getStart().compareTo(o.getStart()); }

}
