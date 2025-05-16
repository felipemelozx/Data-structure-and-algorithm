package binaryTree;

public class Main {
  public static void main(String[] args) {
    BinaryTree three = new BinaryTree(new Node(120));
    three.insert(5);
    three.insert(3);
    three.insert(8);
    three.insert(1);
    three.insert(4);
    three.insert(7);
    three.insert(9);

    System.out.println("Search 4: " + three.search(4));
    System.out.println("Search 6: " + three.search(6));
  }
}
