package the.husky;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import the.husky.jdbc.CheckRepository;
import the.husky.web.servlet.CheckByIdServlet;
import the.husky.web.servlet.RandomChecksServlet;

public class App
{
    public static void main( String[] args ) throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // for database in path /src/main/resources/4000385781.db
        dataSource.setUrl("jdbc:sqlite::resource:4000385781.db");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        CheckRepository checkRepository = new CheckRepository(jdbcTemplate);

        RandomChecksServlet randomChecksServlet = new RandomChecksServlet(checkRepository);
        CheckByIdServlet checkByIdServlet = new CheckByIdServlet(checkRepository);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(randomChecksServlet), "/checks/random");
        contextHandler.addServlet(new ServletHolder(checkByIdServlet), "/checks");

        Server server = new Server(3000);
        server.setHandler(contextHandler);
        server.start();
    }
}

