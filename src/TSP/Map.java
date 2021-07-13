package TSP;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Moori
 * Date: 7/30/11
 * Time: 4:25 PM
 */
public class Map extends JPanel {
    protected TravelingSalesman owner;

    public Map(TravelingSalesman travelingSalesman) {
        this.owner = travelingSalesman;
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {

        int width = getBounds().width;
        int height = getBounds().height;

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        if (!owner.started) return;

        g.setColor(Color.red);

        int xpos = owner.cities[0].getx();
        int ypos = owner.cities[0].gety();

        g.fillOval(xpos - 5, ypos - 5, 15, 15);
        g.setColor(Color.white);

        for (int i = 1; i < TravelingSalesman.CITY_COUNT; i++) {
            xpos = owner.cities[i].getx();
            ypos = owner.cities[i].gety();
            g.fillOval(xpos - 5, ypos - 5, 10, 10);

        }
        g.setColor(Color.blue);
        owner.status.setText(String.valueOf(owner.best.fitness));
        for (int i = 0; i < TravelingSalesman.CITY_COUNT + 1; i++) {
            int icity = owner.best.getCity(i);
            if (i != 0) {
                int last = owner.best.getCity(i - 1);
                g.drawLine(
                        owner.cities[icity].getx(),
                        owner.cities[icity].gety(),
                        owner.cities[last].getx(),
                        owner.cities[last].gety());

                g.setColor(Color.green);
            }
        }
    }
}
