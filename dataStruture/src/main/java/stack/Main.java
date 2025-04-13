package stack;

import likedList.Node;

public class Main {
  public static void main(String[] args) {
    MyStack<Integer> stack = new MyStack<>();

    System.out.println("A pilha está vazia? " + stack.isEmpty());
    System.out.println("Tamanho da pilha: " + stack.size());

    System.out.println("\nAdicionando elementos...");
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.printList();

    System.out.println("\nElemento no topo (peek): " + stack.peek().getData());
    System.out.println("Tamanho da pilha: " + stack.size());
    System.out.println("A pilha está vazia? " + stack.isEmpty());

    System.out.println("\nRemovendo elementos (pop)...");
    System.out.println("Removido: " + stack.pop().getData());
    stack.printList();

    System.out.println("Novo topo: " + stack.peek().getData());
    System.out.println("Tamanho da pilha: " + stack.size());

    System.out.println("\nEsvaziando a pilha...");
    stack.pop();
    stack.pop();
    System.out.println("A pilha está vazia? " + stack.isEmpty());
    stack.printList();
  }
}
