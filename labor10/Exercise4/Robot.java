package labor10.Exercise4;

import java.util.Random;

public class Robot extends Thread {
    private boolean isAlive = true;
    private int x;
    private int y;
    private Playground playground;
    private int robotId;

    public Robot(Playground playground, int x, int y, int robotId) {
        this.playground = playground;
        this.robotId = robotId;
        this.x = x;
        this.y = y;
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
            while (x == previousX && y == previousY) {
                int nextMove = random.nextInt(3) - 1;
                x += nextMove;
                y += nextMove;
                if (x == 0 || x == playground.getTableSize() - 1) {
                    x -= nextMove;
                }
                if (y == 0 || y == playground.getTableSize() - 1) {
                    y -= nextMove;
                }
            }

            if (playground.isThereSomebody(x, y)) {
                System.out.println("Robot id=" + robotId + " move from x:" + previousX + " y:" + previousY + " to " + "x:" + x + " y:" + y);
                System.out.println("Robot id=" + playground.getRobotIdAt(x, y) + " DESTROYED by robot id=" + playground.getRobotIdAt(previousX, previousY));
                playground.destroyRobotAt(x, y);
                playground.destroyRobotAt(previousX, previousY);
            } else {
                playground.moveRobot(previousX, previousY, x, y);
                System.out.println("Robot id=" + robotId + " move from x:" + previousX + " y:" + previousY + " to " + "x:" + x + " y:" + y);
            }
            previousX = x;
            previousY = y;
        }
    }
}
