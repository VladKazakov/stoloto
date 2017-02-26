package com;

import com.dao.TestSingleImpl;
import com.model.TestSingle;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

                JAXBContext jaxbContext = JAXBContext.newInstance(TestSingle.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                TestSingle customer = (TestSingle) jaxbUnmarshaller.unmarshal(file);
                /**
                 * СОХРАНЕНИЕ ОБЪЕКТА В БД
                 */
//                RootImpl rootImpl = new RootImpl();
//                rootImpl.add(customer);
                TestSingleImpl testSingle = new TestSingleImpl();
                testSingle.add(customer);



                // Конвертируем объект обратно в XML
                JAXBContext jc = JAXBContext.newInstance(TestSingle.class);
                Marshaller marshaller = jc.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                String s = file.getName();
                String filePath = "c:\\!test\\send\\" + s;

                File file2 = new File(filePath);
                marshaller.marshal(customer, file2);
                //response.getWriter().println();



                /**
                 * Возврат файла
                 */
                // тип данных, которые вы отправляете
                // например application/pdf, text/plain, text/html, image/jpg
                response.setContentType("text/xml");
                response.setHeader("Content-disposition","attachment; filename="+ file.getName());
                // файл, который вы отправляете
                //File my_file = new File("c:\\!test\\23.jpg");
                // отправить файл в response
                OutputStream out = response.getOutputStream();
                FileInputStream in = new FileInputStream(file2);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0){
                    out.write(buffer, 0, length);
                }
                // освободить ресурсы
                in.close();
                out.flush();




//                response.getWriter().println("XML to Java Object:");
//                response.getWriter().println(customer.toString());

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


    }

}
