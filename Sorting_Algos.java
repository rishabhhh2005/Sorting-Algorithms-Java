import java.util.*;
import java.lang.*;
import java.io.*;

class Sorting_Algos
{
    public static void main (String[] args) throws java.lang.Exception
    {
        int[] arr = {4, 2, 9, 1, 2, 0};

        selectionSort(arr.clone());
        bubbleSort(arr.clone());
        insertionSort(arr.clone());
        mergeSort(arr.clone(), 0, arr.length - 1);
        quickSort(arr.clone(), 0, arr.length - 1);
        heapSort(arr.clone());
        bucketSort(arr.clone());
    }

    // Time Complexity: O(n^2), Space Complexity: O(1)
    public static void selectionSort(int[] arr)
    {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++){
            int minindex = i;
            for(int j = i + 1; j < n; j++){
                if(arr[j] < arr[minindex]){
                    minindex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minindex];
            arr[minindex] = temp;
        }
    }

    // Time Complexity: O(n^2), Space Complexity: O(1)
    public static void bubbleSort(int[] arr)
    {
        int n = arr.length;
        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j + 1] < arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Time Complexity: O(n^2), Space Complexity: O(1)
    public static void insertionSort(int[] arr)
    {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Time Complexity: O(n log n), Space Complexity: O(n)
    public static void mergeSort(int[] arr, int low, int high)
    {
        if(low < high){
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high)
    {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i = 0; i < n1; i++) left[i] = arr[low + i];
        for(int j = 0; j < n2; j++) right[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = low;

        while(i < n1 && j < n2){
            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while(i < n1) arr[k++] = left[i++];
        while(j < n2) arr[k++] = right[j++];
    }

    // Time Complexity: O(n log n), Space Complexity: O(log n)
    public static void quickSort(int[] arr, int low, int high)
    {
        if(low < high){
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while(i <= j){
            while(i <= high && arr[i] <= pivot) i++;
            while(arr[j] > pivot) j--;
            if(i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }

    // Time Complexity: O(n log n), Space Complexity: O(1)
    public static void heapSort(int[] arr)
    {
        int n = arr.length;

        for(int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for(int i = n - 1; i > 0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i)
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < n && arr[left] > arr[largest])
            largest = left;

        if(right < n && arr[right] > arr[largest])
            largest = right;

        if(largest != i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    // Time Complexity: O(n + k), Space Complexity: O(n + k)
    public static void bucketSort(int[] arr)
    {
        int n = arr.length;
        if (n <= 0) return;

        int max = Arrays.stream(arr).max().orElse(0);
        int bucketCount = (max / 5) + 1;

        List<List<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < bucketCount; i++)
            buckets.add(new ArrayList<>());

        for(int num : arr)
            buckets.get(num / 5).add(num);

        int index = 0;
        for(List<Integer> bucket : buckets){
            Collections.sort(bucket);
            for(int num : bucket)
                arr[index++] = num;
        }
    }
}
