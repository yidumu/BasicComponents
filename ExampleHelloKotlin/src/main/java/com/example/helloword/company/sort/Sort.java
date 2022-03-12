package com.company.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        int[] numbers = {0, 2, 3, 7, 13, 14, 1, 5, 11, 6, 9, 4, 8};
        int[] copy = {0, 2, 3, 7, 13, 14, 1, 5, 11, 6, 9, 4, 8};
        int[] heap = {36, 30, 18, 40, 32, 45, 22, 50};
        Sort sort = new Sort();
        sort.HeapSort(numbers);
        for (int i = 0; i < numbers.length; i++)
            System.out.print(" " + numbers[i]);
        System.out.println();
        System.out.print(isSortRight(copy, numbers));
    }

    /*
    for (int i = 0; i < length; i++) {

        }
     */

    /**
     * 归并排序
     *
     * @param disorderly 无序队列,待归并
     * @param ordered    归并完成队列
     * @param s          第一个序列起始位置
     * @param m          第二个序列起始位置
     * @param t          归并序列的长度
     */
    public void Merge(int[] disorderly, int[] ordered, int s, int m, int t) {
        int k = s;
        while (s <= m && m <= t) {
            if (disorderly[s] < disorderly[m]) {
                ordered[k] = disorderly[s];
                s++;
            } else {
                ordered[k] = disorderly[m];
                m++;
            }
            k++;
        }
        if (s <= m) {
            while (s <= m) {
                ordered[k] = disorderly[s];
                s++;
                k++;
            }
        } else {
            while (m <= t) {
                ordered[k] = disorderly[m];
                m++;
                k++;
            }
        }
    }

    public void MergePass(int[] disorderly, int[] ordered, int h) {
        int n = disorderly.length;
        int i = 0;
        while (i <= n - 2 * h) {
            Merge(disorderly, ordered, i, i + h, i + 2 * h);
            i = i + 2 * h;

        }
        while (i < n - h) {


        }
        while (i >= n - h) {

        }

    }

    /**
     * 递归筛选
     *
     * @param array
     * @param root
     * @param length
     */
    public void Sift_Recursion(int[] array, int root, int length) {
        int left = root * 2 + 1;
        if (left >= length) {
            return;
        }
        if (left < length - 1 && array[left] < array[left + 1]) {
            left++;
        }
        if (array[root] < array[left]) {
            swap_copy(array, root, left);
            Sift_Recursion(array, left, length);
        }
    }

    /**
     * 非递归筛选
     *
     * @param array
     * @param root
     * @param length
     */
    public void Sift(int[] array, int root, int length) {
        int left = root * 2 + 1;
        while (left < length) {
            if (left < length - 1 && array[left] < array[left + 1]) {
                left++;
            }
            if (array[root] > array[left]) {
                break;
            } else {
                swap_copy(array, root, left);
                root = left;
                left = root * 2 + 1;
            }
        }
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public void HeapSort(int[] array) {
        int length = array.length;
        for (int i = length / 2; i >= 0; i--) {
            Sift(array, i, length);
        }
        for (int i = 0; i < length; i++) {
            swap_copy(array, 0, length - 1 - i);
            Sift(array, 0, length - 1 - i);
        }
    }


    /**
     * 选择排序
     *
     * @param numbers
     */
    public void SelectSort(int[] numbers) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (numbers[j] > numbers[i]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = tmp;
                }
            }
        }
    }


    /**
     * 快速排序一次划分
     *
     * @param n
     */
    public int PartitionSort(int[] n, int first, int end) {
        int i = first;
        int j = end;
        int tmp;
        while (i < j) {
            while (i < j && n[i] <= n[j]) {
                j--;
            }
            if (i < j) {
                tmp = n[i];
                n[i] = n[j];
                n[j] = tmp;
                i++;
            }
            while (i < j && n[i] <= n[j]) {
                i++;
            }
            if (i < j) {
                tmp = n[i];
                n[i] = n[j];
                n[j] = tmp;
                j--;
            }
        }
        return i;
    }

    /**
     * 快速排序递归实现
     *
     * @param numbers
     * @param first
     * @param end
     */
    public void QuickSort(int[] numbers, int first, int end) {
        if (first < end) {
            int pivot = PartitionSort(numbers, first, end);//划分
            QuickSort(numbers, first, pivot - 1);
            QuickSort(numbers, pivot + 1, end);
        }

    }

    /**
     * 起泡排序
     *
     * @param numbers
     */
    public void BubbleSort(int[] numbers) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param numbers
     */
    public void ShellSort(int[] numbers) {
        int n = numbers.length;
        for (int d = n / 2; d >= 1; d = d / 2) {
            //n-d 希尔排序
            for (int i = d; i < n; i++) { // i d ~ n
                for (int j = i; j - d > 0; j = j - d) { //j i ~ 0 step -d
                    if (numbers[j] < numbers[j - d]) {
                        int tmp = numbers[j];
                        numbers[j] = numbers[j - d];
                        numbers[j - d] = tmp;
                    }
                }
            }
        }
    }

    /**
     * 直接插入排序
     *
     * @param numbers
     */
    public void InsertSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0 && numbers[j] < numbers[j - 1]; j--) {
                int tmp = numbers[j];
                numbers[j] = numbers[j - 1];
                numbers[j - 1] = tmp;
            }
        }
    }

    public static void swap_copy(int[] numbers, int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

    public static void swap_operation(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    public static int[] getRandomArray(int n) {
        int[] ints = new int[n];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt();
        }
        return ints;
    }

    public static boolean isSortRight(int[] basisArray, int[] sortArray) {
        Arrays.sort(basisArray);
        return Arrays.equals(basisArray, sortArray);
    }
}
