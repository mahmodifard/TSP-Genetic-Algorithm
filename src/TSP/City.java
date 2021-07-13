package TSP;

/**
 * Created by IntelliJ IDEA.
 * User: Moori
 * Date: 7/30/11
 * Time: 4:21 PM
 */
public class City {
    int xpos;
    int ypos;

    /**
     * Constructor.
     *
     * @param x The city's x position
     * @param y The city's y position.
     */
    public City(int x, int y) {
        this.xpos = x;
        this.ypos = y;
    }

    public int getx() {
        return xpos;
    }

    public int gety() {
        return ypos;
    }

    int proximity(City cother) {
        return proximity(cother.getx(), cother.gety());
    }

    int proximity(int x, int y) {
        int xdiff = xpos - x;
        int ydiff = ypos - y;
        return (int) Math.sqrt(xdiff * xdiff + ydiff * ydiff);
    }
}
