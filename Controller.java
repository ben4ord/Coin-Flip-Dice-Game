
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller {

    private Model model = new Model();
    private CreateAccountView createAccountView = new CreateAccountView();
    private LoginAccountView loginAccountView = new LoginAccountView();
    private CoinGameView coinGameView = new CoinGameView();
    private DiceGameView diceGameView = new DiceGameView();
    private GameMenuView gameMenuView = new GameMenuView();
    private ScoreboardView scoreboardView = new ScoreboardView();
    private BankruptView bankruptView = new BankruptView();

    private static String userName = "";

    private String cannon = "cannon.wav";
    private String cha_Ching = "cha-ching.wav";
    private String pirateLaugh = "pirateLaugh.wav";


    Controller(){
        createAccountView.setCreateButtonActionListener(new createButtonActionListener());
        createAccountView.setLoginMenuButtonActionListener(new loginMenuButtonActionListener());
        loginAccountView.setLoginButtonActionListener(new loginButtonActionListener());
        loginAccountView.setCreateAccountMenuActionListener(new createAccountMenuActionListener());
        gameMenuView.setExitActionListener(new exitActionListener());
        gameMenuView.setCoinGameActionListener(new coinGameActionListener());
        gameMenuView.setDiceGameActionListener(new diceGameActionListener());
        gameMenuView.setScoreboardActionListener(new scoreboardActionListener());
        scoreboardView.setMenuActionListener(new menuActionListener_scoreboard());
        coinGameView.setMenuButtonListener(new menuActionListener_coinGame());
        coinGameView.setBetButtonListener(new betButtonActionListener_coinGame());
        diceGameView.setMenuButtonListener(new menuActionListener_diceGame());
        diceGameView.setBetButtonListener(new betButtonActionListener_diceGame());

    }


    class createButtonActionListener implements ActionListener { //create button action listener
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            String username = createAccountView.getUsername().getText();
            String password = createAccountView.getPassword().getText();


            if(username.isEmpty() | password.isEmpty()){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Must Enter Username or Password");
                createAccountView.getUsername().setText("");
                createAccountView.getPassword().setText("");
            }
            else{

                coinGameView.setPlayerName(username);
                coinGameView.setPlayerCurrency("500");
                diceGameView.setPlayerName(username);
                diceGameView.setPlayerCurrency("500");
                userName = username;
                String serverAccept = model.clientCreateAccount(username,password);
                if(serverAccept != null){
                    if(serverAccept.equals("AccountExist")){
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Username already exists, please login or change username");
                        createAccountView.getUsername().setText("");
                        createAccountView.getPassword().setText("");
                    }
                    else {
                        gameMenuView.gameMenu.setLocationRelativeTo(createAccountView.createAccount);
                        createAccountView.createAccount.setVisible(false);
                        gameMenuView.gameMenu.setVisible(true);
                        createAccountView.getUsername().setText("");
                        createAccountView.getPassword().setText("");
                    }
                }
            }
        }
    }

    class createAccountMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            loginAccountView.getUsername().setText("");
            loginAccountView.getPassword().setText("");

            createAccountView.createAccount.setLocationRelativeTo(loginAccountView.loginAccount);
            loginAccountView.loginAccount.setVisible(false);
            createAccountView.createAccount.setVisible(true);
        }
    }

    class loginMenuButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            createAccountView.getUsername().setText("");
            createAccountView.getPassword().setText("");

            loginAccountView.loginAccount.setLocationRelativeTo(createAccountView.createAccount);
            createAccountView.createAccount.setVisible(false);
            loginAccountView.loginAccount.setVisible(true);
        }

    }

    class loginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            String username = loginAccountView.getUsername().getText();
            String password = loginAccountView.getPassword().getText();

            if(username.isEmpty() | password.isEmpty()){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Must Enter Username or Password");
                loginAccountView.getUsername().setText("");
                loginAccountView.getPassword().setText("");
            }
            else{
                try {
                    coinGameView.setPlayerName(username);
                    diceGameView.setPlayerName(username);
                    userName = username;
                    String serverAccept = model.clientSignIn(username, password);

                    if(serverAccept != null) {
                        if (serverAccept.equals("AccountExist")) {
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Username or password incorrect");
                            loginAccountView.getUsername().setText("");
                            loginAccountView.getPassword().setText("");
                        }else if(serverAccept.equals("NoAccount")){
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Account Doesn't exist, please create account");
                            loginAccountView.getUsername().setText("");
                            loginAccountView.getPassword().setText("");

                        } else {
                            String[] words = serverAccept.split(" ");
                            String playerCurrency = words[2];


                            if (Integer.parseInt(playerCurrency) == 0) {
                                JFrame error = new JFrame();
                                JOptionPane.showMessageDialog(error, "Out of money, create new account!");

                                bankruptView.bankrupt.setLocationRelativeTo(loginAccountView.loginAccount);
                                bankruptView.PlayMusic(pirateLaugh);
                                loginAccountView.loginAccount.setVisible(false);
                                bankruptView.bankrupt.setVisible(true);
                            } else {
                                coinGameView.setPlayerCurrency(playerCurrency);
                                diceGameView.setPlayerCurrency(playerCurrency);

                                gameMenuView.gameMenu.setLocationRelativeTo(loginAccountView.loginAccount);
                                loginAccountView.loginAccount.setVisible(false);
                                gameMenuView.gameMenu.setVisible(true);
                                loginAccountView.getUsername().setText("");
                                loginAccountView.getPassword().setText("");

                            }
                        }
                    }

                } catch (NullPointerException ex){

                }
            }
        }
    }


    class exitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            gameMenuView.gameMenu.setVisible(false);
            System.exit(0);
        }

    }

    class coinGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            coinGameView.coinGame.setLocationRelativeTo(gameMenuView.gameMenu);
            gameMenuView.gameMenu.setVisible(false);

            String serverMessage = model.clientGetCurrency(userName);
            String[] words = serverMessage.split(" ");
            String currency = words[1];

            coinGameView.setPlayerCurrency(currency);
            coinGameView.coinGame.setVisible(true);
        }

    }

    class diceGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            diceGameView.diceGame.setLocationRelativeTo(gameMenuView.gameMenu);
            gameMenuView.gameMenu.setVisible(false);

            String serverMessage = model.clientGetCurrency(userName);
            String[] words = serverMessage.split(" ");
            String currency = words[1];

            diceGameView.setPlayerCurrency(currency);

            diceGameView.diceGame.setVisible(true);
        }
    }

    class scoreboardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            scoreboardView.scoreboard.setLocationRelativeTo(gameMenuView.gameMenu);
            gameMenuView.gameMenu.setVisible(false);

            String fullList = model.clientUpdateList();

            List<String> myList = List.of(fullList.split(","));
            DefaultListModel<String> top3Model = new DefaultListModel<>();

            for (int i = 0; i < Math.min(3, myList.size()); i++) {
                top3Model.addElement(myList.get(i));
            }


            JList<String> list = new JList<>(top3Model);
            scoreboardView.setTop3(list);

            scoreboardView.scoreboard.setVisible(true);
        }

    }

    class menuActionListener_scoreboard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            gameMenuView.gameMenu.setLocationRelativeTo(scoreboardView.scoreboard);
            scoreboardView.scoreboard.setVisible(false);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class menuActionListener_coinGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            coinGameView.setBetAmount("");
            coinGameView.resetScreen();
            gameMenuView.gameMenu.setLocationRelativeTo(coinGameView.coinGame);
            coinGameView.coinGame.setVisible(false);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class betButtonActionListener_coinGame implements ActionListener { //function for sending the data to model after bet button press
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            String username = coinGameView.getPlayerName();
            String currency = coinGameView.getPlayerCurrency();
            String betAmount = coinGameView.getBetAmount();
            String serverAccept = null;

            String choice = coinGameView.getUserChoice().toString();
            String headsTails = null;
            Boolean win = false;

            try {
                if (Integer.parseInt(currency) <= 0) {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Out of money, create new account!");
                    coinGameView.setBetAmount("");
                    bankruptView.PlayMusic(pirateLaugh);

                    bankruptView.bankrupt.setLocationRelativeTo(coinGameView.coinGame);
                    coinGameView.coinGame.setVisible(false);
                    bankruptView.bankrupt.setVisible(true);

                } else if (Integer.parseInt(betAmount) > Integer.parseInt(currency)) {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Bet amount too large!");
                    coinGameView.setBetAmount("");
                }
                else {

                    double random = Math.random();
                    if (random < .5) {
                        headsTails = "Heads";
                    } else {
                        headsTails = "Tails";
                    }

                    if (choice.equals(headsTails)) {
                        win = true;
                        PlayMusic(cha_Ching);
                        try {
                            int winAmount = Integer.parseInt(currency) + (Integer.parseInt(betAmount)); //maybe make this * 2
                            serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                        } catch (NumberFormatException ex) {
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                            coinGameView.setBetAmount("");
                        }
                    } else {
                        try {
                            PlayMusic(pirateLaugh);
                            int winAmount = Integer.parseInt(currency) - Integer.parseInt(betAmount);
                            serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                        } catch (NumberFormatException ex) {
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                            coinGameView.setBetAmount("");
                        }
                    }

                    if (win.equals(true) && headsTails.equals("Heads")) {
                        coinGameView.setStatus("winHeads");
                    } else if (win.equals(true) && headsTails.equals("Tails")) {
                        coinGameView.setStatus("winTails");
                    } else if (win.equals(false) && headsTails.equals("Heads")) {
                        coinGameView.setStatus("lossHeads");
                    } else if (win.equals(false) && headsTails.equals("Tails")) {
                        coinGameView.setStatus("lossTails");
                    }
                }
            } catch(NumberFormatException ex){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                coinGameView.setBetAmount("");
            }
                try {
                    String[] words = serverAccept.split(" ");
                    String updatedCurrency = words[2];
                    coinGameView.setPlayerCurrency(updatedCurrency);
                    diceGameView.setPlayerCurrency(updatedCurrency);
                } catch (NullPointerException ex) {

                }
            }
        }


    class menuActionListener_diceGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            diceGameView.setBetAmount("");
           // diceGameView.resetScreen();
            gameMenuView.gameMenu.setLocationRelativeTo(diceGameView.diceGame);
            diceGameView.diceGame.setVisible(false);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class betButtonActionListener_diceGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayMusic(cannon);
            String username = diceGameView.getPlayerName();
            String currency = diceGameView.getPlayerCurrency();
            String betAmount = diceGameView.getBetAmount();
            String serverAccept = null;

            String choice = diceGameView.getUserChoice().toString();

            try {
                if (Integer.parseInt(currency) <= 0) {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Out of money, create new account!");
                    bankruptView.PlayMusic(pirateLaugh);

                    diceGameView.setBetAmount("");

                    bankruptView.bankrupt.setLocationRelativeTo(diceGameView.diceGame);
                    diceGameView.diceGame.setVisible(false);
                    bankruptView.bankrupt.setVisible(true);

                } else if (Integer.parseInt(betAmount) > Integer.parseInt(currency)) {
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Bet amount too large!");
                    diceGameView.setBetAmount("");
                } else {

                    Boolean win = false;
                    Random rand = new Random();
                    Integer number = (rand.nextInt(6) + 1); //random # between 1 and 6 (dice roll)
                    System.out.println(number);


                    if (choice.equals(number.toString())) {
                        win = true;
                        PlayMusic(cha_Ching);
                        try {
                            int winAmount = Integer.parseInt(currency) + (Integer.parseInt(betAmount) * 6);
                            serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                            diceGameView.setDiceOutcome(number.toString(), "WIN");
                        } catch (NumberFormatException ex) {
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                            diceGameView.setBetAmount("");
                        }
                    } else {
                        try {
                            PlayMusic(pirateLaugh);
                            int winAmount = Integer.parseInt(currency) - Integer.parseInt(betAmount);
                            serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                            diceGameView.setDiceOutcome(number.toString(), "LOSE");
                        } catch (NumberFormatException ex) {
                            JFrame error = new JFrame();
                            JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                            diceGameView.setBetAmount("");
                        }
                    }
                }
            } catch(NumberFormatException ex){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                diceGameView.setBetAmount("");
            }
            try {
                String[] words = serverAccept.split(" ");
                String updatedCurrency = words[2];
                diceGameView.setPlayerCurrency(updatedCurrency);
                coinGameView.setPlayerCurrency(updatedCurrency);
            } catch (NullPointerException ex){

            }
        }
    }

    private void PlayMusic(String sound) {
        try{
            File musicPath = new File(sound);
            if(musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            }

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }


    }
}
