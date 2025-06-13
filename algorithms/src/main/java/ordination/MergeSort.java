package ordination;

public class MergeSort {
    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return; // Nada a fazer se o array for nulo ou já estiver ordenado
        }
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Método recursivo que divide o array e chama a fusão.
     *
     * @param array O array a ser ordenado.
     * @param left Índice inicial do subarray.
     * @param right Índice final do subarray.
     */
    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Calcula o meio do array
            int middle = left + (right - left) / 2;

            // Ordena as duas metades
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Combina as metades ordenadas
            merge(array, left, middle, right);
        }
    }

    /**
     * Combina dois subarrays ordenados em um único subarray.
     *
     * @param array O array original contendo os subarrays.
     * @param left Índice inicial do primeiro subarray.
     * @param middle Índice final do primeiro subarray.
     * @param right Índice final do segundo subarray.
     */
    private static void merge(int[] array, int left, int middle, int right) {
        // Tamanhos dos subarrays
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Arrays temporários
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copia os dados para os arrays temporários
        for (int i = 0; i < n1; i++)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            rightArray[j] = array[middle + 1 + j];

        // Índices iniciais dos subarrays
        int i = 0, j = 0, k = left;

        // Combina os subarrays
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        // Copia os elementos restantes, se houver
        while (i < n1)
            array[k++] = leftArray[i++];
        while (j < n2)
            array[k++] = rightArray[j++];
    }

    // Exemplo de uso
    public static void main(String[] args) {
        int[] array = { 38, 27, 43, 3, 9, 82, 10 };
        System.out.println("Antes: ");
        printArray(array);

        sort(array);

        System.out.println("Depois: ");
        printArray(array);
    }

    /**
     * Método auxiliar para imprimir o array.
     *
     * @param array O array a ser impresso.
     */
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
