
public class Node {
  public String name;
  public String description;
  public Double latitude;
  public Double longitude;
  public Node parent;
  public float distance;
  public int id;

  public Node(String name, String description, Double latitude, Double longitude, float distance, Node parent, int id) {
    this.name = name;
    this.description = description;
    this.latitude = latitude;
    this.longitude = longitude;
    this.parent = parent;
    this.distance = distance;
    this.id = id;
  }

}