import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/**
 * Created by Xiaojun YU on 2017-02-20.
 */
public class Solver {
    private class Node implements Comparable<Node>{
        private Board curr;
        private int moves;
        private Node pre;
        public Node(Board curr, int moves, Node pre) {
            this.curr = curr;
            this.moves = moves;
            this.pre = pre;
        }
        private Board getCurr() {
            return curr;
        }

        private Node getPre() {
            return pre;
        }

        private int getMoves() {
            return moves;
        }

        private int heuristic(){
            return this.curr.manhattan();
        }

        @Override
        public int compareTo(Node that) {
            // return 1 is particular object is greater than the object we're comparing against
            // , 0, -1
            int thisCost = this.moves + this.heuristic();
            int thatCost = that.moves + that.heuristic();
            return thisCost - thatCost;
        }
    }
    private Stack<Board> res = new Stack<Board>();
    private boolean solvable = false;
    public Solver(Board initial)
    // find a solution to the initial board (using the A* algorithm)
    {
        MinPQ<Node> minPQ;
        MinPQ<Node> twinMinPQ;
        if (initial == null) {
            throw new NullPointerException();
        }
        //List<Board> visited = new ArrayList<Board>();
        //List<Board> twinVisited = new ArrayList<Board>();
        minPQ = new MinPQ<Node>();
        minPQ.insert(new Node(initial, 0, null));
        twinMinPQ = new MinPQ<Node>();
        twinMinPQ.insert(new Node(initial.twin(), 0, null));
        while (true) {
            if (!twinMinPQ.isEmpty()){
                Node root = twinMinPQ.delMin();
                Board current = root.getCurr();
                if (current.isGoal()) {
                    return;
                }
                //twinVisited.add(current);
                int move = root.getMoves();
                Node preNode = root.getPre();
                Board pre;
                if (preNode != null){
                    pre = preNode.getCurr();
                } else {
                    pre = null;
                }
                for (Board neighbour : current.neighbors())
                {
                    if (!neighbour.equals(pre)) {
                        twinMinPQ.insert(new Node(neighbour, move+1, root));
                    }
                }
            }
            if (!minPQ.isEmpty()){
                Node root = minPQ.delMin();
                Board current = root.getCurr();
                if (current.isGoal()) {
                    solvable = true;
                    res.push(current);
                    Node temp = root;
                    while (temp.pre != null) {
                        temp = temp.pre;
                        res.push(temp.curr);
                    }
                    return;
                }
                //visited.add(current);
                int move = root.getMoves();
                Node preNode = root.getPre();
                Board pre;
                if (preNode != null){
                    pre = preNode.getCurr();
                } else {
                    pre = null;
                }
                for (Board neighbour : current.neighbors())
                {
                    if (!neighbour.equals(pre)) {
                        minPQ.insert(new Node(neighbour, move+1, root));
                    }
                }
            } else {
                return;
            }
        }
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        if (solvable) return true;
        return false;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if (solvable) return res.size()-1;
        return -1;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if(solvable)
        {
            return res;
        }
        return null;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
