package com;

import com.model.Root;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

/**
 * Создал Vlad Kazakov дата: 24.02.2017.
 * Класс для формирования XML из объекта Root.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");

        Root root = new Root();
        root.setName("Example");
        root.setList(list);

        JAXBContext jc = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(root, System.out);

    }
}
