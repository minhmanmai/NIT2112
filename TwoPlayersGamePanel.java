/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) & Minh Mai(s4554511)                  ||
||                           Unique Six Assignment                             ||
||                                23-May-2018                                  ||
||                                                                             ||
||       This class is used to create GUI panel for a two players game.        ||
||                                                                             ||                               
||*****************************************************************************||
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;
import java.awt.Insets;

public class TwoPlayersGamePanel extends JPanel{
   private String player1Name, player2Name, winnerName, rollCount1, rollCount2, winnerRollCount; 
   private String firstRollPlayer, instructionText, endText, playerInTurnText;
   private int dieResult, playerInTurn;
   private JLabel messageLB, dieResultLB, rollCountsLB, player1NameLB, player2NameLB;
   private JButton rollBT, newGameBT;
   private JTextField[] player1TF, player2TF;
   private GridBagConstraints c = new GridBagConstraints();
   
   //Constrcutor
   public TwoPlayersGamePanel(String p1, String p2) { 
      super(new GridBagLayout());      
      
      //Initialize objects and variables
      Player player1 = new Player(p1);
      Player player2 = new Player(p2);
      Die die = new Die();
      player1Name = player1.getPlayerName();
      player2Name = player2.getPlayerName();
      winnerName = "";
      rollCount1 = "0";
      rollCount2 = "0";
      winnerRollCount = "0";
      Random random = new Random();
      playerInTurn = random.nextInt(2) + 1;
      if (playerInTurn == 1) {
         firstRollPlayer = player1Name;
      } else {
         firstRollPlayer = player2Name;
      }
      playerInTurnText = firstRollPlayer + " goes first!";
      
      //Initialize labels, text fields and buttons
      rollCountsLB = new JLabel();
      messageLB = new JLabel();
      dieResultLB = new JLabel();
      player1TF = new JTextField[6];
      player2TF = new JTextField[6];
      player1NameLB = new JLabel(player1Name);
      player2NameLB = new JLabel(player2Name);
      rollBT = new JButton("Roll");
      newGameBT = new JButton("New game");
      
      
      //Initialize fonts and border
      Border border = BorderFactory.createLineBorder(Color.BLACK);
      Font resultSetFT = new Font("SANS_SERIF", Font.PLAIN, 15);      
      Font dieResultFT = new Font("Dialog", Font.PLAIN, 100);
   
      //Add game messages
      String instructionText = "<html><p align=\"center\">Welcome to the game " + player1Name + " & " + player2Name + 
         "!</p><br><p align=\"center\">Click the \"Roll\" button to play.</p><p>The first player to roll a unique six wins.</p></html>";
      messageLB = new JLabel();   
      messageLB.setHorizontalAlignment(JLabel.CENTER);
      messageLB.setPreferredSize(new Dimension(350, 100));
      messageLB.setBorder(border);
      addComponent(messageLB, 0, 0, 3, 1, 10, 10, 10, 10);
      messageLB.setText(instructionText);
      
      
      
         
      //Create 6 text fields for player 1
      c.anchor = GridBagConstraints.WEST;
      addComponent(player1NameLB, 0, 1, 1, 1, 2, 15, 2, 2);
      for (int i = 0; i < 6; i++)
      {  
         c.anchor = GridBagConstraints.WEST;
         player1TF[i] = new JTextField(3);
         player1TF[i].setHorizontalAlignment(JTextField.CENTER);
         player1TF[i].setText("0");
         player1TF[i].setFont(resultSetFT);
         player1TF[i].setEditable(false);
         addComponent(player1TF[i], 0, i + 2, 1, 1, 2, 15, 2, 2);
      }
      
      //Create 6 text fields for player 2
      c.anchor = GridBagConstraints.EAST;
      addComponent(player2NameLB, 2, 1, 1, 1, 2, 2, 2, 15);
      for (int j = 0; j < 6; j++)
      {     
         c.anchor = GridBagConstraints.EAST;
         player2TF[j] = new JTextField(3);
         player2TF[j].setHorizontalAlignment(JTextField.CENTER);
         player2TF[j].setText("0");
         player2TF[j].setFont(resultSetFT);
         player2TF[j].setEditable(false);
         addComponent(player2TF[j], 2, j + 2, 1, 1, 2, 2, 2, 15);
         
      }
      
      //Add roll result label
      dieResultLB.setFont(dieResultFT);
      dieResultLB.setText(" ");
      addComponent(dieResultLB, 1, 2, 1, 6, 2, 2, 2, 2);
      
      //Add roll counts text area
      rollCountsLB.setText("<html><p align=\"center\">" + playerInTurnText + 
         "</p><br><p align=\"center\">Number of rolls</p><p align=\"center\">" + player1Name + 
         ": " + rollCount1 + "</p><p align=\"center\">" + player2Name + ": " + rollCount2 + "</p><br></html>");
      addComponent(rollCountsLB, 1, 10, 1, 1, 2, 2, 2, -15);
      
      //Add new game button
      newGameBT.setHorizontalAlignment(GridBagConstraints.CENTER);
      addComponent(newGameBT, 1, 2, 1, 6, 2, 2, 2, -17);
      newGameBT.setVisible(false);
            
      //Add roll button
      rollBT.setHorizontalAlignment(GridBagConstraints.CENTER);
      addComponent(rollBT, 1, 11, 1, 1, 2, 100, 2, 2);
      
      //Roll button actions
      rollBT.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  if (playerInTurn == 1) {
                     if (player2.winCheck() && (uniqueCount(player1) < 5)) {
                        endGame(player2);
                     }else {
                        if (player1.winCheck() && player2.winCheck() && rollCount1.equals(rollCount2)) {
                           gameDraw();
                        }else if (player1.winCheck() && rollCount1.equals(rollCount2)) {
                           endGame(player1);
                        }else if (player2.winCheck() && rollCount2.equals(rollCount1)) {
                           endGame(player2);
                        }else {
                           playerInTurn = 2;
                           dieResult = player1.roll(die);
                           dieResultLB.setText(Integer.toString(dieResult));
                        //Record roll result for the player
                           player1.setRollResult(dieResult);
                        //Set roll result to the text field
                           player1TF[dieResult - 1].setText(Integer.toString(dieResult));
                           player1TF[dieResult - 1].setBackground(Color.GREEN);
                           rollCount1 = Integer.toString(player1.returnCount());
                           rollCountsLB.setText("<html><p align=\"center\">" + 
                              player1Name + " got</p><br><p align=\"center\">Number of rolls</p><p align=\"center\">" + 
                              player1Name + ": " + rollCount1 + "</p><p align=\"center\">" + player2Name + ": " + rollCount2 + 
                              "</p><br></html>");
                        
                        }
                     }                                             
                  }else if (playerInTurn == 2) {
                     if (player1.winCheck() && (uniqueCount(player2) < 5)) {
                        endGame(player1);
                     }else {
                        if (player1.winCheck() && player2.winCheck() && rollCount2.equals(rollCount1)) {
                           gameDraw();
                        }else if (player2.winCheck() && rollCount2.equals(rollCount1)) {
                           endGame(player2);
                        }else if (player1.winCheck() && rollCount1.equals(rollCount2)) {
                           endGame(player1);
                        }else {
                           playerInTurn = 1;
                           dieResult = player2.roll(die);
                           dieResultLB.setText(Integer.toString(dieResult));
                        //Record roll result for the player
                           player2.setRollResult(dieResult);
                        //Set roll result to the text field
                           player2TF[dieResult - 1].setText(Integer.toString(dieResult));
                           player2TF[dieResult - 1].setBackground(Color.GREEN);
                           rollCount2 = Integer.toString(player2.returnCount());
                           rollCountsLB.setText("<html><p align=\"center\">" + 
                              player2Name + " got</p><br><p align=\"center\">Number of rolls</p><p align=\"center\">" + 
                              player1Name + ": " + rollCount1 + "</p><p align=\"center\">" + player2Name + ": " + rollCount2 + 
                              "</p><br></html>");
                        
                        }
                     }                                          
                  }
               }
            }
         );
                  
      //New game button actions
      newGameBT.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  TwoPlayersGamePanel newTwoPlayersGamePanel = new TwoPlayersGamePanel(player1Name, player2Name);
                  removeAll();
                  add(newTwoPlayersGamePanel);  
                  revalidate();
                  repaint();                              
               }
            }
         );
        
   }
   
   //Method to add a component to panel 
   private void addComponent(Component component, int gridx, int gridy, int gridwidth, 
   int gridheight, int inset1, int inset2, int inset3, int inset4) {
      c.gridx = gridx;
      c.gridy = gridy;
      c.gridwidth = gridwidth;
      c.gridheight = gridheight;
      c.insets = new Insets(inset1, inset2, inset3, inset4);
      add(component, c);
   }
   
   //Method to handle game's end when there is a winner 
   private void endGame(Player winner) {
      winnerName = winner.getPlayerName();
      winnerRollCount = Integer.toString(winner.returnCount());
      
      endText = "<html><p align=\"center\">Congratulations, " + winnerName + 
                    "!<br><br>You won after " + winnerRollCount + 
                    " rolls!<br><br>Click \"New game\" button to play again!</p></html>";
      messageLB.setText(endText);
      rollBT.setEnabled(false); 
      newGameBT.setVisible(true);
      rollCountsLB.setText("<html><p align=\"center\">" + 
                           winnerName + " wins!</p><br><p align=\"center\">Number of rolls</p><p align=\"center\">" + 
                           player1Name + ": " + rollCount1 + "</p><p align=\"center\">" + player2Name + ": " + rollCount2 +
                           "</p><br></html>");
      dieResultLB.setVisible(false); 
   }
   
   //Method to handle a draw game
   private void gameDraw() {      
      endText = "<html><p align=\"center\">It's a draw game!<br><br>Click \"New game\" button to play again!</p></html>";
      messageLB.setText(endText);
      rollBT.setEnabled(false); 
      newGameBT.setVisible(true);
      rollCountsLB.setText("<html><p align=\"center\">Draw!</p><br><p align=\"center\">Number of rolls</p><p align=\"center\">" + 
                           player1Name + ": " + rollCount1 + "</p><p align=\"center\">" + player2Name + ": " + rollCount2 +
                           "</p><br></html>");
      dieResultLB.setVisible(false); 
   }
   
   //Method to count the unique numbers
   private int uniqueCount(Player player) {
      int uniqueCount = 0;
      for(int k = 0; k < 6; k++) {
         if (player.gameResult[k] == k + 1) {
            uniqueCount++;
         }
      }      
      return uniqueCount;
   }
   
   /*Test methods
   public static void createAndShowGUI() {
        //Create and set up the window.
      JFrame frame = new JFrame("Unique Six");
      frame.setSize(500, 500);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      TwoPlayersGamePanel firstPanel = new TwoPlayersGamePanel("Test1", "Test2");
        
        //Add contents to the window.
      frame.add(firstPanel);
   
   }

   public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(
         new Runnable() {
            public void run() {
               createAndShowGUI();
            }
         });
   }
   */  
}