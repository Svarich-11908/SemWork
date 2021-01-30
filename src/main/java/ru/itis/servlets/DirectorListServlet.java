package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.itis.services.DirectorService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/directors")
public class DirectorListServlet extends HttpServlet {
    private DirectorService directorService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.directorService = (DirectorService) context.getAttribute("directorService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/directors.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String titleSub = data.get("name").getAsString();
        if (!titleSub.isEmpty()) {
            String movies = objectMapper.writeValueAsString(directorService.getMatching(titleSub));
            resp.getWriter().write(movies);
        } else {
            String movies = objectMapper.writeValueAsString(directorService.getList());
            resp.getWriter().write(movies);
        }
    }
}
