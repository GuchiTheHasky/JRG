package the.husky.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import the.husky.entity.Check;
import the.husky.jdbc.CheckRepository;

import java.io.IOException;

public class CheckByIdServlet extends HttpServlet {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CheckRepository checkRepository;
    public CheckByIdServlet(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkIdParam = request.getParameter("id");
        if (checkIdParam != null && !checkIdParam.isEmpty()) {
            long checkId = Long.parseLong(checkIdParam);
            Check check = checkRepository.getCheckById(checkId);
            String json = OBJECT_MAPPER.writeValueAsString(check);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid ID, please try again.");
        }
    }
}
