package com;

import com.model.Root;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * Создал Vlad Kazakov дата: 24.02.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Root.class);

        Root root = new Root();
        root.setPropertyA("a");
        root.setPropertyB("b");
        root.fieldC = "c";

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(root, System.out);


//
//        try {
//            File file = new File("C:\\simple.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
//
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Root customer = (Root) jaxbUnmarshaller.unmarshal(file);
//            System.out.println();
//            System.out.println("XML to Java Object:");
//            System.out.println(customer.toString());
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//










    }
}
