package Models;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayerModel
{
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private int Id;
    private List<Point> myField = new ArrayList<>();

    public PlayerModel(DataInputStream inputStream, DataOutputStream outputStream)
    {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }


    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Point> getMyField() {
        return myField;
    }

    public void setMyField(List<Point> myField) {
        this.myField = myField;
    }
}
