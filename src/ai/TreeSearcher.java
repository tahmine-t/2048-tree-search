package ai;

import model.Node;
import model.Board;
import model.Movement;

public abstract class TreeSearcher
{
    private int gameMode = 0;
    private int pathCostMode = 0;

    public abstract void search(Node startNode);

    public void setGameMode(String mode)
    {
        mode = mode.toLowerCase();

        if(mode.equals("basic") || mode.equals("b") || mode.equals("0"))
        {
            this.gameMode = 0;
        }

        else // advanced mode
        {
            this.gameMode = 1;
        }
    }

    public void setPathCostMode(String mode)
    {
        mode = mode.toLowerCase();

        if(mode.equals("simple") || mode.equals("s") || mode.equals("0"))
        {
            this.pathCostMode = 0;
        }

        else // use custom move costs
        {
            this.pathCostMode = 1;
        }
    }

    public int getGameMode()
    {
        return this.gameMode;
    }

    public int getPathCostMode()
    {
        return this.pathCostMode;
    }

    public int pathCost(Node node)
    {
        // all moves have a cost of 1
        if(this.pathCostMode == 0)
        {
            return simplePathCost(node);
        }

        else // use custom move costs to calculate total cost
        {
            return weightedPathCost(node);
        }
    }

    private int simplePathCost(Node node)
    {
        int cost = 0;

        while(node.getParent() != null)
        {
            cost += 1;
            node = node.getParent();
        }

        return cost;
    }

    public int weightedPathCost(Node node)
    {
        int cost = 0;

        while(node.getParent() != null)
        {
            cost += getMoveCost(node.getPrevMovement());
            node = node.getParent();
        }

        return cost;
    }

    private int getMoveCost(Movement move)
    {
        int cost = 0;

        switch(move)
        {
            case NONE:
                cost = 0;
                break;
            case LEFT:
                cost = 1;
                break;
            case RIGHT:
                cost = 3;
                break;
            case UP:
                cost = 7;
                break;
            case DOWN:
                cost = 5;
                break;
            default:
                System.out.println("switch shouldn't get here!");
        }

        return cost;
    }
    
    public int heuristic(Node node) 
    {
        // basic mode
        if(this.gameMode == 0)
        {
            return basicHeuristic(node);
        }

        else // advanced mode
        {
            return advancedHeuristic(node);
        }
    }

    private int basicHeuristic(Node node)
    {
        int h = 0;
        Board board = node.getBoard();

        int first = 0;
        int second = 0;

        int maxSum = 0;
        int newSum = 0;

        for(int i = 0; i < board.row; ++i)
        {
            for(int j = 0; j < board.col - 1; ++j)
            {
                first = board.cells[i][j];
                second = board.cells[i][j + 1];

                newSum = first + second;

                if(newSum > maxSum)
                {
                    maxSum = newSum;
                }
            }
        }

        for(int j = 0; j < board.col; ++j)
        {
            for(int i = 0; i < board.row - 1; ++i)
            {
                first = board.cells[i][j];
                second = board.cells[i + 1][j];

                newSum = first + second;

                if(newSum > maxSum)
                {
                    maxSum = newSum;
                }
            }
        }

        h = maxSum;

        return h;
    }

    private int advancedHeuristic(Node node)
    {
        int h = 0;
        Board board = node.getBoard();

        int first = 0;
        int second = 0;

        int maxSum = 0;
        int newSum = 0;

        for(int i = 0; i < board.row; ++i)
        {
            for(int j = 0; j < board.col - 1; ++j)
            {
                first = board.cells[i][j];
                second = board.cells[i][j + 1];

                // advanced mode: only check 
                //if two adjacent elements are equal
                if(first == second)
                {
                    newSum = first * 2;

                    if(newSum > maxSum)
                    {
                        maxSum = newSum;
                    }
                }

            }
        }

        for(int j = 0; j < board.col; ++j)
        {
            for(int i = 0; i < board.row - 1; ++i)
            {
                first = board.cells[i][j];
                second = board.cells[i + i][j];

                // advanced mode: only check 
                //if two adjacent elements are equal
                if(first == second)
                {
                    newSum = first * 2;

                    if(newSum > maxSum)
                    {
                        maxSum = newSum;
                    }
                }
            }
        }

        h = maxSum;

        return h;
    }

    public void printResult(Node node, int depthCounter) 
    {
        if(node.getParent() == null) 
        {
            System.out.println("problem solved at a depth of  : " + depthCounter);
            return;
        }
        
        System.out.println(node.toString());
        node.drawState();
        printResult(node.getParent(), depthCounter + 1);
    }
}
