/*
 *Author: Collin Leonard
 *Date: 4/16/21
 * This program implements two search methods and two sort methods and displays the data on using each of them.
 */

import java.util.Arrays;

public class SearchingAndSortingApp {

    public static void main(String[] args) {
        System.out.println("Welcome to the Searching & Sorting App\n");
        // Create unsorted array
        int[] unsortedArr = {25, 54, 1, 3, 87, 34, 13, 14, 8, 6, 89, 57, 24, 45, 30, 22, 18, 98, 58, 67, 44, 77, 66, 2, 55};
        // Pass array to sorting algorithms
        int[][] selectionSortArr = selectionSort(unsortedArr);
        int[][] insertionSortArr = insertionSort(unsortedArr);
        // Pass sorted array to each search algorithm
        int[] sortedArr = insertionSortArr[0];
        int[] linearSearchReturn = linearSearch(45, sortedArr);
        int[] binarySearchReturn = binarySearch(45, sortedArr);

        // Display info
        System.out.println("Selection Sort Results:");
        displayResultsOfSort(selectionSortArr, unsortedArr);
        System.out.println("\nInsertion Sort Results: ");
        displayResultsOfSort(insertionSortArr, unsortedArr);
        System.out.println("\nLinear Search Results: ");
        displayResultsOfSearch(linearSearchReturn, sortedArr);
        System.out.println("\nBinary Search Results: ");
        displayResultsOfSearch(binarySearchReturn, sortedArr);
    }

    public static int[] linearSearch(int targetValue, int[] arr) {
        int count = 0;
        int returnValue = -1;
        for (int i = 0; i < arr.length; i++){
            ++count;
            if(arr[i] == targetValue) {
                returnValue = i;
                return new int[]{returnValue, count};
            }
        }
        return new int[]{returnValue, count};
    }

    public static int[] binarySearch(int targetValue, int[] arr) {
        int returnValue = -1;
        int count = 0;
        int min = 0;
        int max = arr.length - 1;
        int guess = 0;

        while (max >= min) {
            ++count;
            guess = ((max + min) / 2);
            if (arr[guess] == targetValue){
                returnValue = guess;
                return new int[]{returnValue, count};
            } else if (arr[guess] < targetValue) {
                min = guess + 1;
            } else {
                max = guess - 1;
            }
        }

        return new int[]{returnValue, count};
    }

    // Takes an unsorted array argument, returns an array of array containing the sorted array and the count of steps used
    // to sort the array
    public static int[][] selectionSort(int[] arr) {
        int[] returnArr = Arrays.copyOf(arr, arr.length);
        int count = 0;
        // Move boundary of unsorted array one by one
        for (int i = 0; i < returnArr.length - 1; i++) {
            // Find the value of the minimum item in the unsorted array
            int minIndex = i;
            for (int j = i + 1; j < returnArr.length; j++) {
                ++count;
                if (returnArr[j] < returnArr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap minimum value element with first element
            int temp = returnArr[minIndex];
            returnArr[minIndex] = returnArr[i];
            returnArr[i] = temp;
        }
        int[] countArr = {count};
        return new int[][] {returnArr, countArr};
    }

    // Takes an unsorted array argument, returns an array of array containing the sorted array and the count of steps used
    // to sort the array
    public static int[][] insertionSort(int[] arr) {
        int count = 0;
        int[] returnArr = Arrays.copyOf(arr, arr.length);
        for (int i = 1; i < returnArr.length; i++) {
            // Create key value that will compare to each previous element in array
            int key = returnArr[i];
            int j = i - 1;
            // Move elements if they are greater than the key
            while (j >= 0 && returnArr[j] > key) {
                ++count;
                returnArr[j + 1] = returnArr[j];
                j = j - 1;
            }
            // Set value for key in correct spot
            returnArr[j + 1] = key;
        }
        int[] countArr = {count};
        return new int[][] {returnArr, countArr};
    }

    // Displays the results of the sorting algorithm
    public static void displayResultsOfSort(int[][] sortedResults, int[] originalArr) {
        String originalArrOutput = "";
        for (int i : originalArr) {
            originalArrOutput = originalArrOutput + (Integer.toString(i) + ", ");
        }
        originalArrOutput = originalArrOutput.substring(0, originalArrOutput.length() - 2);
        String sortedArrOutput = "";
        for (int i : sortedResults[0]) {
            sortedArrOutput = sortedArrOutput + (Integer.toString(i) + ", ");
        }
        sortedArrOutput = sortedArrOutput.substring(0, sortedArrOutput.length() - 2);
        System.out.println("Original array: " + originalArrOutput);
        System.out.println("Sorted Array: " + sortedArrOutput);
        System.out.println("Number of Loop Cycles: " + sortedResults[1][0]);
    }

    // Displays the result of the searching algorithm
    public static void displayResultsOfSearch(int[] searchResults, int[] arr) {
        if (searchResults[0] == -1 ){
            System.out.println("Sorry, no such element found.");
        } else {
            int foundItem = arr[searchResults[0]];
            int count = searchResults[1];
            System.out.println("Found Element: " + foundItem);
            System.out.println("Number of Loop Cycles: " + count);
        }
    }
}