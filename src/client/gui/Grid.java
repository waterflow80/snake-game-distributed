package client.gui;

public class Grid {
    private final int WIDTH;
    private final int HEIGHT;
    private final int UNIT_SIZE;
    private final int GAME_UNITS; // derived

    public Grid(int wIDTH, int hEIGHT, int uNIT_SIZE) {
        super();
        WIDTH = wIDTH;
        HEIGHT = hEIGHT;
        UNIT_SIZE = uNIT_SIZE;
        GAME_UNITS  = (wIDTH*hEIGHT)/uNIT_SIZE;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getUNIT_SIZE() {
        return UNIT_SIZE;
    }

    public int getGAME_UNITS() {
        return GAME_UNITS;
    }
}
