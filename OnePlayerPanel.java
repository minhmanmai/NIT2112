import javax.swing.*;
import java.awt.*;
/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) & Minh Mai(s4554511)                  ||
||                           Unique Six Assignment                             ||
||                                23-May-2018                                  ||
||                                                                             ||
||        This class is used to create GUI panel in which user can enter       ||
||                               the player's name                             ||
||                                                                             ||                               
||*****************************************************************************||
*/

import java.awt.event.*;

public class OnePlayerPanel extends JPanel{

   private JLabel name1LB;
   private JTextField name1TF;
   private JButton startGameBT;
   
   //Constructor
   public OnePlayerPanel() {
      super(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      //Ask for player's name
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
   }
   
   //Return player's name string
   public String getPlayerName() {
      return name1TF.getText().trim();
   }
}