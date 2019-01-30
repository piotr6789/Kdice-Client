package com.company;

import Logic.GameLogic;
import Logic.RandomGenerator;
import Models.FieldModel;
import Models.PlayerModel;

import java.io.*;
import java.net.*;

public class Client {
    private static  FieldModel[][] _board = new FieldModel[5][5];
    public static String orderServer = "6786786734";
    public static int counter = 0;
    public static int gameCounter = 0;
    public static boolean isEliminated = false;

    public static void main(String[] args) {

        try {

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket clientSocket = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            PlayerModel player = new PlayerModel(inputStream, outputStream, clientSocket);

            // login and generating board

            //LOGIN
            System.out.println(player.getInputStream().readUTF());
            String toSend = RandomGenerator.generateRandomString();
            player.setLogin(toSend);
            player.getOutputStream().writeUTF("LOGIN " + toSend);
            String ID = player.getInputStream().readUTF();
            player.setId(GameLogic.setID(ID));
            System.out.println(ID);

            while(true){
                gameCounter++;
                counter = 0;
                String start = "";

                while (true) {
                    start = player.getInputStream().readUTF();
                    if(start.startsWith("WYNIK")){
                        System.out.print(start);
                        return;
                    }
                    if(start.startsWith("ELIMINACJA")){
                        isEliminated = true;
                        continue;
                    }
                    if(start.startsWith("KONIEC RUNDY") && !isEliminated){
                        System.out.println(start);
                        isEliminated = false;
                        continue;
                    }
                    else if(start.startsWith("KONIEC RUNDY") && isEliminated){
                        isEliminated = false;
                        continue;
                    }
                    if(start.startsWith("TURA")){
                        System.out.println(start);
                        continue;
                    }
                    //GENERATE BOARD FROM 25xCOMMAND
                    _board = GameLogic.genereteBoardFromStart(player);
                    String test = "";
                    GameLogic gameLogic = new GameLogic(_board, player);
                    boolean end = false;
                    while (!end) {
                        try {
                            orderServer = player.getInputStream().readUTF();
                            if (orderServer.equals("TWOJ RUCH")) {
                                System.out.println(orderServer);
                                _board = GameLogic.genereteBoardAfterAttack();
                                player.getOutputStream().writeUTF(gameLogic.generateAttack(_board));
                                test = player.getInputStream().readUTF();
                                System.out.println(test);
                                if (test.equals("OK")) {
                                    _board = gameLogic.genereteBoardAfterAttack();
                                    orderServer = player.getInputStream().readUTF();
                                    System.out.println(orderServer);
                                }
                            }
                            if (orderServer.charAt(0) == 'A') {
                                System.out.println(orderServer);
                                _board = gameLogic.genereteBoardAfterAttack();
                                orderServer = player.getInputStream().readUTF();
                                System.out.println(orderServer);
                            }


                            if (orderServer.startsWith("KONIEC RUNDY")) {
                                System.out.println(orderServer);
                                end = true;
                            }

                            while (test.equals("OK")) {
                                player.getOutputStream().writeUTF(gameLogic.generateAttack(_board));
                                test = player.getInputStream().readUTF();
                                System.out.println(test);
                                if (test.equals("PASS")) {
                                    break;
                                }
                                _board = gameLogic.genereteBoardAfterAttack();
                                orderServer = player.getInputStream().readUTF();
                                System.out.println(orderServer);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    counter++;
                }
            }
        } catch (ConnectException ce) {
            System.out.println("Nie znaleziono serwera.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
