package com.ruska112;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Group implements Iterable<Integer> {
    private int id;
    private int[] data;

    public Group(int id, int... numbers) {
        this.id = id;
        this.data = new int[numbers.length];
        System.arraycopy(numbers, 0, this.data, 0, numbers.length);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return data.length;
    }

    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        data = new int[size];
    }

    public void setElement(int index, int element) {
        if (index <= 0 || index >= getSize()) {
            throw new IllegalArgumentException();
        }
        data[index] = element;
    }

    public int[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && Arrays.equals(data, group.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < data.length;
            }

            @Override
            public Integer next() {
                return data[currentIndex++];
            }
        };
    }
}
