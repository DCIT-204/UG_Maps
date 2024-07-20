
import java.util.PriorityQueue;

public class CustomPriorityQueue {
    private PriorityQueue<Node> priorityQueue;

    public CustomPriorityQueue() {
        this.priorityQueue = new PriorityQueue<>((node1, node2) -> Float.compare(node1.distance, node2.distance));
    }

    // Add a new Node to the priority queue
    public void add(Node newNode) {
        priorityQueue.add(newNode);
    }

    // Removes the first element in the queue
    public Node remove() {
        return priorityQueue.poll();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

}
