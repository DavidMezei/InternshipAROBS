package labor4.Exercise1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CircleTest {

    @Test
    public void testGetArea(){
        Circle circle = new Circle(2);
        assertEquals(12.57,circle.getArea(),0.01);
    }
}
