package com.zumaran.odontooga.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clients")
public class Client implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    //PHOTO
    @Column
    private String photo;

    @NotEmpty
    @Column
    private String name;

    @NotEmpty
    @Column
    private String lastName;

    // @NotEmpty
    // @Column
    // private String description;

    @NotEmpty
    @Column
    private String email;

    @NotEmpty
    @Column
    private String phone;

    @NotNull
    @Column
    private boolean status;

    @NotNull
    @Past
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyy-MM-dd")    
    private Date createAt;

    private static final long serialVersionUID = 1L;
    
}
