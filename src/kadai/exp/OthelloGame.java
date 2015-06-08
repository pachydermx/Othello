package kadai.exp;

import java.util.Arrays;

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
        this.directions = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, -1}, {0, -1}, {-1, 1}};
    }

    public int[] checkAvailable(int x, int y, OthelloPieceState state){
        boolean available = false;
        int[] distance = new int[8];
        SearchState[] states = new SearchState[8];
        for (int i = 0; i < 8; i++){
            states[i] = SearchState.Advance;
            distance[i] = 0;
        }

        // check adjunct ... if adjunct is the same then stop
        OthelloPieceState[] adjunctState = this.getState(x, y, 1);
        for (int i = 0; i < 8; i++){
            if (state == adjunctState[i] || adjunctState[i] == OthelloPieceState.None){
                states[i] = SearchState.Out;
                distance[i] = 0;
            }
        }
        // check further ... if none advance then stop
        for (int i = 2; i < 8; i++){
            OthelloPieceState[] roundState = this.getState(x, y, i);
            for (int j = 0; j < 8; j++){
                if (states[j] == SearchState.Advance){
                    if (roundState[j] == OthelloPieceState.None){
                        states[j] = SearchState.Out;
                        distance[i] = 0;
                    } else if (roundState[j] == state){
                        states[j] = SearchState.Found;
                        available = true;
                        distance[j] = i;
                    }
                }
            }
            System.out.println(String.format("[%s,%s,%s,%s,%s,%s,%s,%s]",states[0],states[1],states[2],states[3],states[4],states[5],states[6],states[7]));
            // check end
            int advances  = 0;
            for (SearchState j : states){
                if(j == SearchState.Advance){
                    advances++;
                }
            }
            if(advances == 0) break;
        }

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
        System.out.println(String.format("(%d,%d)-%d, [%s,%s,%s,%s,%s,%s,%s,%s]", x, y, distance, states[0], states[1], states[2], states[3], states[4], states[5], states[6], states[7]));
        return states;
    }

    public static void main(String[] args){
        // test
        OthelloGame aGame = new OthelloGame();
        aGame.pieces[1][1] = OthelloPieceState.White;
        aGame.pieces[2][2] = OthelloPieceState.White;
        aGame.pieces[1][2] = OthelloPieceState.Black;
        aGame.pieces[2][1] = OthelloPieceState.Black;
        System.out.println(Arrays.toString(aGame.checkAvailable(3, 2, OthelloPieceState.White)));
    }
}
