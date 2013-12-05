public class BspNode {
   private BspNode front;
   private BspNode back;
   private Wall wall;

   public BspNode(Wall wall) {
      this.front = null;
      this.back = null;
      this.wall = wall;
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

   public Wall getWall() {
      return wall;
   }

   public String toString() {
      return this.wall.toString();
   }
}
