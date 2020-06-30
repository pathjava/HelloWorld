// Oleg Kiselev
// 27.06.2020, 12:00

package ru.progwards.java2.lessons.classloader.test;

import java.security.SecureRandom;
import java.util.Arrays;


public class TestSpeed {

    private final boolean showResultSort;
    private final int[] tempArray;

    public TestSpeed(int sizeTestingArrays, boolean showResultSort) {
        tempArray = new int[sizeTestingArrays];
        this.showResultSort = showResultSort;
    }

    private void fillArray() {
        SecureRandom random = new SecureRandom();
        Arrays.setAll(tempArray, i -> random.nextInt());
    }

    /* сортировка пузырьком */
    public void bubbleSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        if (showResultSort)
            Arrays.stream(arr).forEach(System.out::println);
    }

    /* сортировка выбором */
    public void selectionSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
        if (showResultSort)
            Arrays.stream(arr).forEach(System.out::println);
    }

    /* сортировка вставкой */
    public void insertionSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
        if (showResultSort)
            Arrays.stream(arr).forEach(System.out::println);
    }

    /* сортировка слиянием */
    public void mergeSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        int left = 0;
        int right = arr.length - 1;
        mergeSorting(arr, left, right);
    }

    private void mergeSorting(int[] arr, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        mergeSorting(arr, left, mid);
        mergeSorting(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;
        // создаем временные подмассивы
        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];
        // копируем отсортированные массивы во временные
        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = arr[left + i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = arr[mid + i + 1];
        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;
        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    arr[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    arr[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                arr[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                arr[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    /* пирамидальная сортировка */
    public void heapSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        int n = arr.length;
        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Вызываем процедуру heapify на уменьшенной куче
            heapify(arr, i, 0);
        }
    }

    public void heapify(int[] arr, int n, int i) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2
        // Если левый дочерний элемент больше корня
        if (l < n && arr[l] > arr[largest])
            largest = l;
        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // Если самый большой элемент не корень
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largest);
        }
    }

    /* быстрая сортировка */
    public void quickSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        int left = 0;
        int right = arr.length - 1;
        quickSortRealisation(arr, left, right);
    }

    private void quickSortRealisation(int[] arr, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = arr[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (arr[leftMarker] < pivot)
                leftMarker++;
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (arr[rightMarker] > pivot)
                rightMarker--;
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = arr[leftMarker];
                    arr[leftMarker] = arr[rightMarker];
                    arr[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder)
            quickSortRealisation(arr, leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSortRealisation(arr, leftBorder, rightMarker);
    }

    public static void main(String[] args) {
        TestSpeed test = new TestSpeed(100000, false);
        test.fillArray();

        test.bubbleSort();
        test.selectionSort();
        test.insertionSort();
        test.mergeSort();
        test.heapSort();
        test.quickSort();
    }
}
