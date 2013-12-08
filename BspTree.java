import java.util.*;

public class BspTree {
   private BspNode root;

   public BspNode getRoot() {
      return this.root;
   }

   //set up the tree using the list of nodes provided.
   public void compile(List<BspNode> nodes) {
      SideAssignment assignment = this.assignSides(nodes);
      this.root = compileRec(assignment.front, assignment.middle, assignment.back);
   }

   private BspNode compileRec(List<BspNode> frontNodes, BspNode middle, List<BspNode> backNodes) {
      if(frontNodes.size() > 0) {
         SideAssignment a = this.assignSides(frontNodes);
         middle.setFront(compileRec(a.front, a.middle, a.back));
      }

      if(backNodes.size() > 0) {
         SideAssignment a = this.assignSides(backNodes);
         middle.setBack(compileRec(a.front, a.middle, a.back));
      }

      return middle;
   }

   private class SideAssignment {
      public List<BspNode> front = new ArrayList<BspNode>();
      public BspNode middle;
      public List<BspNode> back = new ArrayList<BspNode>();
   }

   //split the nodes array up by seleting the middle node, and assigning the rest of the nodes to
   //be either in front of or behind it.
   private SideAssignment assignSides(List<BspNode> nodes) {
      SideAssignment a = new SideAssignment();
      a.middle = nodes.remove(nodes.size() / 2);
      Wall mid = a.middle.getWall();

      for(int i = 0; i < nodes.size(); i++) {
         BspNode n = nodes.get(i);
         Wall wall = n.getWall();

         if(mid.intersects(wall)) {
            Wall[] split = mid.split(wall);
            a.front.add(new BspNode(split[0]));
            a.back.add(new BspNode(split[1]));
         }
         else if(wall.inFrontOf(mid)) {
            a.front.add(n);
         }
         else {
            a.back.add(n);
         }
      }

      return a;
   }
}
