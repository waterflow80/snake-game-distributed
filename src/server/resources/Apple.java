package server.resources;

import java.util.Random;

public class Apple {
    private static Random random = new Random();

    private static int x = random.nextInt((int) GuiInfo.WIDTH / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE;
    private static int y = random.nextInt((int) GuiInfo.HEIGHT / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE;

    private static Coordinates appleCoordinates = new Coordinates(x,y);

    public static Coordinates getAppleCoordinates(){
        return appleCoordinates;
    }

    /**
     * Changing the location (coordinates) of the apple
     * => creating a new apple*/
    public static void updateAppleCoordinates(){
        appleCoordinates.setX(random.nextInt((int) GuiInfo.WIDTH / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE);
        appleCoordinates.setY(random.nextInt((int) GuiInfo.HEIGHT / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE);
    }
}
