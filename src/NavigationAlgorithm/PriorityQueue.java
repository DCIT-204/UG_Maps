
public class PriorityQueue {
    private Node head;

    public PriorityQueue() {
        this.head = null;
    }

    // Add a new Node to the prioty queue
    public void add(String name, String description, Double latitude, Double longitude, float distance, Node parent,
            int id) {
        Node newNode = new Node(name, description, latitude, longitude, distance, parent, id);

        // the new node becomes the head if queue is empty or it has a distance lesser
        // than the current head
        if (head == null || head.distance > distance) {
            newNode.nextNode = head;
            head = newNode;
        } else {
            // the queue is traversed to find the position of the new node based on its
            // priority
            Node currentNode = head;
            while (currentNode.nextNode != null && currentNode.nextNode.distance <= distance) {
                currentNode = currentNode.nextNode;
            }
            newNode.nextNode = currentNode.nextNode;
            currentNode.nextNode = newNode;
        }
    }

    // Removes the first element in the queue
    public Node remove() {
        if (head == null) {
            return null;
        }
        Node temp = head;
        head = head.nextNode;
        return temp;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return head == null;
    }

}
