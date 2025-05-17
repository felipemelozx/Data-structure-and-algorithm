# BinaryTree

A **Binary Tree** (árvore binária) é uma estrutura de dados hierárquica onde cada nó tem no máximo dois filhos: **esquerda** e **direita**. Ela é amplamente utilizada em algoritmos de busca, ordenação e organização de dados de forma eficiente.

## Aplicações Reais

A árvore binária é essencial em muitos sistemas que exigem operações rápidas de inserção, busca e remoção. Exemplos:

- **Sistemas de banco de dados**: Para organizar registros de forma hierárquica e balanceada.
- **Compiladores e análise de expressões**: Para representar a estrutura de expressões matemáticas.
- **Autocompletar e buscas preditivas**: Árvores binárias (especialmente as balanceadas) são usadas para armazenar palavras com performance otimizada.
- **Jogos e IA**: Árvores de decisão são comumente implementadas como binary trees.

---

## Tipos de BinaryTree em Java

Embora o Java não tenha uma estrutura de `BinaryTree` pronta na biblioteca padrão, é comum implementá-la manualmente ou usar variações mais especializadas, como `TreeMap` ou `TreeSet`.

### 1. Árvore Binária Simples (Customizada)

Estrutura básica de uma árvore binária:

```java
class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}
```

Operações básicas:

```java
class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}

public class BinaryTree {
    Node root;

    // Insere um valor na árvore
    void insert(int value) {
        root = insertRecursive(root, value);
    }

    // Função auxiliar recursiva para inserção
    Node insertRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }

        return node;
    }

    // Busca um valor na árvore
    boolean search(int value) {
        return searchRecursive(root, value);
    }

    // Função auxiliar recursiva para busca
    boolean searchRecursive(Node node, int value) {
        if (node == null) {
            return false;
        }

        if (value == node.value) {
            return true;
        }

        return value < node.value
            ? searchRecursive(node.left, value)
            : searchRecursive(node.right, value);
    }
}
```

- **Vantagens**:
    - Inserção e busca com complexidade média de **O(log n)**.
    - Estrutura flexível, permite operações personalizadas.
- **Desvantagens**:
    - Se não balanceada, pode se tornar uma lista (complexidade O(n)).

---

## **3. Balanceamento: Por Que e Como Evitar Árvores Degeneradas**

Uma árvore binária não balanceada pode degenerar em uma **lista encadeada** se os elementos forem inseridos em ordem crescente ou decrescente. Por exemplo, inserir `1, 2, 3, 4, 5` resultaria em:

```jsx
 1
   \
    2
		 \
			 3
				 \
					 4
						\
							5
```

Isso transforma operações de busca, inserção e remoção em **O(n)** (complexidade linear), perdendo a eficiência das árvores binárias.

---

### **Por Que Balancear?**

O balanceamento garante que a árvore mantenha uma **altura logarítmica** em relação ao número de nós, preservando a complexidade **O(log n)** para operações críticas. Sem balanceamento, a árvore perde sua principal vantagem: a eficiência.

---

### **Como o Java Faz o Balanceamento Automático?**

As classes `TreeMap` e `TreeSet` em Java utilizam **Red-Black Trees**, uma variação de árvore binária balanceada que aplica regras rígidas durante inserções e remoções:

1. **Regras da Red-Black Tree**:
    - Todo nó é **vermelho** ou **preto**.
    - A raiz é sempre **preta**.
    - Nenhum nó vermelho pode ter um filho vermelho.
    - Todos os caminhos de um nó até as folhas têm o mesmo número de nós pretos.
2. **Balanceamento Automático**:
    - Após inserções/remoções, a árvore é rebalanceada via **rotações** (mudança de hierarquia entre nós) e **recoloração**.
    - Exemplo de rotação:

### **Árvore Desbalanceada vs. Balanceada**

Quando inserimos valores em ordem decrescente, uma árvore binária simples (sem balanceamento) pode ficar desbalanceada:

```
// Árvore desbalanceada (inserção: 3, 2, 1)
    3
   /
  2
 /
1

```

Essa estrutura vira praticamente uma lista ligada, com desempenho degradado (**O(n)** para busca).

**Após rotação à direita (balanceamento manual ou automático):**

```

// Árvore balanceada
   2
  / \
 1   3

```

---

### **Custo do Balanceamento**

O balanceamento adiciona um **overhead computacional**, mas mantém a eficiência assintótica:

- **Complexidade**: Operações como `put()`, `get()` e `remove()` em `TreeMap` têm complexidade **O(log n)**.
- **Trade-off**: A lógica de balanceamento torna inserções/remoções **mais lentas** que em uma árvore não balanceada, mas garante desempenho estável em qualquer cenário.

---

### **Exemplo de Balanceamento no Java (TreeMap)**

```java
java
CopyEdit
TreeMap<Integer, String> treeMap = new TreeMap<>();

// Inserção de elementos em ordem decrescente (cenário de pior caso)
treeMap.put(5, "Cinco");
treeMap.put(4, "Quatro");
treeMap.put(3, "Três");
treeMap.put(2, "Dois");
treeMap.put(1, "Um");

```

Mesmo com os dados inseridos em ordem decrescente, a estrutura interna do `TreeMap` é automaticamente balanceada por trás (via Red-Black Tree), resultando em uma árvore como:

```
cpp
CopyEdit
// Estrutura interna (ilustrativa)
      4
     / \
    2   5
   / \
  1   3

```

Isso garante que todas as operações continuem rápidas mesmo em casos que seriam problemáticos para uma árvore binária simples.

### 2. `TreeSet` e `TreeMap` (Implementações Red-Black Tree)

Java oferece `TreeSet` e `TreeMap`, que usam **Red-Black Trees**, uma variação de árvore binária balanceada.

### `TreeSet`

```java
Set<Integer> set = new TreeSet<>(); set.add(10); set.add(5); set.add(15);

```

### `TreeMap`

```java

Map<Integer, String> map = new TreeMap<>(); map.put(1, "um");
map.put(2, "dois");

```

- **Vantagens**:
    - Mantêm os elementos **ordenados**.
    - Operações como `add`, `remove`, `contains` têm complexidade **O(log n)**.
- **Desvantagens**:
    - Mais custo computacional comparado a `HashSet` e `HashMap`.

---

## BinaryTree vs. Outras Estruturas

### BinaryTree vs. Array

|  | BinaryTree | Array |
| --- | --- | --- |
| Inserção | O(log n) média | O(1) (final) |
| Busca ordenada | O(log n) média | O(n) ou O(log n)* |
| Estrutura hierárquica | Sim | Não |
| Crescimento dinâmico | Sim | Não (fixo ou resize) |
- Arrays ordenados permitem busca binária.

**Conclusão**: Use `BinaryTree` quando precisar de estrutura ordenada dinâmica com inserções e buscas frequentes. Use `Array` para armazenar dados em sequência ou quando a ordem importa menos.

---

### BinaryTree vs. HashMap

|  | BinaryTree | HashMap |
| --- | --- | --- |
| Ordem dos elementos | Mantida (ordenada) | Não garantida |
| Complexidade busca | O(log n) | O(1) média |
| Custom comparator | Sim (`TreeMap`) | Limitado |
| Uso de memória | Maior | Menor (em geral) |

**Conclusão**: Use `HashMap` para acesso rápido sem necessidade de ordenação. Use `TreeMap` (binary tree) quando a ordenação dos dados for importante.

---

## Exemplo Real com BinaryTree: Sistema de Ranking

Imagine um sistema de **ranking de jogadores**. Usar uma árvore binária balanceada permite inserir pontuações e consultar os melhores jogadores rapidamente.

```java
public class RankingExemplo {
	public static void main(String[] args) {
			// TreeMap ordenado em ordem decrescente (maior pontuação primeiro)
			TreeMap<Integer, String> ranking = new TreeMap<>(Collections.reverseOrder());
	    // Inserção dos jogadores com suas respectivas pontuações
	    ranking.put(1500, "Alice");
	    ranking.put(1200, "Bob");
	    ranking.put(1800, "Carol");
	
	    // Exibe o jogador com a maior pontuação
	    Map.Entry<Integer, String> topJogador = ranking.firstEntry();
	    System.out.println("Top jogador: " + topJogador);
	    // Saída: Top jogador: 1800=Carol
	}
}
```

### Por que TreeMap?

- Ordenação automática das pontuações.
- Inserções e consultas eficientes (`O(log n)`).
- Ideal para rankings dinâmicos que mudam frequentemente.

---

## BinaryTree vs. Stack/Queue

| Estrutura | Ordem | Uso principal |
| --- | --- | --- |
| BinaryTree | Hierárquica / Ordenada | Busca e organização |
| Stack | LIFO | Processamento reverso, histórico |
| Queue | FIFO | Processamento em ordem |

---

## Complexidade de Operações

| Operação | BinaryTree (balanceada) | BinaryTree (não balanceada) |
| --- | --- | --- |
| Inserção | O(log n) | O(n) |
| Busca | O(log n) | O(n) |
| Remoção | O(log n) | O(n) |

---

## Conclusão

A **BinaryTree** é uma ferramenta poderosa para quando a ordenação e a estrutura hierárquica dos dados são essenciais. Seja em rankings, sistemas de sugestão, compiladores ou bancos de dados, sua flexibilidade e eficiência (especialmente quando balanceada) a tornam uma escolha certeira para diversos desafios.