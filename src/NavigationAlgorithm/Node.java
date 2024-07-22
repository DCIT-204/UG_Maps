package src.NavigationAlgorithm;

//public class Node {
//  public int id;
//  public Node parent;
//  public double distance;
//
//
//  public Node( int id, double distance, Node parent) {
//    this.parent = parent;
//    this.distance = distance;
//    this.id = id;
//  }
//
//}

class Node implements Comparable<Node> {
  int id;
  double distance;
  Node parent;

  public Node(int id, double distance, Node parent) {
    this.id = id;
    this.distance = distance;
    this.parent = parent;
  }

  @Override
  public int compareTo(Node other) {
    return Double.compare(this.distance, other.distance);
  }
}
