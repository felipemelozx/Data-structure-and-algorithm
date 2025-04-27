package hashMap;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    MyHashMap<Integer, String> map = new MyHashMap<>();
    Random random = new Random();
    int[] sizes = {10000, 100000};

    for (int size : sizes) {
      System.out.println("\nTestando com " + size + " elementos:");

      // Teste de put
      long startTime = System.nanoTime();
      for (int i = 0; i < size; i++) {
        map.put(random.nextInt(), "Value" + i);
      }
      long endTime = System.nanoTime();
      double putTime = (endTime - startTime) / 1_000_000.0; // Em milissegundos
      System.out.printf("Tempo para %d inserções: %.3f ms (%.3f ns por operação)%n",
          size, putTime, (endTime - startTime) / (double) size);

      // Teste de get
      startTime = System.nanoTime();
      for (int i = 0; i < size; i++) {
        map.get(random.nextInt());
      }
      endTime = System.nanoTime();
      double getTime = (endTime - startTime) / 1_000_000.0;
      System.out.printf("Tempo para %d recuperações: %.3f ms (%.3f ns por operação)%n",
          size, getTime, (endTime - startTime) / (double) size);

      // Teste de containsKey
      startTime = System.nanoTime();
      for (int i = 0; i < size; i++) {
        map.containsKey(random.nextInt());
      }
      endTime = System.nanoTime();
      double containsTime = (endTime - startTime) / 1_000_000.0;
      System.out.printf("Tempo para %d verificações containsKey: %.3f ms (%.3f ns por operação)%n",
          size, containsTime, (endTime - startTime) / (double) size);

      // Teste de remove
      startTime = System.nanoTime();
      for (int i = 0; i < size; i++) {
        map.remove(random.nextInt());
      }
      endTime = System.nanoTime();
      double removeTime = (endTime - startTime) / 1_000_000.0;
      System.out.printf("Tempo para %d remoções: %.3f ms (%.3f ns por operação)%n",
          size, removeTime, (endTime - startTime) / (double) size);

    }
  }
}
