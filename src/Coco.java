/*
 * A coco is initialized with a CheckersRunnyThing as a parameter rather than assigning a new one so as to avoid errors (each coco needs to 
 * use the same CheckersRunnyThing)
 * the String "top" is not used in the code but rather just existing for ease on memory when writing the move and jump methods
 * A coco is a piece and requires a position (separate from the square it is assigned to because a piece moves while a suare is stationary)
 */ 

public class Coco extends Piece{
	private GUIRunnyThingy board;
	public Coco(GUIRunnyThingy b, Position p) {
		super(GUIRunnyThingy.coco, "top" , p);
		board = b;
	}
	
	//Used to determine the type of piece, useful when  determining whether jumping is allowed
	public String getType() {
		return "coco";
	}
	
	/*
	 * Checks if the positioning and piece types of each square passed allow for single spaced diagonal movement
	 * The color of the square to be moved to has to be black(ensures diagonal movement), 1 row and 1 column away in the correct direction, 
	 * the square to be moved to has to be empty, and the square the piece should move from has to contain the piece type of this class.
	 */
	public boolean move(Square from, Square to) {
		if(from.getColor().equals(GUIRunnyThingy.carolinaBlue) && to.getDoggo() == null &&
				to.getColor().equals(GUIRunnyThingy.carolinaBlue) && to.getPosition().getRow()  ==  from.getPosition().getRow() + 1
				&& Math.abs(to.getPosition().getColumn() - from.getPosition().getColumn()) == 1) {
			/*
			 * By taking in board as a parameter, coco is able to access buttonArr and alter the buttons that make up the checker board.
			 * To move a piece, the position of the piece itself is changed, the piece instance variable belonging to the 
			 * square to be moved to is assigned as the piece being moved there, and the icons are set accordingly to show correct movement of the piece
			 * The method returns a boolean indicating whether or not a move action was successful
			 */
			this.setPosition(to.getPosition());
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(GUIRunnyThingy.coco);
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
		}
		return false;
	}
	
	public boolean jump(Square from, Square to) {
		Position startPos = from.getPosition();
		Position jumpPos = to.getPosition();
		/*
		 * Checking if starting square has a piece, ending square has no piece, and a piece of the opposite type is in the middle. 
		 * True is returned if jump operation is successful based in the conditions checked
		 * The doggos (Pieces) in each square (from, to, and the one jumped over) are set to their piece values after the action
		 * Images assigned to buttons are then changed to display the results of the jump action
		 * This method will be called in the main class if the from passed (first clicked square) contains a piece of type coco
		 * 2 if statements for 2 cases for jumping (to the left and to the right)
		 */
		if(from.getDoggo() != null && to.getDoggo() == null && startPos.getRow() + 2 == jumpPos.getRow() &&  startPos.getColumn() +2 == jumpPos.getColumn() &&
				board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() +1)).getDoggo() != null &&
				(board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() +1)).getDoggo().getType().equals("frodo") ||
				board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() +1)).getDoggo().getType().equals("kingFrodo"))) {
			this.setPosition(to.getPosition());
			Square middle = board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() +1));
			middle.setDoggo(null);
			int midRow = middle.getPosition().getRow();
			int midCol = middle.getPosition().getColumn();
			board.getButtonArr()[midRow][midCol].getButton().setIcon(null);
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(GUIRunnyThingy.coco);
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
			
		}
		
		
		if(from.getDoggo() != null && from != to && to.getDoggo() == null && startPos.getRow() + 2  == jumpPos.getRow() &&  startPos.getColumn() -2 == jumpPos.getColumn() && 
				board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() -1)).getDoggo() != null && 
				(board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() -1)).getDoggo().getType().equals("frodo") ||
				board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() -1)).getDoggo().getType().equals("kingFrodo"))) {
			this.setPosition(to.getPosition());
			Square middle = board.findSquareWithPos(new Position(startPos.getRow() +1, startPos.getColumn() -1));
			middle.setDoggo(null);
			int midRow = middle.getPosition().getRow();
			int midCol = middle.getPosition().getColumn();
			board.getButtonArr()[midRow][midCol].getButton().setIcon(null);
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(GUIRunnyThingy.coco);
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
		}
		 
		return false;
	}
	
	public GUIRunnyThingy getBoard() {
		return board;
	}
}