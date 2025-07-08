# Programação Dinâmica (DP)

> Programação Dinâmica é uma técnica para resolver problemas complexos ao dividi-los em subproblemas menores, armazenando os resultados desses subproblemas para evitar cálculos repetidos e combinando suas soluções para obter a resposta final.

## Conceitos Básicos

- **Subproblemas**: O problema é dividido em partes menores que podem ser resolvidas independentemente.
- **Sobreposição de Subproblemas**: Os mesmos subproblemas são resolvidos várias vezes em uma abordagem recursiva ingênua, tornando a memoização ou tabulação eficiente.
- **Estrutura Ótima**: A solução ótima do problema depende das soluções ótimas dos subproblemas.
- **Memoização**: Armazena resultados de subproblemas em uma estrutura (como um array ou tabela) para reutilização.
- **Programação Dinâmica**: Combina soluções de subproblemas para resolver o problema original, geralmente de forma recursiva (Top-Down) ou iterativa (Bottom-Up).

## Abordagens

- **Top-Down**: Usa recursão com memoização para armazenar resultados de subproblemas. É intuitivo, mas pode consumir mais memória devido à pilha de recursão.
- **Bottom-Up**: Usa iteração para construir a solução a partir dos subproblemas menores, geralmente mais eficiente em espaço e tempo.

## Exemplos Comuns

### Fibonacci (Top-Down com Memoização)

> Sequência onde cada número é a soma dos dois anteriores (e.g., 0, 1, 1, 2, 3, 5, 8, ...).

```java
public class Fibonacci {
    static int[] memo;

    public static int fib(int n) {
        // Casos base: fib(0) = 0, fib(1) = 1
        if (n <= 1) return n;
        // Verifica se o resultado já foi calculado
        if (memo[n] != -1) return memo[n];
        // Calcula e armazena o resultado
        return memo[n] = fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        int n = 10;
        memo = new int[n + 1];
        Arrays.fill(memo, -1); // Inicializa com -1
        System.out.println(fib(n)); // Saída: 55
    }
}
```

**Complexidade**:
- Tempo: O(n) — cada subproblema é resolvido uma vez.
- Espaço: O(n) — para o array de memoização.

### Fibonacci (Bottom-Up com Tabulação)

> Abordagem iterativa que constrói a solução a partir dos casos base.

```java
public class FibonacciIterativo {
    public static int fib(int n) {
        // Casos base: fib(0) = 0, fib(1) = 1
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; // Caso base
        dp[1] = 1; // Caso base

        // Preenche a tabela de baixo para cima
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(10)); // Saída: 55
    }
}
```

**Otimização de Espaço**:
- Como Fibonacci só depende dos dois valores anteriores, pode-se usar duas variáveis em vez de um array, reduzindo o espaço para O(1).

```java
public static int fibOtimizado(int n) {
    if (n <= 1) return n;
    int prev = 0, curr = 1;
    for (int i = 2; i <= n; i++) {
        int next = prev + curr;
        prev = curr;
        curr = next;
    }
    return curr;
}
```

**Complexidade**:
- Tempo: O(n) — itera de 2 a n.
- Espaço: O(n) para a versão com array; O(1) para a versão otimizada.

### Knapsack Problem (Mochila 0/1)

> Problema clássico de otimização onde se deve maximizar o valor total de itens colocados em uma mochila com capacidade limitada, sem exceder o peso máximo. Cada item pode ser incluído (1) ou não (0).

```java
public class Knapsack {
    public static int knapsack(int[] w, int[] v, int W) {
        int n = w.length;
        int[][] dp = new int[n + 1][W + 1]; // Tabela DP

        // Para cada item
        for (int i = 1; i <= n; i++) {
            // Para cada capacidade da mochila
            for (int j = 0; j <= W; j++) {
                if (w[i - 1] > j)
                    dp[i][j] = dp[i - 1][j]; // Item não cabe, usa s```olução anterior
                else
                    // Escolhe o maior valor: não incluir ou incluir o item
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] pesos = {1, 3, 4, 5};
        int[] valores = {1, 4, 5, 7};
        int capacidade = 7;
        System.out.println(knapsack(pesos, valores, capacidade)); // Saída: 9
    }
}
```
