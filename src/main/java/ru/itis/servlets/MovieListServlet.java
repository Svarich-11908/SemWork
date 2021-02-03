package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import ru.itis.services.MovieService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/html/movies.html").forward(req, resp);
        } catch (ServletException | IOException e) {
            req.getServletContext().log(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        MovieService movieService = (MovieService) context.getAttribute("movieService");
        try {
            JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
            String titleSub = data.get("title").getAsString();
            if (!titleSub.isEmpty()) {
                String movies = objectMapper.writeValueAsString(movieService.getMatching(titleSub));
                resp.getWriter().write(movies);
            } else {
                String movies = objectMapper.writeValueAsString(movieService.getList());
                resp.getWriter().write(movies);
            }
        } catch (IOException | JsonSyntaxException e) {
            context.log(e.getMessage());
        }
    }
}
