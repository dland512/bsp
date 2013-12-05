public interface MapStructure {
   public boolean inFrontOf(MapStructure other);
   public boolean intersects(MapStructure other);
   public MapStructure[] split(MapStructure other);
}
