import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color.*;

public class Chess {
    public static void main(String[] args) {
        new play();
    }
}

public class play implements ActionListener {
    JButton[][] board;// tracks pieces
    JButton[][] whiteCapturedPieces;// holds white pieces that have been removed from play
    JButton[][] blackCapturedPieces;
    JButton movingPiece;//the piece being moved, when clicked highlights potential moves
    JFrame frame;
    final Color tan = new Color(153, 102, 0);// board colors
    final Color brown = new Color(102, 35, 0);
    boolean whiteTurn;//tells whos turn it is
    boolean gameOver;
    boolean pawnPromotion;
    int whiteCapturedPiecesNum;//counts number of pieces removed from play
    int blackCapturedPiecesNum;
    JButton pawnSquare;

    WhitePawn wp;//used for comparisons
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

    public play() {
        gameOver = false;
        whiteTurn = true;
        pawnPromotion = false;// a mode where you can only interact with the graveyard (captured pieces)

        wp = new WhitePawn();
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

        frame = new JFrame("Java Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new JButton[8][8];
        whiteCapturedPieces = new JButton[2][8];
        blackCapturedPieces = new JButton[2][8];
        whiteCapturedPiecesNum = 0;
        blackCapturedPiecesNum = 0;

        fillBoard();
    }

    public void fillBoard() {//fills the board with pieces
        JButton button = new JButton();

        for(int i = 0; i < 8; i++) {//this cycles through each row
            //add graveyard
            button = new JButton();
            button.addActionListener(this);
            button.setBackground(Color.white);
            button.setForeground(Color.white);
            button.setText("" + 0 + 0);//not used, but dont remove
            frame.add(button);
            whiteCapturedPieces[0][i] = button;
 
            button = new JButton();
            button.addActionListener(this);
            button.setBackground(Color.white);
            button.setForeground(Color.white);
            button.setText("" + 0 + 0);
            frame.add(button);
            whiteCapturedPieces[1][i] = button;

            for(int j = 0; j < 8; j++) {// this cycles through each square in each row
                //adds pieces to their proper place
                //find which piece to add
                if(i == 0) {
                    if(j == 0 || j == 7) {
                        button = new JButton(wr.getIcon());
                    } else  if(j == 1 || j == 6) {
                        button = new JButton(wk.getIcon());
                    } else  if(j == 2 || j == 5) {
                        button = new JButton(wb.getIcon());
                    } else  if(j == 3) {
                        button = new JButton(wking.getIcon());
                    } else  if(j == 4) {
                        button = new JButton(wq.getIcon());
                    }
                } else if(i == 1) {
                    button = new JButton(wp.getIcon());
                } else if(i == 6) {
                    button = new JButton(bp.getIcon());
                } else if(i == 7) {
                    if(j == 0 || j == 7) {
                        button = new JButton(br.getIcon());
                    } else  if(j == 1 || j == 6) {
                        button = new JButton(bk.getIcon());
                    } else  if(j == 2 || j == 5) {
                        button = new JButton(bb.getIcon());
                    } else  if(j == 3) {
                        button = new JButton(bking.getIcon());
                    } else  if(j == 4) {
                        button = new JButton(bq.getIcon());
                    }
                } else {
                    button = new JButton();
                }

                // set alternating background, independant of piece or not
                if((i+j) % 2 == 0) {// i think these 2 colors look very nice
                    button.setBackground(tan);
                    button.setForeground(tan);
                } else {
                    button.setBackground(brown);
                    button.setForeground(brown);
                }
                
                //piece has text of its coordinates, the text is hidden by being the same color (foreground)
                button.setText("" + i + j);
                button.addActionListener(this);
                board[i][j] = button;
                frame.add(button);
            }
            //add graveyard
            button = new JButton();
            button.addActionListener(this);
            button.setBackground(Color.white);
            button.setForeground(Color.white);
            button.setText("" + 0 + 0);
            frame.add(button);
            blackCapturedPieces[0][i] = button;
 
            button = new JButton();
            button.addActionListener(this);
            button.setBackground(Color.white);
            button.setForeground(Color.white);
            button.setText("" + 0 + 0);
            frame.add(button);
            blackCapturedPieces[1][i] = button;
        }
        //initialize whole board
        frame.setLayout(new GridLayout(8,12));
        frame.setSize(1000,720);
        frame.setVisible(true);

        UIManager.put("OptionPane.okButtonText", "Play Now");
        JOptionPane.showMessageDialog(frame,"Welcome to chess.");  

    }

    //this is 200 lines long... maybe break it up somehow
    public void actionPerformed(ActionEvent event) {//if buttons is clicked, could be select movingPiece, select place to move, or unselect moving piece
    /*  if its pawn promotion you can't click board, if its not pawn promotion you can only click graveyard
        board can't be clicked once game is over

        note: potential moves are tracked by turning buttons green. there isnt an internal tracking system. once a button can no longer be clicked (logically),
                it is turned back to its normal color
    */
        JButton button = (JButton) event.getSource();
        String icon;
        int xInt = Integer.parseInt(button.getText().substring(0,1));
        int yInt = Integer.parseInt(button.getText().substring(1));
        int[][] moves = new int[0][0];
        Piece piece;

        if(gameOver)// the game is over!
            return;

        if(pawnPromotion) {// get a piece from the graveyard to replace the pawn
            if(button.getBackground() == Color.green) {// available pieces
                pawnSquare.setIcon(button.getIcon());
                
                if(whiteTurn) {
                    button.setIcon(wp.getIcon());
                    for(int i = 0; i < 2; i++) {//reset colors
                        for(int j = 0; j < 8; j++) {
                            whiteCapturedPieces[i][j].setBackground(Color.white);
                            whiteCapturedPieces[i][j].setForeground(Color.white);
                        }
                    }
                } else {
                    button.setIcon(bp.getIcon());
                    for(int i = 0; i < 2; i++) {// reset colors
                        for(int j = 0; j < 8; j++) {
                            blackCapturedPieces[i][j].setBackground(Color.white);
                            blackCapturedPieces[i][j].setForeground(Color.white);
                        }
                    }
                }

                pawnPromotion = false;// end pawn promotion
                whiteTurn = !whiteTurn;
            }
            return;// if pawn promotion is going on, no other moves can happen
        }

        if(button == movingPiece) {// unselect moving piece
            moveOver();// doesnt change whos turn it is
            return;
        }

        if(movingPiece == null && button.getIcon() != null) {// selects a piece
            icon = button.getIcon().toString();
            piece = getPiece(icon);

            if(wrongTurn(piece))// checks if you click the wrong piece if its your turn
                return;

            moves = piece.viewMove(board, xInt, yInt);// finds potential moves

            if(moves == null) {// no moves
                
                if(button.getIcon() == wp.getIcon() && xInt == 7) {// white pawn promotion
                    int dialogResult = JOptionPane.showConfirmDialog (frame, "Would you like to perform a pawn promotion?","Pawn Promotion", JOptionPane.YES_NO_OPTION);
                    
                    if(dialogResult == JOptionPane.YES_OPTION){

                        for(int i = 0; i < 2; i++) {
                            for(int j = 0; j < 8; j++) {
                                if(whiteCapturedPieces[i][j].getIcon() != null && whiteCapturedPieces[i][j].getIcon() != wp.getIcon()) {
                                    pawnPromotion = true;
                                    whiteCapturedPieces[i][j].setBackground(Color.green);
                                    whiteCapturedPieces[i][j].setForeground(Color.green);
                                }
                            }
                        }

                        if(!pawnPromotion) {
                            JFrame popUp2 = new JFrame("Pawn Promption");
                            Label label2;
                            label2 = new Label("There are no captured pieces to promote your pawn");
                            label2.setBounds(10,10,500,30);
                            label2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                            popUp2.add(label2);
                            popUp2.setSize(500,100);
                            popUp2.setLocation(370,325);
                            popUp2.setLayout(null);
                            popUp2.setVisible(true);
                            popUp2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                        } else {
                            JFrame popUp = new JFrame("Pawn Promption");
                            Label label;
                            label = new Label("Select a captured piece.");
                            label.setBounds(10,10,300,30);
                            label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                            popUp.add(label);
                            popUp.setSize(300,100);
                            popUp.setLocation(370,325);
                            popUp.setLayout(null);
                            popUp.setVisible(true);
                            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program

                            pawnSquare = button;
                        }
                    }
                } else if(button.getIcon() == bp.getIcon() && xInt == 0) {// black pawn promotion
                    int dialogResult = JOptionPane.showConfirmDialog (frame, "Would you like to perform a pawn promotion?","Pawn Promotion", JOptionPane.YES_NO_OPTION);

                    if(dialogResult == JOptionPane.YES_OPTION){
                        pawnPromotion = true;

                        for(int i = 0; i < 2; i++) {
                            for(int j = 0; j < 8; j++) {
                                if(blackCapturedPieces[i][j].getIcon() != null && blackCapturedPieces[i][j].getIcon() != bp.getIcon()) {
                                    blackCapturedPieces[i][j].setBackground(Color.green);
                                    blackCapturedPieces[i][j].setForeground(Color.green);
                                }
                            }
                        }
                        
                        if(!pawnPromotion) {
                            JFrame popUp2 = new JFrame("Pawn Promption");
                            Label label2;
                            label2 = new Label("There are no captured pieces to promote your pawn");
                            label2.setBounds(10,10,500,30);
                            label2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                            popUp2.add(label2);
                            popUp2.setSize(500,100);
                            popUp2.setLocation(370,325);
                            popUp2.setLayout(null);
                            popUp2.setVisible(true);
                            popUp2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                        } else {

                            JFrame popUp = new JFrame("Pawn Promption");
                            Label label;
                            label = new Label("Select a captured piece.");
                            label.setBounds(10,10,300,30);
                            label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                            popUp.add(label);
                            popUp.setSize(300,100);
                            popUp.setLocation(370,325);
                            popUp.setLayout(null);
                            popUp.setVisible(true);
                            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                        
                            pawnSquare = button;
                        }
                    }
                }

                return;
            }

            for(int i = 0; i < moves.length; i++) {// shows which moves are available.
                board[moves[i][0]][moves[i][1]].setBackground(Color.green);
                board[moves[i][0]][moves[i][1]].setForeground(Color.green);
            }
            
            movingPiece = button;// tracks what piece will move
        }

        if(button.getBackground() == Color.green) {
            
            if(movingPiece.getIcon() == wp.getIcon() && xInt == 7) {// white pawn promotion
                int dialogResult = JOptionPane.showConfirmDialog (frame, "Would you like to perform a pawn promotion?","Pawn Promotion", JOptionPane.YES_NO_OPTION);
                
                if(dialogResult == JOptionPane.YES_OPTION){

                    for(int i = 0; i < 2; i++) {
                        for(int j = 0; j < 8; j++) {
                            if(whiteCapturedPieces[i][j].getIcon() != null && whiteCapturedPieces[i][j].getIcon() != wp.getIcon()) {
                                pawnPromotion = true;
                                whiteCapturedPieces[i][j].setBackground(Color.green);
                                whiteCapturedPieces[i][j].setForeground(Color.green);
                            }
                        }
                    }

                    if(!pawnPromotion) {
                        JFrame popUp2 = new JFrame("Pawn Promption");
                        Label label2;
                        label2 = new Label("There are no captured pieces to promote your pawn");
                        label2.setBounds(10,10,500,30);
                        label2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                        popUp2.add(label2);
                        popUp2.setSize(500,100);
                        popUp2.setLocation(370,325);
                        popUp2.setLayout(null);
                        popUp2.setVisible(true);
                        popUp2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                    } else {
                        JFrame popUp = new JFrame("Pawn Promption");
                        Label label;
                        label = new Label("Select a captured piece.");
                        label.setBounds(10,10,300,30);
                        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                        popUp.add(label);
                        popUp.setSize(300,100);
                        popUp.setLocation(370,325);
                        popUp.setLayout(null);
                        popUp.setVisible(true);
                        popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program

                        pawnSquare = button;
                    }
                }
            } else if(movingPiece.getIcon() == bp.getIcon() && xInt == 0) {// black pawn promotion
                int dialogResult = JOptionPane.showConfirmDialog (frame, "Would you like to perform a pawn promotion?","Pawn Promotion", JOptionPane.YES_NO_OPTION);

                if(dialogResult == JOptionPane.YES_OPTION){
                    pawnPromotion = true;

                    for(int i = 0; i < 2; i++) {
                        for(int j = 0; j < 8; j++) {
                            if(blackCapturedPieces[i][j].getIcon() != null && blackCapturedPieces[i][j].getIcon() != bp.getIcon()) {
                                blackCapturedPieces[i][j].setBackground(Color.green);
                                blackCapturedPieces[i][j].setForeground(Color.green);
                            }
                        }
                    }
                    
                    if(!pawnPromotion) {
                        JFrame popUp2 = new JFrame("Pawn Promption");
                        Label label2;
                        label2 = new Label("There are no captured pieces to promote your pawn");
                        label2.setBounds(10,10,500,30);
                        label2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                        popUp2.add(label2);
                        popUp2.setSize(500,100);
                        popUp2.setLocation(370,325);
                        popUp2.setLayout(null);
                        popUp2.setVisible(true);
                        popUp2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                    } else {

                        JFrame popUp = new JFrame("Pawn Promption");
                        Label label;
                        label = new Label("Select a captured piece.");
                        label.setBounds(10,10,300,30);
                        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                        popUp.add(label);
                        popUp.setSize(300,100);
                        popUp.setLocation(370,325);
                        popUp.setLayout(null);
                        popUp.setVisible(true);
                        popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                    
                        pawnSquare = button;
                    }
                }
            }

            if(button.getIcon() != null) {// attack!
                //increment num of pieces in graveyard, add to graveyard
                if(whiteTurn) {
                    addToGraveYard(blackCapturedPieces, button.getIcon());
                    blackCapturedPiecesNum++;
                } else {
                    addToGraveYard(whiteCapturedPieces, button.getIcon());
                    whiteCapturedPiecesNum++;
                }

                if(button.getIcon() == wking.getIcon()) {//game over!
                    JFrame popUp = new JFrame("Game Over...");  
                    Label label;
                    label = new Label("Black wins!");
                    label.setBounds(60,10,180,30);
                    label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    popUp.add(label);
                    popUp.setSize(260,100);
                    popUp.setLocation(370,325);
                    popUp.setLayout(null);
                    popUp.setVisible(true);
                    popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                    gameOver = true;
                } else if(button.getIcon() == bking.getIcon()) {//game over!
                    JFrame popUp = new JFrame("Game Over...");  
                    Label label;
                    label = new Label("White wins!");  
                    label.setBounds(60,10,180,30);
                    label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    popUp.add(label);
                    popUp.setSize(260,100);
                    popUp.setLocation(370,325);
                    popUp.setLayout(null);
                    popUp.setVisible(true);
                    popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// closes the pop up instead of the whole program
                    gameOver = true;
                }
            }

            button.setIcon(movingPiece.getIcon());//move piece
            movingPiece.setIcon(null);

            moveOver();
            if(!pawnPromotion)
                whiteTurn = !whiteTurn;
        }
    }

    public void moveOver() {// called at the end of a turn
        // does not change whos turn it is!
        // resets all background colors
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i+j) % 2 == 0) {
                    board[i][j].setBackground(tan);
                    board[i][j].setForeground(tan);
                } else {
                    board[i][j].setBackground(brown);
                    board[i][j].setForeground(brown);
                }
            }
        }

        movingPiece = null;
    }

    Piece getPiece(String icon) {// says what piece is here
        if(icon.equals(wp.getIcon().toString())) {
            return wp;
        } else if(icon.equals(wr.getIcon().toString())) {
            return wr;
        } else if(icon.equals(wk.getIcon().toString())) {
            return wk;
        } else if(icon.equals(wb.getIcon().toString())) {
            return wb;
        } else if(icon.equals(wking.getIcon().toString())) {
            return wking;
        } else if(icon.equals(wq.getIcon().toString())) {
            return wq;
        } else if(icon.equals(bp.getIcon().toString())) {
            return bp;
        } else if(icon.equals(br.getIcon().toString())) {
            return br;
        } else if(icon.equals(bk.getIcon().toString())) {
            return bk;
        } else if(icon.equals(bb.getIcon().toString())) {
            return bb;
        } else if(icon.equals(bking.getIcon().toString())) {
            return bking;
        } else if(icon.equals(bq.getIcon().toString())) {
            return bq;
        }

        return null;
    }

    boolean wrongTurn(Piece piece) {//checks if you clicked the wrong color piece
        if(!whiteTurn) {// if its black turn, and you click a white piece, its the wrong turn
            if(piece == wp || piece == wr || piece == wk || piece == wb || piece == wking || piece == wq) {
                return true;
            }
        } else {// if its white turn, and you click a black piece, its the wrong turn
            if(piece == bp || piece == br || piece == bk || piece == bb || piece == bking || piece == bq) {
                return true;
            }
        }

        return false;
    }

    void addToGraveYard(JButton[][] graveyard, Icon icon) {//adds piece to its graveyard
        int graveYardOccupants;

        if(whiteTurn) {
            graveYardOccupants = blackCapturedPiecesNum;
        } else {
            graveYardOccupants = whiteCapturedPiecesNum;
        }

        graveyard[graveYardOccupants%2][graveYardOccupants/2].setIcon(icon);
        
    }
}
