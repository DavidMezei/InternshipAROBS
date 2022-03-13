package labor10.Exercise4;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Playground {
    private int[][] table;
    private Map<Integer, Robot> robots;

    public Playground(int size) {
        robots = Collections.synchronizedMap(new HashMap<Integer, Robot>());
        table = new int[size][size];
    }

    public void startGame() {
        for (Map.Entry<Integer, Robot> entry : robots.entrySet()) {
            entry.getValue().start();
            try {
                entry.getValue().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The game has finished!");
    }

    public void addRobot(Robot robot) {
        if (isThereSomebody(robot.getX(), robot.getY())) {
            robot.destroy();
            destroyRobotAt(robot.getX(), robot.getY());
        } else {
            robots.put(robot.getRobotId(), robot);
            table[robot.getX()][robot.getY()] = robot.getRobotId();
        }
    }

    public synchronized boolean isThereSomebody(int x, int y) {
        return table[x][y] > 0;
    }

    public synchronized void destroyRobotAt(int x, int y) {
        robots.get(table[x][y]).destroy();
        System.out.println("Robot id=" + table[x][y] + " DESTROYED ");
        //robots.remove(table[x][y]);
        table[x][y] = 0;
    }

    public synchronized void moveRobot(int fromX, int fromY, int toX, int toY) {
        table[toX][toY] = robots.get(table[fromX][fromY]).getRobotId();
        table[fromX][fromY] = 0;
    }
}
