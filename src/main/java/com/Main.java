package com;

import com.model.Root;
import com.model.Stoloto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Создал Vlad Kazakov дата: 24.02.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Root root = new Root();
        List list = new ArrayList();



        List<Stoloto> list1 = new ArrayList<Stoloto>();
        list1.add(new Stoloto("NAME10", "VALUE10"));
        // ПОЛЯ
        Stoloto stoloto1 = new Stoloto("NAME1", "value1");
        Stoloto stoloto2 = new Stoloto("NAME2", "value2");
        Stoloto stoloto3 = new Stoloto("NAME3", list1);
        list.add(stoloto1);
        list.add(stoloto2);
        list.add(stoloto3);


        root.setList(list);

        JAXBContext jc = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(root, System.out);



        try {
            File file = new File("C:\\!test\\simple.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Root customer = (Root) jaxbUnmarshaller.unmarshal(file);
            System.out.println();
            System.out.println("XML to Java Object:");
            System.out.println(customer.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }











    }
}
