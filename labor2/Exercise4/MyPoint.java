package labor2.Exercise4;

public class MyPoint {
    private int x;
    private int y;
    private int z;

    public MyPoint() {
        x = 0;
        y = 0;
        z = 0;
    }

    public MyPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
    }

    public double distance(int x, int y, int z) {
        return Math.sqrt(Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2) + Math.pow(z - getZ(), 2));
    }

    public double distance(MyPoint point) {
        return Math.sqrt(Math.pow(point.getX() - getX(), 2) + Math.pow(point.getY() - getY(), 2) + Math.pow(point.getZ() - getZ(), 2));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public static void main(String[] args) {
        MyPoint point1 = new MyPoint(2, 1, 2);
        MyPoint point2 = new MyPoint(1, 1, 0);

        System.out.println("The distance between points is " + point1.distance(point2));

    }
}
