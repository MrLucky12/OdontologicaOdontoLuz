package com.zumaran.odontooga.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.persistence.CascadeType;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "offices")
public class Office implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotEmpty
    @Column
    private String name;

    @NotEmpty
    @Column
    private String description;

    @NotNull
    @Past
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyy-MM-dd")    
    private Date createAt;

    @NotNull
    @Column
    private boolean status;

    @OneToOne
    @JoinColumn(name = "dentist", referencedColumnName = "id")
    private Dentist dentist;

    @Column
    private String photo;

    @OneToMany(mappedBy = "offices", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "office_id", referencedColumnName = "product_id")
    private List<Product> products;
    
    private static final long serialVersionUID = 1L;


}
