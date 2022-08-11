package com.zumaran.odontooga.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "Officedetail")
public class OfficeDetail implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    //@MapsId("id")
    //@JoinTable(name = "products")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    //@JoinTable(name = "offices")
    //@MapsId("id")
    @JoinColumn(name = "office_id")
    private Office office;

    @NotEmpty
    @Column
    private Integer stock;

    @NotNull
    @Past
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyy-MM-dd")    
    private Date createAt;
    

    private static final long serialVersionUID = 1L;


}
