package likedList;

public class Main {
  public static void main(String[] args) {
    MyLinkedList<Integer> list = new MyLinkedList<>();
    list.add(10);
    list.add(20);
    list.add(30);

    list.printList();

    list.remove(20);
    list.printList();
  }
}
