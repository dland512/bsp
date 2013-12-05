import org.junit.Assert;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WallTest {
    @Test
    public void intersects() {
        Wall w1 = new Wall(1, 1, 3, 4, Wall.Front.X_POS);
        Wall w2 = new Wall(4, 10, 7, 7, Wall.Front.X_POS);
        Assert.assertTrue(w1.intersects(w2));
    }

    @Test
    public void doesNotIntersect() {
        Wall w1 = new Wall(1, 1, 5, 5, Wall.Front.X_POS);
        Wall w2 = new Wall(4, 10, 7, 7, Wall.Front.X_POS);
        Assert.assertTrue(w1.intersects(w2));
    }

    @Test
    public void intersectionPoints() {
        Wall w1 = new Wall(1, 1, 3, 3, Wall.Front.X_POS);
        Wall w2 = new Wall(4, 8, 8, 4, Wall.Front.X_POS);
        
        Wall[] segs = w1.split(w2);

        Wall front = segs[0];
        Assert.assertEquals(front.x1, 6.0, 0);
        Assert.assertEquals(front.y1, 6.0, 0);
        Assert.assertEquals(front.x2, 8.0, 0);
        Assert.assertEquals(front.y2, 4.0, 0);

        Wall back = segs[1];
        Assert.assertEquals(back.x1, 4.0, 0);
        Assert.assertEquals(back.y1, 8.0, 0);
        Assert.assertEquals(back.x2, 6.0, 0);
        Assert.assertEquals(back.y2, 6.0, 0);
    }

    @Test
    public void intersectionPoints2() {
        Wall w1 = new Wall(1, 1, 3, 3, Wall.Front.X_NEG);
        Wall w2 = new Wall(4, 8, 8, 4, Wall.Front.X_POS);
        
        Wall[] segs = w1.split(w2);

        Wall front = segs[0];
        Assert.assertEquals(front.x1, 4.0, 0);
        Assert.assertEquals(front.y1, 8.0, 0);
        Assert.assertEquals(front.x2, 6.0, 0);
        Assert.assertEquals(front.y2, 6.0, 0);

        Wall back = segs[1];
        Assert.assertEquals(back.x1, 6.0, 0);
        Assert.assertEquals(back.y1, 6.0, 0);
        Assert.assertEquals(back.x2, 8.0, 0);
        Assert.assertEquals(back.y2, 4.0, 0);
    }

    @Test
    public void inFrontOfNeg() {
        Wall w1 = new Wall(1, 1, 3, 3, Wall.Front.X_NEG);
        Wall w2 = new Wall(4, 8, 8, 4, Wall.Front.X_NEG);

        Assert.assertTrue(w1.inFrontOf(w2));
    }

    @Test
    public void inFrontOfPos() {
        Wall w1 = new Wall(8, 8, 10, 10, Wall.Front.X_POS);
        Wall w2 = new Wall(4, 8, 8, 4, Wall.Front.X_POS);

        Assert.assertTrue(w1.inFrontOf(w2));
    }

    @Test
    public void inBack() {
        Wall w1 = new Wall(8, 8, 10, 10, Wall.Front.X_POS);
        Wall w2 = new Wall(4, 8, 8, 4, Wall.Front.X_NEG);

        Assert.assertFalse(w1.inFrontOf(w2));
    }
}
