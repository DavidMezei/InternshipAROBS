package labor3_ISP.Exercise6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private HashMap<Integer, Product> products;
    private int credit;
    private static VendingMachine instance;

    private VendingMachine() {
        products = new HashMap<>();
        products.put(1, new Product("Cola", 2, 1));
        products.put(2, new Product("Fanta", 3, 2));
    }

    public VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    public String selectProduct(int id) {
        if (products.containsKey(id)) {
            return products.get(id).getName();
        }
        return "There is no product with id=" + id;
    }

    public void insertCoin(int value) {
        if (value > 0) {
            credit += value;
        }
    }

    public void displayCredit() {
        System.out.println("Your credit: " + credit);
    }

    public void userMenu() {
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println("Product name:" + entry.getValue().getName() + "  id:" + entry.getKey());
        }
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.userMenu();
        System.out.println("Your selected product is " + vendingMachine.selectProduct(1));
    }
}
