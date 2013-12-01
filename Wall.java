public class Wall {
   private Wall front;
   private Wall back;
   private int value;

   public Wall(int value) {
      this.front = null;
      this.back = null;
      this.value = value;
   }

   public Wall getFront() {
      return this.front;
   }

   public void setFront(Wall wall) {
      this.front = wall;
   }

   public Wall getBack() {
      return this.back;
   }

   public void setBack(Wall wall) {
      this.back = wall;
   }

   public int getValue() {
      return value;
   }

   public boolean inFrontOf(Wall wall) {
      return this.getValue() <= wall.getValue();
   }

   public String toString() {
      return "" + this.value;
   }
}
