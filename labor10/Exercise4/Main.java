package labor10.Exercise4;

public class Main {
    public static void main(String[] args) {
        Playground playground = new Playground(100);
        playground.addRobot(new Robot(playground,1));
        playground.addRobot(new Robot(playground,2));
        playground.addRobot(new Robot(playground,3));
        playground.addRobot(new Robot(playground,4));
        playground.addRobot(new Robot(playground,5));
        playground.addRobot(new Robot(playground,6));
        playground.addRobot(new Robot(playground,7));
        playground.addRobot(new Robot(playground,8));
        playground.addRobot(new Robot(playground,9));
        playground.startGame();
    }
}
