import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ScoreboardView {

    protected JFrame scoreboard;
    private JPanel top3panel;
    private JPanel menuPanel;
    private JButton menu;
    private JList top3;
    private Icon scoreboardPicture = new ImageIcon("scoreBoard.png");
    private JLabel statusImage;

    ScoreboardView(){
        scoreboard = new JFrame();
        top3panel = new JPanel();
        menuPanel = new JPanel();
        menu = new JButton("Menu");
        menu.setFont(new Font("Poor Richard",Font.BOLD,20));
        top3 = new JList();
        statusImage = new JLabel(scoreboardPicture);


        top3panel.add(top3);
        menuPanel.add(menu);


        scoreboard.add(top3panel, BorderLayout.NORTH);
        scoreboard.add(menuPanel, BorderLayout.SOUTH);
        scoreboard.add(statusImage, BorderLayout.CENTER);


        scoreboard.setSize(1000,900);
        scoreboard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scoreboard.setVisible(false);
    }

    public void setMenuActionListener(ActionListener aL) {
        menu.addActionListener(aL);
    }

    public void setTop3(JList top3) {
        this.top3 = top3;

        this.top3.setFont(new Font("Poor Richard",Font.BOLD,30));
        top3panel.removeAll(); //clear the existing elements
        top3panel.add(top3); //add the new JList

        //call revalidate and repaint to update the layout
        top3panel.revalidate();
        top3panel.repaint();
    }
}
