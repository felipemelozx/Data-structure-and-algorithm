package likedList;

public class MyLinkedList<T> {
  private Node<T> head;


  // add a new node to the end of the list
  public void add(T newNode) {
    Node<T> node = new Node<>(newNode, null);
    if (head == null) {
      head = node;
      return;
    }
    Node<T> current = head;
    while (current.getNextNode() != null) {
      current = current.getNextNode();
    }
    current.setNextNode(node);
  }

  // remove a node from the list
  public void remove(T data) {
    if(head == null) {
      return;
    }
    if(head.getData().equals(data)) {
      head = head.getNextNode();
      return;
    }

    Node<T> current = head;
    while (current.getNextNode() != null && !current.getNextNode().getData().equals(data)) {
      current = current.getNextNode();
    }

    if (current.getNextNode() != null) {
      current.setNextNode(current.getNextNode().getNextNode());
    }
  }
  public void printList() {
    Node<T> current = head;
    while (current != null) {
      System.out.print(current.getData() + " -> ");
      current = current.getNextNode();
    }
    System.out.println("null");
  }
}
