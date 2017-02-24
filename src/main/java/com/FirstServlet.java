package com;

import com.model.Root;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;


/**
 * Создал Vlad Kazakov дата: 22.02.2017.
 */
public class FirstServlet extends HttpServlet {
    static final String SAVE_DIR = "E:/Test/";
    static final int BUFFER_SIZE = 4096;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String description = request.getParameter("description");
//        String data = request.getParameter("data");
//
//        response.getWriter().println("description="+description);
//        response.getWriter().println("data="+data);
//
//        response.getWriter().println("POST request body:");
//
//
//        InputStreamReader reader = new InputStreamReader(request.getInputStream());
//        int c;
//        while ((c=reader.read())>=0) {
//            response.getWriter().print((char)c);
//        }

        try {



            File file = new File("C:\\simple.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Root customer = (Root) jaxbUnmarshaller.unmarshal(file);
            System.out.println();
            System.out.println("XML to Java Object:");
            System.out.println(customer.toString());
            response.getWriter().println("Object:" + customer.toString());



        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
        }
