/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) & Minh Mai(s4554511)                  ||
||                           Unique Six Assignment                             ||
||                                23-May-2018                                  ||
||                                                                             ||
||       This class is used to create GUI panel in which user can enter        ||
||                             the players names.                              ||
||                                                                             ||                               
||*****************************************************************************||
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoPlayersPanel extends JPanel{

   private static JLabel name1LB, name2LB;
   private static JTextField name1TF, name2TF;
   private JButton startGameBT;
   
   //default constructor
   public TwoPlayersPanel() {
      super(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      name1LB = new JLabel("What is your name?");
      c.insets = new Insets(10,10,10,10);
      c.gridx = 0;
      c.gridy = 3;
      add(name1LB, c);
      
      name1TF = new JTextField(20);
      c.insets = new Insets(2,2,2,2);
      c.gridx = 0;
      c.gridy = 4;
      add(name1TF, c);
      
      name2LB = new JLabel("What is the other player's name?");
      c.insets = new Insets(10,10,10,10);
      c.gridx = 0;
      c.gridy = 5;
      add(name2LB, c);
      
      name2TF = new JTextField(20);
      c.insets = new Insets(2,2,2,2);
      c.gridx = 0;
      c.gridy = 6;
      add(name2TF, c); 
   }   
   
   //Method to get player 1's name
   public String getPlayer1Name() {
      return name1TF.getText().trim();
   } 
   
   //Method to get player 2's name
   public String getPlayer2Name() {
      return name2TF.getText().trim();
   }  
}