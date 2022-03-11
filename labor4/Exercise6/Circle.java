package labor4.Exercise6;

public class Circle extends Shape{
    private double radius=1.0;

    public Circle(){

    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI*getRadius()*getRadius();
    }

    public double getPerimeter(){
        return Math.PI * getRadius()*2;
    }

    @Override
    public String toString() {
        return "A Circle with radius"+getRadius() + ", which is a subclass of "+super.toString();
    }
}
