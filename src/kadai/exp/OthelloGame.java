package kadai.exp;

import java.util.Arrays;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloGame {
    public OthelloPieceState[][] pieces;
    private int[][] directions;
    public int blackScore, whiteScore, possibleSteps;

    public OthelloGame() {
        this.pieces = new OthelloPieceState[8][8];
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                this.pieces[i][j] = OthelloPieceState.None;
            }
        }
        this.directions = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    }

    private int[] checkAvailable(int x, int y, OthelloPieceState state){
        int[] distance = new int[8];
        SearchState[] states = new SearchState[8];
        for (int i = 0; i < 8; i++){
            states[i] = SearchState.Advance;
            distance[i] = 0;
        }

        // check adjunct ... if adjunct is the same then stop
        OthelloPieceState[] adjunctState = this.getState(x, y, 1);
        for (int i = 0; i < 8; i++){
            if (state == adjunctState[i] || adjunctState[i] == OthelloPieceState.None || adjunctState[i] == OthelloPieceState.Possible){
                states[i] = SearchState.Out;
                distance[i] = 0;
            }
        }
        // check further ... if none advance then stop
        for (int i = 2; i < 8; i++){
            OthelloPieceState[] roundState = this.getState(x, y, i);
            for (int j = 0; j < 8; j++){
                if (states[j] == SearchState.Advance){
                    if (roundState[j] == OthelloPieceState.None || roundState[j] == OthelloPieceState.Possible){
                        states[j] = SearchState.Out;
                        distance[i] = 0;
                    } else if (roundState[j] == state){
                        states[j] = SearchState.Found;
                        distance[j] = i;
                    }
                }
            }
            //System.out.println(String.format("[%s,%s,%s,%s,%s,%s,%s,%s]",states[0],states[1],states[2],states[3],states[4],states[5],states[6],states[7]));
            // check end
            int advances  = 0;
            for (SearchState j : states){
                if(j == SearchState.Advance){
                    advances++;
                }
            }
            if(advances == 0) break;
        }
        //System.out.println(Arrays.toString(distance));
        return distance;
    }

    private OthelloPieceState[] getState(int x, int y, int distance){
        OthelloPieceState[] states = new OthelloPieceState[8];
        for (int i = 0; i < 8; i++){
            int actX = x + distance * this.directions[i][0];
            int actY = y + distance * this.directions[i][1];
            if (actX > 0 && actX < 8 && actY > 0 && actY < 8){
                if (this.pieces[actX][actY] != null) {
                    states[i] = this.pieces[actX][actY];
                } else {
                    states[i] = OthelloPieceState.None;
                }
            } else {
                states[i] = OthelloPieceState.None;
            }
        }
        //System.out.println(String.format("(%d,%d)-%d, [%s,%s,%s,%s,%s,%s,%s,%s]", x, y, distance, states[0], states[1], states[2], states[3], states[4], states[5], states[6], states[7]));
        return states;
    }

    public boolean placePiece(int x, int y, OthelloPieceState state, boolean act){
        if (this.pieces[x][y] != OthelloPieceState.None && this.pieces[x][y] != OthelloPieceState.Possible){
            return false;
        }

        int successBuffer = 8;
        int[] distance = this.checkAvailable(x, y, state);
        for (int i = 0; i < 8; i ++){
            if (distance[i] > 0){
                if (act) {
                    for (int j = 0; j < distance[i]; j++) {
                        int actX = x + this.directions[i][0] * j;
                        int actY = y + this.directions[i][1] * j;
                        this.pieces[actX][actY] = state;
                        //System.out.println(String.format("Turn %d,%d to %s", actX, actY, state));
                    }
                }
            } else {
                successBuffer--;
            }
        }
        if (successBuffer == 0){
            return false;
        } else {
            return true;
        }
    }

    public void scan(OthelloPieceState state){
        this.blackScore = 0;
        this.whiteScore = 0;
        this.possibleSteps = 0;
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                if (this.pieces[x][y] == OthelloPieceState.Possible){
                    this.pieces[x][y] = OthelloPieceState.None;
                } else if (this.pieces[x][y] == OthelloPieceState.Black){
                    this.blackScore++;
                } else if (this.pieces[x][y] == OthelloPieceState.White){
                    this.whiteScore++;
                }
                if (this.placePiece(x, y, state, false)){
                    this.pieces[x][y] = OthelloPieceState.Possible;
                    this.possibleSteps++;
                    //System.out.println(String.format("setting %d, %d to possible", x, y));
                }
            }
        }
    }

    public OthelloPieceState[] getBoard(){
        OthelloPieceState[] output = new OthelloPieceState[64];
        int i = 0;
        for (OthelloPieceState[] row : this.pieces){
            for(OthelloPieceState point: row){
                output[i] = point;
                i++;
            }
        }

        return output;
    }

    public static void main(String[] args){
        // test
        OthelloGame aGame = new OthelloGame();
        aGame.pieces[1][1] = OthelloPieceState.White;
        aGame.pieces[2][2] = OthelloPieceState.White;
        aGame.pieces[1][2] = OthelloPieceState.Black;
        aGame.pieces[2][1] = OthelloPieceState.Black;
        aGame.scan(OthelloPieceState.Black);
        System.out.println("-------------------");
        boolean result = aGame.placePiece(3, 2, OthelloPieceState.Black, true);
        System.out.println(result);
        System.out.println(Arrays.toString(aGame.getBoard()));
    }
}
