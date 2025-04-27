package hashMap;

public class Node <k, v>{
  k key;
  v value;
  Node<k,v> next;

  Node(k key, v value){
    this.key = key;
    this.value = value;
  }
}
