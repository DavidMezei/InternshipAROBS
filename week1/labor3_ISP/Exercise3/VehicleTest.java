package week1.labor3_ISP.Exercise3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehicleTest {

    @Test
    public void testToString() {
        Vehicle vehicle = new Vehicle("Dacia", "Logan", 150, 'B');
        assertEquals("Should return Dacia Logan speed 150 fuel type B", "Dacia Logan speed 150 fuel type B", vehicle.toString());
    }
}
