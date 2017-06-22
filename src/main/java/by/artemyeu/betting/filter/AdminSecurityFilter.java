package by.artemyeu.betting.filter;

import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Acer on 16.05.2017.
 */
@WebFilter(urlPatterns = {"/jsp/admin/*"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class AdminSecurityFilter implements Filter {

    /** The path parameter. */
    private final String PATH_PARAMETER = "INDEX_PATH";

    /** The user attribute. */
    private final String USER_ATTRIBUTE = "user";

    /** The index path. */
    private String indexPath;

    /**
     * Inits the.
     *
     * @param fConfig the f config
     * @throws ServletException the servlet exception
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter(PATH_PARAMETER);
    }

    /**
     * Do filter.
     *
     * @param request the request
     * @param response the response
     * @param chain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User user=(User)httpRequest.getSession().getAttribute(USER_ATTRIBUTE);
        if (user==null || user.getRole()!= UserRole.ADMIN) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }
        chain.doFilter(request, response);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
    }
}
