import java.util.*;
/**
 * Clasa ce defineste tipul Pair.
 * @author B-52
 *
 */
class Pair{
	
	int x;	//coordonata x
	int y;  //coordonata y
	
	/**
	 * Constructor ce initializeaza 
	 * datele acestui obiect
	 * @param l
	 * @param c
	 */
	public Pair(int l , int c){
		x=l;
		y=c;
	}
	
}

/**
 * Clasa ce defineste tipul Board
 * @author B-52
 *
 */
class Board {
	String[][] board;    									 //matricea jocului
	int lines, columns; 									 //dimensiunea
	int playerLine, playerColumn, oponentLine, oponentColumn;//informatii privind cei 2 jucatori
	String player, oponent;									 //identificatori
	
	/**
	 * Constructor pentru tipul Board
	 * ce initializeaza matricea dupa
	 * dimensiunile primite ca parametru
	 * @author B-52
	 * @param lines
	 * @param columns
	 */
	public Board(int lines, int columns) {
		this.lines = lines;
		this.columns = columns;
		board = new String[lines][columns];
	}
	
	/**
	 * Constructor ce construieste
	 * un nou obiect dupa informatiile
	 * celui primit ca parametru
	 * @author B-52
	 * @param b
	 */
	public Board(Board b) {
		
		this(b.lines, b.columns);
		
		setPositions(b.playerLine, b.playerColumn, b.oponentLine,
				b.oponentColumn);
		
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				set(i, j, b.board[i][j]);
			}
		}
		
		setPlayers(b.player, b.oponent);
		
	}
	
	/**
	 * Metoda ce atribuie un string
	 * elementului matricei specificat prin 
	 * parametrii primiti
	 * @author B-52
	 * @param line
	 * @param column
	 * @param s
	 */
	public void set(int line, int column, String s) {
		board[line][column] = s;
	}
	
	/**
	 * Metoda ce initializeaza identificatorii
	 * jucatorilor
	 * @param player
	 * @param oponent
	 */
	public void setPlayers(String player, String oponent) {
		this.player = player;
		this.oponent = oponent;
	}
	
	/**
	 * Metoda ce seteaza pozitiile curente
	 * ale celor doi jucatori
	 * @author B-52
	 * @param currentL
	 * @param currentC
	 * @param oCurrentL
	 * @param oCurrentC
	 */
	public void setPositions(int currentL, int currentC, int oCurrentL,
			int oCurrentC) {
		this.playerLine = currentL;
		this.playerColumn = currentC;
		this.oponentLine = oCurrentL;
		this.oponentColumn = oCurrentC;
	}
	
	/**
	 * Metoda ce adauga intr-o lista
	 * primita ca parametru toate miscarile posibilie
	 * ale unui jucator(si acesta fiind primit ca parametru)
	 * @author B-52
	 * @param toFill
	 * @param toMove
	 */
	public void getAllPosMoves(ArrayList<Move> toFill, String toMove) {
		
		//Pentru fiecare jucator verificam directiile U-D-R-L
		if (toMove.equals("B52")) {
			if (board[playerLine][playerColumn - 1].equals("-")) {
				toFill.add(new Move("LEFT"));
			}

			if (board[playerLine][playerColumn + 1].equals("-")) {
				toFill.add(new Move("RIGHT"));
			}
			if (board[playerLine - 1][playerColumn].equals("-")) {
				toFill.add(new Move("UP"));
			}

			if (board[playerLine + 1][playerColumn].equals("-")) {
				toFill.add(new Move("DOWN"));
			}

		} else {
			if (board[oponentLine - 1][oponentColumn].equals("-")) {
				toFill.add(new Move("UP"));
			}

			if (board[oponentLine + 1][oponentColumn].equals("-")) {
				toFill.add(new Move("DOWN"));
			}

			if (board[oponentLine][oponentColumn - 1].equals("-")) {
				toFill.add(new Move("LEFT"));
			}

			if (board[oponentLine][oponentColumn + 1].equals("-")) {
				toFill.add(new Move("RIGHT"));
			}
		}

	}
	
	/**
	 * toString:D
	 */
	public String toString() {
		String ret = "";
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				ret += board[i][j];
			}
			ret += "\n";
		}
		return ret;
	}
}

/**
 * Clasa ce defineste tipul
 * Move
 * @author B-52
 *
 */
class Move {
	String move;
	
	/**
	 * Constructor ce initializeaza
	 * mutarea.
	 * @author B-52
	 * @param move
	 */
	public Move(String move) {
		this.move = move;
	}
	
	/**
	 * Metoda ce executa mutarea pe o tabla
	 * primita ca parametru 
	 * @author B-52
	 * @param b
	 * @param playAs
	 */
	public void executeMove(Board b, String playAs) {
		
		//cazul in care move.equals("UP")...modificam elementul de deasupra
		//celui curent si updatam pozitia curenta a jucatorului,
		//Idem pentru celelalte cazuri
		if (move.equals("UP")) {
			if (playAs.equals("B52")) {
				b.board[b.playerLine - 1][b.playerColumn] = b.player;
				b.playerLine -= 1;
			} else {
				b.board[b.oponentLine - 1][b.oponentColumn] = b.oponent;
				b.oponentLine -= 1;
			}
			return;
		}

		if (move.equals("DOWN")) {
			if (playAs.equals("B52")) {
				b.board[b.playerLine + 1][b.playerColumn] = b.player;
				b.playerLine += 1;
			} else {
				b.board[b.oponentLine + 1][b.oponentColumn] = b.oponent;
				b.oponentLine += 1;
			}
			return;
		}

		if (move.equals("LEFT")) {
			if (playAs.equals("B52")) {
				b.board[b.playerLine][b.playerColumn - 1] = b.player;
				b.playerColumn -= 1;
			} else {
				b.board[b.oponentLine][b.oponentColumn - 1] = b.oponent;
				b.oponentColumn -= 1;
			}
			return;
		}

		if (move.equals("RIGHT")) {
			if (playAs.equals("B52")) {
				b.board[b.playerLine][b.playerColumn + 1] = b.player;
				b.playerColumn += 1;
			} else {
				b.board[b.oponentLine][b.oponentColumn + 1] = b.oponent;
				b.oponentColumn += 1;
			}
			return;
		}
	}
	
	/**
	 * @author B-52
	 * toString():D
	 */
	public String toString() {
		return move;
	}

}

/**
 * Clasa ce defineste tipul
 * Solution
 * @author B-52
 *
 */
public class Solution {

	public static final int INFINITY = 9999;
	public static final int MAXDEPTH = 4;
	
	
	/**
	 * Metoda evaluate folosita in algoritmul
	 * MiniMax
	 * @param b 
	 * @param line
	 * @param column
	 * @param player
	 * @return
	 */
	public static int evaluate(Board b , int line , int column , String player){
		
		ArrayList<Pair> lista = new ArrayList<Pair>(); //lista este folosita pentru 
													   //a tine perechile neevaluate

		lista.add(new Pair(line,column));
		
		Board copie = new Board(b);
		
		int contor = 0,lin,col;
		
		while(lista.size() > 0){				//cat timp sunt elemente de evaluat
			lin = lista.get(lista.size()-1).x;
			col = lista.get(lista.size()-1).y;
			
			copie.board[lin][col] = player ;   //marchez perechea ca fiind evaluata
											   //(execut mutarea)
			lista.remove(lista.size()-1);      //sterg perechea
			
			//verific daca o directie este accesibila
			if(copie.board[lin-1][col].equals("-")){
				lista.add(new Pair(lin-1,col));
			
			}
			
			if(copie.board[lin+1][col].equals("-")){
				lista.add(new Pair(lin+1,col));
			
			}
			
			if(copie.board[lin][col + 1].equals("-")){
				lista.add(new Pair(lin,col+1));
			
			}
			
			if(copie.board[lin][col-1].equals("-")){
				lista.add(new Pair(lin,col-1));
				
			}
			contor++; // dupa un element evaluat contorizez
		}
		//practic metoda returneaza numarul de elemente
		//de pe harta accesibile jucatorului
		return contor;
	}
	
	/**
	 * Metoda maxi
	 * @author B-52
	 * @param depth
	 * @param b
	 * @param nextMove
	 * @param outOfTime
	 * @return
	 */
	public static int maxi(int depth, Board b, Move nextMove, long outOfTime){

		if (depth == 0) {
			return evaluate(b, b.playerLine, b.playerColumn, b.player);
		}

		if (System.currentTimeMillis() >= outOfTime) { //daca ne apropiem de cele 5s
			return evaluate(b, b.playerLine, b.playerColumn, b.player);
		}

		ArrayList<Move> moves = new ArrayList<Move>();

		b.getAllPosMoves(moves, "B52");

		if (moves.size() == 0) {
			return -INFINITY;
		}

		int maxi = -INFINITY;
		
		for (Move it : moves) {
			Board copie = new Board(b);
			int r = 0;
			it.executeMove(copie, "B52");  //execut mutarea
			r = mini(depth - 1, copie, outOfTime);

			if (maxi < r) {
				maxi = r;
				nextMove.move = it.move;
			}

		}

		return maxi;
	}
	
	/**
	 * Metoda mini
	 * @param depth
	 * @param b
	 * @param outOfTime
	 * @return
	 */
	public static int mini(int depth, Board b, long outOfTime) {
		if (depth == 0) {
			return evaluate(b, b.oponentLine, b.oponentColumn, "Oponent");
		}
		if (System.currentTimeMillis() >= outOfTime) { //daca ramanem fara timp
				return evaluate(b, b.oponentLine, b.oponentColumn, "Oponent");
		
		}

		ArrayList<Move> moves = new ArrayList<Move>();

		b.getAllPosMoves(moves, "OPONENT");

		if (moves.size() == 0) {
			return INFINITY;
		}

		int mini = INFINITY;

		for (Move it : moves) {
			Board copie = new Board(b);
			int r = 0;
			it.executeMove(copie, "OPONENT");
			r = maxi(depth - 1, copie, new Move("ASD"), outOfTime);
			if (mini > r) {
				mini = r;
			}
		}
		return mini;

	}
	
	/**
	 * Metoda nextMove ce apeleaza
	 * metoda maxi si printeaza mutarea
	 * ce trebuie facuta
	 * @param b
	 */
	static void nextMove(Board b) {
		Move move = new Move("s");
		long outOfTime = System.currentTimeMillis() + 4000;
		 maxi(MAXDEPTH, b, move, outOfTime);
		System.out.println(move);
	}

	/* Tail starts here */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		String player = sc.nextLine();
		String[] positions = sc.nextLine().split(" ");
		int playerLine  = Integer.parseInt(positions[0]), 
			playerCol   = Integer.parseInt(positions[1]), 
			oponentLine = Integer.parseInt(positions[2]), 
			oponentCol  = Integer.parseInt(positions[3]);

		String[] boardSize = sc.nextLine().split(" ");

		int boardL = Integer.parseInt(boardSize[0]), 
			boardC = Integer.parseInt(boardSize[1]);

		Board b = new Board(boardL, boardC);

		for (int i = 0; i < boardL; i++) {
			char[] line = sc.nextLine().toCharArray();
			for (int j = 0; j < boardC; j++) {
				b.set(i, j, line[j] + "");
			}
		}
		sc.close();
		if (b.board[oponentLine][oponentCol].equals(player)) {
			int temp = playerLine;
			playerLine = oponentLine;
			oponentLine = temp;
			temp = playerCol;
			playerCol = oponentCol;
			oponentCol = temp;
		}
		b.setPositions(playerLine, playerCol, oponentLine, oponentCol);
		b.setPlayers(b.board[playerLine][playerCol],
				b.board[oponentLine][oponentCol]);
		nextMove(b);
	}
}
