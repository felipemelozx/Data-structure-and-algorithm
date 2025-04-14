# Stack
A stack, ou pilha, é uma estrutura de dados do tipo LIFO (Last In, First Out), ou seja,
o último elemento a entrar é o primeiro a sair. É uma estrutura linear, onde os
elementos são organizados em sequência, mas com acesso restrito: sempre inserimos e
removemos do topo da pilha.

Exemplo da vida real:
pense em uma pilha de pratos no escorredor. Você coloca os pratos limpos um em cima do outro.
Quando alguém vá pegar um prato, sempre pega o de cima “O último que foi colocado”.
Esse é exatamente o comportamento esperado de uma stack.

## Exemplos práticos do uso de Stack
- Histórico de navegação em navegadores de internet como Chrome e Firefox.
- Desfazer ações em editores de texto.
- Pilha de chamadas de funções.

## Stack em Java: Implementações disponíveis

Em java, não existe uma interface `Stack` como ocorre com `Queue`, mas há
diferentes implementações de `Stack` disponíveis na biblioteca padrão que
podem ser usadas como pilha, cada uma com suas próprias características e
recomendações de uso.

### 1. `Stack`: class da biblioteca padrão, herda de vetor
- A class `Stack` é a implementação tradicional da pilha em java. Ela herda
  de `vector`, tornando sincronizada por padrão. Isso significa que
  ela foi projetada para funcionar em ambientes com múltiplas threads.

- **Como usar**:
    - Utilize ``push()`` para inserir elementos no topo da pilha e `pop()` para remover do topo e `peek()`
      para visualizar o topo sem remover, respeitando o princípio LIFO.

```java
public class StackExemplo {
  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();

     stack.push("Primeiro");
     stack.push("Segundo");
     stack.push("Terceiro");

     System.out.println(stack.peek()); // "Terceiro"

    while (!stack.isEmpty()) {
        System.out.println(stack.pop()); // Remove e imprime os elementos em ordem inversa
    }
  }
}
```

- **Vantagens**:
    - Inserção e remoção têm complexidade **O(1)**.
    - Fácil de usar, já vem com métodos específicos de pilha (push, pop, peek).
    - É thread-safe, o que pode ser útil em alguns cenários concorrentes.
- **Desvantagens**:
    - Baseada em Vector, implicando sincronização desnecessária em ambientes single-thread.
    - Desempenho inferior a outras opções mais modernas, como `ArrayDeque`.

### 2. ``ArrayDeque`` (Deque usado como Stack)
`ArrayDeque` é uma estrutura baseada em array redimensionável, muito mais
eficiente que `Stack` para uso de pilha.

- **Como usar**:
    - Utilize ``push()`` para adicionar elementos no topo e `pop()` para remover. Também oferece `peek()`.

```java
public class DequeComoStack {
  public static void main(String[] args) {
    Deque<String> stack = new ArrayDeque<>();

        stack.push("Primeiro");
        stack.push("Segundo");
        stack.push("Terceiro");

        System.out.println(stack.peek()); // "Terceiro"

        while (!stack.isEmpty()) {
            System.out.println(stack.pop()); // Remove em ordem LIFO
        }
    }
}
```
-
- **Vantagens**:
    - Melhor desempenho em tempo de execução em comparação com `Stack`.
    - Operações push e pop têm complexidade O(1).
    - Não tem o overhead de sincronização desnecessária

- **Desvantagens**:
    - Não é thread-safe. Em ambientes multithreaded, exige sincronização manual.
    - Não permite elementos null.

### 3. `LinkedList` (usada como Stack)
Embora geralmente usada como lista ou fila, a LinkedList também pode atuar como pilha, usando os métodos `push()` e `pop()`.

- **Como usar**:
    - ``push()`` insere no início da lista, `pop()` remove do início, funcionando como uma pilha.

```java
public class LinkedListComoStack {
  public static void main(String[] args) {
      LinkedList<String> stack = new LinkedList<>();
    
      stack.push("A");
      stack.push("B");
      stack.push("C");

      while (!stack.isEmpty()) {
        System.out.println(stack.pop()); // C, B, A
      }
  }
}
```

- **Vantagens**:
    - Flexível: pode atuar como lista, fila ou pilha.
    - Operações de inserção e remoção no início têm complexidade O(1).

- **Desvantagens**:
    - Mais consumo de memória que `ArrayDeque`, devido às referências dos nós.
    - Também não é thread-safe.

## Comparações

### 1. Stack vs. Queue

| Característica        | Stack (Pilha)                     | Queue (Fila)                     |
|-----------------------|-----------------------------------|----------------------------------|
| **Ordem de Acesso**   | LIFO (Last In, First Out)         | FIFO (First In, First Out)       |
| **Inserção**          | Sempre no topo                    | Sempre no final                  |
| **Remoção**           | Sempre do topo                    | Sempre do início                 |
| **Exemplo Real**      | Histórico de navegação            | Fila de impressão                |
| **Uso Comum**         | Algoritmos recursivos, undo       | Processamento de tarefas, buffers|

#### Quando usar Stack:
Quando o último elemento adicionado precisa ser o primeiro a ser processado, como em pilha de chamadas de métodos, desfazer ações ou navegação entre páginas.

#### Quando usar Queue:
Quando a ordem de chegada deve ser respeitada, como em filas de requisições, envio de mensagens ou tarefas em background.

### 2. Stack vs. List

| Característica        | Stack (Pilha)                     | List (Lista)                     |
|-----------------------|-----------------------------------|----------------------------------|
| **Estrutura Ideal**   | LIFO                              | Acesso indexado                  |
| **Inserção/Remoção**  | Sempre no topo                    | Em qualquer posição              |
| **Acesso Aleatório**  | Não                               | Sim                              |
| **Semântica Clara**   | Sim, orientada a pilhas           | Não, genérica                    |

#### Por que não usar List como Stack?
Embora seja possível remover o último elemento de uma lista com `remove(size() - 1)`, isso torna o código semanticamente confuso. Listas permitem acesso em qualquer posição, o que vai contra o comportamento esperado de uma pilha, dificultando a manutenção e clareza do código.

## Desempenho e Complexidade

| Implementação    | push/pop | peek  | Thread-safe | Observações                          |
|------------------|----------|-------|-------------|--------------------------------------|
| **Stack**        | O(1)     | O(1)  | Sim         | Baseada em Vector, mais lenta        |
| **ArrayDeque**   | O(1)     | O(1)  | Não         | Mais rápida, recomendada             |
| **LinkedList**   | O(1)     | O(1)  | Não         | Boa opção, mas consome mais memória  |

### Resumo de Desempenho
- Todas as implementações oferecem `push()` e `pop()` com complexidade **O(1)**.
- **`ArrayDeque`** tem o melhor desempenho geral para uso como pilha.
- **`Stack`** é sincronizada, o que gera overhead em aplicações não concorrentes.

## Conclusão

A pilha (**stack**) é uma estrutura de dados simples, porém poderosa, com aplicações práticas em algoritmos, controle de fluxo e manipulação de dados. Em Java, embora a classe `Stack` ainda exista, a melhor escolha para novas aplicações é o **`ArrayDeque`**, que combina desempenho e simplicidade.

### Resumo Prático
- Use `Stack` apenas se precisar de sincronização nativa.
- Prefira **`ArrayDeque`** para código moderno, rápido e legível.
- Evite usar `List` como stack — não foi projetada para isso e pode gerar confusão.