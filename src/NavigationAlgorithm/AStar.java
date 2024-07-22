package src.NavigationAlgorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class AStar {

    static ArrayList<List<Integer>> routes = new ArrayList<>();
    static double dist = 0;
    static ArrayList<Double> molly = new ArrayList<>();

    static List<List<Integer>> getSolution(Node node) {
        ArrayList<Integer> path = new ArrayList<>();
        double totalDistance = 0;

        while (node != null) {
            path.add(0, node.id); // Add elements to the front for proper ordering
            if (node.parent != null) {
                totalDistance += Util.getDistance(node.parent.id, node.id);
            }
            node = node.parent;
        }

        molly.add(totalDistance);
        System.out.println(path);
        System.out.println("Distance is " + totalDistance);
        routes.add(path);
        System.out.println(molly);
        return routes;
    }

    public static int Astar(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0, null));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.id == end) {
                getSolution(node);
                return 0; // Path found
            }

            if (visited.contains(node.id)) {
                continue;
            }

            visited.add(node.id);

            for (int neighbour : Util.getNeighbours(node.id)) {
                if (!visited.contains(neighbour)) {
                    double newDistance = node.distance + Util.getDistance(node.id, neighbour);
                    double priority = newDistance + Util.getDistance(neighbour, end);
                    queue.add(new Node(neighbour, priority, node));
                }
            }
        }

        System.out.println(routes);
        return -1; // Return -1 if there is no path found
    }

    public static void main(String[] args) {
        System.out.println(Astar(1, 24));
    }
}