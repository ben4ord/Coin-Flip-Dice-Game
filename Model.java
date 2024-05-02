
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Model {
    protected Socket chat;
    protected String serverMsg;
    String IP = "127.0.0.1";
    int port = 5000;

    Model(){
        try {
            chat = new Socket(IP, port);
        }catch(IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Server couldn't connect, close app and try again");
        }
    }

    public String clientSignIn(String userName, String password){
        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String clientInfo = String.format("%s %s signIn", userName, password);
            writer.println(clientInfo);
            writer.flush();

            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();

        }catch (IOException | NullPointerException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Couldn't connect to database, close app and try again");
            return null;
        }

        return serverMsg;
    }
    public String clientCreateAccount(String userName, String password){
        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String clientInfo = String.format("%s %s createAccount", userName, password);
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();

        }catch (IOException | NullPointerException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Couldn't connect to database, close app and try again");
            return null;
        }

        return serverMsg;
    }
    public String clientUpdateData(String userName,String amount){
        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String clientInfo = String.format("%s %s updateData", userName, amount);
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();

        }catch (IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Error updating data, try again");
            return null;
        }

        return serverMsg;
    }

    public String clientUpdateList(){
        try {

            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String clientInfo = "updateList";
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
        } catch(IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Error updating list, try again");
            return null;
        }

        return serverMsg;
    }

    public String clientGetCurrency(String userName){

        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String clientInfo = String.format("%s 0 updateCurrency",userName);
            writer.println(clientInfo);
            writer.flush();

            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
        } catch(IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Error updating currency");
            return null;
        }

        System.out.println("CLIENT serverMessage: " + serverMsg);
        return serverMsg;
    }
}
