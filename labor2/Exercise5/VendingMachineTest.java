package labor2.Exercise5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    @Test
    public void testSelectProduct() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("Select Fanta", "Cola", vendingMachine.selectProduct(1));
    }
}
