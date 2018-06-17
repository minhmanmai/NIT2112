/*
||*****************************************************************************||
||                                                                             ||
||                   NIT2112 Object Oriented Programming                       ||
||                William Oung(s4578850) & Minh Mai(s4554511)                  ||
||                           Unique Six Assignment                             ||
||                                11-May-2018                                  ||
||                                                                             ||
||       This class is used to create GUI panel in which user can choose       ||
||                           the number of players.                            ||
||                                                                             ||                               
||*****************************************************************************||
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SixNumbersPanel2 extends JPanel{

   private JLabel numOfPlayerLB, validationMsg;
   private JButton startGame;
   public static int numOfPlayer;
   
   //Constructor
   public SixNumbersPanel2() {
      super(new GridBagLayout());
      //Initialize objects
      ButtonGroup buttonGroup = new ButtonGroup();   
      OnePlayerPanel onePlayerPanel = new OnePlayerPanel();
      TwoPlayersPanel twoPlayersPanel = new TwoPlayersPanel();
      
      //Configure validation message
      validationMsg = new JLabel("", SwingConstants.CENTER);
      validationMsg.setForeground(Color.red);
      
      //Ask for the number of players
      numOfPlayerLB = new JLabel("Please select a number of players:");
      addComponent(numOfPlayerLB, 0, 0, 0, 0, 10, 10, 10, 10);
      
      //Toggle buttons - behave like radio buttons, only one option is selected at a time
      JToggleButton button1 = new JToggleButton(Integer.toString(1));
      addComponent(button1, 0, 1, 50, 10, 2, 2, 2, 2);
      buttonGroup.add(button1);
      button1.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {               
                  setNumOfPlayer(1);
                  remove(twoPlayersPanel);
                  remove(validationMsg);
                  addComponent(onePlayerPanel, 0, 3, 0, 0, 10, 10, 10, 10);
                  addComponent(startGame, 0, 10, 0, 0, 10, 10, 10, 10);    
                  revalidate();
                  repaint();
               }
            }
         );
      // Second toggle button 
      JToggleButton button2 = new JToggleButton(Integer.toString(2));
      addComponent(button2, 0, 2, 50, 10, 2, 2, 2, 2);
      buttonGroup.add(button2);
      button2.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setNumOfPlayer(2);
                  remove(onePlayerPanel);
                  remove(validationMsg);
                  addComponent(twoPlayersPanel, 0, 3, 0, 0, 10, 10, 10, 10);
                  addComponent(startGame, 0, 10, 0, 0, 10, 10, 10, 10); 
                  revalidate();
                  repaint();
               }
            }
         );      
      
      //Start game button and its behaviours
      startGame = new JButton("Start!");
      startGame.addActionListener(
                     new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                           //Validate user input and start game
                           if (SixNumbersPanel2.numOfPlayer == 1) {
                              if (onePlayerPanel.getPlayerName().isEmpty()) {
                                 validationMsg.setText("<html><div style='text-align: center;'>Please enter your name</div></html>");
                                 addComponent(validationMsg, 0, 5, 0, 0, 2, 2, 2, 2);
                              } else {
                                 removeAll();
                                 OnePlayerGamePanel gamePanel = new OnePlayerGamePanel(onePlayerPanel.getPlayerName());
                                 add(gamePanel);
                              }
                           } else if (SixNumbersPanel2.numOfPlayer == 2 ) {
                              if (twoPlayersPanel.getPlayer1Name().isEmpty() && twoPlayersPanel.getPlayer2Name().isEmpty()) {
                                 validationMsg.setText("<html><div style='text-align: center;'>Please enter the players' names</div></html>");
                                 addComponent(validationMsg, 0, 5, 0, 0, 2, 2, 2, 2);
                              }else if (twoPlayersPanel.getPlayer2Name().isEmpty() && !twoPlayersPanel.getPlayer1Name().isEmpty()) {
                                 validationMsg.setText("<html><div style='text-align: center;'>Please enter the other player's name</div></html>");
                                 addComponent(validationMsg, 0, 5, 0, 0, 2, 2, 2, 2);
                              }else if (twoPlayersPanel.getPlayer1Name().isEmpty() && !twoPlayersPanel.getPlayer2Name().isEmpty()) {
                                 validationMsg.setText("<html><div style='text-align: center;'>Please enter your name</div></html>");
                                 addComponent(validationMsg, 0, 5, 0, 0, 2, 2, 2, 2);
                              } else {
                                 removeAll();
                                 TwoPlayersGamePanel gamePanel = new TwoPlayersGamePanel(twoPlayersPanel.getPlayer1Name(), twoPlayersPanel.getPlayer2Name());
                                 add(gamePanel);
                              }
                           }                           
                           //Refresh the panel
                           revalidate();
                           repaint();
                        }
                     });
   
   }
   //Method to set number of players
   public void setNumOfPlayer(int num) {
      numOfPlayer = num;
   }
   
   //Method to add a component to panel 
   private void addComponent(Component component, int gridx, int gridy, int ipadx, int ipady, 
   int inset1, int inset2, int inset3, int inset4) {
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = gridx;
      c.gridy = gridy;
      c.ipadx = ipadx;
      c.ipady = ipady;
      c.insets = new Insets(inset1, inset2, inset3, inset4);
      add(component, c);
   }
   
   /*Test methods
   public static void createAndShowGUI() {
        //Create and set up the window.
      JFrame frame = new JFrame("Unique Six");
      frame.setVisible(true);
      frame.setSize(500, 500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SixNumbersPanel2 sixNumbersPanel2 = new SixNumbersPanel2();
   
        //Add contents to the window.
      frame.add(sixNumbersPanel2);
   
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