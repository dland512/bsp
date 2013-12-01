public class BspNode<T extends MapStructure> {
   private BspNode front;
   private BspNode back;
   private T structure;

   public BspNode(T structure) {
      this.front = null;
      this.back = null;
      this.structure = structure;
   }

   public BspNode getFront() {
      return this.front;
   }

   public void setFront(BspNode node) {
      this.front = node;
   }

   public BspNode getBack() {
      return this.back;
   }

   public void setBack(BspNode node) {
      this.back = node;
   }

   public T getStructure() {
      return structure;
   }

   public boolean inFrontOf(BspNode node) {
      return this.structure.inFrontOf(node.getStructure());
   }

   public String toString() {
      return this.structure.toString();
   }
}
