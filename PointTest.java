import org.junit.Assert;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointTest {
    @Test
    public void inFrontOfNeg() {
        Point p = new Point(3, 3);
        Wall w = new Wall(4, 8, 8, 4, Wall.Front.X_NEG);
        Assert.assertTrue(p.inFrontOf(w));
    }

    @Test
    public void inFrontOfPos() {
        Point p = new Point(10, 10);
        Wall w = new Wall(4, 8, 8, 4, Wall.Front.X_POS);
        Assert.assertTrue(p.inFrontOf(w));
    }

    @Test
    public void inBack() {
        Point p = new Point(10, 10);
        Wall w = new Wall(4, 8, 8, 4, Wall.Front.X_NEG);
        Assert.assertFalse(p.inFrontOf(w));
    }
}
