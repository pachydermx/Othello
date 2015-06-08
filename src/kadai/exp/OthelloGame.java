package kadai.exp;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloGame {
    public boolean currentWhite;
    public OthelloPieceState[][] pieces;
    private int[][] directions;
    public OthelloGame() {
        for (OthelloPieceState[] row : pieces = new OthelloPieceState[8][8]){
            for(OthelloPieceState piece: row) {
                piece = OthelloPieceState.None;
            }
        }
        this.directions = new int[][]{{-1, 0}, {-1, -1}, {1, -1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    }

    public boolean checkAvailable(int x, int y, OthelloPieceState state){
        boolean available = false;
        int[] distance = new int[8];
        SearchState[] states = new SearchState[8];
        for (int i : distance) i = 1;
        for (SearchState i : states) i = SearchState.Advance;

        // check border
        boolean[] isBorder = this.checkBorder(x, y);
        for (int i = 0; i < 8; i++){
            if (isBorder[i]) states[i] = SearchState.Out;
        }
        // check adjunct ... if adjunct is the same then stop
        // check further ... if none advance then stop
        while (true){
            int advances  = 0;
            for (SearchState i : states){
                if(i == SearchState.Advance){
                    advances++;
                }
            }
            if(advances == 0) break;
        }

        return available;
    }

    private boolean[] checkBorder(int x, int y){
        boolean[] available = new boolean[8];
        for (boolean i : available) i = true;
        if (x == 0){
            available[0] = false;
            available[1] = false;
            available[7] = false;
        }
        if (y == 0){
            available[1] = false;
            available[2] = false;
            available[3] = false;
        }
        if (x == 7){
            available[3] = false;
            available[4] = false;
            available[5] = false;
        }
        if (y == 7){
            available[5] = false;
            available[6] = false;
            available[7] = false;
        }
        return available;
    }

    private OthelloPieceState[] getState(int x, int y, int distance){
        OthelloPieceState[] states = new OthelloPieceState[8];
        for (int i = 0; i < 8; i++){
            int actX = x + distance * this.directions[i][0];
            int actY = y + distance * this.directions[i][1];
            if (actX > 0 && actX < 8 && actY > 0 && actY < 8){
                states[i] = this.pieces[actX][actY];
            } else {
                states[i] = OthelloPieceState.None;
            }
        }
        return states;
    }
}
