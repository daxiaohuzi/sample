package com.library.Code;

/**
 * Created by xiaoye on 2016/5/11.
 */
public class Stack<T> {

    protected int size;
    protected T[] array;

    protected int capacity = 3;

    public Stack() {
        this(5);
    }

    public Stack(int size) {
        array = (T[]) new Object[size];
    }

    public void push(T t) {
        add(t);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        return (T) array[size - 1];
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        final int index = --size;
        final T obj = (T) array[index];
        array[index] = null;
        return obj;
    }

    public void add(T t) {
        if (size == array.length) {
            T[] newData = (T[]) new Object[size + capacity];
            System.arraycopy(array, 0, newData, 0, size);
            array = newData;
        }
        array[size++] = t;
    }

    public T remove(int index) {
        Object[] a = array;
        int s = size;
        if (index >= s) {
            return null;
        }
        T result = (T) a[index];
        System.arraycopy(a, index + 1, a, index, --s - index);
        a[s] = null;  // Prevent memory leak
        size = s;
        return result;
    }

//    public boolean remove(T t){
//        Object[] a = array;
//        int s = size;
//        if (t != null) {
//            for (int i = 0; i < s; i++) {
//                if (t.equals(a[i])) {
//                    System.arraycopy(a, i + 1, a, i, --s - i);
//                    a[s] = null;  // Prevent memory leak
//                    size = s;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int location) {
        if (location >= size) return null;
        return array[location];
    }
}
