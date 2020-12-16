import javax.swing.*;

public class BlackPawn extends Piece {
    public BlackPawn() {
        icon = new ImageIcon("icons\\BlackPawn.png");
        color = 1;
    }

    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {
        int[][] ret;
        int length = 0;
        int count = 0;
        boolean oneSpace = pawnSingleMoveCheck(board, xInt-1, yInt);
        boolean twoSpace = pawnSingleMoveCheck(board, xInt-2, yInt);
        boolean attack1 = pawnAttackMoveCheck(board, xInt-1, yInt-1);
        boolean attack2 = pawnAttackMoveCheck(board, xInt-1, yInt+1);

        if(xInt != 6) 
            twoSpace = false;

        if(oneSpace) {
            length++;
            if(twoSpace) {
                length++;
            }
        }
        if(attack1)
            length++;
        if(attack2)
            length++;
        if(length == 0)
            return null;

        ret = new int[length][2];

        if(oneSpace) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt;
            count++;
            if(twoSpace) {
                ret[count][0] = xInt - 2;
                ret[count][1] = yInt;
                count++;
            }
        }
        if(attack1) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt - 1;
            count++;
        }
        if(attack2) {
            ret[count][0] = xInt - 1;
            ret[count][1] = yInt + 1;
            count++;
        }

        return ret;
    }
}