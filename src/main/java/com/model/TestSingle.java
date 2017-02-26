package com.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Создал Vlad Kazakov дата: 26.02.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "testsingle")
public class TestSingle implements Serializable {

    @XmlAttribute
    @Column
    public String name;

    @XmlElement
    @ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="testsingle_id"))
    @Column
    public List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public TestSingle() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
