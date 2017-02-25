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


        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // process only if its multipart content
        if (true) {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                // Parse the request
                List<FileItem> multiparts = upload.parseRequest(request);

                String name1 = null;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        name1 = name;
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

                // File uploaded successfully
                request.setAttribute("message", "Your file has been uploaded!");




                //File file = new File("C:\\!test\\simple.xml");
                File file = new File(UPLOAD_DIRECTORY + File.separator + name1);

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
            catch (Exception e)
            {
                request.setAttribute("message", "File Upload Failed due to " + e);
            }
        } else
        {
            request.setAttribute("message", "This Servlet only handles file upload request");
        }
        //request.getRequestDispatcher("/result.jsp").forward(request, response);











    }
        }
