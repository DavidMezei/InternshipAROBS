import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class RequestCounter implements ServletRequestListener {
    private AtomicInteger counter = new AtomicInteger();
    private static final Logger logger = LoggerFactory.getLogger(RequestCounter.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();
        if (request.getServletPath().equals("/convertHex")) {
            counter.addAndGet(1);
            session.setAttribute("requestCount", counter.get());
            logger.info("requestCount: " + counter.get());
        }
        logger.info("requestDestroyed");
    }
}
