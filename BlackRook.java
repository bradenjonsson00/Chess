import javax.swing.*;

public class BlackRook extends Piece {
    public BlackRook() {
        icon = new ImageIcon("icons\\BlackRook.png");
        color = 1;
    }

    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {

        int south = howFarSouth(board, xInt, yInt);
        int north = howFarNorth(board, xInt, yInt);
        int east = howFarEast(board, xInt, yInt);
        int west = howFarWest(board, xInt, yInt);

        if(south + north + east + west == 0) {
            return null;
        }

        int[][] ret = new int[south + north + east + west][2];

        for(int i = 0; i < south; i++) {
            ret[i][0] = xInt + i + 1;
            ret[i][1] = yInt;
        }

        for(int i = south; i < south+north; i++) {
            ret[i][0] = xInt - i + south - 1;
            ret[i][1] = yInt;
        }

        for(int i = south+north; i < south+north+east; i++) {
            ret[i][0] = xInt;
            ret[i][1] = yInt + i - south - north + 1;
        }

        for(int i = south+north+east; i < south+north+east+west; i++) {
            ret[i][0] = xInt;
            ret[i][1] = yInt - i + south + north + east - 1;
        }

        return ret;
    }
}