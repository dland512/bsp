import java.util.*;

public class Bsp {
   public static void main(String[] args) {
      List<BspNode> nodes = new ArrayList<BspNode>();

      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(2)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(4)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(6)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(8)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(10)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(12)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(14)));
      nodes.add(new BspNode<IntegerNodeValue>(new IntegerNodeValue(16)));

      BspTree tree = new BspTree();
      tree.compile(nodes);

      BspTraverser traverser = new BspTraverser(tree, new NodeVisitor());
      traverser.traverseInward(13);
   }
}
