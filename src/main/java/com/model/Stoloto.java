package com.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Создал Vlad Kazakov дата: 22.02.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Stoloto {
    private List<String> list;

    public Stoloto() {
    }

    public Stoloto(List<String> list) {
        this.list = list;
    }

    @XmlElement(required = true)
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
