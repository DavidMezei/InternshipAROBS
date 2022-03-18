import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class RequestCounter implements ServletRequestListener {
    AtomicInteger counter = new AtomicInteger();

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();
        if (request.getServletPath().equals("/convertHex")) {
            counter.addAndGet(1);
            session.setAttribute("requestCount", counter.get());
        }
    }
}
