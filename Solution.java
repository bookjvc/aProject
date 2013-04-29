import java.util.*;

/**
 * Clasa ce defineste tipul Pair.
 * 
 * @author B-52
 * 
 */
class Pair implements Comparable<Pair> {

	int x; // coordonata x
	int y; // coordonata y
	int cost = 9999;

	/**
	 * Constructor ce initializeaza datele acestui obiect
	 * 
	 * @param l
	 * @param c
	 */
	public Pair(int l, int c) {
		x = l;
		y = c;
	}

	@Override
	public int compareTo(Pair arg0) {
		// TODO Auto-generated method stub
		return arg0.cost - cost;
	}

}

/**
 * Clasa ce defineste tipul Board
 * 
 * @author B-52
 * 
 */
class Board {
	String[][] board; // matricea jocului
	int lines, columns; // dimensiunea
	int playerLine, playerColumn, oponentLine, oponentColumn;// informatii
																// privind cei 2
																// jucatori
	String player, oponent; // identificatori

	/**
	 * Constructor pentru tipul Board ce initializeaza matricea dupa
	 * dimensiunile primite ca parametru
	 * 
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
	 * Constructor ce construieste un nou obiect dupa informatiile celui primit
	 * ca parametru
	 * 
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
	 * Metoda ce atribuie un string elementului matricei specificat prin
	 * parametrii primiti
	 * 
	 * @author B-52
	 * @param line
	 * @param column
	 * @param s
	 */
	public void set(int line, int column, String s) {
		board[line][column] = s;
	}

	/**
	 * Metoda ce initializeaza identificatorii jucatorilor
	 * 
	 * @param player
	 * @param oponent
	 */
	public void setPlayers(String player, String oponent) {
		this.player = player;
		this.oponent = oponent;
	}

	/**
	 * Metoda ce seteaza pozitiile curente ale celor doi jucatori
	 * 
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
	 * Metoda ce adauga intr-o lista primita ca parametru toate miscarile
	 * posibilie ale unui jucator(si acesta fiind primit ca parametru)
	 * 
	 * @author B-52
	 * @param toFill
	 * @param toMove
	 */
	public void getAllPosMoves(ArrayList<Move> toFill, String toMove) {

		// Pentru fiecare jucator verificam directiile U-D-R-L
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
 * Clasa ce defineste tipul Move
 * 
 * @author B-52
 * 
 */
class Move {
	String move;

	/**
	 * Constructor ce initializeaza mutarea.
	 * 
	 * @author B-52
	 * @param move
	 */
	public Move(String move) {
		this.move = move;
	}

	/**
	 * Metoda ce executa mutarea pe o tabla primita ca parametru
	 * 
	 * @author B-52
	 * @param b
	 * @param playAs
	 */
	public void executeMove(Board b, String playAs) {

		// cazul in care move.equals("UP")...modificam elementul de deasupra
		// celui curent si updatam pozitia curenta a jucatorului,
		// Idem pentru celelalte cazuri
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
	 * @author B-52 toString():D
	 */
	public String toString() {
		return move;
	}

}

/**
 * Clasa ce defineste tipul Solution
 * 
 * @author B-52
 * 
 */
public class Solution {

	public static final int INFINITY = 9999;
	public static final int MAXDEPTH = 10;

	/**
	 * Metoda ce verifica daca
	 * cei doi boti nu se pot intalni
	 * @param b
	 * @return
	 */
	public static boolean checkIfTrapped(Board b) {

		int myLine = b.playerLine, myColumn = b.playerColumn;

		Board copy = new Board(b);

		Stack<Pair> check = new Stack<Pair>();

		int lin, col;

		check.push(new Pair(myLine, myColumn));

		while (!check.isEmpty()) {
			lin = check.peek().x;
			col = check.peek().y;
			check.pop();
			copy.board[lin][col] = "v"; // marchez verificat

			if (copy.board[lin - 1][col].equals("-")) {
				check.push(new Pair(lin - 1, col));
			}
			if (copy.board[lin + 1][col].equals("-")) {
				check.push(new Pair(lin + 1, col));
			}
			if (copy.board[lin][col - 1].equals("-")) {
				check.push(new Pair(lin, col - 1));
			}
			if (copy.board[lin][col + 1].equals("-")) {
				check.push(new Pair(lin, col + 1));
			}
		}
		//verific daca oponentul are elemente verificate in preajma
		if (copy.board[b.oponentLine - 1][b.oponentColumn].equals("v"))
			return false;
		if (copy.board[b.oponentLine + 1][b.oponentColumn].equals("v"))
			return false;
		if (copy.board[b.oponentLine][b.oponentColumn - 1].equals("v"))
			return false;
		if (copy.board[b.oponentLine][b.oponentColumn + 1].equals("v"))
			return false;
		return true;
	}
	/**
	 * Metoda ce calculeaza un scor
	 * in functie de apropierea celor doi boti
	 * @param b
	 * @return
	 */
	public static int evaluateSame(Board b) {
		return 50 - Math.abs(b.playerLine - b.oponentLine) + 50
				- Math.abs(b.playerColumn - b.oponentColumn);
	}
	
	/**
	 * Metoda care numara cu exactitate
	 * numarul de elemente accesibile unui bot
	 * @param b
	 * @param player
	 * @return
	 */
	public static int evaluateBun(Board b, int player) {

		int myLine = b.playerLine, myColumn = b.playerColumn;
		if (player == 2) {
			myLine = b.oponentLine;
			myColumn = b.oponentColumn;
		}
		Board copy = new Board(b);

		Stack<Pair> check = new Stack<Pair>();

		int lin, col;

		check.push(new Pair(myLine, myColumn));

		while (!check.isEmpty()) {
			lin = check.peek().x;
			col = check.peek().y;
			check.pop();
			copy.board[lin][col] = "v";

			if (copy.board[lin - 1][col].equals("-")) {
				check.push(new Pair(lin - 1, col));
			}
			if (copy.board[lin + 1][col].equals("-")) {
				check.push(new Pair(lin + 1, col));
			}
			if (copy.board[lin][col - 1].equals("-")) {
				check.push(new Pair(lin, col - 1));
			}
			if (copy.board[lin][col + 1].equals("-")) {
				check.push(new Pair(lin, col + 1));
			}
		}
		int count = 0;
		for (int i = 0; i < b.lines; i++) {
			for (int j = 0; j < b.columns; j++) {
				if (copy.board[i][j].equals("v"))
					count++;
			}
		}
		return count;
	}

	/**
	 * Metoda maxi2
	 * 
	 * @param depth
	 * @param b
	 * @param nextMove
	 * @param outOfTime
	 * @return 
	 */
	public static int maxi2(int depth, Board b, Move nextMove, long outOfTime) {

		if (depth == 0) {
			return evaluate(b, b.playerLine, b.playerColumn, b.player);
		}

		if (System.currentTimeMillis() >= outOfTime) { // daca ne apropiem de
														// cele 5s
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
			it.executeMove(copie, "B52"); // execut mutarea
			r = mini2(depth - 1, copie, outOfTime);

			if (maxi < r) {
				maxi = r;
				nextMove.move = it.move;
			}

		}

		return maxi;
	}

	/**
	 * Metoda mini2
	 * 
	 * @param depth
	 * @param b
	 * @param outOfTime
	 * @return 
	 */
	public static int mini2(int depth, Board b, long outOfTime) {
		if (depth == 0) {
			return evaluate(b, b.oponentLine, b.oponentColumn, "Oponent");
		}
		if (System.currentTimeMillis() >= outOfTime) { // daca ramanem fara timp
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
			r = maxi2(depth - 1, copie, new Move("ASD"), outOfTime);
			if (mini > r) {
				mini = r;
			}
		}
		return mini;

	}
	
	/**
	 * Metoda maxi
	 * 
	 * @author B-52
	 * @param depth
	 * @param b
	 * @param nextMove
	 * @param outOfTime
	 * @return 
	 */
	public static int maxi(int depth, Board b, Move nextMove, long outOfTime) {

		if (depth == 0) {
			return evaluateSame(b);
		}

		if (System.currentTimeMillis() >= outOfTime) { // daca ne apropiem de
														// cele 5s
			return evaluateSame(b);
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
			it.executeMove(copie, "B52"); // execut mutarea
			r = mini(depth - 1, copie, outOfTime);

			if (maxi < r) {
				maxi = r;
				nextMove.move = it.move;
			}

		}

		return maxi;
	}
	/**
	 * Metoda goLeft
	 * ce imi numara numarul de mutari
	 * din stanga
	 * @param b
	 * @return
	 */
	public static int goLeft(Board b) {
		int contor = 0;
		int i = b.playerLine;
		int j = b.playerColumn - 1;
		while (b.board[i][j].equals("-")) {
			contor++;
			j--;
		}
		return contor;
	}
	/**
	 * Metoda goRight
	 * ce imi numara numarul de mutari
	 * din dreapta
	 * @param b
	 * @return
	 */
	public static int goRight(Board b) {
		int contor = 0;
		int i = b.playerLine;
		int j = b.playerColumn + 1;
		while (b.board[i][j].equals("-")) {
			contor++;
			j++;
		}
		return contor;
	}

	/**
	 * Metoda mini
	 * 
	 * @param depth
	 * @param b
	 * @param outOfTime
	 * @return
	 */
	public static int mini(int depth, Board b, long outOfTime) {

		if (depth == 0) {
			return evaluateSame(b);
		}
		if (System.currentTimeMillis() >= outOfTime) { // daca ramanem fara timp

			return evaluateSame(b);

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
	 * Metoda maxi 1
	 * @param depth
	 * @param b
	 * @param nextMove
	 * @param outOfTime
	 * @return
	 */
	public static int maxi1(int depth, Board b, Move nextMove, long outOfTime) {

		if (depth == 0) {
			return evaluateBun(b, 1);
		}

		if (System.currentTimeMillis() >= outOfTime) { // daca ne apropiem de
														// cele 5s
			return evaluateBun(b, 1);
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
			it.executeMove(copie, "B52"); // execut mutarea
			r = mini1(depth - 1, copie, outOfTime);

			if (maxi < r) {
				maxi = r;
				nextMove.move = it.move;
			}

		}

		return maxi;
	}
	/**
	 * Metoda veche evaluate
	 * @param b
	 * @param line
	 * @param column
	 * @param player
	 * @return
	 */
	public static int evaluate(Board b, int line, int column, String player) {

		ArrayList<Pair> lista = new ArrayList<Pair>(); // lista este folosita
														// pentru
														// a tine perechile
														// neevaluate

		lista.add(new Pair(line, column));

		Board copie = new Board(b);

		int contor = 0, lin, col;

		while (lista.size() > 0) { // cat timp sunt elemente de evaluat
			lin = lista.get(lista.size() - 1).x;
			col = lista.get(lista.size() - 1).y;

			copie.board[lin][col] = player; // marchez perechea ca fiind
											// evaluata
											// (execut mutarea)
			lista.remove(lista.size() - 1); // sterg perechea

			// verific daca o directie este accesibila
			if (copie.board[lin - 1][col].equals("-")) {
				lista.add(new Pair(lin - 1, col));

			}

			if (copie.board[lin + 1][col].equals("-")) {
				lista.add(new Pair(lin + 1, col));

			}

			if (copie.board[lin][col + 1].equals("-")) {
				lista.add(new Pair(lin, col + 1));

			}

			if (copie.board[lin][col - 1].equals("-")) {
				lista.add(new Pair(lin, col - 1));

			}
			contor++; // dupa un element evaluat contorizez
		}
		// practic metoda returneaza numarul de elemente
		// de pe harta accesibile jucatorului
		return contor;
	}
	

	/**
	 * Metoda mini
	 * 
	 * @param depth
	 * @param b
	 * @param outOfTime
	 * @return
	 */
	public static int mini1(int depth, Board b, long outOfTime) {
		if (depth == 0) {
			return evaluateBun(b, 2);
		}
		if (System.currentTimeMillis() >= outOfTime) { // daca ramanem fara timp
			return evaluateBun(b, 2);

		}

		ArrayList<Move> moves = new ArrayList<Move>();

		b.getAllPosMoves(moves, "OPONENT");

		if (moves.size() == 0) {
			return -INFINITY;
		}

		int mini = INFINITY;

		for (Move it : moves) {
			Board copie = new Board(b);
			int r = 0;
			it.executeMove(copie, "OPONENT");
			r = maxi1(depth - 1, copie, new Move("ASD"), outOfTime);
			if (mini > r) {
				mini = r;
			}
		}
		return mini;

	}
	/**
	 * verifica daca cei doi boti
	 * sunt apropiati
	 * @param b
	 * @return
	 */
	public static boolean checkIfNear(Board b) {
		if (Math.abs(b.playerLine - b.oponentLine) < 4
				&& Math.abs(b.playerColumn - b.oponentColumn) < 4) {
			return true;
		}
		return false;
	}

	/**
	 * Metoda nextMove ce apeleaza metoda maxi si printeaza mutarea ce trebuie
	 * facuta
	 * 
	 * @param b
	 */
	static void nextMove(Board b) {
		Move move = new Move("s");
	
			long outOfTime = System.currentTimeMillis() + 4000;
			//daca suntem fata in fata
			if (Math.abs(b.playerLine - b.oponentLine) <= 1
					&& b.playerColumn - b.oponentColumn == 0) {
				if (b.board[b.playerLine][b.playerColumn - 1].equals("-")) {
					if (b.board[b.playerLine][b.playerColumn + 1].equals("-")) {
						if (goLeft(b) > goRight(b)) {
							move.move = "LEFT";
						} else {
							move.move = "RIGHT";
						}
					}
				}
			} else if (checkIfTrapped(b)) { //daca suntem separati
				maxi1(MAXDEPTH, b, move, outOfTime);
			} else if (checkIfNear(b)) { // daca suntem apropiati
				maxi(MAXDEPTH, b, move, outOfTime);
			} else {
				maxi(MAXDEPTH, b, move, outOfTime);
			}

			if (move.move.equals("s")) {//daca nu a mers pana acum
				maxi2(MAXDEPTH, b, move, outOfTime);
				System.out.println(move);
			} else
				System.out.println(move);
		
	}

	/* Tail starts here */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		String player = sc.nextLine();
		String[] positions = sc.nextLine().split(" ");
		int playerLine = Integer.parseInt(positions[0]), playerCol = Integer
				.parseInt(positions[1]), oponentLine = Integer
				.parseInt(positions[2]), oponentCol = Integer
				.parseInt(positions[3]);

		String[] boardSize = sc.nextLine().split(" ");

		int boardL = Integer.parseInt(boardSize[0]), boardC = Integer
				.parseInt(boardSize[1]);

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
