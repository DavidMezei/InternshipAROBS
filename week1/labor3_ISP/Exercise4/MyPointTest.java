package labor2.Exercise4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyPointTest {

    @Test
    public void testDistance() {
        MyPoint point1 = new MyPoint(2, 1, 2);
        MyPoint point2 = new MyPoint(1, 1, 0);
        assertEquals(2.24, point1.distance(point2), 0.01);
    }
}
