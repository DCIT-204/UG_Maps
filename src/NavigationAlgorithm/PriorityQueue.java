
public class PriorityQueue {
    private Node head;

    public PriorityQueue() {
        this.head = null;
    }

    // Add a new Node to the prioty queue
    public void add(String name, String description, Double latitude, Double longitude, float distance, Node parent,
            int id) {
        Node newNode = new Node(name, description, latitude, longitude, distance, parent, id);

        if (head == null || head.distance > distance) {
            newNode.nextNode = head;
            head = newNode;
        } else {

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
