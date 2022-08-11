package com.zumaran.odontooga.app.models.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "works")
public class Work {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "editable")
    private boolean editable;

    // TARGET CUSTOMER => CLIENT
    @Column(name = "target")
    private String targetCustomer;

    @ManyToMany
    @JoinTable(name = "works_dentists", joinColumns = @JoinColumn(name = "id_work"), inverseJoinColumns = @JoinColumn(name = "id_dentist"))
    private List<Dentist> dentist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Work)) return false;
        Work work = (Work) o;
        // SUPER.GETID()
        return this.getId().equals(work.getId());
    }

    @Override
    public int hashCode() { return this.getId().hashCode(); }

}
