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

import java.util.Scanner;
/**
A class that tests the RPNCalculator class by taking an
input from the user and evaluating the expresssion.
**/
public class RPNtester
{
/**
* The main method in this class the RPNCalculator
* object, takes an expression from the user, and
* evaluates the expression.
* @param args is not used
*/
   public static void main(String[] args)
   {
   //build a calculator
      RPNCalculator myCalc = new RPNCalculator();
   //grab an expression from the user
      Scanner scan = new Scanner(System.in);
      String expression = scan.nextLine();   
   //evaluate the expression
   //try
      float answer = myCalc.evaluate(expression);
      System.out.print(answer);
   }
}
