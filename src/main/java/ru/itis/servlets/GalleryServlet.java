package ru.itis.servlets;

import ru.itis.services.PhotoService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/gallery")
@MultipartConfig
public class GalleryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        try {
            PhotoService photoService = (PhotoService) context.getAttribute("photoService");
            req.setAttribute("photos", photoService.getGallery());
            req.getRequestDispatcher("/jsp/gallery.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            context.log(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        try {
            PhotoService photoService = (PhotoService) context.getAttribute("photoService");
            Part part = req.getPart("file");
            if (part.getContentType().contains("image")) {
                photoService.upload(part.getInputStream(), part.getSubmittedFileName());
            }
            resp.sendRedirect("/gallery");
        } catch (ServletException | IOException e) {
            context.log(e.getMessage());
        }
    }
}
