package ru.itis.servlets;

import ru.itis.models.Director;
import ru.itis.services.DirectorService;
import ru.itis.services.MovieService;

import javax.servlet.ServletConfig;
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

    private MovieService movieService;
    private DirectorService directorService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.movieService = (MovieService) context.getAttribute("movieService");
        this.directorService = (DirectorService) context.getAttribute("directorService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("id") == null) resp.sendRedirect("/directors");
            else {
                Long id = Long.parseLong(req.getParameter("id"));
                Optional<Director> director = directorService.getFullDirectorById(id);
                if (!director.isPresent()) resp.sendRedirect("/directors");
                else {
                    Director d = director.get();
                    req.setAttribute("director", d);
                    req.setAttribute("movies", movieService.getByDirectorId(d.getId()));
                    req.getRequestDispatcher("/jsp/director.jsp").forward(req, resp);
                }
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/directors");
        }
    }
}
