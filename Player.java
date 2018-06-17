/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) & Minh Mai(s4554511)                  ||
||                           Unique Six Assignment                             ||
||                                11-May-2018                                  ||
||                                                                             ||
||     This Player class is the backbone on how the game is played and how     ||
||   the result would be populated. It is mainly used in Part 1 of our game.   ||
||                                                                             ||
||*****************************************************************************||
*/


public class Player {
   private String name;
   public int[] gameResult;
   public int rollResult, count;

   //default constructor
   public Player(String name) {
      this.gameResult = new int[] { 0, 0, 0, 0, 0, 0 };
      this.name = name;
   }
   
   //second contructor that does not need a name
   public Player() {
      this.gameResult = new int[] { 0, 0, 0, 0, 0, 0 };
   }

   //returning player's name
   public String getPlayerName() {
      return this.name;
   }
   
   //to create a new instance of Die class and rolling the die
   public int roll(Die die) {
      count++; 
      return die.possibleResult();
   }   
   
   //returning count
   public int returnCount() 
   {
      return this.count;
   }
   
   //Method to check when the player has got unique six
   public boolean winCheck() {
      int uniqueNumbers = 0;
      for (int i = 0; i < 6; i++) {
         if (gameResult[i] == i + 1) {
            uniqueNumbers++;
         }
      }      
      return (uniqueNumbers == 6);
   }
   
   //Method to set roll result
   public void setRollResult(int result)
   {
      this.gameResult[result - 1] = result;
   }
   
}//end class