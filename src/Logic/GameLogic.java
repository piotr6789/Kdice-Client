package Logic;

import Models.FieldModel;
import Models.PlayerModel;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogic
{
    private static FieldModel[][] board;
    private static PlayerModel player;

    public GameLogic(FieldModel[][] board, PlayerModel player){
        this.board = board;
        this.player = player;
    }

    public static FieldModel[][] genereteBoardFromStart(PlayerModel player) throws IOException {

        String board = " ";
        int start = 2;
        int end = 17;
        for (int i = 0; i < 25; i++) {
            board = board + " " + player.getInputStream().readUTF();
            System.out.println(board.substring(start,end));
            start = start + 16;
            end = end + 16;
        }


        FieldModel[][] _board = new FieldModel[5][5];
        for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 5; j++) {
                _board[i][j] = new FieldModel();
             }
        }
        String replaceString = board.replaceAll("\\s","");
        String finalString = replaceString.replaceAll("[^0-9.]", "");

        _board[0][0].set_ownerId(Character.getNumericValue(finalString.charAt(2)));
        _board[0][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(3)));
        _board[0][1].set_ownerId(Character.getNumericValue(finalString.charAt(6)));
        _board[0][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(7)));
        _board[0][2].set_ownerId(Character.getNumericValue(finalString.charAt(10)));
        _board[0][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(11)));
        _board[0][3].set_ownerId(Character.getNumericValue(finalString.charAt(14)));
        _board[0][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(15)));
        _board[0][4].set_ownerId(Character.getNumericValue(finalString.charAt(18)));
        _board[0][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(19)));
        _board[1][0].set_ownerId(Character.getNumericValue(finalString.charAt(22)));
        _board[1][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(23)));
        _board[1][1].set_ownerId(Character.getNumericValue(finalString.charAt(26)));
        _board[1][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(27)));
        _board[1][2].set_ownerId(Character.getNumericValue(finalString.charAt(30)));
        _board[1][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(31)));
        _board[1][3].set_ownerId(Character.getNumericValue(finalString.charAt(34)));
        _board[1][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(35)));
        _board[1][4].set_ownerId(Character.getNumericValue(finalString.charAt(38)));
        _board[1][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(39)));
        _board[2][0].set_ownerId(Character.getNumericValue(finalString.charAt(42)));
        _board[2][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(43)));
        _board[2][1].set_ownerId(Character.getNumericValue(finalString.charAt(46)));
        _board[2][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(47)));
        _board[2][2].set_ownerId(Character.getNumericValue(finalString.charAt(50)));
        _board[2][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(51)));
        _board[2][3].set_ownerId(Character.getNumericValue(finalString.charAt(54)));
        _board[2][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(55)));
        _board[2][4].set_ownerId(Character.getNumericValue(finalString.charAt(58)));
        _board[2][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(59)));
        _board[3][0].set_ownerId(Character.getNumericValue(finalString.charAt(62)));
        _board[3][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(63)));
        _board[3][1].set_ownerId(Character.getNumericValue(finalString.charAt(66)));
        _board[3][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(67)));
        _board[3][2].set_ownerId(Character.getNumericValue(finalString.charAt(70)));
        _board[3][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(71)));
        _board[3][3].set_ownerId(Character.getNumericValue(finalString.charAt(74)));
        _board[3][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(75)));
        _board[3][4].set_ownerId(Character.getNumericValue(finalString.charAt(78)));
        _board[3][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(79)));
        _board[4][0].set_ownerId(Character.getNumericValue(finalString.charAt(82)));
        _board[4][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(83)));
        _board[4][1].set_ownerId(Character.getNumericValue(finalString.charAt(86)));
        _board[4][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(87)));
        _board[4][2].set_ownerId(Character.getNumericValue(finalString.charAt(90)));
        _board[4][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(91)));
        _board[4][3].set_ownerId(Character.getNumericValue(finalString.charAt(94)));
        _board[4][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(95)));
        _board[4][4].set_ownerId(Character.getNumericValue(finalString.charAt(98)));
        _board[4][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(99)));

        return _board;
    }

    public static int setID(String ID)
    {
        String newString = ID.replaceAll("\\s","");

        return Character.getNumericValue(newString.charAt(5));
    }


    public String generateAttack(FieldModel[][] board) {
        getFields(board, player);
        String attackCommand = "ATAK ";
        int numberOfFields = player.getMyField().size();
        for (int i = 0; i < numberOfFields; i++) {
            if (player.getMyField().get(i).x == 0 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1) {
                if (board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x + 1) + " " + player.getMyField().get(i).y;
                }
                if (player.getMyField().get(i).y == 0 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 4 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                }
                if (player.getMyField().get(i).y == 2 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 1 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 3 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
            }



            else if (player.getMyField().get(i).x == 4 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1) {
                if (board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x - 1) + " " + player.getMyField().get(i).y;
                }
                if (player.getMyField().get(i).y == 0 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 4 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                }
                if (player.getMyField().get(i).y == 2 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 1 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 3 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
            }





            else if (player.getMyField().get(i).x == 3 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1) {
                if (board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x - 1) + " " + player.getMyField().get(i).y;
                } if (board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x + 1) + " " + player.getMyField().get(i).y;
                }
                if (player.getMyField().get(i).y == 0 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 4 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                }
                if (player.getMyField().get(i).y == 2 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 1 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 3 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
            }





            else if (player.getMyField().get(i).x == 2 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1) {
                if (board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x - 1) + " " + player.getMyField().get(i).y;
                } if (board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x + 1) + " " + player.getMyField().get(i).y;
                }
                if (player.getMyField().get(i).y == 0 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 4 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                }
                if (player.getMyField().get(i).y == 2 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 1 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 3 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
            }




            else if (player.getMyField().get(i).x == 1 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1) {
                if (board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x - 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x - 1) + " " + player.getMyField().get(i).y;
                } if (board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_ownerId() != player.getId()
                        && board[player.getMyField().get(i).x + 1][player.getMyField().get(i).y].get_cubesNumber()
                        <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()) {
                    return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x + 1) + " " + player.getMyField().get(i).y;
                }
                if (player.getMyField().get(i).y == 0 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 4 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                }
                if (player.getMyField().get(i).y == 2 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 1 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
                if (player.getMyField().get(i).y == 3 && board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber() > 1){
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y - 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y - 1);
                    }
                    if(board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_ownerId() != player.getId()
                            && board[player.getMyField().get(i).x][player.getMyField().get(i).y + 1].get_cubesNumber()
                            <= board[player.getMyField().get(i).x][player.getMyField().get(i).y].get_cubesNumber()){
                        return attackCommand + player.getMyField().get(i).x + " " + player.getMyField().get(i).y + " " + (player.getMyField().get(i).x) + " " + (player.getMyField().get(i).y + 1);
                    }
                }
            }
        }
        return "PASS";
    }

    private static void getFields(FieldModel[][] board, PlayerModel player)
    {
        set_player(player);
        List<Point> myField = new ArrayList<>();
        player.setMyField(myField);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(board[i][j].get_ownerId() == player.getId()){
                    player.getMyField().add(new Point(i, j));
                }
            }
        }
    }

    public static void printBoard() throws IOException {
        String board = " ";
        int start = 2;
        int end = 17;
        for (int i = 0; i < 25; i++) {
            board = board + " " + player.getInputStream().readUTF();
            System.out.println(board.substring(start,end));
            start = start + 16;
            end = end + 16;
        }
    }

    public static FieldModel[][] genereteBoardAfterAttack() throws IOException {

        String board = " ";
        int start = 2;
        int end = 17;
        for (int i = 0; i < 25; i++) {
            board = board + " " + player.getInputStream().readUTF();
            start = start + 16;
            end = end + 16;
        }


        FieldModel[][] _board = new FieldModel[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                _board[i][j] = new FieldModel();
            }
        }
        String replaceString = board.replaceAll("\\s","");
        String finalString = replaceString.replaceAll("[^0-9.]", "");

        _board[0][0].set_ownerId(Character.getNumericValue(finalString.charAt(2)));
        _board[0][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(3)));
        _board[0][1].set_ownerId(Character.getNumericValue(finalString.charAt(6)));
        _board[0][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(7)));
        _board[0][2].set_ownerId(Character.getNumericValue(finalString.charAt(10)));
        _board[0][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(11)));
        _board[0][3].set_ownerId(Character.getNumericValue(finalString.charAt(14)));
        _board[0][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(15)));
        _board[0][4].set_ownerId(Character.getNumericValue(finalString.charAt(18)));
        _board[0][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(19)));
        _board[1][0].set_ownerId(Character.getNumericValue(finalString.charAt(22)));
        _board[1][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(23)));
        _board[1][1].set_ownerId(Character.getNumericValue(finalString.charAt(26)));
        _board[1][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(27)));
        _board[1][2].set_ownerId(Character.getNumericValue(finalString.charAt(30)));
        _board[1][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(31)));
        _board[1][3].set_ownerId(Character.getNumericValue(finalString.charAt(34)));
        _board[1][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(35)));
        _board[1][4].set_ownerId(Character.getNumericValue(finalString.charAt(38)));
        _board[1][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(39)));
        _board[2][0].set_ownerId(Character.getNumericValue(finalString.charAt(42)));
        _board[2][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(43)));
        _board[2][1].set_ownerId(Character.getNumericValue(finalString.charAt(46)));
        _board[2][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(47)));
        _board[2][2].set_ownerId(Character.getNumericValue(finalString.charAt(50)));
        _board[2][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(51)));
        _board[2][3].set_ownerId(Character.getNumericValue(finalString.charAt(54)));
        _board[2][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(55)));
        _board[2][4].set_ownerId(Character.getNumericValue(finalString.charAt(58)));
        _board[2][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(59)));
        _board[3][0].set_ownerId(Character.getNumericValue(finalString.charAt(62)));
        _board[3][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(63)));
        _board[3][1].set_ownerId(Character.getNumericValue(finalString.charAt(66)));
        _board[3][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(67)));
        _board[3][2].set_ownerId(Character.getNumericValue(finalString.charAt(70)));
        _board[3][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(71)));
        _board[3][3].set_ownerId(Character.getNumericValue(finalString.charAt(74)));
        _board[3][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(75)));
        _board[3][4].set_ownerId(Character.getNumericValue(finalString.charAt(78)));
        _board[3][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(79)));
        _board[4][0].set_ownerId(Character.getNumericValue(finalString.charAt(82)));
        _board[4][0].set_cubesNumber(Character.getNumericValue(finalString.charAt(83)));
        _board[4][1].set_ownerId(Character.getNumericValue(finalString.charAt(86)));
        _board[4][1].set_cubesNumber(Character.getNumericValue(finalString.charAt(87)));
        _board[4][2].set_ownerId(Character.getNumericValue(finalString.charAt(90)));
        _board[4][2].set_cubesNumber(Character.getNumericValue(finalString.charAt(91)));
        _board[4][3].set_ownerId(Character.getNumericValue(finalString.charAt(94)));
        _board[4][3].set_cubesNumber(Character.getNumericValue(finalString.charAt(95)));
        _board[4][4].set_ownerId(Character.getNumericValue(finalString.charAt(98)));
        _board[4][4].set_cubesNumber(Character.getNumericValue(finalString.charAt(99)));

        return _board;
    }

    public static void printBoard2() throws IOException {

            for (int i = 0; i < 5 ; i++) {
                for (int j = 0 ; j < 5 ; j++) {
                    player.getOutputStream().writeUTF("PLANSZA " + i + " " + j + " " + board[i][j].get_ownerId() + " " + board[i][j].get_cubesNumber());
                }
            }
        }

    public FieldModel[][] get_board() {
        return board;
    }

    public void set_board(FieldModel[][] _board) {
        this.board = _board;
    }

    public PlayerModel get_player() {
        return player;
    }

    public static void set_player(PlayerModel _player) {
        player = _player;
    }
}
