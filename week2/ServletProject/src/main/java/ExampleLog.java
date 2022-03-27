import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleLog {
    private static final Logger logger = LoggerFactory.getLogger(ExampleLog.class);

    public static void main(String[] args)  {
        logger.info("Example log from {}", ExampleLog.class.getSimpleName());
    }
}
