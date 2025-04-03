package array;

import java.util.Arrays;

public class MyArray<T> {
  private Object[] data;
  private int size;
  private static final int INITIAL_CAPACITY = 10;

  public MyArray() {
    this.data = new Object[INITIAL_CAPACITY];
    this.size = 0;
  }

  public MyArray(int capacity) {
    this.data = new Object[capacity];
    this.size = 0;
  }

  public void add(T element) {
    //ensureCapacity();
    if (size == data.length) {
      throw new IndexOutOfBoundsException("Capacidade máxima atingida: " + data.length);
    }
    data[size++] = element;
  }

  @SuppressWarnings("unchecked")
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Índice fora do limite: " + index);
    }
    return (T) data[index];
  }

  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Índice fora do limite: " + index);
    }
    System.arraycopy(data, index + 1, data, index, size - index - 1);
    data[--size] = null; // Evita memory leak
  }

  public int size() {
    return size;
  }

/*  private void ensureCapacity() {
    if (size == data.length) {
      int newCapacity = data.length * 2;
      data = Arrays.copyOf(data, newCapacity);
    }
  }*/

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(data, size));
  }

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
