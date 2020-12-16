import javax.swing.*;

public class BlackKnight extends Piece {
    public BlackKnight() {
        icon = new ImageIcon("icons\\BlackKnight.png");
        color = 1;
    }

    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {

        boolean norWest = singleMoveCheck(board, xInt-2, yInt-1);
        boolean norEast = singleMoveCheck(board, xInt-2, yInt+1);
        boolean eastNor = singleMoveCheck(board, xInt-1, yInt+2);
        boolean eastSou = singleMoveCheck(board, xInt+1, yInt+2);
        boolean souEast = singleMoveCheck(board, xInt+2, yInt+1);
        boolean souWest = singleMoveCheck(board, xInt+2, yInt-1);
        boolean westSou = singleMoveCheck(board, xInt+1, yInt-2);
        boolean westNor = singleMoveCheck(board, xInt-1, yInt-2);
        int[][] ret;
        int count = 0;
        int trueCount = 0;// counts the number of bools that are true

        if(norWest) trueCount++;
        if(norEast) trueCount++;
        if(eastNor) trueCount++;
        if(eastSou) trueCount++;
        if(souEast) trueCount++;
        if(souWest) trueCount++;
        if(westSou) trueCount++;
        if(westNor) trueCount++;    

        if(trueCount == 0) return null;
        
        ret = new int[trueCount][2];

        if(norWest) {
            ret[count][0] = xInt - 2;
            ret[count][1] = yInt - 1;
            count++;
        }
        
        if(norEast) {
            ret[count][0] = xInt - 2;
            ret[count][1] = yInt + 1;
            count++;
        }
        
        if(eastNor) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt + 2;
            count++;
        }
        
        if(eastSou) {
            ret[count][0] = xInt + 1;
            ret[count][1] = yInt + 2;
            count++;
        }
        
        if(souEast) {
            ret[count][0] = xInt + 2;
            ret[count][1] = yInt + 1;
            count++;
        }
        
        if(souWest) {
            ret[count][0] = xInt + 2;
            ret[count][1] = yInt - 1;
            count++;
        }
        
        if(westSou) {
            ret[count][0] = xInt + 1;
            ret[count][1] = yInt - 2;
            count++;
        }
        
        if(westNor) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt - 2;
            count++;
        }
        
        
        return ret;
    }
}