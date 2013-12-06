public class PrintingNodeVisitor implements NodeVisitor {
   public void visitNode(BspNode node) {
      System.out.println(node.getWall().toString());
   }
}
