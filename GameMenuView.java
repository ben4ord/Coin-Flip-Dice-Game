
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameMenuView {

    protected JFrame gameMenu;
    private JPanel buttons;
    private JButton scoreboard;
    private JButton coinGame;
    private JButton diceGame;
    private JButton exit;
    private Icon StartImage = new ImageIcon("MenuBoat.gif");

    private String menuSong = "menuSong.wav";

    GameMenuView(){
        gameMenu = new JFrame();
        buttons = new JPanel();

        scoreboard = new JButton("Scoreboard");
        scoreboard.setFont(new Font("Poor Richard",Font.BOLD,20));
        coinGame = new JButton("Coin Flip");
        coinGame.setFont(new Font("Poor Richard",Font.BOLD,20));
        diceGame = new JButton("Dice Roll");
        diceGame.setFont(new Font("Poor Richard",Font.BOLD,20));
        exit = new JButton("Exit");
        exit.setFont(new Font("Poor Richard",Font.BOLD,20));

        PlayMusic(menuSong);

        buttons.setLayout(new GridLayout(1,4));
        buttons.add(scoreboard);
        buttons.add(coinGame);
        buttons.add(diceGame);
        buttons.add(exit);
        buttons.setPreferredSize(new Dimension(200,125));
        gameMenu.add(new JLabel(StartImage),BorderLayout.CENTER);
        gameMenu.add(buttons, BorderLayout.SOUTH);


        gameMenu.setSize(600,600);
        gameMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameMenu.setVisible(false);
    }

    private void PlayMusic(String sound) {
        try{
            File musicPath = new File(sound);
            if(musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(10);
            }

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void setScoreboardActionListener(ActionListener aL){
        scoreboard.addActionListener(aL);
    }

    public void setCoinGameActionListener(ActionListener aL){
        coinGame.addActionListener(aL);
    }

    public void setDiceGameActionListener(ActionListener aL) { diceGame.addActionListener(aL);}

    public void setExitActionListener(ActionListener aL){
        exit.addActionListener(aL);
    }
}
