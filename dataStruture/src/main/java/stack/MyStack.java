package stack;

import likedList.Node;

public class MyStack<T> {
  private Node<T> top;
  private int size;


  public void push(T newItem) {
    Node<T> node = new Node<T>(newItem , null);
    if(top == null) {
      top = node;
    } else {
      node.setNextNode(top);
      top = node;
    }
    size++;
  }
  public Node<T> pop() {
    Node<T> temp = top;
    top = top.getNextNode();
    size--;
    return temp;
  }

  public Node<T> peek() {
    return top;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void printList() {
    Node<T> current = top;
    while (current != null) {
      System.out.print(current.getData() + " -> ");
      current = current.getNextNode();
    }
    System.out.println("null");
  }
}
