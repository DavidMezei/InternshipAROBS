package labor10.Exercise4;

import java.util.Random;

public class Robot extends Thread {
    private boolean isAlive = true;
    private int x;
    private int y;
    private Playground playground;
    private int robotId;

    public Robot(Playground playground, int robotId) {
        this.playground = playground;
        Random random = new Random();
        x = random.nextInt(100);
        y = random.nextInt(100);
        this.robotId = robotId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRobotId() {
        return robotId;
    }

    public void destroy() {
        isAlive = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        int previousX = x;
        int previousY = y;
        while (isAlive) {
            synchronized (this) {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyAll();
            }
            int nextMove = random.nextInt(3) - 1;
            x += nextMove;
            y += nextMove;
            if (x == 0 || x == 99) {
                x -= nextMove;
            }
            if (y == 0 || y == 99) {
                y -= nextMove;
            }
            if (playground.isThereSomebody(x, y)) {
                playground.destroyRobotAt(x, y);
                playground.destroyRobotAt(previousX,previousY);
            } else {
                playground.moveRobot(previousX, previousY, x, y);
                System.out.println("Robot id=" + robotId + " move from x:" + previousX + " y:" + previousY + " to " + "x:" + x + " y:" + y);
            }
            previousX = x;
            previousY = y;
        }
    }
}
