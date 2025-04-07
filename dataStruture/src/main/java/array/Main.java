package array;

public class Main {
  public static void main(String[] args) {
    MyArray<Integer> myArray = new MyArray<>();
    MyArray<Integer> myArray2 = new MyArray<>(11);
    myArray.add(10);
    myArray.add(20);
    myArray.add(30);

    myArray2.add(11);
    myArray2.add(11);
    myArray2.add(11);
    myArray2.add(11);
    myArray2.add(11);
    myArray2.add(11);
    myArray2.add(11);
    myArray2.add(11);


    System.out.println(myArray2);

    System.out.println("Array: " + myArray);
    System.out.println("Elemento no índice 1: " + myArray.get(1));

    myArray.remove(1);
    System.out.println("Após remoção: " + myArray);
  }
}
