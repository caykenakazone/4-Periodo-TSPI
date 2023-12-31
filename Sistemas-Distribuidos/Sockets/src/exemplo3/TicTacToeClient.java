package exemplo3;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class TicTacToeClient extends JFrame
                             implements Runnable {

   private static final long serialVersionUID = -8328202846488056890L;
   private JTextField id;
   private JTextArea display;
   private JPanel boardPanel, panel2;
   private Square board[][], currentSquare;
   private Socket connection;
   private DataInputStream input;
   private DataOutputStream output;
   private Thread outputThread;
   private char myMark;
   private boolean myTurn;

   public TicTacToeClient() {
      display = new JTextArea( 4, 30 );
      display.setEditable( false );
      getContentPane().add( new JScrollPane( display ),
                            BorderLayout.SOUTH );

      boardPanel = new JPanel();
      GridLayout layout = new GridLayout( 3, 3, 0, 0 );
      boardPanel.setLayout( layout );

      board = new Square[ 3 ][ 3 ];

      for ( int row = 0; row < board.length; row++ ) {
         for ( int col = 0;
                   col < board[ row ].length; col++ ) {
            board[ row ][ col ] =
               new Square( ' ', row * 3 + col );
            board[ row ][ col ].addMouseListener(
               new SquareListener(
                  this, board[ row ][ col ] ) );

            boardPanel.add( board[ row ][ col ] );        
         }
      }

      id = new JTextField();
      id.setEditable( false );
      
      getContentPane().add( id, BorderLayout.NORTH );
      
      panel2 = new JPanel();
      panel2.add( boardPanel, BorderLayout.CENTER );
      getContentPane().add( panel2, BorderLayout.CENTER );

      setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 350);
		setVisible(true);
   }

   public void start()
   {
      try {
         connection = new Socket(
            InetAddress.getByName( "127.0.0.1" ), 4000 );
         input = new DataInputStream(
                        connection.getInputStream() );
         output = new DataOutputStream(
                        connection.getOutputStream() );
      }
      catch ( IOException e ) {
         e.printStackTrace();         
      }

      outputThread = new Thread( this );
      outputThread.start();
   }

   // Control thread that allows continuous update of the
   // text area display.
   public void run()
   {
      // First get player's mark (X or O)
      try {
         myMark = input.readChar();
         id.setText( "You are player \"" + myMark + "\"" );
         myTurn = ( myMark == 'X' ? true  : false );
      }
      catch ( IOException e ) {
         e.printStackTrace();         
      }

      // Receive messages sent to client
      while ( true ) {
         try {
            String s = input.readUTF();
            processMessage( s );
         }
         catch ( IOException e ) {
            e.printStackTrace();         
         }
      }
   }

   public static void main(String args[]) {
		TicTacToeClient app = new TicTacToeClient();

		app.start();
	}

   // Process messages sent to client
   public void processMessage( String s )
   {
      if ( s.equals( "Valid move." ) ) {
         display.append( "Valid move, please wait.\n" );
         currentSquare.setMark( myMark );
         currentSquare.repaint();
      }
      else if ( s.equals( "Invalid move, try again" ) ) {
         display.append( s + "\n" );
         myTurn = true;
      }
      else if ( s.equals( "Opponent moved" ) ) {
         try {
            int loc = input.readInt();
 
            board[ loc / 3 ][ loc % 3 ].setMark(
                  ( myMark == 'X' ? 'O' : 'X' ) );
            board[ loc / 3 ][ loc % 3 ].repaint();
                 
            display.append(
               "Opponent moved. Your turn.\n" );
            myTurn = true;
         }
         catch ( IOException e ) {
            e.printStackTrace();         
         }
      }
      else
         display.append( s + "\n" );

      display.setCaretPosition(
         display.getText().length() );
   }

   public void sendClickedSquare( int loc )
   {
      if ( myTurn )
         try {
            output.writeInt( loc );
            myTurn = false;
         }
         catch ( IOException ie ) {
            ie.printStackTrace();         
         }
   }

   public void setCurrentSquare( Square s )
   {
      currentSquare = s;
   }

}

@SuppressWarnings("serial")
// Maintains one square on the board
class Square extends JPanel {
   private char mark;
   private int location;

   public Square( char m, int loc)
   {
      mark = m;
      location = loc;
      setSize ( 30, 30 );
      
      setVisible(true);
   }

   public Dimension getPreferredSize() { 
      return ( new Dimension( 30, 30 ) );
   }

   public Dimension getMinimumSize() {
      return ( getPreferredSize() );
   }

   public void setMark( char c ) { mark = c; }

   public int getSquareLocation() { return location; }

   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );
      g.drawRect( 0, 0, 29, 29 );
      g.drawString( String.valueOf( mark ), 11, 20 );   
   }
}

class SquareListener extends MouseAdapter {
   private TicTacToeClient applet;
   private Square square;

   public SquareListener( TicTacToeClient t, Square s )
   {
      applet = t;
      square = s;
   }

   public void mouseReleased( MouseEvent e )
   {
      applet.setCurrentSquare( square );
      applet.sendClickedSquare( square.getSquareLocation() );
   }

}
