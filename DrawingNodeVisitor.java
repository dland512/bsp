import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.lang.reflect.*;
import java.awt.event.*;

public class DrawingNodeVisitor implements NodeVisitor {
    private int n = 1;
    private DisplayManager displayManager = null;

    public DrawingNodeVisitor(DisplayManager dm) {
        this.displayManager = dm;
    }

    public void resetCount() {
        this.n = 1;
    }

    public void visitNode(BspNode node) {
        Graphics2D g = this.displayManager.getGraphics();
        g.setColor(Color.BLACK);
        Wall wall = node.getWall();
        g.drawLine((int)wall.x1, (int)wall.y1, (int)wall.x2, (int)wall.y2);

        //draw order number at mid point
        int x = (int)(wall.x1 + 0.5 * (wall.x2 - wall.x1));
        int y = (int)(wall.y1 + 0.5 * (wall.y2 - wall.y1));
        g.drawString(n++ + "", x, y);
        g.dispose();
    }
}
