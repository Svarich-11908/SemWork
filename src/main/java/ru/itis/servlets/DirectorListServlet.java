package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import ru.itis.services.DirectorService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/directors")
public class DirectorListServlet extends HttpServlet {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/html/directors.html").forward(req, resp);
        } catch (ServletException | IOException e) {
            req.getServletContext().log(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        try {
            DirectorService directorService = (DirectorService) context.getAttribute("directorService");
            JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
            String titleSub = data.get("name").getAsString();
            if (!titleSub.isEmpty()) {
                String movies = objectMapper.writeValueAsString(directorService.getMatching(titleSub));
                resp.getWriter().write(movies);
            } else {
                String movies = objectMapper.writeValueAsString(directorService.getList());
                resp.getWriter().write(movies);
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            context.log(e.getMessage());
        }
    }
}
