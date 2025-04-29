# HashMap em Java

O `HashMap` é uma estrutura de dados da linguagem Java que implementa a interface `Map`. Ele armazena pares chave-valor, permitindo que valores sejam acessados rapidamente por meio de suas chaves.

Internamente, o `HashMap` utiliza uma tabela de hash, que distribui os elementos com base no valor retornado pelos métodos `hashCode()` das chaves. Isso proporciona alta performance em operações como inserção, busca e remoção — geralmente em tempo constante (`O(1)`).

## Características principais

- **Chaves únicas**: Cada chave no `HashMap` deve ser única. Valores duplicados são permitidos, mas chaves duplicadas substituem o valor anterior.
- **Aceita null**: Permite uma chave `null` e vários valores `null`.
- **Alta performance**: Operações de `get()`, `put()` e `remove()` têm complexidade média de `O(1)`, assumindo uma boa função de hash. Em casos extremos com muitas colisões, a complexidade pode ser `O(log n)` (Java 8+).
- **Sem ordem garantida**: Os elementos não são armazenados em ordem. Para ordenação por chave ou ordem de inserção, use `TreeMap` ou `LinkedHashMap`.
- **Não é thread-safe**: Múltiplas threads podem causar comportamentos inesperados sem sincronização.

## Estrutura interna (visual simplificada)

Um `HashMap` pode ser visualizado como:

```
Índice:   0      1      2      3      4      5
         ↓      ↓      ↓      ↓      ↓      ↓
       null   [A:1]  [B:2]   null   [C:3]  null

```

- Os pares chave-valor são armazenados em **buckets** (baldes).
- A chave é transformada em um índice usando a função `hashCode()`.
- Em caso de colisão (duas chaves com o mesmo índice), os elementos são encadeados (lista ligada) ou reestruturados como uma árvore binária balanceada (Java 8+, quando o número de elementos em um bucket excede 8, definido pela constante `TREEIFY_THRESHOLD`).

## Exemplo prático

```java
import java.util.HashMap;

public class ExemploHashMap {
    public static void main(String[] args) {
        HashMap<String, Integer> estoque = new HashMap<>();

        estoque.put("Maçã", 3);
        estoque.put("Banana", 5);
        estoque.put("Laranja", 2);

        System.out.println("Bananas em estoque: " + estoque.get("Banana"));

        estoque.remove("Maçã");

        for (String fruta : estoque.keySet()) {
            System.out.println(fruta + " => " + estoque.get(fruta));
        }
    }
}

```

## Casos de uso reais

- Contagem de frequência de palavras, produtos ou eventos.
- Mapeamento de identificadores (como usuários → dados).
- Tabelas de configuração (chave = nome do parâmetro).
- Caches simples em memória.

### Exemplo de cache

```java
HashMap<String, Integer> cache = new HashMap<>();
public Integer calcularSeNaoCacheado(String chave) {
    return cache.computeIfAbsent(chave, k -> calcularValor(k));
}
private Integer calcularValor(String chave) {
    // Simula cálculo caro
    return chave.length();
}

```

## Comparações com outras coleções

### HashMap vs TreeMap vs LinkedHashMap vs ConcurrentHashMap

| Característica | HashMap | LinkedHashMap | TreeMap | ConcurrentHashMap |
| --- | --- | --- | --- | --- |
| Ordem | Nenhuma | Ordem de inserção | Ordenado por chave | Nenhuma |
| Performance | Rápido (`O(1)`) | Levemente mais lento | Mais lento (`O(log n)`) | Rápido, thread-safe |
| Permite null chave | Sim | Sim | Não | Não |
| Estrutura interna | Tabela de hash | Tabela de hash + lista | Árvore Red-Black | Tabela de hash segmentada |
| Concorrência | Não thread-safe | Não thread-safe | Não thread-safe | Thread-safe |

## Erros comuns

- **Não sobrescrever `hashCode()` e `equals()` corretamente**:
    - Se você usa objetos personalizados como chave, implemente ambos os métodos para garantir o funcionamento correto do `HashMap`. Uma função `hashCode()` mal implementada, que gera muitos valores iguais, aumenta colisões e degrada o desempenho.

    ```java
    import java.util.Objects;
    
    class Produto {
        private int id;
    
        public Produto(int id) {
            this.id = id;
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Produto)) return false;
            Produto p = (Produto) o;
            return this.id == p.id;
        }
    }
    
    ```

- **Modificação durante iteração**:
    - Iterar sobre um `HashMap` e modificá-lo diretamente (sem `Iterator`) lança `ConcurrentModificationException`.

    ```java
    Iterator<String> it = mapa.keySet().iterator();
    while (it.hasNext()) {
        String chave = it.next();
        it.remove(); // Correto
        // mapa.remove(chave); // Errado durante iteração
    }
    
    ```

- **Confiar na ordem dos elementos**:
    - Não conte com a ordem de iteração — ela pode mudar a cada execução.

## HashMap em ambiente concorrente

`HashMap` não é seguro para múltiplas threads. Para evitar problemas:

```java
// Versão sincronizada
Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());

// Alternativa moderna e eficiente
Map<String, String> concurrentMap = new ConcurrentHashMap<>();
// Nota: ConcurrentHashMap não permite chaves ou valores null

```

## Boas práticas

- **Defina a capacidade inicial e o fator de carga**:
    - Se o número de elementos for grande, especifique a capacidade inicial e o fator de carga para evitar realocações. O fator de carga (0.75 por padrão) determina quando a tabela de hash será redimensionada (ao atingir 75% da capacidade).

    ```java
    HashMap<String, Integer> mapa = new HashMap<>(1000, 0.75f);
    
    ```

- **Evite objetos mutáveis como chave**:
    - Se a chave mudar após a inserção, ela pode se tornar inacessível.
- **Use tipos genéricos**:
    - Evite erros de compilação e casts desnecessários.

    ```java
    Map<String, Integer> mapa = new HashMap<>();
    
    ```


## Operações avançadas

- **Iterar por pares (chave e valor)**:

    ```java
    for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }
    // Ou, usando estilo funcional (Java 8+):
    mapa.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
    
    ```

- **Verificar se chave ou valor existe**:

    ```java
    mapa.containsKey("Banana"); // true
    mapa.containsValue(5);      // true
    
    ```

- **Atualização condicional**:

    ```java
    mapa.putIfAbsent("Banana", 0); // Insere apenas se a chave não existir
    mapa.replace("Banana", 10);    // Substitui o valor de uma chave existente
    mapa.compute("Banana", (k, v) -> v == null ? 1 : v + 1);
    mapa.merge("Banana", 1, Integer::sum);
    
    ```


## Conclusão

O `HashMap` é uma das estruturas mais eficientes para associação rápida de dados a uma chave. Ele oferece excelente desempenho, mas requer atenção com a implementação de `hashCode()` e `equals()`, especialmente com chaves personalizadas. Com sua combinação de desempenho e flexibilidade, o `HashMap` é uma ferramenta indispensável para desenvolvedores Java, desde aplicações simples até sistemas complexos.

### Quando usar `HashMap`:

- Busca e inserção rápidas por chave.
- Armazenamento de dados sem necessidade de ordem.
- Mapas com muitos elementos.

### Evite se:

- Precisa garantir ordem (use `LinkedHashMap` ou `TreeMap`).
- Está lidando com concorrência sem sincronização (use `ConcurrentHashMap`).