package ai;

import model.Node;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class UCS extends TreeSearcher
{
    public void search(Node startNode)
    {
        PriorityQueue<Node> fringe = 
            new PriorityQueue<>(new NodeComparator(this));
        HashSet<String> inFringe = new HashSet<>();

        if(startNode.isGoal()) 
        {
            System.out.println("you win!");
            printResult(startNode, 0);
            return;
        }

        fringe.offer(startNode);
        inFringe.add(startNode.hash());

        while(!fringe.isEmpty()) 
        {
            Node temp = fringe.poll();

            ArrayList<Node> children = temp.successor();
            
            for(Node child : children) 
            {
                if (!(inFringe.contains(child.hash())))
                {
                    if (child.isGoal())
                    {
                        printResult(child, 0);
                        System.out.println("you win !!!");
                        return;
                    }

                    fringe.offer(child);
                    inFringe.add(child.hash());
                }
            }
        }
        
        System.out.println("no solution");
    }
}

class NodeComparator implements Comparator<Node>
{
    private TreeSearcher searchEngine;

    public NodeComparator(TreeSearcher searchEngine)
    {
        this.searchEngine = searchEngine;
    }
    
    public int compare(Node n1, Node n2)
    {
        int cost1 = searchEngine.pathCost(n1);
        int cost2 = searchEngine.pathCost(n2);

        if(cost1 < cost2)
        {
            return -1;
        }

        else if(cost1 == cost2)
        {
            return 0;
        }

        else // cost1 > cost2 
        {
            return 1;
        }
    }
}
