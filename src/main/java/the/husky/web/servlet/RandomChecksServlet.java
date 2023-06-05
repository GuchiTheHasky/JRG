package the.husky.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import the.husky.jdbc.CheckRepository;

import java.io.IOException;

public class RandomChecksServlet extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CheckRepository checkRepository;

    public RandomChecksServlet(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            String jsonOutput = OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(checkRepository.getRandomChecks());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonOutput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
