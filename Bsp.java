import java.util.*;

public class Bsp {
   public static void main(String[] args) {
      List<BspNode> nodes = new ArrayList<BspNode>();

      nodes.add(new BspNode(new Wall(200, 400, 250, 350, Wall.Front.X_NEG)));
      nodes.add(new BspNode(new Wall(50, 50, 150, 150, Wall.Front.X_NEG)));
      nodes.add(new BspNode(new Wall(200, 300, 300, 200, Wall.Front.X_NEG)));

      BspTree tree = new BspTree();
      tree.compile(nodes);

      BspTraverser traverser = new BspTraverser(tree, new DrawingNodeVisitor());
      traverser.traverseOutward(new Point(1, 3));
   }
}
