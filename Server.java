import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.BufferUnderflowException;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    protected static Socket conn;

    public static void main(String[] args) throws IOException {
        try {
            String IP = "127.0.0.1";
            int port = 5000;

            String serverMessage = "";
            ServerSocket server = new ServerSocket(5000);

            while(true) {

                System.out.println("Server> waiting for client to connect...");
                conn = server.accept();
                System.out.println("Server> client connected!");
                Handler handler = new Handler(conn);

                new Thread(handler).start();
            }

        } catch(IOException e){
            conn.close();
        }
    }
}

class Handler implements Runnable {
    private final Socket socket;

    Handler(Socket socket){

        this.socket = socket;
    }

    public void run() {
        try {
            String serverMessage = "";
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            try {
                while (true) {
                    String clientMSG = reader.readLine();

                    System.out.println("clientMSG: " + clientMSG);

                    String[] words = clientMSG.trim().split(" ");
                    String userName = null;
                    String passwordOrCurrency = null;
                    String userAction = null;
                    if (words.length == 3) {
                        userName = words[0];
                        passwordOrCurrency = words[1];
                        userAction = words[2];
                    } else {
                        userAction = words[0];
                        System.out.println("userAction: " + userAction);
                    }

                    String cmd = null;

                    try {
                        Connection connection = null;
                        String uri = "jdbc:sqlite:CoinFlipUsers.db";
                        connection = DriverManager.getConnection(uri);
                        Statement stm = connection.createStatement();


                        if (userAction.equals("signIn")) {
                            connection = DriverManager.getConnection(uri);
                            cmd = "SELECT userName, password, currency FROM users;";
                            ResultSet rs = connection.createStatement().executeQuery(cmd);
                            while (rs.next()) {

                                String userNameDataBase = rs.getString("userName");
                                String passwordDataBase = rs.getString("password");
                                String userCurrencyDataBase = rs.getString("currency");

                                if (userName.equals(userNameDataBase) && passwordOrCurrency.equals(passwordDataBase)) {
                                    serverMessage = String.format("%s %s %s", userName, passwordOrCurrency, userCurrencyDataBase); //this was in original
                                    break;
                                }else  if(userName.equals(userNameDataBase) && !passwordOrCurrency.equals(passwordDataBase)){
                                    serverMessage = "AccountExist";
                                    break;
                                }else{
                                    serverMessage = "NoAccount";
                                }
                            }
                            stm.execute(cmd);


                        } else if (userAction.equals("createAccount")) {
                            connection = DriverManager.getConnection(uri);
                            String searchCMD = "SELECT userName FROM users;";
                            ResultSet rs = connection.createStatement().executeQuery(searchCMD);
                            while (rs.next()) {

                                String userNameDataBase = rs.getString("userName");

                                if (userName.equals(userNameDataBase)) {

                                    serverMessage = "AccountExist";
                                    break;
                                }
                            }

                            if (!serverMessage.equals("AccountExist")) {

                                cmd = "INSERT INTO users (userName, password, currency) VALUES (?,?,?);";
                                PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                                preparedStatement.setString(1, userName);
                                preparedStatement.setString(2, passwordOrCurrency);
                                preparedStatement.setString(3, "500");
                                preparedStatement.executeUpdate();
                                serverMessage = String.format("%s %s 500", userName, passwordOrCurrency);
                            }


                        } else if (userAction.equals("updateData")) {

                            connection = DriverManager.getConnection(uri);
                            cmd = "UPDATE users SET currency = ? WHERE userName = ?;";
                            PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                            preparedStatement.setString(1, passwordOrCurrency);
                            preparedStatement.setString(2, userName);

                            preparedStatement.executeUpdate();
                            serverMessage = String.format("%s meow %s", userName, passwordOrCurrency);

                        } else if (userAction.equals("updateList")) {

                            connection = DriverManager.getConnection(uri);

                            cmd = "SELECT userName, currency FROM users ORDER BY currency DESC;";
                            ResultSet rs = connection.createStatement().executeQuery(cmd);
                            String users = "";
                            int x = 1;
                            while (rs.next()) {

                                String userNameDataBase = rs.getString("userName");
                                String currencyDataBase = rs.getString("currency");
                                users += (x + ".  " + userNameDataBase + " " + currencyDataBase + ",");
                                x++;
                            }
                            serverMessage = users;
                            stm.execute(cmd);

                        } else if (userAction.equals("updateCurrency")) {

                            connection = DriverManager.getConnection(uri);

                            String searchCMD = "SELECT userName, currency FROM users;";
                            ResultSet rs = connection.createStatement().executeQuery(searchCMD);

                            while (rs.next()) {

                                String userNameDataBase = rs.getString("userName");
                                String currencyDataBase = rs.getString("currency");


                                System.out.println(userNameDataBase + " " + currencyDataBase);
                                if(userNameDataBase.equals(userName)){
                                    serverMessage = String.format("%s %s",userNameDataBase,currencyDataBase);
                                    break;
                                }
                            }
                            stm.execute(searchCMD);

                        }
                        connection.close();

                    } catch (SQLException | NullPointerException ex) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Database error, close app and try again");
                    }

                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                    printWriter.println(serverMessage);
                    printWriter.flush();
                }


            } catch (IOException e) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server> waiting for client to connect...");
        }
    }
}