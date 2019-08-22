package net.vampkyivua.hedgehog;

import java.util.*;
import java.util.logging.Logger;

import static net.vampkyivua.hedgehog.Grid.Node;
/**
 * Author: Mykhailo Drozdov
 * Created: 2019-08-19 17:41
 */
public class PathFinder {
    private static final Logger log = Logger.getLogger("main");

    // A* algorithm using max cost as optimal
    Path findPath(Grid grid) {
        Pair<Integer, Integer> size = grid.size();
        Node start = grid.get(0, 0);
        Node goal = grid.get(size.left - 1, size.right - 1);

        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Integer> pathCost = new HashMap<>();

        Queue<Node> frontier = new PriorityQueue<>((a, b) -> b.cost - a.cost);
        frontier.add(start);
        pathCost.put(start, start.cost);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.equals(goal)) break;

            log.finer("current:" + current);
            log.finer("neighbors:" + Arrays.toString(grid.neighbors(current)));

            for (Node next : grid.neighbors(current)) {
                int newCost = pathCost.get(current) + next.cost;
                if (!pathCost.containsKey(next) || newCost > pathCost.get(next)) {
                    pathCost.put(next, newCost);
                    cameFrom.put(next, current);
                    frontier.add(next);
                }
            }

            log.finer("frontier:" + frontier);
            log.finer("---------");
        }

        if (!cameFrom.containsKey(goal))
            throw new HedgehogException("Oops! Couldn't find a way");

        Path path = new Path();
        Node curr = goal;
        while (curr != null) {
            Node prev = cameFrom.get(curr);
            path.add(curr);
            curr = prev;
        }
        path.reverse();

        return path;
    }
}
