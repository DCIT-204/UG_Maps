package src.NavigationAlgorithm;

public class Node {
  public int id;
  public Node parent;
  public double totalDistance;

  public double costToNode;

  public double costToTarget;

  public Node(int id, Node parent, double costToNode, double costToTarget) {
    this.id = id;
    this.parent = parent;
    this.costToNode = costToNode;
    this.costToTarget = costToTarget;
    this.totalDistance = costToNode + costToTarget;
  }
}