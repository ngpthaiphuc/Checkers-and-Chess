public class KingFrodo extends Frodo {
	//A KingFrodo is a Frodo that can move in both directions with a different imageIcon (crown)
	private GUIRunnyThingy board;
	public KingFrodo(GUIRunnyThingy b, Position p) {
		super(b, p);
		setImage(b.getImage("king"));
		board = super.getBoard();		
	}
	
	//Used to determine the type of piece, useful when determining whether jumping is allowed
	public String getType() {
		return "King";
	}
	
	//Same as Frodo but row to move to can be above or below the initial piece position
	public boolean move(Square from, Square to){
		if(from.getDoggo() != null && to.getDoggo() == null && from.getDoggo().getType().equals("King") &&
				Math.abs(from.getPosition().getRow() - to.getPosition().getRow()) == 1 && 
				Math.abs(from.getPosition().getColumn() - to.getPosition().getColumn()) == 1) {
			this.setPosition(to.getPosition());
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(board.getImage("king"));
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
			return true;
		}
		return false;
	}
	
	//Same as Frodo but can jump in either direction
	public boolean jump(Square from, Square to) {
		Position startPos = from.getPosition();
		Position jumpPos = to.getPosition();
		//The square being jumped over
		Square middle = board.findSquareWithPos(new Position((startPos.getRow() + jumpPos.getRow()) /2,
				(startPos.getColumn() + jumpPos.getColumn()) /2));
		
		if(from.getDoggo() != null && to.getDoggo() == null && from.getDoggo().getType().equals("King") &&
				Math.abs(from.getPosition().getRow() - to.getPosition().getRow()) == 2 && 
				Math.abs(from.getPosition().getColumn() - to.getPosition().getColumn()) == 2 &&
				(middle.getDoggo().getType().equals("Coco") || (middle.getDoggo().getType().equals("Queen")))){
			//Removing the piece being jumped over
			board.removePiece(middle);
			//Jumping to the indicated square -> add new doggo to indicated square and delete existing doggo from the first square
			this.setPosition(to.getPosition());
			to.setDoggo(from.getDoggo());
			from.setDoggo(null);
			int toRow = to.getPosition().getRow();
			int toCol = to.getPosition().getColumn();
			board.getButtonArr()[toRow][toCol].getButton().setIcon(board.getImage("king"));
			int fromRow = from.getPosition().getRow();
			int fromCol = from.getPosition().getColumn();
			board.getButtonArr()[fromRow][fromCol].getButton().setIcon(null);
//			board.findSquareWithPos(new Position((startPos.getRow() + jumpPos.getRow())/2, (startPos.getColumn() + jumpPos.getColumn())/2)).getButton().setIcon(null);
//			board.findSquareWithPos(new Position((startPos.getRow() + jumpPos.getRow())/2, (startPos.getColumn() + jumpPos.getColumn())/2)).setDoggo(null);
			return true;
		}
		return false;
	}
}