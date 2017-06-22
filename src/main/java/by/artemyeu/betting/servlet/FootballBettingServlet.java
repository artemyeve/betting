package by.artemyeu.betting.servlet;

import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.command.CommandCreator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Created by Acer on 16.05.2017.
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024 , // 1MB
        maxFileSize = 1024 * 1024 * 20,      // 20MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet("/controller")
public class FootballBettingServlet extends HttpServlet implements ServletContextListener {



    /**
     * Context initialized.
     *
     * @param servletContextEvent the servlet context event
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    /**
     * Context destroyed.
     *
     * @param servletContextEvent the servlet context event
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().terminatePool();

    }


    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        sessionRequestContent.extractValues(request);

        CommandCreator client = new CommandCreator();
        AbstractCommand command = client.defineCommand(sessionRequestContent);
        String page;

    }
}
