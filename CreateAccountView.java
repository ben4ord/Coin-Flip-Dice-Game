
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccountView {

    protected JFrame createAccount;
    private JButton createButton;
    private JButton loginMenu;
    protected JTextField username;
    protected JTextField password;
    private JPanel userInput;
    private JPanel buttons;
    private JLabel usernameLabel;
    private JLabel passwordLabel;


    CreateAccountView(){
        createAccount = new JFrame();
        userInput = new JPanel();
        buttons = new JPanel();

        createButton = new JButton("Create Account");
        createButton.setFont(new Font("Poor Richard",Font.BOLD,30));
        loginMenu = new JButton("Login Menu");
        loginMenu.setFont(new Font("Poor Richard",Font.BOLD,30));

        userInput.setLayout(new GridLayout(2,2));
        username = new JTextField(15); //creating the text fields for the name and age
        username.setFont(new Font("Poor Richard",Font.BOLD,30));
        password = new JTextField(15);
        password.setFont(new Font("Poor Richard",Font.BOLD,30));
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Poor Richard",Font.BOLD,30));
        userInput.add(usernameLabel);
        userInput.add(username);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Poor Richard",Font.BOLD,30));
        userInput.add(passwordLabel);
        userInput.add(password);

        buttons.setLayout(new GridLayout(2,1));
        buttons.add(createButton);
        buttons.add(loginMenu);


        createAccount.add(userInput, BorderLayout.CENTER);
        createAccount.add(buttons, BorderLayout.SOUTH);

        createAccount.setSize(600,600);
        createAccount.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createAccount.setVisible(false);
    }

    public void setCreateButtonActionListener(ActionListener aL){
        createButton.addActionListener(aL);
    }

    public void setLoginMenuButtonActionListener(ActionListener aL){
        loginMenu.addActionListener(aL);
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPassword() {
        return password;
    }
}
