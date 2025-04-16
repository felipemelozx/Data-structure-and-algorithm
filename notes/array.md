# Array

Um **array** é uma estrutura de dados que armazena uma sequência de elementos do mesmo tipo em posições contíguas na memória. Cada elemento é acessado por um índice numérico, começando em zero. Arrays são estruturas estáticas, ou seja, possuem tamanho fixo após a criação.

## Exemplo da vida real

Imagine um organizador de talheres com divisórias fixas: cada compartimento tem uma posição específica e armazena um tipo de talher. Você pode acessar qualquer compartimento diretamente, basta saber o índice. Esse é o comportamento de um array: acesso direto e rápido.

## Exemplos práticos do uso de Array

- Armazenar notas de alunos em um sistema.
- Representar tabuleiros de jogos (como Sudoku ou Xadrez).
- Manipulação de dados em algoritmos numéricos.

## Array em Java: Características e uso

Em Java, arrays são objetos com tamanho fixo e elementos indexados. Eles podem armazenar tanto tipos primitivos (`int`, `double`, etc.) quanto objetos (`String`, etc.).

### 1. Criando um array

```java
public class ExemploArray {
  public static void main(String[] args) {
    int[] numeros = new int[5]; // Cria um array de inteiros com 5 posições

    numeros[0] = 10;
    numeros[1] = 20;
    numeros[2] = 30;

    for (int i = 0; i < numeros.length; i++) {
      System.out.println(numeros[i]);
    }
  }
}
```
### 2. Inicialização direta

```java
public class ExemploArray {
  public static void main(String[] args) {
    String[] frutas = {"Maçã", "Banana", "Laranja"};
    
    for (String fruta : frutas) {
      System.out.println(fruta);
    }
  }
}
```

## Vantagens

- Acesso rápido a qualquer elemento pelo índice (complexidade **O(1)**).
- Substituir valor: O(1), sem deslocamento.
- Simples de declarar e usar.
- Boa performance em estruturas fixas.

## Desvantagens

- Tamanho fixo: uma vez criado, não pode ser redimensionado.
- Inserir valor novo no meio: O(n), com deslocamento (se tiver espaço) ou criação de novo array.
- Não oferece métodos utilitários como uma lista (`List`).
## Comparações

### Comparação: Array vs. List

| Característica              | Array                          | List (`ArrayList`, `LinkedList`)         |
|-----------------------------|--------------------------------|------------------------------------------|
| **Tamanho**                 | Fixo após criação              | Dinâmico                                 |
| **Acesso por índice**       | Sim (O(1))                     | Sim (`ArrayList`: O(1), `LinkedList`: O(n)) |
| **Inserção/remoção fácil**  | Não (exige deslocamento)       | Sim (especialmente em `LinkedList`)      |
| **Inserção no meio**        | O(n) se for necessário criar novo array | O(n)                                     |
| **Substituição de valor**   | O(1) — basta alterar o índice  | O(1) em `ArrayList`, O(n) em `LinkedList`|
| **Performance**             | Mais rápido em acesso direto   | Mais flexível, mas pode ser mais lento   |
| **Métodos utilitários**     | Não (exceto via `java.util.Arrays`) | Sim (`add`, `remove`, `contains`, etc.)  |
| **Eficiência de memória**   | Mais leve (armazenamento contíguo) | Mais pesado (overhead de objetos)       |

> 🔹 **Observação:** Inserir um valor em uma posição específica de um array *sem alterar o tamanho* (ou seja, apenas substituir) é uma operação **O(1)**.  
> 🔸 No entanto, inserir um novo valor exigindo realocação (por exemplo, empurrando elementos para frente ou criando um novo array) é uma operação **O(n)**.

#### Quando usar Array:
- Quando o tamanho é conhecido e fixo.
- Quando o desempenho no acesso direto é essencial.
- Em situações com restrições de memória (arrays são mais leves).

#### Quando usar List:
- Quando precisa de estrutura dinâmica.
- Quando precisa de métodos de manipulação.
- Em aplicações com muitas inserções/remoções.

---

## Desempenho e Complexidade

| Operação              | Array       | `ArrayList` | `LinkedList` |
|-----------------------|-------------|-------------|--------------|
| **Acesso por índice** | O(1)        | O(1)        | O(n)         |
| **Inserção no final** | O(n)¹       | O(1)*       | O(1)         |
| **Inserção no meio**  | O(n)        | O(n)        | O(n)         |
| **Remoção**           | O(n)        | O(n)        | O(n)         |
> 🔹 Para arrays, inserção no final exige criar um novo array se o tamanho estiver cheio **O(n)**. Atribuição em índice existente é **O(1)**.  
> 🔹 `ArrayList` pode ter **O(n)** em inserção se precisar crescer.

---

## Conclusão

Arrays são estruturas simples, rápidas e eficientes para armazenar dados com tamanho fixo. São ótimos para representar coleções pequenas ou com número conhecido de elementos. Para maior flexibilidade, estruturas como `ArrayList` são mais adequadas.
