public class Point {

   public float x;
   public float y;

   public Point() {
   }

   public Point(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public boolean inFrontOf(Wall wall) {
      if(wall.y1 == wall.y2) {
         //special case for horizontal line
         if(wall.front == Wall.Front.Y_POS)
            return this.y >= wall.y1;
         else
            return this.y <= wall.y1;
      }
      else {
         //find the t value for the other line at the y value of this line by starting with
         //this.y = wall.y1 + t * (wall.y2 - wall.y1)
         //and solving for t
         float t = (this.y - wall.y1) / (wall.y2 - wall.y1);
         
         //now find the x value at that point on the other line
         float xpos = wall.x1 + t * (wall.x2 - wall.x1);

         //check if the x value is on the same side of the wall as the front
         if(wall.front == Wall.Front.X_POS)
            return this.x >= xpos;
         else
            return this.x <= xpos;
      }
   }

   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }  
}
