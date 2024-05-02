

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CoinGameView {


    protected JFrame coinGame;
    private JComboBox<String> userChoice;
    private JPanel betArea;
    private JPanel playerInfo;
    private JLabel playerName;
    private JLabel playerCurrency;
    private JButton betButton = new JButton("Bet");
    private JButton menuButton = new JButton("Menu");
    private JTextField betAmount = new JTextField(9);
    private String[] options = {"Heads","Tails"};
    private  JPanel statusArea;
    private  JLabel statusImage;
    private  JLabel coinOutcome;
    private Icon happyCat = new ImageIcon("catpirate.gif");
    private Icon sadJack = new ImageIcon("sadJack.gif");
    private Icon heads = new ImageIcon("heads.jpg");
    private Icon tails = new ImageIcon("tails.jpg");




    CoinGameView(){

        coinGame = new JFrame();
        betArea = new JPanel();
        playerInfo = new JPanel();
        statusArea = new JPanel();
        userChoice = new JComboBox<String>(options);
        playerCurrency = new JLabel();
        playerName = new JLabel();

        betButton.setFont(new Font("Poor Richard",Font.BOLD,20));
        betAmount.setFont(new Font("Poor Richard",Font.BOLD,20));
        userChoice.setFont(new Font("Poor Richard",Font.BOLD,20));
        menuButton.setFont(new Font("Poor Richard",Font.BOLD,20));
        playerName.setFont(new Font("Poor Richard",Font.BOLD,20));
        playerCurrency.setFont(new Font("Poor Richard",Font.BOLD,20));

        coinGame.add(betArea,BorderLayout.SOUTH);
        coinGame.add(playerInfo,BorderLayout.NORTH);
        coinGame.add(statusArea,BorderLayout.WEST);


        betArea.add(betAmount);
        betArea.add(betButton);
        betArea.add(userChoice);
        betArea.add(menuButton);


        playerInfo.add(playerName,FlowLayout.LEFT);
        playerInfo.add(playerCurrency,FlowLayout.CENTER);


        coinGame.setSize(500,400);

        coinGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes view and client connection

        coinGame.setVisible(false);

    }


    void setBetButtonListener(ActionListener aL){
        betButton.addActionListener(aL);
    }//listener for button

    void setMenuButtonListener(ActionListener aL) { menuButton.addActionListener(aL); }

    public void setPlayerName(String username){
        playerName.setText(username);
    }

    public String getPlayerName(){
        return  playerName.getText().toString();
    }

    public String getBetAmount() {
        return betAmount.getText();
    }

    public void setBetAmount(String amount) {
        betAmount.setText(amount);
    }

    public String getUserChoice() {
        return userChoice.getSelectedItem().toString();
    }

    public void setPlayerCurrency(String updatedAmount){playerCurrency.setText(updatedAmount);}

    public String getPlayerCurrency(){
        return playerCurrency.getText().toString();
    }

    public void resetScreen(){
        try {
            statusArea.setVisible(false);
            coinOutcome.setVisible(false);
            userChoice.setSelectedIndex(0);
        } catch(NullPointerException e){
            userChoice.setSelectedIndex(0);
        }
    }

    public void setStatus(String winLoss){

        if(winLoss.equals("winHeads")){
            coinOutcome = new JLabel(heads);
            statusImage = new JLabel(happyCat);
            statusArea.add(coinOutcome,FlowLayout.LEFT);
            statusArea.add(statusImage,FlowLayout.LEFT);
            statusArea.setVisible(true);
            coinOutcome.setVisible(true);


        }else if(winLoss.equals("winTails")){
            coinOutcome = new JLabel(tails);
            statusImage = new JLabel(happyCat);
            statusArea.add(coinOutcome,FlowLayout.LEFT);
            statusArea.add(statusImage,FlowLayout.LEFT);
            statusArea.setVisible(true);
            coinOutcome.setVisible(true);


        }else if(winLoss.equals("lossTails")){
            coinOutcome = new JLabel(tails);
            statusImage = new JLabel(sadJack);
            statusArea.add(coinOutcome,FlowLayout.LEFT);
            statusArea.add(statusImage,FlowLayout.LEFT);
            statusArea.setVisible(true);
            coinOutcome.setVisible(true);


        }else if(winLoss.equals("lossHeads")){
            coinOutcome = new JLabel(heads);
            statusImage = new JLabel(sadJack);
            statusArea.add(coinOutcome,FlowLayout.LEFT);
            statusArea.add(statusImage,FlowLayout.LEFT);
            statusArea.setVisible(true);
            coinOutcome.setVisible(true);
        }
    }
}
