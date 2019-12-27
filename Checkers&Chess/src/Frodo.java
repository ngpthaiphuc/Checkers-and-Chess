/*
 * A frodo is initialized with a CheckersRunnyThing as a parameter rather than assigning a new one so as to avoid errors (each frodo needs to 
 * use the same CheckersRunnyThing)
 * the String "bottom" is not used in the code but rather just existing for ease on memory when writing the move and jump methods
 * A frodo is a piece and requires a position (separate from the square it is assigned to because a piece moves while a square is stationary)
 */

public class Frodo extends Piece{
	
	private GUIRunnyThingy board;
	public Frodo(GUIRunnyThingy b, Position p) {
		super(b.getImage("frodo"), p);
		board = b;
	}
	
	//Accessor to determine the type of piece, useful when determining whether jumping is allowed
	public String getType() {
		return "Frodo";
	}
	
	//Accessor to initialize KingFrodo for GUI
	public GUIRunnyThingy getBoard() {
		return board;
	}
	
	/* 
	 * Checks if the positioning and piece types of each square passed allow for single spaced diagonal movement
	 * The color of the square to be moved to has to be black(ensures diagonal movement), 1 row and 1 column away in the correct direction, 
	 * the square to be moved to has to be empty, and the square the piece should move from has to contain the piece type of this class.
	 */
	public boolean move(Square from, Square to) {
		if(from.getDoggo() != null && to.getDoggo() == null && to.getPosition().getRow() ==  from.getPosition().getRow() -1 &&
				Math.abs(to.getPosition().getColumn() - from.getPosition().getColumn()) == 1) {
			this.setPosition(to.getPosition());
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(board.getImage("frodo"));
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
		}
		return false;
	}
	
	/* Same as Coco!
	 * Checking if starting square has a piece, ending square has no piece, and a piece of the opposite type is in the middle. 
	 * True is returned if jump operation is successful based in the conditions checked
	 * The doggos (Pieces) in each square (from, to, and the one jumped over) are set to their piece values after the action
	 * Images assigned to buttons are then changed to display the results of the jump action
	 * This method will be called in the main class if the from passed (first clicked square) contains a piece of type coco
	 * 2 if statements for 2 cases for jumping (to the left and to the right)
	 */
	public boolean jump(Square from, Square to) {
		Position startPos = from.getPosition();
		Position jumpPos = to.getPosition();
		//The square being jumped over
		Square middleR = null;
		Square middleL = null;
		if(startPos.getRow() -1 < 8 && startPos.getColumn() +1 < 8)
			middleR = board.findSquareWithPos(new Position(startPos.getRow() -1, startPos.getColumn() +1));
		if(startPos.getRow() -1 < 8 && startPos.getColumn() -1 >= 0)
			middleL = board.findSquareWithPos(new Position(startPos.getRow() -1, startPos.getColumn() -1));
		
		//Jumping right
		if(from.getDoggo() != null && to.getDoggo() == null && startPos.getRow() -2 == jumpPos.getRow() && middleR != null &&
				(startPos.getColumn() +2 == jumpPos.getColumn() && middleR.getDoggo() != null &&
				(middleR.getDoggo().getType().equals("Coco") || middleR.getDoggo().getType().equals("Queen")))) {
			//Removing the piece being jumped over
			board.removePiece(middleR);
			//Jumping to the indicated square -> add new doggo to indicated square and delete existing doggo from the first square
			this.setPosition(to.getPosition());
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(board.getImage("frodo"));
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
		}
		//Jumping left
		else if(from.getDoggo() != null && to.getDoggo() == null && startPos.getRow() -2  == jumpPos.getRow() && middleL != null &&
				(startPos.getColumn() -2 == jumpPos.getColumn() && middleL.getDoggo() != null &&
				(middleL.getDoggo().getType().equals("Coco") || middleL.getDoggo().getType().equals("Queen")))) {
			//Removing the piece being jumped over
			board.removePiece(middleL);
			//Jumping to the indicated square
			this.setPosition(to.getPosition());
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(board.getImage("frodo"));
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
		}
		return false;
	}
}