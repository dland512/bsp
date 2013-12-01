public class IntegerNodeValue implements MapStructure {
   private Integer intVal;

   public IntegerNodeValue(Integer value) {
      this.intVal = value;
   }

   public boolean inFrontOf(MapStructure other) {
      return this.intVal < ((IntegerNodeValue)other).getInteger();
   }

   public String toString() {
      return this.intVal.toString();
   }

   private Integer getInteger() {
      return intVal;
   }
}
