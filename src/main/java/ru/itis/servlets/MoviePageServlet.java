package ru.itis.servlets;

import ru.itis.models.Movie;
import ru.itis.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/movie")
public class MoviePageServlet extends HttpServlet {

    private MovieService movieService;
    private DirectorService directorService;
    private FavouriteService favouriteService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.movieService = (MovieService) context.getAttribute("movieService");
        this.directorService = (DirectorService) context.getAttribute("directorService");
        this.favouriteService = (FavouriteService) context.getAttribute("favouriteService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("id") == null) resp.sendRedirect("/movies");
            else {
                Long id = Long.parseLong(req.getParameter("id"));
                Optional<Movie> movie = movieService.getFullMovieById(id);
                if (!movie.isPresent()) resp.sendRedirect("/movies");
                else {
                    Movie m = movie.get();
                    req.setAttribute("movie", m);
                    req.setAttribute("hours", m.getLength() / 60);
                    req.setAttribute("minutes", m.getLength() % 60);
                    req.setAttribute("director", directorService.getDirectorById(m.getDirectorId()).get());
                    req.setAttribute("isFavourite", favouriteService.isFavourite(
                            (Long)req.getSession().getAttribute("userId"), m.getId()));
                    req.setAttribute("favNumber", favouriteService.countFavourites(m.getId()));
                    req.getRequestDispatcher("/jsp/movie.jsp").forward(req, resp);
                }
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/movies");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long movieId = Long.parseLong(req.getParameter("movieId"));
        Long userId = (Long)req.getSession().getAttribute("userId");
        if (action.equals("fav")) {
            favouriteService.addFavourite(userId, movieId);
        } else {
            favouriteService.deleteFavourite(userId, movieId);
        }
        resp.sendRedirect("/movie?id=" + movieId);
    }
}
