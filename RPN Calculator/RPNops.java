public interface RPNops { // Stack class ADT
   // +
   public float add(float x, float y);

   // -
   public float subtract(float x, float y);
   
     // *
   public float mult(float x, float y);
   
     // //
   public float div(float x, float y);
   
     // %
   public float mod(float x, float y);
   
     // /
   public float fdiv(float x, float y);

   // ^
   public float power(float x, float y);
   
     // .
   public float dot();
   
     // C
   public void clear();
   
       // $
   public String reveal();  

}