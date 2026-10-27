// RecursiveAssignment.java
// This file contains skeleton code for the recursive programming assignment.
// Your task is to complete the methods marked with TODO and test them using the main method.
public class RecursiveAssignment {
    // TODO 1: Write a recursive method to calculate the sum from 1 to n
public static int sum(int n) {
    // Base case:
    if (n <= 0) {return 0;}
    // Recursive case:
    return n + sum(n -1);
}
// TODO 2: Write a recursive method to calculate the sum from a to b
public static int sum(int a, int b) {
    // Base case:
    if (a > b) {return 0;}
    // Recursive case:
    return a + sum(a + 1, b);
}
// TODO 3: Write a recursive method to find the minimum value in the array
public static int findMin(int[] arr, int n) {
    // Base case:
    if (n == 1) {return arr[0];}
    // Recursive case:
    int min = findMin(arr, n - 1);
    return Math.min(arr[n - 1], min);
}
// TODO 4: Write a recursive method to calculate the sum of all elements in the array
public static int findSum(int[] arr, int n) {
    // Base case:
    if (n <= 0) {return 0;}
    // Recursive case:
    return arr[n - 1] + findSum(arr, n - 1);
}
// TODO 5: Write a recursive binary search function
public static int binarySearch(int[] arr, int key) {
    return binarySearchHelper(arr, 0, arr.length - 1, key);
}
// Helper method for binary search
public static int binarySearchHelper(int[] arr, int left, int right, int key) {
    // Base case:
    if (left > right) {return -1;}
    int mid = left + (right - left) /2;
    if (arr[mid] == key) {return mid;}
    // Recursive case:
    if (key < arr[mid]) {return binarySearchHelper(arr, left, mid-1, key);}
    else {return binarySearchHelper(arr, mid + 1, right, key);}
}
public static void main(String[] args) {
    // Test for sum(n)
    System.out.println("sum(5) should return 15: " + sum(5));
    // Test for sum(a, b)
    System.out.println("sum(5, 7) should return 18: " + sum(5, 7));
    // Test for findMin
    int[] arr = {2, 4, 1, 5};
    System.out.println("findMin(arr, 4) should return 1: " + findMin(arr, 4));
    // Test for findSum
    System.out.println("findSum(arr, 4) should return 12: " + findSum(arr, 4));
    // Test binary search key = 11
    int[] sortedArr = {-2, 0, 1, 3, 4, 7, 11, 15};
    System.out.println("binarySearch(sortedArr, 11) should return 6: " +
    binarySearch(sortedArr, 11));
    // TODO: Trace the binary search process by adding print statements in binarySearchHelper
    // Test other keys
    System.out.println("binarySearch(sortedArr, -2) should return 0: " +
    binarySearch(sortedArr, -2));
    System.out.println("binarySearch(sortedArr, 4) should return 4: " +
    binarySearch(sortedArr, 4));
    System.out.println("binarySearch(sortedArr, 5) should return -1: " +
    binarySearch(sortedArr, 5));
    }
}
