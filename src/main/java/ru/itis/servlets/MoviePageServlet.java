package ru.itis.servlets;

import ru.itis.dto.DirectorDto;
import ru.itis.models.Movie;
import ru.itis.services.*;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String redirectPath = (String) context.getAttribute("movieCardRedirect");
        try {
            MovieService movieService = (MovieService) context.getAttribute("movieService");
            DirectorService directorService = (DirectorService) context.getAttribute("directorService");
            FavouriteService favouriteService = (FavouriteService) context.getAttribute("favouriteService");
            if (req.getParameter("id") == null) resp.sendRedirect(redirectPath);
            else {
                Long id = Long.parseLong(req.getParameter("id"));
                Optional<Movie> movie = movieService.getFullMovieById(id);
                if (!movie.isPresent()) resp.sendRedirect(redirectPath);
                else {
                    Movie m = movie.get();
                    Optional<DirectorDto> director = directorService.getDirectorById(m.getDirectorId());
                    req.setAttribute("movie", m);
                    req.setAttribute("hours", m.getLength() / 60);
                    req.setAttribute("minutes", m.getLength() % 60);
                    if (director.isPresent())
                        req.setAttribute("director", director.get());
                    req.setAttribute("isFavourite", favouriteService.isFavourite(
                            (Long)req.getSession().getAttribute("userId"), m.getId()));
                    req.setAttribute("favNumber", favouriteService.countFavourites(m.getId()));
                    req.getRequestDispatcher("/jsp/movie.jsp").forward(req, resp);
                }
            }
        } catch (NumberFormatException e) {
            try {
                resp.sendRedirect(redirectPath);
            } catch (IOException err) {
                context.log(err.getMessage());
            }
        } catch (ServletException | IOException e) {
            context.log(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        try {
            FavouriteService favouriteService = (FavouriteService) context.getAttribute("favouriteService");
            String action = req.getParameter("action");
            Long movieId = Long.parseLong(req.getParameter("movieId"));
            Long userId = (Long) req.getSession().getAttribute("userId");
            if (action.equals("fav")) {
                favouriteService.addFavourite(userId, movieId);
            } else {
                favouriteService.deleteFavourite(userId, movieId);
            }
            resp.sendRedirect("/movie?id=" + movieId);
        } catch (NumberFormatException | IOException e) {
            context.log(e.getMessage());
        }
    }
}
