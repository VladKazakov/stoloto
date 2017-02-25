package com.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Создал Vlad Kazakov дата: 22.02.2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Stoloto {

    @XmlAttribute
    private String name;

    @XmlElement
    private String text;


    private List<Stoloto> list = new ArrayList<Stoloto>();


    public List<Stoloto> getList() {
        return list;
    }

    @XmlElement//(name = "friend")
    @XmlElementWrapper
    public void setList(List<Stoloto> list) {
        this.list = list;
    }


    public Stoloto(String name, List<Stoloto> list) {
        this.name = name;
        this.list = list;
    }

    public Stoloto() {
    }

    public Stoloto(String name) {
        this.name = name;
    }

    public Stoloto(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Stoloto{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", list=" + list +
                '}';
    }
}