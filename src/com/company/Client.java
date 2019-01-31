package com.company;

import Logic.GameHelper;
import Logic.RandomGenerator;
import Models.FieldModel;
import Models.PlayerModel;

import java.io.*;
import java.net.*;

public class Client {
    private static boolean isEliminated = false;

    public static void main(String[] args) {

        try {

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket clientSocket = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            PlayerModel player = new PlayerModel(inputStream, outputStream);

            // login and generating board

            //LOGIN
            System.out.println(player.getInputStream().readUTF());
            String toSend = RandomGenerator.generateRandomString();
            player.getOutputStream().writeUTF("LOGIN " + toSend);
            String ID = player.getInputStream().readUTF();
            player.setId(GameHelper.setID(ID));
            System.out.println(ID);

                while (true) {
                    String start;
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
                    FieldModel[][] _board = GameHelper.genereteBoardFromStart(player);
                    String test = "";
                    GameHelper gameHelper = new GameHelper(player);
                    boolean end = false;
                    while (!end) {
                        try {
                            String orderServer = player.getInputStream().readUTF();
                            if (orderServer.startsWith("TWOJ RUCH")) {
                                System.out.println(orderServer);
                                _board = GameHelper.genereteBoardAfterAttack();
                                player.getOutputStream().writeUTF(gameHelper.generateAttack(_board));
                                test = player.getInputStream().readUTF();
                                System.out.println(test);
                                if (test.startsWith("OK")) {
                                    _board = GameHelper.genereteBoardAfterAttack();
                                    orderServer = player.getInputStream().readUTF();
                                    System.out.println(orderServer);
                                }
                            }
                            if (orderServer.startsWith("ATAK")) {
                                System.out.println(orderServer);
                                _board = GameHelper.genereteBoardAfterAttack();
                                orderServer = player.getInputStream().readUTF();
                                System.out.println(orderServer);
                            }


                            if (orderServer.startsWith("KONIEC RUNDY")) {
                                System.out.println(orderServer);
                                end = true;
                            }

                            while (test.startsWith("OK")) {
                                player.getOutputStream().writeUTF(gameHelper.generateAttack(_board));
                                test = player.getInputStream().readUTF();
                                System.out.println(test);
                                if (test.startsWith("PASS")) {
                                    break;
                                }
                                _board = GameHelper.genereteBoardAfterAttack();
                                orderServer = player.getInputStream().readUTF();
                                System.out.println(orderServer);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
//            }
        } catch (ConnectException ce) {
            System.out.println("Nie znaleziono serwera.");
        } catch(SocketException se){
            System.out.println("Serwer przestal dzialac.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
