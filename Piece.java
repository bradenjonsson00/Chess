/* this is the abstract chess piece, all other pieces inherit this class
*/
import java.awt.*;
import javax.swing.*;

public abstract class Piece {
    ImageIcon icon;// image of the chess piece, used as an identifier
    int color;// white = 0, black = 1
    
    WhitePawn wp;//these are needed for comparisons for attacks
    WhiteRook wr;
    WhiteKnight wk;
    WhiteBishop wb;
    WhiteKing wking;
    WhiteQueen wq;
    BlackPawn bp;
    BlackRook br;
    BlackKnight bk;
    BlackBishop bb;
    BlackKing bking;
    BlackQueen bq;

    public Piece() {
        // that chould be here   <---
    }

    public ImageIcon getIcon() {
        wp = new WhitePawn();//    ^^    
        wr = new WhiteRook();
        wk = new WhiteKnight();
        wb = new WhiteBishop();
        wking = new WhiteKing();
        wq = new WhiteQueen();
        bp = new BlackPawn();
        br = new BlackRook();
        bk = new BlackKnight();
        bb = new BlackBishop();
        bking = new BlackKing();
        bq = new BlackQueen();

        return icon;
    }

    public int getColor() {
        return color;
    }

    public int[][] viewMove(JButton[][] board, int xInt, int yInt) {//returns a 2d array of places it can move
    //ex:       ([0][0], [0][1]) == (x,y), the coordinates of the place to be highlighted in green
    //          ([1][0], [1][1]),   ([2][0], [2][1]), etc, only 2 wide, and however long
        return new int[0][0];
    }

    public int howFarNorth(JButton[][] board, int xInt, int yInt) {// sees how far north the piece can go before it can hit a piece/edge, and checks if it can attack
        int ret = 1;

        while(ret <= xInt) {    
            if(board[xInt - ret][yInt].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt - ret][yInt].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt - ret][yInt].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt - ret][yInt].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt - ret][yInt].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt - ret][yInt].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt - ret][yInt].getIcon().toString().equals(wq.getIcon().toString()))) {
                            ret++;
                    }
                } else {// color is white
                    if((board[xInt - ret][yInt].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt - ret][yInt].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt - ret][yInt].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt - ret][yInt].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt - ret][yInt].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt - ret][yInt].getIcon().toString().equals(bq.getIcon().toString()))) {
                            ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - 1;
    }

    public int howFarEast(JButton[][] board, int xInt, int yInt) {// sees how far east the piece can go before hitting a piece/edge, and checks if it can attack
        int ret = yInt + 1;

        while(ret <= board.length-1) {
            if(board[xInt][ret].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt][ret].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt][ret].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt][ret].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt][ret].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt][ret].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt][ret].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[xInt][ret].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt][ret].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt][ret].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt][ret].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt][ret].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt][ret].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - yInt - 1;
    }

    public int howFarSouth(JButton[][] board, int xInt, int yInt) {// sees how far south the piece can go before hitting a piece/edge, and checks if it can attack
        int ret = xInt + 1;

        while(ret < board.length) {
            if(board[ret][yInt].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[ret][yInt].getIcon().toString().equals(wp.getIcon().toString())) || (board[ret][yInt].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[ret][yInt].getIcon().toString().equals(wk.getIcon().toString())) || (board[ret][yInt].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[ret][yInt].getIcon().toString().equals(wking.getIcon().toString())) || (board[ret][yInt].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[ret][yInt].getIcon().toString().equals(bp.getIcon().toString())) || (board[ret][yInt].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[ret][yInt].getIcon().toString().equals(bk.getIcon().toString())) || (board[ret][yInt].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[ret][yInt].getIcon().toString().equals(bking.getIcon().toString())) || (board[ret][yInt].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - xInt - 1;
    }

    public int howFarWest(JButton[][] board, int xInt, int yInt) {// sees how far west the piece can go before hitting a piece/edge, and checks if it can attack
        int ret = 1;

        while(ret <= yInt) {
            if(board[xInt][yInt - ret].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt][yInt - ret].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt][yInt - ret].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt][yInt - ret].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt][yInt - ret].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt][yInt - ret].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt][yInt - ret].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[xInt][yInt - ret].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt][yInt - ret].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt][yInt - ret].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt][yInt - ret].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt][yInt - ret].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt][yInt - ret].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - 1;
    }

    public int howFarNorthEast(JButton[][] board, int xInt, int yInt) {// sees how far North-East the piece can go before hitting a piece/edge, and checks if it can attack
        int ret = 1;

        while(xInt - ret >= 0 && yInt + ret <= board.length-1) {
            if(board[xInt - ret][yInt + ret].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt - ret][yInt + ret].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt - ret][yInt + ret].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt - ret][yInt + ret].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt - ret][yInt + ret].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt - ret][yInt + ret].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt - ret][yInt + ret].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[xInt - ret][yInt + ret].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt - ret][yInt + ret].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt - ret][yInt + ret].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt - ret][yInt + ret].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt - ret][yInt + ret].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt - ret][yInt + ret].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - 1;
    }

    public int howFarSouthEast(JButton[][] board, int xInt, int yInt) {
        int ret = 1;

        while(xInt + ret <= board.length-1 && yInt + ret <= board.length-1) {// sees how far South-East the piece can go before hitting a piece/edge, and checks if it can attack
            if(board[xInt + ret][yInt + ret].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt + ret][yInt + ret].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt + ret][yInt + ret].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt + ret][yInt + ret].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt + ret][yInt + ret].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt + ret][yInt + ret].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt + ret][yInt + ret].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[xInt + ret][yInt + ret].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt + ret][yInt + ret].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt + ret][yInt + ret].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt + ret][yInt + ret].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt + ret][yInt + ret].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt + ret][yInt + ret].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - 1;
    }

    public int howFarSouthWest(JButton[][] board, int xInt, int yInt) {// sees how far South-West the piece can go before hitting a piece/edge, and checks if it can attack
        int ret = 1;

        while(xInt + ret <= board.length-1 && yInt - ret >= 0) {
            if(board[xInt + ret][yInt - ret].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt + ret][yInt - ret].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt + ret][yInt - ret].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt + ret][yInt - ret].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt + ret][yInt - ret].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt + ret][yInt - ret].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt + ret][yInt - ret].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[xInt + ret][yInt - ret].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt + ret][yInt - ret].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt + ret][yInt - ret].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt + ret][yInt - ret].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt + ret][yInt - ret].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt + ret][yInt - ret].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }

        return ret - 1;
    }

    public int howFarNorthWest(JButton[][] board, int xInt, int yInt) {// sees how far North-West the piece can go before hitting a piece/edge, and checks if it can attack
        int ret = 1;

        while(xInt - ret >= 0 && yInt - ret >= 0) {
            if(board[xInt - ret][yInt - ret].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt - ret][yInt - ret].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt - ret][yInt - ret].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt - ret][yInt - ret].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt - ret][yInt - ret].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt - ret][yInt - ret].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt - ret][yInt - ret].getIcon().toString().equals(wq.getIcon().toString()))) {
                        ret++;
                    }
                } else {// color is white
                    if((board[xInt - ret][yInt - ret].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt - ret][yInt - ret].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt - ret][yInt - ret].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt - ret][yInt - ret].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt - ret][yInt - ret].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt - ret][yInt - ret].getIcon().toString().equals(bq.getIcon().toString()))) {
                        ret++;
                    }
                }
                break;
            }
            ret++;
        }
        return ret - 1;
    }

    public boolean singleMoveCheck(JButton[][] board, int xInt, int yInt) {//used for knights and kings, recieves coordinates, and checks if the piece can play there
        if(xInt >= 0 && xInt <= 7 && yInt >= 0 && yInt <= 7) {
            
            if(board[xInt][yInt].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt][yInt].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(wq.getIcon().toString()))) {
                        return true;
                    }
                } else {// color is white
                    if((board[xInt][yInt].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(bq.getIcon().toString()))) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean pawnSingleMoveCheck(JButton[][] board, int xInt, int yInt) {//used for pawns, checks if the pawn can go to the coordinates
        if(xInt >= 0 && xInt <= 7 && yInt >= 0 && yInt <= 7 && board[xInt][yInt].getIcon() == null) {
            return true;
        }

        return false;
    }

    public boolean pawnAttackMoveCheck(JButton[][] board, int xInt, int yInt) {//used for pawns, checks if the pawn can go to the coordinates (diagonal)
        if(xInt >= 0 && xInt <= 7 && yInt >= 0 && yInt <= 7) {
            
            if(board[xInt][yInt].getIcon() != null) {
                if(color == 1) {//color is black
                    if((board[xInt][yInt].getIcon().toString().equals(wp.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(wr.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(wk.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(wb.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(wking.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(wq.getIcon().toString()))) {
                        
                        return true;
                    }
                } else {// color is white
                    if((board[xInt][yInt].getIcon().toString().equals(bp.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(br.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(bk.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(bb.getIcon().toString())) || 
                    (board[xInt][yInt].getIcon().toString().equals(bking.getIcon().toString())) || (board[xInt][yInt].getIcon().toString().equals(bq.getIcon().toString()))) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

/*  public boolean castlingCheck() {
        the king/rook can move onto eachother
        then give a pop up asking if you want to perform a castling
        then move the pieces to the proper places
    }
*/
}