# Merge Sort - Entendendo o Algoritmo de Ordenação

---

O Merge Sort é um algoritmo de ordenação muito usado, baseado na ideia de "dividir para conquistar". Ele pode parecer complicado à primeira vista, mas na prática é bem simples de entender!

## Como funciona?

O Merge Sort funciona assim:

1. **Divide:** Ele pega a lista de números e divide em duas partes menores, repetindo esse processo até que cada parte tenha apenas um elemento.
2. **Conquista (ou junta):** Depois, ele começa a juntar essas partes, sempre colocando os números em ordem. No final, tudo fica organizado!

### Visualizando o processo

Imagina que você tem a lista:

```
[38, 27, 43, 3, 9, 82, 10]
```

O Merge Sort vai dividir assim:

```
[38, 27, 43, 3, 9, 82, 10]
         ↓
   [38, 27, 43]   [3, 9, 82, 10]
     ↓     ↓         ↓      ↓
 [38] [27, 43]   [3, 9] [82, 10]
      ↓   ↓        ↓   ↓
   [27] [43]    [3] [9] [82] [10]
```

Depois, ele começa a juntar e ordenar:

```
[38] [27] [43] [3] [9] [82] [10]
   ↓    ↓     ↓    ↓     ↓
[27,38] [3,9] [10,82]
      ↓        ↓
  [27,38,43] [3,9,10,82]
         ↓
[3,9,10,27,38,43,82]
```

## Implementação em Java

Aqui está um exemplo simples de como implementar o Merge Sort em Java:

```java
public static void mergeSort(int[] a, int n) {
    if (n < 2) return;
    int mid = n / 2;
    int[] l = new int[mid];
    int[] r = new int[n - mid];

    for (int i = 0; i < mid; i++) l[i] = a[i];
    for (int i = mid; i < n; i++) r[i - mid] = a[i];

    mergeSort(l, mid);
    mergeSort(r, n - mid);
    merge(a, l, r, mid, n - mid);
}

public static void merge(int[] a, int[] l, int[] r, int left, int right) {
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
        if (l[i] <= r[j]) a[k++] = l[i++];
        else a[k++] = r[j++];
    }
    while (i < left) a[k++] = l[i++];
    while (j < right) a[k++] = r[j++];
}
```

## Prós e Contras de Criar Novos Arrays

**Prós:**
- O código fica mais simples de entender, já que cada parte é tratada separadamente.
- Menos chance de bugs relacionados a índices.

**Contras:**
- Gasta mais memória, pois a cada divisão são criados novos arrays temporários.
- Para listas muito grandes, pode acabar pesando no uso de RAM.

Se você precisa economizar memória, pode usar uma abordagem que trabalha mais com ponteiros (índices), evitando criar tantos arrays novos.

## Exemplo Usando Ponteiros (índices) em Java

Aqui vai uma versão do Merge Sort que usa apenas um array auxiliar, sem ficar criando vários arrays novos:

```java
public static void mergeSort(int[] arr, int left, int right, int[] aux) {
    if (left >= right) return;
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid, aux);
    mergeSort(arr, mid + 1, right, aux);
    merge(arr, left, mid, right, aux);
}

public static void merge(int[] arr, int left, int mid, int right, int[] aux) {
    int i = left, j = mid + 1, k = left;
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) aux[k++] = arr[i++];
        else aux[k++] = arr[j++];
    }
    while (i <= mid) aux[k++] = arr[i++];
    while (j <= right) aux[k++] = arr[j++];
    for (int l = left; l <= right; l++) arr[l] = aux[l];
}
```

Para usar:

```java
int[] arr = {5, 1, 6, 2, 3, 4};
int[] aux = new int[arr.length];
mergeSort(arr, 0, arr.length - 1, aux);
```

Assim, você economiza memória, pois só cria um array auxiliar do mesmo tamanho do original.

## Testando

Para garantir que está funcionando, podemos testar assim:

```java
@Test
public void positiveTest() {
    int[] atual = { 5, 1, 6, 2, 3, 4 };
    int[] esperado = { 1, 2, 3, 4, 5, 6 };
    MergeSort.mergeSort(atual, atual.length);
    assertArrayEquals(esperado, atual);
}
```

## Complexidade

- **Tempo:** O Merge Sort sempre faz o mesmo número de divisões e junções, então seu tempo de execução é O(n log n), mesmo nos piores casos.
- **Espaço:** Usando arrays novos, o consumo de memória é O(n). Usando ponteiros e um array auxiliar, também é O(n), mas com menos desperdício.

---

Merge Sort é ótimo quando você precisa de uma ordenação eficiente e estável, mesmo para listas grandes. E o melhor: depois que você entende a lógica, fica fácil implementar em qualquer lugar!

