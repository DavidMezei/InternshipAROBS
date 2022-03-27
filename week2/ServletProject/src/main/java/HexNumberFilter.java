import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/convertHex")
public class HexNumberFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(HexNumberFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("HexNumberFilter.doFilter()");
        long startTime = System.currentTimeMillis();
        ServletContext context = servletRequest.getServletContext();
        String input = servletRequest.getParameter("hex");

        if (input != null && !input.isEmpty()) {
            if (!input.matches("[A-Fa-f0-9]+")) {
                servletResponse.getWriter().println("This is not a Hex number:" + input + " Try again!");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();
        context.setAttribute("executionTime",endTime-startTime);
        logger.info("HexNumberFilter.doFilter() execution time: " + (endTime-startTime));
    }
}
