package hashMap;

import java.util.Objects;

public class MyHashMap<K, V> {
  private static final int INITIAL_CAPACITY = 16;
  private static final float LOAD_FACTOR = 0.75f;
  private Node<K, V>[] buckets;
  private int size = 0;

  public MyHashMap() {
    buckets = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
  }

  private int getBucketIndex(K key) {
    int hash = Objects.hashCode(key);
    return key == null ? 0 : (hash ^ (hash >>> 16)) & (buckets.length - 1);
  }

  public void put(K key, V value) {
    int index = getBucketIndex(key);
    Node<K, V> head = buckets[index];

    while (head != null) {
      if (Objects.equals(head.key, key)) {
        head.value = value;
        return;
      }
      head = head.next;
    }

    Node<K, V> newNode = new Node<>(key, value);
    newNode.next = buckets[index];
    buckets[index] = newNode;
    size++;

    if ((float) size / buckets.length >= LOAD_FACTOR) {
      resize();
    }
  }

  public V get(K key) {
    int index = getBucketIndex(key);
    Node<K, V> head = buckets[index];

    while (head != null) {
      if (Objects.equals(head.key, key)) {
        return head.value;
      }
      head = head.next;
    }
    return null;
  }

  public boolean containsKey(K key) {
    return get(key) != null;
  }

  public void remove(K key) {
    int index = getBucketIndex(key);
    Node<K, V> head = buckets[index];
    Node<K, V> prev = null;

    while (head != null) {
      if (Objects.equals(head.key, key)) {
        if (prev != null) {
          prev.next = head.next;
        } else {
          buckets[index] = head.next;
        }
        size--;
        return;
      }
      prev = head;
      head = head.next;
    }
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void resize() {
    Node<K, V>[] oldBuckets = buckets;
    buckets = (Node<K, V>[]) new Node[oldBuckets.length * 2];
    size = 0;

    for (Node<K, V> node : oldBuckets) {
      while (node != null) {
        put(node.key, node.value);
        node = node.next;
      }
    }
  }
}