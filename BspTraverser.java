public class BspTraverser {
   private BspTree tree = null;
   private NodeVisitor visitor = null;

   public BspTraverser(BspTree tree, NodeVisitor visitor) {
      this.tree = tree;
      this.visitor = visitor;
   }

   public void traverseInward(Point pov) {
      traverseInwardRec(tree.getRoot(), pov);
   }

   private void traverseInwardRec(BspNode node, Point pov) {
      if(node != null) {
         if(pov.inFrontOf(node.getWall())) {
            traverseInwardRec(node.getBack(), pov);
            visitor.visitNode(node);
            traverseInwardRec(node.getFront(), pov);
         }
         else {
            traverseInwardRec(node.getFront(), pov);
            visitor.visitNode(node);
            traverseInwardRec(node.getBack(), pov);
         }
      }
   }

   public void traverseOutward(Point pov) {
      traverseOutwardRec(tree.getRoot(), pov);
   }

   private void traverseOutwardRec(BspNode node, Point pov) {
      if(node != null) {
         if(pov.inFrontOf(node.getWall())) {
            traverseOutwardRec(node.getFront(), pov);
            visitor.visitNode(node);
            traverseOutwardRec(node.getBack(), pov);
         }
         else {
            traverseOutwardRec(node.getBack(), pov);
            visitor.visitNode(node);
            traverseOutwardRec(node.getFront(), pov);
         }
      }
   }

   public void printInOrder() {
      printInOrderRec(tree.getRoot());
   }

   private void printInOrderRec(BspNode n) {
      if(n != null) {
         printInOrderRec(n.getFront());
         System.out.println(n);
         printInOrderRec(n.getBack());
      }
   }
}
