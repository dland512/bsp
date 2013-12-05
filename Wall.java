public class Wall {
   public enum Front {
      X_POS,
      X_NEG,
      Y_POS,
      Y_NEG
   };

   public float x1;
   public float y1;
   public float x2;
   public float y2;
   public Front front;

   public Wall() {
   }

   public Wall(float x1, float y1, float x2, float y2, Front front) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      this.front = front;
   }

   public boolean inFrontOf(Wall other) {
      if(this.y1 == this.y2) {
         //special case for horizontal line
         if(this.front == Front.Y_POS)
            return other.y1 > this.y1;
         else
            return other.y1 < this.y1;
      }
      else {
         //first pick a point on the line to use
         float y = this.y1;

         //find the t value for the other line at the y value of this line by starting with
         //y = other.y1 + t * (other.y2 - other.y1)
         //and solving for t
         float t = (y - other.y1) / (other.y2 - other.y1);
         
         //now find the x value at that point on the other line
         float x = other.x1 + t * (other.x2 - other.x1);                                           

         //check if the x value is on the same side of the wall as the front
         if(other.front == Wall.Front.X_POS)
            return this.x1 >= x;
         else
            return this.x1 <= x;
      }
   }

   public String toString() {
      return "(" + this.x1 + ", " + this.y1 + "), (" + this.x2 + ", " + this.y2 + ")";
   }

   public boolean intersects(Wall other) {
      float[] vals = calcIntersectionValues(this, other);
      float tn = vals[0];
      float td = vals[1];

      //if td == 0, lines are parallel, otherwise lines intersect if tn / td <= 1
      if(td != 0)
         return (tn / td) <= 1;
      else
         return false;
   }

   public Wall[] split(Wall other) {
      float[] vals = calcIntersectionValues(this, other);
      float tn = vals[0];
      float td = vals[1];
      float t = tn / td;

      //get point of intersection
      float xInter = other.x1 + (other.x2 - other.x1) * t;
      float yInter = other.y1 + (other.y2 - other.y1) * t;

      //split the segment in two on the intersection
      Wall newWall = new Wall();
      newWall.x1 = other.x1;
      newWall.y1 = other.y1;
      newWall.x2 = xInter;
      newWall.y2 = yInter;
      other.x1 = xInter;
      other.y1 = yInter;

      Wall frontSeg = null;
      Wall backSeg = null;

      //set front and back according to which parts of split lie on front of this wall
      if(this.front == Wall.Front.X_NEG) {
         //if wall's front is in -x, find which side of split is farther in that direction
         if(Math.min(newWall.x1, xInter) < xInter) {
            frontSeg = newWall;
            backSeg = other;
         }
         else {
            frontSeg = other;
            backSeg = newWall;
         }
      }
      else {
         //if wall's front is in +x, find which side of split is farther in that direction
         if(Math.max(newWall.x1, xInter) > xInter) {
            frontSeg = newWall;
            backSeg = other;
         }
         else {
            frontSeg = other;
            backSeg = newWall;
         }
      }

      return new Wall[] { frontSeg, backSeg };
   }

   private float[] calcIntersectionValues(Wall wall1, Wall wall2) {
      float x43 = wall2.x2 - wall2.x1;
      float y31 = wall2.y1 - wall1.y1;
      float x31 = wall2.x1 - wall1.x1;
      float y43 = wall2.y2 - wall2.y1;
      float y21 = wall1.y2 - wall1.y1;
      float x21 = wall1.x2 - wall1.x1;

      float tn = x21*y31 - x31*y21;
      float td = x43*y21 - x21*y43;

      return new float[] { tn, td };
   }
}
