package week1.labor4.Exercise6;

public class Shape {
    private String color="red";
    private boolean filled=true;

    public Shape() {

    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        if (isFilled()){
            return "Shape with color of "+getColor() + "and filled";
        }
        return "Shape with color of "+getColor() + "and not filled";
    }
}
