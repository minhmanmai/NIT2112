/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) Minh Mai(s4554511)                    ||
||                           Unique Six Assignment                             ||
||                                11-May-2018                                  ||
||                                                                             ||
||       This class will create a panel after getting name from player.        ||
||      This class will enable users to roll the dice and play the game.       || 
||                                                                             ||                              
||*****************************************************************************||*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;

public class OnePlayerGamePanel extends JPanel implements ActionListener {
   private JTextField[] player1TF = new JTextField[6];
   private JTextField rollCount = new JTextField();
   private JLabel p1Name, playerTurn, timesRolled, instructions, dieResult;
   private String p1String;
   private JButton rollButton;
   private int y=11, count=0;
   Die die = new Die();
       
    //initialize Player, making an array for an addition to part 3
   Player player = new Player();
   
   //constructor
   public OnePlayerGamePanel(String pName) {
      //initialize layout and declaration any variable
      super(new GridBagLayout());   //calling parent constructor
      GridBagConstraints c = new GridBagConstraints();
      p1String = pName;
   
      //initialize timesRolled TF and Label
      rollCount = new JTextField("0",3);
      rollCount.setEditable(false);
      rollCount.setHorizontalAlignment(JTextField.CENTER);
      timesRolled = new JLabel("Times Rolled");
      
      /******************Left Grid Begin********************/
      //initializing
      c.gridx=0;
      
      //add instructions Label
      c.gridy=0;
      c.gridwidth=10;
      c.gridheight=10;
      String instruct = "<html> Welcome to the game, "+ p1String + "! <br><br>Click the Roll button "
         + "to start your game. <br><br> To win, you must get all unique six numbers.</html>";
      instructions = new JLabel();
      c.insets = new Insets(10,10,10,10); //insets(top,left,bottom,right)
      instructions.setPreferredSize(new Dimension(300, 100)); //change the dimension here to scale the size of the game
      instructions.setHorizontalAlignment(JLabel.CENTER);
      Border border = BorderFactory.createLineBorder(Color.BLACK);
      instructions.setBorder(border);
      add(instructions,c);
      instructions.setText(instruct);
      
      //reset
      c.gridwidth=1;
      c.gridheight=1;
      
      //initializing and add player 1 result array
      //x=0, y=11 to 16
      for (int i=0;i<6;i++)
      {  
         if (i==0)
         {
            c.insets = new Insets(20,0,1,0); //insets(top,left,bottom,right)
            c.gridy=y;
            player1TF[i] = new JTextField(3);
            player1TF[i].setEditable(false);
            player1TF[i].setHorizontalAlignment(JTextField.CENTER);
            player1TF[i].setText("0");
            add(player1TF[i],c);
            y++;
         }
         else
         {
            c.insets = new Insets(0,0,1,0); //insets(top,left,bottom,right)
            c.gridy=y;
            player1TF[i] = new JTextField(3);
            player1TF[i].setEditable(false);
            player1TF[i].setHorizontalAlignment(JTextField.CENTER);
            player1TF[i].setText("0");
            add(player1TF[i],c);
            y++;
         }
      }
      
      //add player 1 name position
      //x=0, y=35
      c.insets = new Insets(0,10,-10,0);
      c.gridy=35;
      SixNumbersPanel2 firstPanel = new SixNumbersPanel2();
      p1Name = new JLabel("Player "+pName);
      add(p1Name,c);
      
      //add player 1 result textbox 
      //x=0, y=36
      c.insets = new Insets(0,0,2,0);
      c.gridy=36;
      add(rollCount,c);
      
      //add player 1 Label for timesRolled
      //x=1, y=36
      c.insets = new Insets(0,-10,2,0);
      c.gridx=1;
      add(timesRolled,c);
      
      /******************Left Grid End********************/
      
      /******************Center Grid Begin***************/
      //reset insets, grid, initiate respective labels and button
      c.insets = new Insets(10,0,0,10);
      c.gridx=3;
      c.gridwidth=10;           
            
            
      //add dieResult label
      c.gridy=11;
      c.gridheight=7;
      c.insets = new Insets(10,0,10,0);
      String dResult = "<html>0</html>";
      dieResult = new JLabel(dResult);
      dieResult.setHorizontalAlignment(JLabel.CENTER);
      dieResult.setFont(new Font("Dialog", Font.PLAIN, 100)); //can change font type and size here
      add(dieResult,c);
   
      
      //add playerTurn label
      //************disabling this for part 2********//
      c.gridy=22;
      c.insets = new Insets(10,10,0,10);
      String turn = pName+ "'s Turn";
      playerTurn = new JLabel(turn);
      playerTurn.setVisible(false);  
      add(playerTurn,c);
      
      //add rollButton button
      c.gridy=30;
      c.ipady=20;
      c.ipadx=100;
      c.insets = new Insets(0,10,10,10);
      rollButton = new JButton("Roll");
      add(rollButton,c);
      rollButton.addActionListener(this);
   
      
      /******************Center Grid End***************/
      
   }
    
     //action when rollButton is clicked
   public void actionPerformed(ActionEvent evt) {
         
         //setting die result to appear when button is clicked         
      int dResult = player.roll(die);
      dieResult.setText(Integer.toString(dResult));
      player.setRollResult(dResult);
      player1TF[dResult -1 ].setText(Integer.toString(dResult));
      player1TF[dResult -1 ].setBackground(Color.GREEN);
            
           //populate roll count 
      rollCount.setText("" + player.returnCount());
           
         //if all textboxes are filled then will perform actions below  
      if (player.winCheck())         {
         rollButton.setEnabled(false);
         instructions.setText("<html>Congratulations, "+p1String+"!<br><br> You have won the game!"
            + "<br><br>It took you " + player.returnCount() + " rolls to win.</html>");
      }        
   }
      
    
/******CODE BELOW IS FOR TESTING PURPOSES********* 
******************DO NOT REMOVE******************/    

/*

public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Unique Six");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OnePlayerGamePanel firstPanel = new OnePlayerGamePanel("Test");
        
        //Add contents to the window.
        frame.add(firstPanel);
        
        //Display the window.
        frame.pack();

    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }//end main
*/   
}//end class