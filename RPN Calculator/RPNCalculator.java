/**********************************************
Title: RPN Calculator
Programmer: Sofiane Lachab
Date: 10-13-2023
Class/Hour: Data Structures and Algorithms TThF 2-3
Description: Complete the RPNCalculator class by properly
implementing the Stack and RPNops interfaces.
Honor Code: On my honor, I have neither given nor received
unauthorized assistance on this assignment. Sofiane Lachab
**********************************************/

/**
A class that contains the constructors and methods
for RPNCalculator objects, utilizing the Stack and
RPNops interfaces.
**/ 
public class RPNCalculator implements Stack, RPNops 
{ 
   /**
   A nested class that is used for the Link objects
   that are necessary for the Stack data type and
   creating the RPNCalculator objects.
   **/
   public class Link 
   {  
      /**
      * Object element that is the data stored in the node.
      */
      private Object e;  // Value for this node
      /**
      * Link next that points to the next node in the linked list.
      */
      private Link n;    // Point to next node in list
   
   // Constructors
      /**
      * Constructor for Link object with parameters for the element and next.
      * @param it is an Object fo the node's element
      * @param inn is a Link that the constructed one will point to
      */  
      Link(Object it, Link inn) { e = it; n = inn; }
      /**
      * Constructor for the Link object with parameters for next.
      * @param inn is a Link that the construced one will point to
      */  
      Link(Link inn) { e = null; n = inn; }
   
      /**
      * Getter that denotes the element's data that is stored
      * in the Link.
      * @return Object e that is the element stored in the Link
      */  
      Object element() { 
         return e; }                  // Return the value
      /**
      * Setter that alters the element's data that is stored
      * in the Link.
      * @param it is the Object that the element will become
      * @return Object e that is the new element stored in the Link
      */  
      Object setElement(Object it) 
      { 
         e = it; // Set element value
         return e; 
      } 
      /**
      * Getter that denotes the next Link that comes after the called
      * Link in the list.
      * @return Link that is the Link lies after the current Link in the list
      */  
      Link next() { 
         return n; }                       // Return next Link
      /**
      * Setter that alters the next Link that comes after the called
      * Link in the list.
      * @param inn is the nex Link that the called one will point to
      * @return Link that is the new Link that lies after the current Link
      */  
      Link setNext(Link inn) 
      { 
         n = inn; // Set next Link
         return n; 
      }      
   }

   /**
   * Link top that points to the node at the top of the list.
   */
   private Link top;               // Pointer to first element
   /**
   * int for the number of nodes in the linked list.
   */
   private int size;               // Number of elements
   
   // Constructors
   /**
   * Constructor for the RPNCalculator object that creates an empty stack,
   * setting the top to null and the size to 0.
   */
   public RPNCalculator() 
   { 
      top = null; 
      size = 0; 
   }

   /**
   * Clears all elements from the stack, setting the top to null
   * and the size to 0.
   */
   public void clear()
   {
      top = null;
      size = 0;
   }
   
   /**
   * Creates a new Link with the given object and pushes it to the
   * top of the stack.
   * @param it is the element for the new node being pushed on the stack
   * @return true for the operation completing
   */
   public boolean push(Object it)
   {
      top = new Link(it, top);    // Creates new Link for the stack
      size++;
      return true;
   }
   
   /**
   * If the stack isn't empty, this removes the top
   * element from the stack and moves the marker for the top.
   * @return Object that was removed from the top
   */
   public Object pop()
   {
      Object data;
      if (top == null)   // if top is null, do nothing and return null
      {
         data = null;
      }
      else               // if top has data, remove and readjust stack
      {
         data = top.element();
         top = top.next();
         size--;    // account for new length
      }
      return data;
   }
   
   /**
   * Getter that returns data for what's at the top of the stack.
   * @return Object that is the top element
   */
   public Object topValue()
   {
      return top.element();
   }
   
   /**
   * Getter that returns the size of the stack, giving how many
   * elements are in the list.
   * @return int size that is denotes how many elements in the stack
   */
   public int length()
   {
      return size;
   }
   
   /**
   * Checks to see if the stack is completely empty.
   * @return boolean for wheter the stack is empty or not
   */
   public boolean isEmpty()
   {
      boolean result = false;
      if (size == 0)
      {
         result = true;
      }
      return result;
   }
   
   /**
   * Adds the given values and removes the top two elements
   * from the stack.
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the sum of the two given elements
   */   
   public float add(float x, float y)
   {
      float answer = y + x;
      pop();
      pop();
      return answer;
   }
   
   /**
   * Subtracts the given values and removes the top two elements
   * from the stack.
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the difference of the two given elements
   */
   public float subtract(float x, float y)
   {
      float answer = y - x;  // Top of stack is second, so y is first
      pop();
      pop();
      return answer;
   }
   
   /**
   * Multiplies the given values and removes the top two elements
   * from the stack.
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the prodcut of the two given elements
   */
   public float mult(float x, float y)
   {
      float answer = y * x;
      pop();
      pop();
      return answer;
   }
   
   /**
   * Divides the given values and removes the top two elements
   * from the stack (integer division).
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the quotient of the two given elements
   */
   public float div(float x, float y)
   {
      float answer = 0;
      try     // Check for divide by 0
      {
         if (x == 0.0)
         {
            throw new ArithmeticException();
         }
         answer = (int) y / x;
         pop();
         pop();
      }
      catch (ArithmeticException e)  // error catch for divide by 0
      {
         System.out.println("ERROR [//]");
         System.out.print(reveal());
         System.exit(0);
      }
      return answer;
   }
   
   /**
   * Takes the remainder of the given values and removes
   * the top two elements from the stack (integer).
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the remainder of the two given elements
   */
   public float mod(float x, float y)
   {
      float answer = 0;
      try   // Check for divide by 0
      {
         if (x == 0.0)
         {
            throw new ArithmeticException();
         }
         answer = (int) y % (int) x;
         pop();
         pop();
      }
      catch (ArithmeticException e) // error catch for divide by 0
      {
         System.out.println("ERROR [%]");
         System.out.print(reveal());
         System.exit(0);
      }
      return answer;
   }
   
   /**
   * Divides the given values and removes the top two elements
   * from the stack (float division).
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the quotient of the two given elements
   */
   public float fdiv(float x, float y)
   {
      float answer = 0;
      try   // Check for divide by 0
      {
         if (x == 0.0)
         {
            throw new ArithmeticException();
         }
         answer = y / x;
         pop();
         pop();
      }
      catch (ArithmeticException e)  // error catch for divide by 0
      {
         System.out.println("ERROR [/]");
         System.out.print(reveal());
         System.exit(0);
      }
      return answer;
   }
   
   /**
   * Takes one of the given values as the base and raises it
   * to the other as the exponent.
   * @param x is the top element from the stack
   * @param y is the second highest element from the stack
   * @return float for the resulting value of the two given elements
   */
   public float power(float x, float y)
   {
      float answer = 0;
      try   // Check if even root of a negative
      {
         if (1 / x % 2 == 0.0 && y < 0)
         {
            throw new ArithmeticException();
         }
         else if (y < 0) // Take absolute value to calculate negative roots
         {
            float val = Math.abs(y);
            answer = (float) Math.pow(val, x) * -1;
            pop();
            pop();
         }
         else  // basic exponent math for positive base and power
         {
            answer = (float) Math.pow(y, x);
            pop();
            pop();
         }
      }
      catch (ArithmeticException e)  // error catch for even root of negative
      {
         System.out.println("ERROR [^]");
         System.out.print(reveal());
         System.exit(0);
      }
      return answer;
   }
   
   /**
   * Returns the value at the top of the stack (formatting it
   * to one decimal point).
   * @return float for the element at the top of the stack
   */
   public float dot()
   {
      float result;
      String form = "" + (float) this.topValue(); // make String to format it
      form = String.format("%.1f", Float.valueOf(form));  // one decimal place
      result = Float.parseFloat(form);
      return result;
   }
   
   /**
   * Converts the values in the stack to a String,
   * starting from the top of the stack.
   * @return String that represents the stack
   */
   public String reveal()
   {
      String result = "[TOS]\n";  // top of stack
      Link curr = top;
      for (int i = 0; i < size; i++) // all elements in the stack
      {
         result += ("[ " + curr.element() + " ]\n");
         curr = curr.next();
      }
      result += "[BOS]"; // bottom of stack
      return result;
   }
   
   /**
   * Helper function that checks if the given input (from the user)
   * is an operand.
   * @param data is the user's input that is being checked
   * @return boolean for if the input is an operand
   */
   public boolean isOp(String data)
   {
      boolean result = false;
      if (data.equals("+") || data.equals("-") ||  data.equals("*")
         || data.equals("/") || data.equals("%") || data.equals("//")
         || data.equals("^") || data.equals(".") || data.equals("?")
         || data.equals("$") || data.equals("C")) // all possible operands
      {
         result = true;
      }
      return result;
   }
   
   /**
   * Function that evaluates the given input (from the user)
   * and performs the calculations using RPN.
   * @param input is the given expression (user's input)
   * @return float that is the top of the stack when completed
   */
   public float evaluate(String input)
   {
      float result;
      String[] data = input.split(" "); // split expression into elements
      for (String elem : data) // parse through elements
      {
         if (isOp(elem)) // if an operand
         {
            if (size >= 2) // if there are at least two elements
            {
               float x = (float) top.element(); // x and y for operations
               float y = (float) top.next().element();
               switch(elem)  // performs operations depending on operand
               {
                  case "+":
                     push(add(x, y));
                     break;
                  case "-":
                     push(subtract(x, y));
                     break;
                  case "*":
                     push(mult(x, y));
                     break;
                  case "/":
                     push(fdiv(x, y));
                     break;
                  case "%":
                     push(mod(x, y));
                     break;
                  case "//":
                     push(div(x, y));
                     break;
                  case "^":
                     push(power(x, y));
                     break;
                  case "?":
                     System.out.println("{" + size + "}");
                     System.out.println(); // adds spacing
                     break;
                  case "$":
                     System.out.println(reveal());
                     System.out.println(); // adds spacing
                     break;
                  case ".":
                     if (size > 0)
                     {
                        System.out.println(dot());
                     }
                     else
                     {
                        System.out.println("[empty]");
                     }
                     System.out.println(); // adds spacing
                     break;
                  case "C":
                     clear();
                     break;     
                  default:
                     break; // nothing necessary, operation is complete
               }                  
            }
            else
            {
               switch(elem)
               {
                  case ".":
                     if (size > 0) // if there's an element
                     {
                        System.out.println(dot());
                     }
                     else // print empty if no element
                     {
                        System.out.println("[empty]");
                     }
                     System.out.println(); // adds spaciing
                     break;
                  case "?":
                     System.out.println("{" + size + "}");
                     System.out.println(); // adds spacing
                     break;
                  case "$":
                     System.out.println(reveal());
                     System.out.println(); // adds spacing
                     break;
                  case "C":
                     clear();
                     break;
                  default: // operation with not enough elements
                     System.out.println("ERROR [op]");
                     System.out.println("pop on empty stack");
                     System.out.print(reveal());
                     System.exit(0); // end program because of error
               }
            }
         }
         else // if not an operand, push the element
         {
            push(Float.parseFloat(elem));
         }
      }
      System.out.print(""); // adds spacing
      result = dot();
      return result; // returns top of stack
   }
}