package ai;

import model.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;

public class IDS extends TreeSearcher
{
    public Node goal;
    private boolean foundGoal = false;
    private boolean treeIsOver = false;

    public void search(Node startNode)
    {
        // iteration starts from depth 1 to 2, 3, 4, ...
        int depth = 1;

        while(!this.foundGoal && !this.treeIsOver)
        {
            depthLimitSearch(startNode, depth);
            ++depth;
        }

        if(this.foundGoal)
        {
            printResult(this.goal, 0);
            System.out.println("You Win !!!");
        }

        else if(this.treeIsOver)
        {
            System.out.println("No Solution!");
        }
    }

    public void depthLimitSearch(Node startNode, int depthLimit)
    {
        int currentDepth = 1;
        this.treeIsOver = true; // assuming that depthLimit is the actual depth of the tree
        Stack<Node> fringe = new Stack<>();
        Set<String> inFringe = new HashSet<>();
        
        fringe.push(startNode);
        inFringe.add(startNode.hash());
        
        while(!fringe.isEmpty())
        {
            Node next = fringe.pop();
            
            // found the goal
            if(next.isGoal())
            {
                this.foundGoal = true;
                this.goal = next;
                return;
            }

            currentDepth = getNodeDepth(next);
            
            // if the search depth doesn't exceed the limit
            // add the children to the fringe
            if((currentDepth + 1) <= depthLimit)
            {
                ArrayList<Node> children = next.successor();

                for(Node child : children)
                {
                    if(!inFringe.contains(child.hash()))
                    {
                        inFringe.add(child.hash());
                        fringe.push(child);
                    }
                }
            }

            // check if we need another iteration in the ids (i.e. there is more depth to the tree)
            else if(currentDepth == depthLimit)
            {
                ArrayList<Node> children = next.successor();

                if(!children.isEmpty())
                {
                    this.treeIsOver = false;
                }
            }
        }

        // if reach here, no goal found!
    }

    private int getNodeDepth(Node node)
    {
        int depth = 1;

        while (node.getParent() != null)
        {
            ++depth;
            node = node.getParent();
        }

        return depth;
    }
}
