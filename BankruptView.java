import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BankruptView {
    protected JFrame bankrupt;
    private Icon bankruptImage = new ImageIcon("brokeSponge.gif"); //finish this with the correct jpg file
    private JLabel image;
    private String pirateLaugh = "pirateLaugh.wav";



    BankruptView(){
        bankrupt = new JFrame();
        image = new JLabel(bankruptImage);
        bankrupt.add(image, BorderLayout.CENTER);

        bankrupt.setSize(800,600);
        bankrupt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bankrupt.setVisible(false);
    }
    public void PlayMusic(String sound) {
        try{
            File musicPath = new File(sound);
            if(musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(100);

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
