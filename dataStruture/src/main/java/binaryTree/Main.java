package binaryTree;

public class Main {
  public static void main(String[] args) {
    BinaryTree three = new BinaryTree(new Node(5));
    //three.insert(5);
    three.insert(3);
    three.insert(1);
    three.insert(10);
    three.insert(15);
    three.insert(7);

    System.out.println(three.preorderTraversal());
    System.out.println(three.inorderTraversal());
    System.out.println(three.postorderTraversal());

    System.out.println("Search 4: " + three.search(4));
    System.out.println("Search 6: " + three.search(6));
  }
}
