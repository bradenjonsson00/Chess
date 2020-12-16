import javax.swing.*;

public class WhiteKing extends Piece {
    public WhiteKing() {
        icon = new ImageIcon("icons\\WhiteKing.png");
        color = 0;//white
    }
    
    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {
        
        int[][] ret;// a 2d array holding potential moves
        int count = 0;//counts the number of stored moves
        int trueCount = 0;// counts the number of bools that are true
        // a series of checks on where the piece can move
        boolean nor = singleMoveCheck(board, xInt-1, yInt);
        boolean norEast = singleMoveCheck(board, xInt-1, yInt+1);
        boolean east = singleMoveCheck(board, xInt, yInt+1);
        boolean souEast = singleMoveCheck(board, xInt+1, yInt+1);
        boolean sou = singleMoveCheck(board, xInt+1, yInt);
        boolean souWest = singleMoveCheck(board, xInt+1, yInt-1);
        boolean west = singleMoveCheck(board, xInt, yInt-1);
        boolean norWest = singleMoveCheck(board, xInt-1, yInt-1);
//      boolean castling = castlingCheck();

        //find trueCount, if zero, return null. else return an array of moves
        if(nor) trueCount++;
        if(norEast) trueCount++;
        if(east) trueCount++;
        if(souEast) trueCount++;
        if(sou) trueCount++;
        if(souWest) trueCount++;
        if(west) trueCount++;
        if(norWest) trueCount++;    

        if(trueCount == 0) return null;

        ret = new int[trueCount][2];

        //stores moves based of weither bool is true
        if(nor) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt;
            count++;
        }
        
        if(norEast) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt + 1;
            count++;
        }
        
        if(east) {
            ret[count][0] = xInt;
            ret[count][1] = yInt + 1;
            count++;
        }
        
        if(souEast) {
            ret[count][0] = xInt + 1;
            ret[count][1] = yInt + 1;
            count++;
        }
        
        if(sou) {
            ret[count][0] = xInt + 1;
            ret[count][1] = yInt;
            count++;
        }
        
        if(souWest) {
            ret[count][0] = xInt + 1;
            ret[count][1] = yInt - 1;
            count++;
        }
        
        if(west) {
            ret[count][0] = xInt;
            ret[count][1] = yInt - 1;
            count++;
        }
        
        if(norWest) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt - 1;
            count++;
        }
        
        return ret;
    }
}