package hr.tel.fer.lab1.antlr;

public enum Operator {
     EQ,NEQ;
   
	
	public static Operator parse(String text) {
       switch (text) {
         case "==": return EQ;
         case "!=": return NEQ;
         default:   return null;
       }
   }
}