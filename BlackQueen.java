import javax.swing.*;

public class BlackQueen extends Piece {
    public BlackQueen() {
        icon = new ImageIcon("icons\\BlackQueen.png");
        color = 1;
    }

    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {

        int south = howFarSouth(board, xInt, yInt);
        int north = howFarNorth(board, xInt, yInt);
        int east = howFarEast(board, xInt, yInt);
        int west = howFarWest(board, xInt, yInt);
        int northEast = howFarNorthEast(board, xInt, yInt);
        int southEast = howFarSouthEast(board, xInt, yInt);
        int southWest = howFarSouthWest(board, xInt, yInt);
        int northWest = howFarNorthWest(board, xInt, yInt);

        int[][] ret = new int[south + north + east + west + northEast + southEast + southWest + northWest][2];

        if(south + north + east + west + northEast + southEast + southWest + northWest == 0) {
            return null;
        }

        for(int i = 0; i < south; i++) {
            ret[i][0] = xInt + i + 1;
            ret[i][1] = yInt;
        }

        for(int i = south; i < south+north; i++) {
            ret[i][0] = xInt - i + south -1;
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

        for(int i = south+north+east+west; i < northEast+south+north+east+west; i++) {
            ret[i][0] = xInt - i - 1 + south+north+east+west;
            ret[i][1] = yInt + i + 1 - south-north-east-west;
        }
        
        for(int i = northEast+south+north+east+west; i < northEast + southEast + south+north+east+west; i++) {
            ret[i][0] = xInt + i + 1 - northEast - south-north-east-west;
            ret[i][1] = yInt + i + 1 - northEast - south-north-east-west;
        }
        
        for(int i = northEast + southEast + south+north+east+west; i < northEast + southEast + southWest + south+north+east+west; i++) {
            ret[i][0] = xInt + i + 1 - northEast - southEast - south-north-east-west;
            ret[i][1] = yInt - i - 1 + northEast + southEast + south+north+east+west;
        }
        
        for(int i = northEast + southEast + southWest + south+north+east+west; i < northEast + southEast + southWest + northWest + south+north+east+west; i++) {
            ret[i][0] = xInt - i - 1 + northEast + southEast + southWest + south+north+east+west;
            ret[i][1] = yInt - i - 1 + northEast + southEast + southWest + south+north+east+west;
        }

        return ret;
    }
}