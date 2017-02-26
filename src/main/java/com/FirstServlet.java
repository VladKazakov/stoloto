package com;

import com.dao.RootImpl;
import com.model.Root;
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
 * Сервлет для обработки входящего POST-запроса
 */
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Path, где будут сохраняться загруженные файлы
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

                // Распарсиваем запрос (request)
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
                RootImpl testSingle = new RootImpl();
                testSingle.add(customer);


                /**
                 * Конвертируем объект в XML и сохраняем его для последующей передачи в файл
                 */
                JAXBContext jc = JAXBContext.newInstance(Root.class);
                Marshaller marshaller = jc.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Путь, где будут сохраняться сформированные файлы
                String filePath = "c:\\!test\\send\\" + file.getName();
                File file2 = new File(filePath);
                marshaller.marshal(customer, file2);



                /**
                 * Возврат файла
                 */
                response.setContentType("text/xml");
                response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
                OutputStream out = response.getOutputStream();
                FileInputStream in = new FileInputStream(file2);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();


            } catch (PropertyException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("Ошибка. ContentType неверно установлен.");
        }
    }
}
