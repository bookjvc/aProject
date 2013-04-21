import java.util.*;

class Board{
	 String[][] board;
	 int lines,
	 	 columns,
	 	 playerLine ,
	 	 playerColumn,
	 	 oponentLine ,
	 	 oponentColumn;
	 String player,oponent;
	
	public Board(int lines , int columns){
		this.lines 	 = lines;
		this.columns = columns;
		board = new String[lines][columns];
	}
	
	public Board(Board b){
		this(b.lines,b.columns);
		setPositions(b.playerLine, b.playerColumn, b.oponentLine, b.oponentColumn);
		for(int i=0 ; i<lines;i++){
			for(int j=0 ; j<columns;j++){
				set(i, j, b.board[i][j]);
			}
		}
		setPlayers(b.player, b.oponent);
	}
	
	/*Metoda care primeste ca parametri doua int-uri si un string pentru a seta la linia line si coloana column din matricea board stringul s */
	public void set(int line , int column , String s){
		board[line][column] = s;
	}
	
	public void setPlayers(String player , String oponent){
		this.player = player;
		this.oponent = oponent;
	}
	
	public void setPositions(int currentL , int currentC , int oCurrentL , int oCurrentC){
		this.playerLine    = currentL;
		this.playerColumn  = currentC;
		this.oponentLine   = oCurrentL;
		this.oponentColumn = oCurrentC;
	}
	
	public void getAllPosMoves(ArrayList<Move> toFill , String toMove){
		if(toMove.equals("B52")){
			if(board[playerLine-1][playerColumn].equals("-")){
				toFill.add(new Move("UP"));
			}
			
			if(board[playerLine+1][playerColumn].equals("-")){
				toFill.add(new Move("DOWN"));
			}
			
			if(board[playerLine][playerColumn-1].equals("-")){
				toFill.add(new Move("LEFT"));
			}
			
			if(board[playerLine][playerColumn +1].equals("-")){
				toFill.add(new Move("RIGHT"));
			}
				
		}else{
			if(board[oponentLine-1][oponentColumn].equals("-")){
				toFill.add(new Move("UP"));
			}
			
			if(board[oponentLine+1][oponentColumn].equals("-")){
				toFill.add(new Move("DOWN"));
			}
			
			if(board[oponentLine][oponentColumn-1].equals("-")){
				toFill.add(new Move("LEFT"));
			}
			
			if(board[oponentLine][oponentColumn +1].equals("-")){
				toFill.add(new Move("RIGHT"));
			}
		}
		
	}
	
	public String toString(){
		String ret = "";
		for(int i=0 ; i<lines ; i++){
			for(int j=0 ; j<columns ; j++){
				ret+=board[i][j];
			}
			ret+="\n";
		}
		return ret;
	}
}

class Move{
	String move;
	
	public Move(String move){
		this.move = move;
	}
	
	public void executeMove(Board b,String playAs){
		
		if(move.equals("UP")){
			if(playAs.equals("B52")){
				b.board[b.playerLine-1][b.playerColumn] = b.player;
				b.playerLine -= 1;
			}else{
				b.board[b.oponentLine-1][b.oponentColumn] = b.oponent;
				b.oponentLine -= 1;
			}
			return;
		}
		
		if(move.equals("DOWN")){
			if(playAs.equals("B52")){
				b.board[b.playerLine+1][b.playerColumn] = b.player;
				b.playerLine += 1;
			}else{
				b.board[b.oponentLine+1][b.oponentColumn] = b.oponent;
				b.oponentLine += 1;
			}
			return;
		}
		
		if(move.equals("LEFT")){
			if(playAs.equals("B52")){
				b.board[b.playerLine][b.playerColumn-1] = b.player;
				b.playerColumn -= 1;
			}else{
				b.board[b.oponentLine][b.oponentColumn-1] = b.oponent;
				b.oponentColumn -= 1;
			}
			return;
		}
		
		if(move.equals("RIGHT")){
			if(playAs.equals("B52")){
				b.board[b.playerLine][b.playerColumn+1] = b.player;
				b.playerColumn += 1;
			}else{
				b.board[b.oponentLine][b.oponentColumn+1] = b.oponent;
				b.oponentColumn += 1;
			}
			return;
		}
	}
	
	public String toString(){
		return move;
	}
	
}

public class Solution {
	
	public static final int INFINITY = 9999;
	public static final int MAXDEPTH = 10;
	

public static int maxi(int depth , Board b , Move nextMove , long outOfTime){
	
	
	if(depth == 0){  			
		return INFINITY;
	}
	
	if(System.currentTimeMillis() >= outOfTime){
		return MAXDEPTH - depth;
	}
	
	ArrayList<Move> moves = new ArrayList<Move>();
	
	b.getAllPosMoves(moves, "B52");
	
	if(moves.size() == 0){
		return -INFINITY;
	}
	
	int maxi = -INFINITY;
	
	for(Move it : moves){
		Board copie = new Board(b);
		int r=0;
		it.executeMove(copie, "B52");
		r = mini(depth-1,copie,outOfTime);
		
		if(maxi < r){
			maxi = r;
			nextMove.move = it.move;
		}
	}
	
	return maxi;
}

public static int mini(int depth , Board b , long outOfTime){
	if(depth == 0){  			
		return MAXDEPTH;
	}
	
	if(System.currentTimeMillis() >= outOfTime){
		return MAXDEPTH - depth;
	}
	
	ArrayList<Move> moves = new ArrayList<Move>();
	
	b.getAllPosMoves(moves, "OPONENT");
	
	if(moves.size() == 0){
		return INFINITY;
	}
	
	int mini = INFINITY;
	
	for(Move it : moves){
		Board copie = new Board(b);
		int r = 0;
		it.executeMove(copie, "OPONENT");
		r = maxi(depth-1,copie,new Move("ASD"),outOfTime);
		if(mini > r){
			mini = r;
		}
	}
	return mini;
	
}
	

/* Head ends here */
static void nextMove(Board b){
   Move move = new Move("s");
   long outOfTime = System.currentTimeMillis() + 3800;
   maxi(MAXDEPTH, b, move, outOfTime);
   System.out.println(move);
  }
/* Tail starts here */
@SuppressWarnings({ "resource", "unused" })
public static void main(String[] args) {
    
	Scanner sc = new Scanner(System.in);
	String playerID = sc.nextLine();
	String[] positions = sc.nextLine().split(" ");
	int playerLine     = Integer.parseInt(positions[0]),
		playerCol      = Integer.parseInt(positions[1]),
		oponentLine    = Integer.parseInt(positions[2]),
		oponentCol     = Integer.parseInt(positions[3]);
	
	String[] boardSize = sc.nextLine().split(" ");
	
	int boardL         = Integer.parseInt(boardSize[0]),
		boardC		   = Integer.parseInt(boardSize[1]);
	
	Board b = new Board(boardL,boardC);
	
	for(int i=0 ; i<boardL ; i++){
		char[] line = sc.nextLine().toCharArray();
		for(int j=0 ; j<boardC ; j++){
			b.set(i, j, line[j]+"");
		}
	}
	if(b.board[oponentLine][oponentCol].equals(playerID)){
		int temp = playerLine;
		playerLine = oponentLine;
		oponentLine = temp;
		temp = playerCol;
		playerCol = oponentCol;
		oponentCol = temp;
	}
	b.setPositions(playerLine, playerCol, oponentLine, oponentCol);
	b.setPlayers(b.board[playerLine][playerCol], b.board[oponentLine][oponentCol]);
	nextMove(b);
    }
}
