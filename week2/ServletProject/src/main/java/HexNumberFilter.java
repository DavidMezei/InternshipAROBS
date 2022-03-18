import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/convertHex")
public class HexNumberFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
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
    }
}
