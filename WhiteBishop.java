import javax.swing.*;

public class WhiteBishop extends Piece {
    public WhiteBishop() {
        icon = new ImageIcon("icons\\WhiteBishop.png");
        color = 0;
    }
    

    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {

        int northEast = howFarNorthEast(board, xInt, yInt);
        int southEast = howFarSouthEast(board, xInt, yInt);
        int southWest = howFarSouthWest(board, xInt, yInt);
        int northWest = howFarNorthWest(board, xInt, yInt);

        if(northEast + southEast + southWest + northWest == 0) {
            return null;
        }

        int[][] ret = new int[northEast + southEast + southWest + northWest][2];

        for(int i = 0; i < northEast; i++) {
            ret[i][0] = xInt - i - 1;
            ret[i][1] = yInt + i + 1;
        }
        
        for(int i = northEast; i < northEast + southEast; i++) {
            ret[i][0] = xInt + i + 1 - northEast;
            ret[i][1] = yInt + i + 1 - northEast;
        }
        
        for(int i = northEast + southEast; i < northEast + southEast + southWest; i++) {
            ret[i][0] = xInt + i + 1 - northEast - southEast;
            ret[i][1] = yInt - i - 1 + northEast + southEast;
        }
        
        for(int i = northEast + southEast + southWest; i < northEast + southEast + southWest + northWest; i++) {
            ret[i][0] = xInt - i - 1 + northEast + southEast + southWest;
            ret[i][1] = yInt - i - 1 + northEast + southEast + southWest;
        }

        return ret;
    }
}