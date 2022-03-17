package week1.labor3_ISP.Exercise1;

public class Tree {
    private int height;

    Tree() {
        height = 15;
    }

    public void grow(int meters) {
        if (meters >= 1) {
            height += meters;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(height);
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.grow(5);

        System.out.println("Tree height is " + tree.toString());
    }
}
