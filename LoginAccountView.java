import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginAccountView {

    protected JFrame loginAccount;
    private JButton loginButton;
    private JButton createAccountMenu;
    protected JTextField username;
    protected JTextField password;
    private JPanel userInput;
    private JPanel buttons;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    LoginAccountView(){
        loginAccount = new JFrame();
        userInput = new JPanel();
        buttons = new JPanel();

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Poor Richard",Font.BOLD,30));
        createAccountMenu = new JButton("Create Account Menu");
        createAccountMenu.setFont(new Font("Poor Richard",Font.BOLD,30));

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
        buttons.add(loginButton);
        buttons.add(createAccountMenu);

        loginAccount.add(userInput, BorderLayout.CENTER);
        loginAccount.add(buttons, BorderLayout.SOUTH);

        loginAccount.setSize(600,600);
        loginAccount.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginAccount.setLocationRelativeTo(null);
        loginAccount.setVisible(true);
    }

    public void setLoginButtonActionListener(ActionListener aL){
        loginButton.addActionListener(aL);
    }

    public void setCreateAccountMenuActionListener(ActionListener aL){
        createAccountMenu.addActionListener(aL);
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPassword() {
        return password;
    }
}
