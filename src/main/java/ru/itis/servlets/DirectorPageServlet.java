package ru.itis.servlets;

import ru.itis.models.Director;
import ru.itis.services.DirectorService;
import ru.itis.services.MovieService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/director")
public class DirectorPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String redirectPath = (String) context.getAttribute("directorCardRedirect");
        try {
            MovieService movieService = (MovieService) context.getAttribute("movieService");
            DirectorService directorService = (DirectorService) context.getAttribute("directorService");
            if (req.getParameter("id") == null) resp.sendRedirect(redirectPath);
            else {
                Long id = Long.parseLong(req.getParameter("id"));
                Optional<Director> director = directorService.getFullDirectorById(id);
                if (!director.isPresent()) resp.sendRedirect(redirectPath);
                else {
                    Director d = director.get();
                    req.setAttribute("director", d);
                    req.setAttribute("movies", movieService.getByDirectorId(d.getId()));
                    req.getRequestDispatcher("/jsp/director.jsp").forward(req, resp);
                }
            }
        } catch (NumberFormatException e) {
            try {
                resp.sendRedirect(redirectPath);
            } catch (IOException err) {
                context.log(err.getMessage());
            }
        } catch (IOException | ServletException e) {
            context.log(e.getMessage());
        }
    }
}
