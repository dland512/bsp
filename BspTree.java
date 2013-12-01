import java.util.*;

public class BspTree {
   private BspNode root;

   public BspNode getRoot() {
      return this.root;
   }

   public void compile(List<BspNode> nodes) {
      Split split = this.split(nodes);
      this.root = compileRec(split.front, split.middle, split.back);
   }

   private BspNode compileRec(List<BspNode> frontNodes, BspNode middle, List<BspNode> backNodes) {
      if(frontNodes.size() > 0) {
         Split front = this.split(frontNodes);
         middle.setFront(compileRec(front.front, front.middle, front.back));
      }

      if(backNodes.size() > 0) {
         Split back = this.split(backNodes);
         middle.setBack(compileRec(back.front, back.middle, back.back));
      }

      return middle;
   }

   private class Split {
      public List<BspNode> front = new ArrayList<BspNode>();
      public BspNode middle;
      public List<BspNode> back = new ArrayList<BspNode>();
   }

   private Split split(List<BspNode> nodes) {
      Split s = new Split();
      s.middle = nodes.remove(nodes.size() / 2);

      for(int i = 0; i < nodes.size(); i++) {
         BspNode n = nodes.get(i);
         if(n.inFrontOf(s.middle))
            s.front.add(n);
         else
            s.back.add(n);
      }

      return s;
   }
}
