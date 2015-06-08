package kadai.exp;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloGame {
    public Boolean currentWhite;
    public OthelloPieceState[][] pieces;
    public OthelloGame() {
        for (OthelloPieceState[] row : pieces = new OthelloPieceState[8][8]){
            for(OthelloPieceState piece: row) {
                piece = OthelloPieceState.None;
            }
        }
    }

    public Boolean checkAvailable(int x, int y, OthelloPieceState state){
        Boolean available = false;
        

        return available;
    }
}
