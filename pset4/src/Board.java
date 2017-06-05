// import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Xiaojun YU on 2017-02-11.
 */
public class Board{
    private final int[][] board;
    private final int n;

    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    {
        this.n = blocks.length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }


    public int dimension()                 // board dimension n
    {
        return this.n;
    }

    private int goalBoard(int i, int j) {
        int res = i * n + j + 1;
        if (res != n * n) return res;
        return 0;
    }
    public int hamming()                   // number of blocks out of place
    {
        int count = 0;
        // num represent the the number in each block in the goal state
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] != goalBoard(i, j) && board[i][j] != 0)
                    count++;
            }
        }
        return count;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int count = 0;
        // num represent the the number in each block in the goal state
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int goalInt = goalBoard(i, j);
                if (board[i][j] != goalInt && board[i][j] != 0){
                    int rowGoal = board[i][j] / n;
                    int colGoal = board[i][j] % n - 1;
                    if (colGoal == -1) {
                        rowGoal -= 1;
                        colGoal = n - 1;
                    }
                    count += Math.abs(i - rowGoal) + Math.abs(j - colGoal);
                }
            }
        }
        return count;
    }

    private boolean swap(int i, int j, int it, int jt) {
        if(it < 0 || it >= n || jt < 0 || jt >= n) {
            return false;
        }
        int temp = board[i][j];
        board[i][j] = board[it][jt];
        board[it][jt] = temp;
        return true;
    }



    public boolean isGoal()                // is this board the goal board?
    {
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] != goalBoard(i, j))
                    return false;
            }
        }
        return true;
    }
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board board = new Board(this.board);
        while (true) {
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            int i2 = StdRandom.uniform(n);
            int j2 = StdRandom.uniform(n);
            if (this.board[i][j] != 0 && this.board[i2][j2] != 0 && this.board[i][j] != this.board[i2][j2]) {
                board.swap(i, j, i2, j2);
                return board;
            }
        }
    }
/*
    private boolean isBoard(Board that) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;
    }
    */

    @Override
    public boolean equals(Object y){
        // does this board equal y?
        if (y == this) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this.n != that.n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        int iZero = 0, jZero = 0;
        boolean found = false;
        // find the space in the board
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    iZero = i;
                    jZero = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        Stack<Board> boards = new Stack<Board>();
        Board that = new Board(this.board);
        if (iZero + 1 < n && that.swap(iZero, jZero,iZero + 1, jZero)) {
            boards.push(that);
            that = new Board(this.board);
        }
        if (jZero + 1 < n && that.swap(iZero, jZero,iZero, jZero + 1)) {
            boards.push(that);
            that = new Board(this.board);
        }
        if (iZero - 1 >= 0 && that.swap(iZero, jZero,iZero - 1, jZero)) {
            boards.push(that);
            that = new Board(this.board);
        }
        if (jZero - 1 >= 0 && that.swap(iZero, jZero,iZero, jZero - 1)) {
            boards.push(that);
        }
        return boards;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(this.board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        /*
        if (args.length == 0) return;
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        System.out.println("Test constructor and toString");
        Board initial = new Board(blocks);
        System.out.print(initial.toString());
        System.out.println("Passes");
        System.out.println("The dimension of the board is: " + initial.dimension());
        assert initial.isGoal() == false;
        System.out.println("isGoal passes");
        System.out.print(("Test neighbors"));
        for (Board board : initial.neighbors()) {
            System.out.println(board.toString());
        }
        System.out.println("Test manhattan");
        assert initial.manhattan() == 7;
        System.out.println("Manhattan passes");
        */
    }


}
