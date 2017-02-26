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
public class Root implements Serializable {

    /**
     * Поле Id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * Атрибут
     */
    @XmlAttribute
    @Column
    private String name;

    /**
     * Список с элементами типа String
     */
    @XmlElement
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "testsingle_id"))
    @Column
    private List<String> list;


    /**
     * Конструктор
     */
    public Root() {
    }

    /**
     * Геттеры с сеттеры
     *
     * @return
     */
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
