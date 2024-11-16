package ai;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class DFS extends TreeSearcher
{
    public void search(Node startNode) 
    {
        Stack<Node> fringe = new Stack<Node>();
        Hashtable<String, Boolean> inFringe = new Hashtable<>();

        if (startNode.isGoal())
        {
            System.out.println("you win!");
            printResult(startNode, 0);
            return;
        }

        fringe.add(startNode);
        inFringe.put(startNode.hash(), true);

        while (!fringe.isEmpty()) 
        {
            Node temp = fringe.pop();

            ArrayList<Node> children = temp.successor();
            for (Node child : children) 
            {
                if (!(inFringe.containsKey(child.hash()))) 
                {
                    if (child.isGoal()) 
                    {
                        printResult(child, 0);
                        System.out.println("you win !!!"); 
                        return;
                    }

                    fringe.add(child);
                    inFringe.put(child.hash(), true);
                }
            }
        }

        System.out.println("no solution");
    }
}
