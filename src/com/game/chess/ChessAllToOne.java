
package com.game.chess;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author zengjinlong
 * TODO how to make it more intelligent?
 */
public class ChessAllToOne {

    private static final int LENGTH = 7;
    // 37 valid cells, 36 full and 1 empty, need 35 steps.
    private static final int MAX_STEP = 35;
    static Cell[][] mChessBoard = new Cell[LENGTH][LENGTH];
    int mStep = 0;
    Move[] mMoves = new Move[MAX_STEP];
    HashSet<Move> mMovableSet;

    private static final int INVALID_STATE = 9;
    private static final int FULL_STATE = 1;
    private static final int EMPTY_STATE = 0;
    static class Cell {
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Cell(Cell c) {
            x = c.x;
            y = c.y;
            stat = c.stat;
        }

        int stat; // -1, 0, 1
        int x;
        int y;

        public boolean isValid() {
            return 0 <= x && x < LENGTH && 0 <= y && y < LENGTH && stat != INVALID_STATE;
        }
        @Override
        public int hashCode() {
            return x << 8 + y << 4 + stat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o.getClass() == getClass()) {
                return (x == ((Cell) o).x && y == ((Cell) o).y && stat == ((Cell) o).stat);
            }
            return false;
        }

        @Override
        public String toString() {
            return "(" + (x + 1) + "," + (y + 1) + "-" + stat + ")";
        }
    }

    static class Move {
        Cell from;
        Cell to;
        boolean isXMove;

        Move(Cell f, Cell t, boolean isX) {
            from = f;
            to = t;
            isXMove = isX;
        }

        public void execute() {
            Cell cell;
            if (isXMove) {
                int x = (from.x + to.x) /2;
                cell = mChessBoard[x][from.y];
            } else {
                int y = (from.y + to.y) /2;
                cell = mChessBoard[from.x][y];
            }
            from.stat = 0;
            cell.stat = 0; // jump over pass the middle one, and the middle one disappear
            to.stat = 1;
        }

        public void moveBack() {
            Cell cell;
            if (isXMove) {
                int x = (from.x + to.x) /2;
                cell = mChessBoard[x][from.y];
            } else {
                int y = (from.y + to.y) /2;
                cell = mChessBoard[from.x][y];
            }
            from.stat = 1;
            cell.stat = 1;
            to.stat = 0;
        }

        @Override
        public int hashCode() {
            return from.hashCode() << 8 + to.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o.getClass() == getClass()) {
                Move m = (Move) o;
                return (from.equals(m.from) && to.equals(m.to));
            }
            return false;
        }

        @Override
        public String toString() {
            return from.toString() + "->" + to.toString();
        }
    }

    ChessAllToOne() {
        initChessBoard();
    }

    private void initChessBoard() {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                mChessBoard[i][j] = new Cell(i, j);
                mChessBoard[i][j].stat = 1;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                mChessBoard[i][j].stat = INVALID_STATE;
                mChessBoard[i][LENGTH - 1 - j].stat = INVALID_STATE;
                mChessBoard[LENGTH - 1 - i][LENGTH - 1 - j].stat = INVALID_STATE;
                mChessBoard[LENGTH - 1 - i][j].stat = INVALID_STATE;
            }
        }
        mChessBoard[1][1].stat = 1;
        mChessBoard[1][5].stat = 1;
        mChessBoard[5][1].stat = 1;
        mChessBoard[5][5].stat = 1;

        mChessBoard[0][2].stat = 0; // the init empty cell;
    }

    private HashSet<Move> getAllMoves(int step) {
        HashSet<Move> moveSet = new HashSet<Move>();
        if (step < 18) {
            ArrayList<Cell> emptyCells = new ArrayList<Cell>();
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (mChessBoard[i][j].stat == 0) {
                        emptyCells.add(mChessBoard[i][j]);
                    }
                }
            }
//            System.out.println("emptyCells:" + emptyCells.size());
            Cell from = null;
            for (Cell cell : emptyCells) {
                if (cell.x + 2 < LENGTH && mChessBoard[cell.x + 2][cell.y].stat == 1) {
                    from = mChessBoard[cell.x + 2][cell.y];
                    addIfValid(from, cell, true, moveSet);
                }
                if (cell.x - 2 >= 0 && mChessBoard[cell.x - 2][cell.y].stat == 1) {
                    from = mChessBoard[cell.x - 2][cell.y];
                    addIfValid(from, cell, true, moveSet);
                }
                if (cell.y + 2 < LENGTH && mChessBoard[cell.x][cell.y + 2].stat == 1) {
                    from = mChessBoard[cell.x][cell.y + 2];
                    addIfValid(from, cell, false, moveSet);
                }
                if (cell.y - 2 >= 0 && mChessBoard[cell.x][cell.y - 2].stat == 1) {
                    from = mChessBoard[cell.x][cell.y - 2];
                    addIfValid(from, cell, false, moveSet);
                }
            }
        } else {
            ArrayList<Cell> FullCells = new ArrayList<Cell>();
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (mChessBoard[i][j].stat == 1) {
                        FullCells.add(mChessBoard[i][j]);
                    }
                }
            }
//            System.out.println("FullCells:" + FullCells.size());
            Cell to = null;
            for (Cell cell : FullCells) {
                if (cell.x + 2 < LENGTH && mChessBoard[cell.x + 2][cell.y].stat == 0) {
                    to = mChessBoard[cell.x + 2][cell.y];
                    addIfValid(cell, to, true, moveSet);
                }
                if (cell.x - 2 >= 0 && mChessBoard[cell.x - 2][cell.y].stat == 0) {
                    to = mChessBoard[cell.x - 2][cell.y];
                    addIfValid(cell, to, true, moveSet);
                }
                if (cell.y + 2 < LENGTH && mChessBoard[cell.x][cell.y + 2].stat == 0) {
                    to = mChessBoard[cell.x][cell.y + 2];
                    addIfValid(cell, to, false, moveSet);
                }
                if (cell.y - 2 >= 0 && mChessBoard[cell.x][cell.y - 2].stat == 0) {
                    to = mChessBoard[cell.x][cell.y - 2];
                    addIfValid(cell, to, false, moveSet);
                }
            }
        }
        return moveSet;
    }

    private void addIfValid(Cell from, Cell to, boolean isXMove, HashSet<Move> moveSet) {
        if (from.isValid() && to.isValid()) {
            int diff = 0;
            Cell middleCell;
            if (isXMove) {
                diff = from.x - to.x;
                int x = (from.x + to.x) /2;
                middleCell = mChessBoard[x][from.y];
            } else {
                diff = from.y - to.y;
                int y = (from.y + to.y) /2;
                middleCell = mChessBoard[from.x][y];
            }
            if ((diff == -2 || diff == 2) && middleCell.stat == 1) {
                moveSet.add(new Move(from, to, isXMove));
            }
        }
    }

    int mTryCount = 0;
    int mSolveCount = 0;
    boolean mGameEnd = false;
    int mBackSteps = 0;
    private boolean isGameEnd() {
        return mGameEnd;
    }
    private boolean needGoBack() {
        return mBackSteps-- >= 0;
    }

    public void beginGame() {
        System.out.println("beginGame!!!!!!!!!!!");
        long time = System.currentTimeMillis();
        initChessBoard();
        
        HashSet<Move> moves = getAllMoves(0);
        if (moves.size() > 0) {
            for (Move move : moves) {
                moveStep(move, 0);
                if (isGameEnd()) {
                    break;
                }
                System.out.println("moveNext, moveBack!!!");
                move.moveBack(); // move back and try another move
            }
        }
        System.out.println("GameEnd!!!!!!!!!!!, takes time:" + ":" + (System.currentTimeMillis() - time));
        printAllWays();
    }

    private void moveStep(Move move, int step) {
        mMoves[step] = move;
        move.execute();
        if (step == MAX_STEP - 1) {
//            mGameEnd = true;
//            printMoves(mMoves);
//            printChessBoard(mChessBoard);
            keepChessBoardAndMoves();
            return;
        }
        HashSet<Move> moves = getAllMoves(step);
        if (moves.size() > 0) {
            ArrayList<Move> ms = new ArrayList<Move>();
            ms.addAll(moves);
            // reduce the loop to get a quick solution...
//            if (ms.size() > 9) {
//                int num80percent = (int) (ms.size() * 0.8);
//                while (ms.size() > num80percent) {
//                    int random = getRandom(ms.size() - 1);
//                    ms.remove(random);
//                }
//            }
            step++;
            for (Move m : ms) {
                moveStep(m, step);
                if (isGameEnd()) {
                    break;
                }
                m.moveBack(); // move back and try another move

                if (needGoBack()) {
                    // still need go back.
                    break;
                }
                if (step < 6) {
                    System.out.println("try another way for Step:" + step + ",moves.size:" + moves.size() + ",mTryCount:" + mTryCount);
                }
            }
        } else {
            mTryCount++;
//            System.out.println("Game ended in " + step + " steps!!!, mTryCount:" + mTryCount);
//            printChessBoard();
            // TODO mBackSteps should be carefully set, it matters much!
            // but how to exclude the impossible moves is a big question.
            mBackSteps = MAX_STEP - step - 1;
        }
    }

    ArrayList<Move[]> mSolveMoves = new ArrayList<Move[]>();
    ArrayList<Cell[][]> mFinalChessBoard = new ArrayList<Cell[][]>();
    private void keepChessBoardAndMoves() {
        mSolveCount++;
        System.out.println("Game solved!!!!!!!!!!!!!!, mTryCount:" + mTryCount + ",mSolveCount:" + mSolveCount);
        if (mSolveMoves.size() > 20) {
            return;
//            mGameEnd = true;
        }
        if (mSolveCount % 100 == 7) {
            Move[] move = new Move[MAX_STEP];
            Cell[][] board = new Cell[LENGTH][LENGTH];
            for (int i = 0; i < MAX_STEP; i++) {
                move[i] = mMoves[i];
            }
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    board[i][j] = new Cell(mChessBoard[i][j]);
                }
            }
            mSolveMoves.add(move);
            mFinalChessBoard.add(board);
        }
    }

    private void printAllWays() {
        System.out.println("printAllWays :" + mSolveMoves.size() + ",mSolveCount:" + mSolveCount + ",mTryCount:" + mTryCount);
        for (int i = 0; i < mSolveMoves.size(); i++) {
            System.out.println("This is way num:" + i);
            printMoves(mSolveMoves.get(i));
            printChessBoard(mFinalChessBoard.get(i));
        }
    }
    private void printMoves(Move[] moves) {
        for (Move cell : moves) {
            if (cell != null) {
                System.out.println(cell.toString());
            }
        }
    }

    public void printChessBoard(Cell[][] chessBoard) {
        System.out.println("***********************************");
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                System.out.print(chessBoard[j][i].stat + "  ");
            }
            System.out.println();
        }
        System.out.println("***********************************");
    }

    public static int getRandom(int max) {
        return (int) (System.currentTimeMillis() % max);
    }

    public static void main(String[] args) {
        ChessAllToOne chess = new ChessAllToOne();
        chess.printChessBoard(mChessBoard);
        chess.beginGame();
    }
}
