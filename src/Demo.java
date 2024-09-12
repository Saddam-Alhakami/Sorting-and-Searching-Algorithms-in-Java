public class Demo {
    // Declare a global variable to count the steps
    static int steps;

    public static void main(String[] args) {
        // Original array
        int numbs[] = {3, 7, 2, 0, 6, 9, 11, 4, 5};

        System.out.println("Original array:");
        printArray(numbs);

        // Create separate arrays for each sorting method to avoid sorting an already sorted array
        int[] numbsForInsertionSort = numbs.clone();
        int[] numbsForBubbleSort = numbs.clone();
        int[] numbsForSelectionSort = numbs.clone();
        int[] numbsQuickSort = numbs.clone();
        int[] numbsMergeSort = numbs.clone();

        // Merge sort
        System.out.println("\nMerge Sort:");
        mergeSort(numbsMergeSort, 0, numbsMergeSort.length - 1);
        printArray(numbsMergeSort);

        // Quick sort
        System.out.println("\nQuick Sort:");
        quickSort(numbsQuickSort, 0, numbsQuickSort.length - 1);
        printArray(numbsQuickSort);

        // Insertion sort
        System.out.println("\nInsertion Sort:");
        insertionSort(numbsForInsertionSort);
        printArray(numbsForInsertionSort);

        // Bubble sort
        System.out.println("\nBubble Sort:");
        bubbleSort(numbsForBubbleSort);
        printArray(numbsForBubbleSort);

        // Selection sort
        System.out.println("\nSelection Sort:");
        selectionSort(numbsForSelectionSort);
        printArray(numbsForSelectionSort);

        // Now proceed with the linear and binary search examples
        int nums[] = new int[10000];
        nums[9999] = 1;  // Setting the target at the end of the array for demonstration
        int target = 1;

        // Reset steps for linear search
        steps = 0;
        int result = linearSearch(nums, target);
        System.out.println("Linear search steps: " + steps);
        if (result != -1)
            System.out.println("Element found at index: " + result);
        else
            System.out.println("No such element");

        // Reset steps for binary search
        steps = 0;
        int result2 = binarySearch(nums, target, 0, nums.length - 1);
        System.out.println("Binary search steps: " + steps);
        if (result2 != -1)
            System.out.println("Element found at index: " + result2);
        else
            System.out.println("No such element in binary search");
    }

    // Merge Sort implementation
    private static void mergeSort(int[] arr, int link, int rechts) {
        if (link < rechts) {
            int mid = (link + rechts) / 2;

            // Recursively sort the two halves
            mergeSort(arr, link, mid);
            mergeSort(arr, mid + 1, rechts);

            // Merge the sorted halves
            merge(arr, link, mid, rechts);
        }
    }

    // Merge two sorted halves of the array
    private static void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        // Temporary arrays to store the sorted halves
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[low + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = low;

        // Merge the sorted halves
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements of the leftArray
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements of the rightArray
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Quick Sort implementation
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Recursively sort the elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partition the array for Quick Sort
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Insertion Sort implementation
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort implementation
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            swap(arr, i, minIndex);
        }
    }

    // Bubble Sort implementation
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // Swap two elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Linear Search implementation
    public static int linearSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            steps++;  // Count steps
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search implementation
    public static int binarySearch(int[] nums, int target, int left, int right) {
        steps++;  // Count steps
        if (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                return binarySearch(nums, target, left, mid - 1);
            } else {
                return binarySearch(nums, target, mid + 1, right);
            }
        }
        return -1;
    }

    // Utility function to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
