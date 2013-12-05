import java.util.*;

public class Bsp {
   public static void main(String[] args) {
      List<BspNode> nodes = new ArrayList<BspNode>();

      nodes.add(new BspNode<Wall>(new Wall(4, 8, 5, 7, Wall.Front.X_NEG)));
      nodes.add(new BspNode<Wall>(new Wall(1, 1, 3, 3, Wall.Front.X_NEG)));
      nodes.add(new BspNode<Wall>(new Wall(4, 6, 6, 4, Wall.Front.X_NEG)));

      BspTree tree = new BspTree();
      tree.compile(nodes);

      BspTraverser traverser = new BspTraverser(tree, new NodeVisitor());
      traverser.print();
      // traverser.traverseInward(13);
   }
}
