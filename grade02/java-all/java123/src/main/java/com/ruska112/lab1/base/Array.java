package com.ruska112.lab1.base;

public class Array {
    private int[] array;
    private int size;

    public int GetSize() {
        return size;
    }

    public Array(int size) {
        this.size = size;
        array = new int[this.size];
    }

    public void PrintAll() {
        for (int value : array) {
            System.out.printf("%d ", value);
        }
        System.out.print("\n");
    }

    public void SetValue(int index, int value) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else if (index < 0) {
            System.out.println("ERROR: index less than 0");
        } else {
            System.out.println("ERROR: index more than size");
        }
    }

    public int SumOfAll() {
        int result = 0;
        for (int value : array) {
            result += value;
        }
        return result;
    }

    public int EvenCount() {
        int count = 0;
        for (int value : array) {
            if (value % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public int CountInSegment(int a, int b) {
        int count = 0;
        for (int value : array) {
            if (value >= a && value <= b) {
                count++;
            }
        }
        return count;
    }

    public boolean AllPositive() {
        boolean result = true;
        for (int value : array) {
            if (value <= 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public void Rearrange() {
        int a = 0;
        int b = array.length - 1;
        while (a != b && a != (array.length / 2)) {
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
            a++;
            b--;
        }
    }
}
