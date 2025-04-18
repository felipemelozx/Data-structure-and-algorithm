# LinkedList

A `LinkedList` é uma estrutura de dados linear baseada em nós encadeados. Em Java, ela implementa as interfaces `List`, `Deque` e `Queue`, tornando-a extremamente versátil: pode ser usada como lista, fila ou pilha. Diferentemente de arrays, os elementos não são armazenados de forma contígua, mas ligados por referências.

Cada nó em uma `LinkedList` contém:

- **Dado**: o valor armazenado (ex.: "A", "B").
- **Referência ao próximo**: aponta para o próximo nó.
- **Referência ao anterior** (em uma lista duplamente ligada): aponta para o nó anterior.

### Exemplo da vida real

Imagine uma corrente: cada elo (nó) conhece apenas o próximo (e, em uma lista duplamente ligada, também o anterior). Isso permite adicionar ou remover elos facilmente no início ou fim, sem mover os outros.

---

## Exemplos práticos de uso da LinkedList

- **Navegação em playlists de música**: avançar ou retroceder faixas.
- **"Desfazer" (undo) em editores de texto**: armazenar estados anteriores.
- **Gerenciamento de processos em sistemas operacionais**: filas de execução.
- **Fila de impressão**: como uma `Queue`.
- **Cache ou buffers de dados**: inserções/remoções frequentes.

---

## LinkedList em Java

A classe `LinkedList`, do pacote `java.util`, implementa uma **lista duplamente ligada**, onde cada nó conhece o anterior e o próximo.

### Como usar como Lista

```java
import java.util.LinkedList;

public class LinkedListExemplo {
    public static void main(String[] args) {
        LinkedList<String> lista = new LinkedList<>();

        lista.add("A");
        lista.add("B");
        lista.add("C");

        System.out.println(lista.get(1)); // "B"

        lista.remove("B"); // Remove o elemento "B"
        lista.addFirst("X"); // Adiciona no início
        lista.addLast("Z");  // Adiciona no fim

        // Iteração com for-each
        for (String item : lista) {
            System.out.println(item); // X, A, C, Z
        }

        // Iteração com Iterator
        Iterator<String> iterator = lista.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next()); // X, A, C, Z
        }
    }
}
```

### Como usar como Fila

```java
import java.util.LinkedList;
import java.util.Queue;

public class FilaExemplo {
    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();

        fila.offer("A");
        fila.offer("B");
        fila.offer("C");

        System.out.println(fila.poll()); // "A"
    }
}
```

### Como usar como Pilha

```java
import java.util.LinkedList;

public class PilhaExemplo {
    public static void main(String[] args) {
        LinkedList<String> pilha = new LinkedList<>();

        pilha.push("A");
        pilha.push("B");
        pilha.push("C");

        while (!pilha.isEmpty()) {
            System.out.println(pilha.pop()); // C, B, A
        }
    }
}
```

---

## Vantagens

- **Versatilidade**: atua como lista, fila ou pilha.
- **Inserções/remoções rápidas no início e no fim**: O(1) para essas operações.
- **Suporte nativo a operações**: `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()`.
- **Permite elementos** `null`.

---

## Desvantagens

- **Acesso por índice lento**: O(n), pois percorre a lista até o elemento desejado.
- **Maior consumo de memória**: armazena referências `prev` e `next` para cada nó, ao contrário de estruturas baseadas em array (como `ArrayList`).
- **Não é segura para threads**: requer sincronização manual em ambientes concorrentes.

---

## Comparações

### LinkedList vs ArrayList

| Característica | LinkedList | ArrayList |
| --- | --- | --- |
| Estrutura Interna | Lista encadeada | Array dinâmico |
| Inserção no meio | Lenta (O(n)) | Lenta (O(n)) |
| Inserção no início | Rápida (O(1)) | Lenta (O(n)) |
| Acesso por índice | Lento (O(n)) | Rápido (O(1)) |
| Consumo de memória | Alto | Baixo |
| Ideal para | Inserções/remoções nas extremidades | Acesso rápido por índice |

**Quando usar LinkedList**:\
Se o foco for **inserções e remoções frequentes nas extremidades**, a `LinkedList` é ideal.

**Quando evitar**:\
Se precisar acessar elementos por índice frequentemente, prefira `ArrayList`.

---

## Operações Avançadas

A `LinkedList` suporta métodos avançados, como:

- **Iteração reversa** com `descendingIterator()`:

```java
public class ReversaExemplo {
  public static void main(String[] args) {
    Iterator<String> descIterator = lista.descendingIterator();
    while (descIterator.hasNext()) {
        System.out.println(descIterator.next()); // Z, C, A, X
    }
  }
}

```

- **ListIterator** para navegação bidirecional:

```java
public class ListIteratorExemplo {
  public static void main(String[] args) {
    ListIterator<String> listIterator = lista.listIterator();
    while (listIterator.hasNext()) {
      System.out.println(listIterator.next());
    }
    while (listIterator.hasPrevious()) {
      System.out.println(listIterator.previous());
    }
  }
}
```

---

## Erros Comuns

- **Acesso por índice em loops**: usar `get(i)` em loops é lento (O(n) por acesso).

```java
public class LoopExemplo {
  public static void main(String[] args) {
    // Evite
    for (int i = 0; i < lista.size(); i++) {
      System.out.println(lista.get(i));
    }
    // Prefira
    for (String item : lista) {
      System.out.println(item);
    }   
  }
}
```

- **Não verificar lista vazia**: chamar `pop()` ou `poll()` em uma lista vazia pode lançar exceção.

  ```java
  if (!lista.isEmpty()) {
      String item = lista.pop();
  }
  ```

- **Modificações concorrentes**: alterar a lista durante iteração causa `ConcurrentModificationException`.

  ```java
  Iterator<String> iterator = lista.iterator();
  while (iterator.hasNext()) {
      String item = iterator.next();
      // Use iterator.remove() em vez de lista.remove(item)
      iterator.remove();
  }
  ```

---

## Sincronização para Concorrência

A `LinkedList` não é **segura para threads**. Para usá-la em ambientes concorrentes, sincronize manualmente:

```java
import java.util.Collections;

LinkedList<String> lista = new LinkedList<>();
List<String> threadSafeList = Collections.synchronizedList(lista);

// Ou use CopyOnWriteArrayList para cenários específicos
import java.util.concurrent.CopyOnWriteArrayList;
List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
```

---

## Boas Práticas

- **Use tipos genéricos**: evite erros em tempo de execução.

  ```java
  LinkedList<String> lista = new LinkedList<>(); // Correto
  LinkedList listaRaw = new LinkedList(); // Evite
  ```

- **Prefira métodos específicos**: use `addFirst()`/`addLast()` em vez de `add(0)`/`add(size())` para melhor desempenho.

- **Verifique lista vazia**: antes de chamar `pop()`, `poll()` ou `peek()`.

- **Evite modificações concorrentes**: use `Iterator` ou `ListIterator` para remoções seguras durante iteração.

---

## Comparação de Desempenho

| Implementação | `push/pop` | `peek` | Thread-safe | Casos de uso recomendados |
| --- | --- | --- | --- | --- |
| Stack | O(1) | O(1) | Sim | Legado, evitar |
| ArrayDeque | O(1) | O(1) | Não | Filas e pilhas eficientes |
| LinkedList | O(1) | O(1) | Não | Inserções/remoções nas extremidades |

**Nota**: `ArrayDeque` é geralmente preferido para filas e pilhas devido à eficiência e menor consumo de memória. A `LinkedList` é usada quando se precisa de suporte à interface `List` ou compatibilidade com APIs legadas.

---

## Estrutura Visual da LinkedList

A `LinkedList` pode ser visualizada assim:

```
[null]<--[X|prev|next]<-->[A|prev|next]<-->[C|prev|next]<-->[Z|prev|next]-->null
```

- Cada nó armazena o dado (`X`, `A`, etc.) e ponteiros para o nó anterior (`prev`) e próximo (`next`).
- O primeiro nó tem `prev = null`, e o último tem `next = null`.

---

## Resumo Prático

- **Use LinkedList** para inserções/remoções rápidas no início ou fim.
- **Evite** quando o acesso aleatório por índice for frequente.
- **Alternativa eficiente**: prefira `ArrayDeque` para filas ou pilhas, exceto quando precisar da interface `List`.
- **Sincronização**: use `Collections.synchronizedList()` em ambientes concorrentes.

---

## Conclusão

A `LinkedList` é uma estrutura poderosa e flexível na biblioteca Java, ideal para cenários com manipulações frequentes nas extremidades. Apesar de seu acesso por índice ser lento, ela brilha em operações como `addFirst()`, `addLast()`, `push()` e `pop()`. Para casos onde a eficiência de memória ou acesso rápido por índice é crucial, considere `ArrayList` ou `ArrayDeque`.