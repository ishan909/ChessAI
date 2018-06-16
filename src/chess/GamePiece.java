

public interface GamePiece {

  // check (king only)
  public boolean isInCheck();
  // checks validity
  public boolean validMove();
  // returns type of piece as String
  public String getType();
  // can't take your own piece, valid square to move on
  public boolean canAttack();
  // alive
  public boolean alive();


}
