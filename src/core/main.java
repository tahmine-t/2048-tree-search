package core;

import ai.*;
import model.Board;
import model.Node;

import java.util.Hashtable;
import java.util.Scanner;

import static model.Movement.NONE;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        // input search type
        System.out.println("Enter the search type: <search type> <gameMode> <costMode>");
        String searchType = input.next();
        String gameMode = input.next();
        String pathCostMode = input.next();
        input.nextLine();

        // input game start board size
        System.out.println("Enter the goal value : \n then enter rows and columns and your board");
        int goalValue = Integer.parseInt(input.nextLine());
        String mn = input.nextLine();
        int rows = Integer.parseInt(mn.split(" ")[0]);
        int columns = Integer.parseInt(mn.split(" ")[1]);

        // input the game board
        String[][] board = new String[rows][columns];
        String[] lines = new String[rows];
        for (int i = 0; i < rows; i++) {
            lines[i] = input.nextLine();
            String[] line = lines[i].split(" ");
            System.arraycopy(line, 0, board[i], 0, columns);
        }

        Mapper mapper = new Mapper();
        int[][] cells = mapper.createCells(board, rows, columns);
        Board gameBoard = mapper.createBoard(cells, goalValue, rows, columns);
        Board.mode = Constants.MODE_NORMAL;
        System.out.println(gameBoard.toString());

        Hashtable<String, Boolean> initHash = new Hashtable<>();
        Node start = new Node(gameBoard, null, NONE);

        TreeSearcher searcher = null;

        switch(searchType)
        {
            case "bfs":
                searcher = new BFS();
                break;
            case "dfs":
                searcher = new DFS();
                break;
            case "ids":
                searcher = new IDS();
                break;
            case "ucs":
                searcher = new UCS();
                break;
            case "astar":
                searcher = new AStar();
                break;
            case "greedy":
                searcher = new GreedyInformed();
                break;
            case "idastar":
                searcher = new IDAStar();
                break;
            case "idastaropt":
                searcher = new IDAStarOptimal();
                break;
            default:
                System.out.println("Program shouldn't enter default case!");
        }

        searcher.setGameMode(gameMode);
        searcher.setPathCostMode(pathCostMode);
        searcher.search(start);
    }
}
