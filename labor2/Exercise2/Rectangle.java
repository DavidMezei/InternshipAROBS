package labor2.Exercise2;

public class Rectangle {
    private int length = 2;
    private int width = 1;
    private String color = "red";

    public Rectangle() {
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public Rectangle(int length, int width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getColor() {
        return color;
    }

    public int getPerimeter() {
        return 2 * length + 2 * width;
    }

    public int getArea() {
        return length * width;
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        System.out.println("Rectangle color: " + rectangle.getColor());
        System.out.println("Rectangle perimeter: " + rectangle.getPerimeter());
        System.out.println("Rectangle area: " + rectangle.getArea());

    }
}
