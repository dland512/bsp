import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.lang.reflect.*;
import java.awt.event.*;

public class DrawingNodeVisitor implements NodeVisitor {

    protected static final DisplayMode[] MID_RES_MODES = {
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(640, 480, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(1024, 768, 16, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1024, 768, 24, 0),
    };

    private GraphicsConfiguration gc = null;
    private BufferedImage image = null;
    private GraphicsDevice device = null;
    private Window window = null;

    public DrawingNodeVisitor() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = environment.getDefaultScreenDevice();

        DisplayMode displayMode = this.findFirstCompatibleMode(MID_RES_MODES);
        this.setFullScreen(displayMode);

        this.window = this.device.getFullScreenWindow();
        this.gc = this.window.getGraphicsConfiguration();
        this.image = gc.createCompatibleImage(this.window.getWidth(), this.window.getHeight(), Transparency.BITMASK);

        // this.window.setFont(new Font("Dialog", Font.PLAIN, 12));
        // this.window.setBackground(Color.blue);
        // this.window.setForeground(Color.white);

        this.window.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
               exit();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        this.window.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
               System.out.format("%d, %d\n", e.getX(), e.getY());
            }

            public void mouseDragged(MouseEvent e) {
            }
        });
    }

    private void exit() {
        Thread thread = new Thread() {
            public void run() {
                System.exit(0);
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    private DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
        DisplayMode goodModes[] = this.device.getDisplayModes();

        for(int i = 0; i < modes.length; i++)
            for(int j = 0; j < goodModes.length; j++)
                if(displayModesMatch(modes[i], goodModes[j]))
                    return modes[i];

        return null;
    }

    private void setFullScreen(DisplayMode displayMode) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);

        this.device.setFullScreenWindow(frame);

        if(displayMode != null && this.device.isDisplayChangeSupported()) {
            try {
                this.device.setDisplayMode(displayMode);
            }
            catch (IllegalArgumentException ex) {
            }

            // fix for mac os x
            frame.setSize(displayMode.getWidth(), displayMode.getHeight());
        }
        // avoid potential deadlock in 1.4.1_02
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    frame.createBufferStrategy(2);
                }
            });
        }
        catch (InterruptedException ex) {
        }
        catch (InvocationTargetException  ex) {
        }
    }

    private boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
        if (mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight())
            return false;

        if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
            mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
            mode1.getBitDepth() != mode2.getBitDepth()) {
            return false;
        }

        if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
            mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
            mode1.getRefreshRate() != mode2.getRefreshRate()) {
            return false;
        }

        return true;
    }

    public Graphics2D getGraphics() {
        BufferStrategy strategy = this.window.getBufferStrategy();
        return (Graphics2D)strategy.getDrawGraphics();
    }

    private int n = 1;

    public void visitNode(BspNode node) {
        Graphics2D g = this.getGraphics();
        g.setColor(Color.BLACK);
        Wall wall = node.getWall();
        System.out.println(wall);
        g.drawLine((int)wall.x1, (int)wall.y1, (int)wall.x2, (int)wall.y2);
        g.drawString(n++ + "", (int)wall.x1, (int)wall.y1);
        g.dispose();
    }
}
