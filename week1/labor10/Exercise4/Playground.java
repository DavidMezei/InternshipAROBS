package week1.labor10.Exercise4;

import java.util.*;

public class Playground {
    private int[][] table;
    private Map<Integer, Robot> robots;
    private int size;

    public Playground(int size) {
        robots = new HashMap<Integer, Robot>();
        table = new int[size][size];
        this.size = size;
    }

    public void startGame() {
        ArrayList<Robot> array = new ArrayList<>(robots.values());
        for (var r : array) {
            r.start();
        }
        for (var r : array) {
            try {
                r.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The game has finished!");
    }

    public void addRobot(Robot robot) {
        robots.put(robot.getRobotId(), robot);
        table[robot.getX()][robot.getY()] = robot.getRobotId();
    }

    public synchronized boolean isThereSomebody(int x, int y) {
        return table[x][y] > 0;
    }

    public synchronized void destroyRobotAt(int x, int y) {
        if (table[x][y] > 0) {
            robots.get(table[x][y]).destroy();
            robots.remove(table[x][y]);
            table[x][y] = 0;
        }
    }

    public int getTableSize() {
        return size;
    }

    public synchronized int getRobotIdAt(int x, int y) {
        return table[x][y];
    }

    public synchronized void moveRobot(int fromX, int fromY, int toX, int toY) {
        if (table[fromX][fromY] > 0) {
            table[toX][toY] = robots.get(table[fromX][fromY]).getRobotId();
            table[fromX][fromY] = 0;
        }
    }
}
