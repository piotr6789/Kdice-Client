package Models;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerModel
{
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket clientSocket;
    private int Id;
    private String login;
    private List<Point> myField = new ArrayList<>();

    public PlayerModel(DataInputStream inputStream, DataOutputStream outputStream, Socket clientSocket)
    {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.clientSocket = clientSocket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Point> getMyField() {
        return myField;
    }

    public void setMyField(List<Point> myField) {
        this.myField = myField;
    }
}
