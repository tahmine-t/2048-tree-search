package ai;

import model.Node;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class IDAStarOptimal extends TreeSearcher
{
    public void search(Node startNode)
    {
        PriorityQueue<Node> fringe = 
            new PriorityQueue<>(new NodeComparator(this));
        HashSet<String> inFringe = new HashSet<>();

        // the initial limit would be the the cost of the root node
        int limit = heuristic(startNode) - pathCost(startNode);
        // max value used as infinity
        int nextLimit = 0; 
        // finish condition for the search
        boolean finished = false;
        
        while(!finished)
        {
            fringe.offer(startNode);
            inFringe.add(startNode.hash());
            nextLimit = 0;
            
            while(!fringe.isEmpty())

            {
                Node next = fringe.poll();

                if(next.isGoal())
                {
                    System.out.println("you win!");
                    printResult(next, 0);
                    return;
                }
                
                ArrayList<Node> children = next.successor();

                for(Node child: children)
                {
                    if(!inFringe.contains(child.hash()))
                    {
                        // calculte the function f(n) = h(n) - g(n)
                        int f = heuristic(child) - pathCost(child);

                        if(f >= limit)
                        {
                            fringe.offer(child);
                            inFringe.add(child.hash());
                        }

                        else if(f > nextLimit)
                        {
                            nextLimit = f;
                        }
                    }
                }
            }

            // if the next limit is still inifinity (was not updated)
            // that means all nodes' been traversed
            if(nextLimit == Integer.MAX_VALUE)
            {
                finished = true; // goal not found in the tree
            }

            // setting the limit for the next cycle (if the search is not finished)
            limit = nextLimit;
        }
    }
}
