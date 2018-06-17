/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) & Minh Mai(s4554511)                  ||
||                           Unique Six Assignment                             ||
||                                11-May-2018                                  ||
||                                                                             ||
||  This is the main driver program for our Unique Six game on part 2 and 3.   ||
||                                                                             ||                               
||*****************************************************************************||
*/

import javax.swing.*;


public class DisplaySixNumbersPanel 
{
   public static void main(String[ ] args) 
   {
      JFrame frame = new JFrame("Six Numbers Game");
      SixNumbersPanel2 firstPanel = new SixNumbersPanel2();   
      frame.setSize(500, 500);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(firstPanel);
   } //end main
} //end class