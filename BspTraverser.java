public class BspTraverser {
   private BspTree tree = null;
   private NodeVisitor visitor = null;

   public BspTraverser(BspTree tree, NodeVisitor visitor) {
      this.tree = tree;
      this.visitor = visitor;
   }

   public void traverseInward(int pov) {
      traverseInwardRec(tree.getRoot(), pov);
   }

   private void traverseInwardRec(BspNode node, int pov) {
      if(node != null) {
         if(inFront(pov, node)) {
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

   public void traverseOutward(int pov) {
      traverseOutwardRec(tree.getRoot(), pov);
   }

   private void traverseOutwardRec(BspNode node, int pov) {
      if(node != null) {
         if(inFront(pov, node)) {
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

   private boolean inFront(int pov, BspNode node) {
      IntegerNodeValue v = (IntegerNodeValue)node.getStructure();
      Integer i = Integer.parseInt(v.toString());
      return pov <= i;
   }

   public void print() {
      printRec(tree.getRoot());
   }

   private void printRec(BspNode n) {
      if(n != null) {
         printRec(n.getFront());
         System.out.println(n);
         printRec(n.getBack());
      }
   }
}
