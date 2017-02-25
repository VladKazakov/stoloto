package com;

import com.model.Root;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.util.List;


/**
 * Создал Vlad Kazakov дата: 22.02.2017.
 */
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String UPLOAD_DIRECTORY = "c:/!test/qwe/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Принимаем файл, распарсиваем и сохраняем объект в базу данных
         */
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // Проверяем тип контента multipart.
        if (isMultipart) {
            // Создаем фабрику для дисков
            FileItemFactory factory = new DiskFileItemFactory();

            // Создаем обработчик для загрузки файла
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {

                // Разпарсиваем запрос (request)
                List<FileItem> multiparts = upload.parseRequest(request);

                // Сохраняем на диске переданный файл
                String namefile = null;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        namefile = name;
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

                File file = new File(UPLOAD_DIRECTORY + File.separator + namefile);

                JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Root customer = (Root) jaxbUnmarshaller.unmarshal(file);
                /**
                 * СОХРАНЕНИЕ ОБЪЕКТА В БД
                 */


                /**
                 * Возврат файла
                 */
//                // тип данных, которые вы отправляете
//                // например application/pdf, text/plain, text/html, image/jpg
//                response.setContentType("image/jpg");
//                response.setHeader("Content-disposition","attachment; filename=23.jpg");
//                // файл, который вы отправляете
//                File my_file = new File("c:\\!test\\23.jpg");
//                // отправить файл в response
//                OutputStream out = response.getOutputStream();
//                FileInputStream in = new FileInputStream(my_file);
//                byte[] buffer = new byte[4096];
//                int length;
//                while ((length = in.read(buffer)) > 0){
//                    out.write(buffer, 0, length);
//                }
//                // освободить ресурсы
//                in.close();
//                out.flush();




                response.getWriter().println("XML to Java Object:");
                response.getWriter().println(customer.toString());

            } catch (PropertyException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("message", "File Upload Failed due to " + e);
            }
        } else {
            request.setAttribute("message", "This Servlet only handles file upload request");
        }
        //request.getRequestDispatcher("/result.jsp").forward(request, response);


        /**
         * Возвращаем файл
         */
//        response.getWriter().println();
//        response.getWriter().println("View XML: ");
//
//        try {
//            Root root = new Root();
//            List list = new ArrayList();
//
//            List<Stoloto> list1 = new ArrayList<Stoloto>();
//            list1.add(new Stoloto("NAME10", "VALUE10"));
//            // ПОЛЯ
//            Stoloto stoloto1 = new Stoloto("NAME1", "value1");
//            Stoloto stoloto2 = new Stoloto("NAME2", "value2");
//            Stoloto stoloto3 = new Stoloto("NAME3", list1);
//            list.add(stoloto1);
//            list.add(stoloto2);
//            list.add(stoloto3);
//
//
//            root.setList(list);
//
//            JAXBContext jc = JAXBContext.newInstance(Root.class);
//            Marshaller marshaller = jc.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(root, System.out);
//            response.getWriter().println();
//
//
//
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    }

}
