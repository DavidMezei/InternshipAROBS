package labor10.Exercise4;

public class Main {
    public static void main(String[] args) {
        Playground playground = new Playground(10);
        playground.addRobot(new Robot(playground,1,2,1));
        playground.addRobot(new Robot(playground,3,4,2));
        playground.addRobot(new Robot(playground,2,5,3));
        playground.addRobot(new Robot(playground,7,3,4));
        playground.addRobot(new Robot(playground,6,8,5));
        playground.addRobot(new Robot(playground,2,7,6));
        playground.addRobot(new Robot(playground,1,4,7));
        playground.addRobot(new Robot(playground,4,8,8));
        playground.addRobot(new Robot(playground,5,9,9));
        playground.startGame();
    }
}
