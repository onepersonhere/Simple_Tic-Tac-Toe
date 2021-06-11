package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static char[][] enterCells(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Cells: ");

        String cells = scanner.nextLine();
        char[] ch = new char[cells.length()];

        for(int i = 0; i < cells.length(); i++){

            ch[i] = cells.charAt(i);
        }

        char[][] arr = {{ch[0], ch[1], ch[2]}, {ch[3], ch[4], ch[5]}, {ch[6], ch[7], ch[8]}};

        showCells(arr);
        return arr;
    }
    static boolean analyseGame(char[][] Arr){
        List<Character> list = new ArrayList<Character>();
        for (int i = 0; i < Arr.length; i++) {
            // tiny change 1: proper dimensions
            for (int j = 0; j < Arr[i].length; j++) {
                // tiny change 2: actually store the values
                list.add(Arr[i][j]);
            }
        }

        char[] ch = new char[list.size()];
        for (int i = 0; i < ch.length; i++) {
            ch[i] = list.get(i);
        }

        char winner = '_';
        int winningcombi = 0;
        char X = 'X';
        char O = 'O';
        char[] arr = {X, O};
        int numofX = 0;
        int numofO = 0;

        for(int j = 0; j < 2; j++){
            char r = arr[j];
            for(int i = 0; i <= 6; i+=3) {
                if (r == ch[i] && r == ch[i+1] && r == ch[i+2]) {
                    winningcombi++;
                    winner = r;
                }
            }
            for(int i = 0; i <= 2; i++) {
                if (r == ch[i] && r == ch[i + 3] && r == ch[i + 6]) {
                    winningcombi++;
                    winner = r;
                }
            }

            if(r == ch[0] && r == ch[4] && r == ch[8]){
                winningcombi++;
                winner = r;
            }
            if(r == ch[2] && r == ch[4] && r == ch[6]){
                winningcombi++;
                winner = r;
            }
        }


        if(winningcombi == 0){
            for(char c: ch){
                if(c == 'X'){
                    numofX++;
                }
                if(c == 'O'){
                    numofO++;
                }
            }
            if(Math.abs(numofX - numofO) > 1){
                System.out.println("Impossible");
                return false;
            }
            /*
            for(char c : ch){
                if(c == ' '){
                    System.out.println("Game not finished");
                    return true;
                }*/
            if(numofO + numofX == 9) {
                System.out.println("Draw");
                return false;
            }
        }

        if(winningcombi >= 1){
            System.out.println(winner + " wins");
            return false;
        }
        return true;
    }
    static char[][] enterCoord(char[][] arr, char c){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates: ");

        int row = 0;
        int column = 0;
        String str = scanner.nextLine();

        if(Character.isLetter(str.charAt(0)) || Character.isLetter(str.charAt(2))){
            System.out.println("You should enter numbers!");
            return enterCoord(arr, c);
        }else{
            row = Character.getNumericValue(str.charAt(0)) - 1; //System.out.println(row);
            column = Character.getNumericValue(str.charAt(2)) - 1; //System.out.println(column);
        }
        if(row > 2 || row < 0 || column > 2 || column < 0){
            System.out.println("Coordinates should be from 1 to 3!");
            return enterCoord(arr, c);
        }

        if(arr[row][column] == ' ') {
            arr[row][column] = c;
        }else{
            System.out.println("This cell is occupied! Choose another one!");
            return enterCoord(arr, c);
        }
        return arr;
    }
    static void showCells(char[][] arr){
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            for(int j = -1; j < 4; j++){
                if(j == -1 || j == 3){
                    System.out.print("| ");
                }else{
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------");

    }
    public static void main(String[] args) {

        char[][] arr = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
        showCells(arr);
        boolean bool = true;
        char c = 'X';
        int count = 0;
        while(bool) {
            arr = enterCoord(arr, c);
            showCells(arr);
            bool = analyseGame(arr);
            count++;
            if(count % 2 == 0){
                c = 'X';
            }else{
                c = 'O';
            }
        }
    }
}
