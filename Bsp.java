import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Bsp {
    public static void main(String[] args) {
        java.util.List<BspNode> nodes = new java.util.ArrayList<BspNode>();

        nodes.add(new BspNode(new Wall(0, 150, 500, 100, Wall.Front.X_POS)));
        nodes.add(new BspNode(new Wall(500, 100, 500, 300, Wall.Front.X_NEG)));
        nodes.add(new BspNode(new Wall(500, 300, 800, 300, Wall.Front.Y_POS)));
        nodes.add(new BspNode(new Wall(800, 450, 0, 450, Wall.Front.Y_NEG)));

        BspTree tree = new BspTree();
        tree.compile(nodes);

        final DisplayManager displayManager = new DisplayManager();
        final DrawingNodeVisitor visitor = new DrawingNodeVisitor(displayManager);
        final BspTraverser traverser = new BspTraverser(tree, visitor);

        displayManager.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
                visitor.resetCount();
                Graphics2D g = displayManager.getGraphics();

                //erase screen
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, displayManager.getWidth(), displayManager.getHeight());
                g.dispose();

                traverser.traverseOutward(new Point(e.getX(), e.getY()));
            }

            public void mouseDragged(MouseEvent e) {
            }
        });
    }
}
