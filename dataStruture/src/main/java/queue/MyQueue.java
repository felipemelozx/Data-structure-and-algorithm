package queue;

import likedList.Node;

public class MyQueue <T>{
  private Node<T> head;
  private Node<T> tail;

  /** add a new item to the end of the queue*/
  public void add(T newItem) {
    Node<T> node = new Node<>(newItem, null);
    if(head == null) {
      head = node;
    } else {
      tail.setNextNode(node);
    }
    tail = node;
  }

  /** remove an item from the front of the queue*/
  public T get() {
    if(head == null) {
      throw new ArrayIndexOutOfBoundsException("Queue is empty");
    }
    T data = head.getData();
    head = head.getNextNode();
    return data;
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
