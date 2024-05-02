
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class DiceGameView {

    protected JFrame diceGame;
    private JComboBox<String> userChoice;
    private JPanel betArea;
    private JPanel playerInfo;
    private JLabel playerName;
    private JLabel playerCurrency;

    private JLabel diceOutcome;
    private Icon sadjack= new ImageIcon("sadJack.gif");

    private Icon dice1 = new ImageIcon("1Dice.jpg");
    private Icon dice2 = new ImageIcon("2Dice.jpg");
    private Icon dice3 = new ImageIcon("3Dice.jpg");
    private Icon dice4 = new ImageIcon("4Dice.jpg");
    private Icon dice5 = new ImageIcon("5Dice.jpg");
    private Icon dice6 = new ImageIcon("6Dice.jpg");
    private Icon laughingPirate = new ImageIcon("laughingPirate.gif");
    private Icon happyCat = new ImageIcon("catpirate.gif");

    private  JLabel statusImage;
    private  JPanel statusArea;

    private JPanel images;
    private JButton betButton = new JButton("Bet");
    private JButton menuButton = new JButton("Menu");
    private JTextField betAmount = new JTextField(9);
    private String[] options = {"1", "2", "3", "4", "5", "6"};


    DiceGameView(){

        diceGame = new JFrame();
        betArea = new JPanel();
        playerInfo = new JPanel();
        userChoice = new JComboBox<String>(options);
        playerCurrency = new JLabel();
        playerName = new JLabel();
        images = new JPanel();
        statusArea = new JPanel();
        diceOutcome = new JLabel(dice1);
        statusImage = new JLabel(happyCat);


        betButton.setFont(new Font("Poor Richard",Font.BOLD,20));
        menuButton.setFont(new Font("Poor Richard",Font.BOLD,20));
        betAmount.setFont(new Font("Poor Richard",Font.BOLD,20));
        userChoice.setFont(new Font("Poor Richard",Font.BOLD,20));
        playerName.setFont(new Font("Poor Richard",Font.BOLD,20));
        playerCurrency.setFont(new Font("Poor Richard",Font.BOLD,20));

        diceGame.add(betArea,BorderLayout.SOUTH);
        diceGame.add(playerInfo,BorderLayout.NORTH);
        diceGame.add(images,BorderLayout.CENTER);

        images.add(statusArea,BorderLayout.WEST);
        statusArea.add(diceOutcome,BorderLayout.EAST);
        statusArea.add(statusImage,BorderLayout.WEST);

        images.setVisible(false);

        betArea.add(betAmount);
        betArea.add(betButton);
        betArea.add(userChoice);
        betArea.add(menuButton);

        playerInfo.add(playerName,FlowLayout.LEFT);
        playerInfo.add(playerCurrency,FlowLayout.CENTER);


        diceGame.setSize(500,400);
        diceGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes view and client connection

        diceGame.setVisible(false);
    }


    void setBetButtonListener(ActionListener aL){
        betButton.addActionListener(aL);
    }

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
            diceOutcome.setVisible(false);
            userChoice.setSelectedIndex(0);
        } catch(NullPointerException e){
            userChoice.setSelectedIndex(0);
        }
    }

    public void setDiceOutcome(String diceNum, String winLoss){
        images.setVisible(false);

        Random rand = new Random();

        Integer number = (rand.nextInt(2) + 1); //random # between 1 and 6 (dice roll)
        if(winLoss.equals("LOSE")){
            statusArea.remove(diceOutcome);
            statusArea.remove(statusImage);
            if(number == 1){
                statusImage= new JLabel(laughingPirate);
            }else{
                statusImage = new JLabel(sadjack);
            }
            switch (diceNum) {
                case "1" -> {
                    diceOutcome = new JLabel(dice1);

                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "2" -> {
                    diceOutcome = new JLabel(dice2);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "3" -> {
                    diceOutcome = new JLabel(dice3);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "4" -> {
                    diceOutcome = new JLabel(dice4);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "5" -> {
                    diceOutcome = new JLabel(dice5);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "6" -> {
                    diceOutcome = new JLabel(dice6);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
            }

            images.setVisible(true);

        }else if(winLoss.equals("WIN")){
            statusArea.remove(diceOutcome);
            statusArea.remove(statusImage);
            statusImage = new JLabel(happyCat);
            switch (diceNum) {
                case "1" -> {
                    diceOutcome = new JLabel(dice1);
                    statusArea.add(diceOutcome, BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "2" -> {
                    diceOutcome = new JLabel(dice2);
                    statusArea.add(diceOutcome, BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "3" -> {
                    diceOutcome = new JLabel(dice3);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "4" -> {
                    diceOutcome = new JLabel(dice4);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "5" -> {
                    diceOutcome = new JLabel(dice5);
                    statusArea.add(diceOutcome,BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
                case "6" -> {
                    diceOutcome = new JLabel(dice6);
                    statusArea.add(diceOutcome, BorderLayout.EAST);
                    statusArea.add(statusImage,BorderLayout.WEST);
                }
            }

            images.setVisible(true);
        }
    }
}
