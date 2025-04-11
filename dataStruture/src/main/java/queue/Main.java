package queue;

public class Main {
  public static void main(String[] args) {
    MyQueue<Integer> queue = new MyQueue<>();
    queue.add(10);
    queue.add(20);
    queue.add(30);
    queue.printList();
    System.out.println("Removed: " + queue.get());
    queue.printList();
  }
}
