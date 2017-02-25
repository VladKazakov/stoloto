package com.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement//(name = "TEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {


    List<Stoloto> property = new ArrayList<Stoloto>();

    public Root() {
    }

    public Root(List<Stoloto> list) {
        this.property = list;
    }

    public List<Stoloto> getList() {
        return property;
    }


    public void setList(List<Stoloto> list) {
        this.property = list;
    }

    @Override
    public String toString() {
        return "Root{" +
                "property=" + property +
                '}';
    }
}
