package com.paddingleft.edocleet;

import java.util.NoSuchElementException;

public class RecursiveLinkedList<E> {
  private static class Node<E> {
    E val;
    Node<E> next;

    Node(E val) {
      this.val = val;
    }
  }

  private Node<E> first = null;

  private int size = 0;

  public RecursiveLinkedList() {
  }

  /***** 增 *****/

  public void addFirst(E e) {
    Node<E> x = new Node<>(e);
    x.next = first;
    first = x;
    size++;
  }

  public void addLast(E e) {
    first = addLast(first, e);
    size++;
  }

  // a -> b -> c -> d -> e -> null
  private Node<E> addLast(Node<E> node, E e) {
    if (node == null) {
      return new Node<>(e);
    }

    node.next = addLast(node.next, e);

    return node;
  }

  public void add(int index, E e) {
    checkPositionIndex(index);
    first = add(first, index, e);
    size++;
  }

  private Node<E> add(Node<E> node, int index, E e) {
    if (index == 0) {
      Node<E> newNode = new Node<>(e);
      newNode.next = node;
      return newNode;
    }

    node.next = add(node, index - 1, e);
    return node;
  }

  /***** 删 *****/

  public E removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    E deletedVal = first.val;
    first = first.next;
    size--;
    return deletedVal;
  }

  public void removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    first = removeLast(first);
    size--;
  }

  // x -> y -> null
  private Node<E> removeLast(Node<E> node) {
    if (node.next == null) {
      // node 就是最后一个节点，让自己直接消失
      return null;
    }

    node.next = removeLast(node.next);
    return node;
  }

  public void remove(int index) {
    checkElementIndex(index);
    first = remove(first, index);
    size--;
  }

  private Node<E> remove(Node<E> node, int index) {
    if (index == 0) {
      // 找到了第 index 个链表节点
      return node.next;
    }

    node.next = remove(node.next, index - 1);

    return node;
  }

  /***** 查 *****/

  public E getFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return first.val;
  }

  public E getLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return getLast(first);
  }

  private E getLast(Node<E> node) {
    if (node.next == null) {
      return node.val;
    }
    return getLast(node.next);
  }

  public E IndexOf(int index) {
    checkElementIndex(index);
    Node<E> p = getNode(index);
    return p.val;
  }

  /***** 改 *****/

  public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> p = getNode(index);

    E oldVal = p.val;
    p.val = element;

    return oldVal;
  }

  /***** 其他工具函数 *****/
  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }

  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  /**
   * 检查 index 索引位置是否可以存在元素
   */
  private void checkElementIndex(int index) {
    if (!isElementIndex(index))
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
  }

  /**
   * 检查 index 索引位置是否可以添加元素
   */
  private void checkPositionIndex(int index) {
    if (!isPositionIndex(index))
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
  }

  // 返回 index 对应的 Node
  // 注意：请保证传入的 index 是合法的
  private Node<E> getNode(int index) {
    return getNode(first, index);
  }

  // 返回「从 node 开始的第 index 个链表节点」
  private Node<E> getNode(Node<E> node, int index) {
    // base case
    if (index == 0) {
      return node;
    }
    // 返回 「从 node.next 开始的第 index - 1 个链表节点」
    return getNode(node.next, index - 1);
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 3, 4, };
    // printArr(arr);
    System.out.println(IndexOf(arr, 300));
  }

  private static int IndexOf(int[] arr, int targetVal) {
    // for (int i = 0; i < arr.length; i++) {
    // if (arr[i] == targetVal) {
    // return i;
    // }
    // }
    // return -1;

    return IndexOf(arr, 0, targetVal);
  }

  private static int IndexOf(int[] arr, int i, int targetVal) {

    if (i == arr.length) {
      return -1;
    }

    if (arr[i] == targetVal) {
      return i;
    }

    return IndexOf(arr, i + 1, targetVal);
  }

  private static void printArr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }

    printArr(arr, 0);
  }

  // 按顺序打印 arr[index] 以及之后的所有元素
  // {1, 2, 3, 4}
  // printArr(arr, 0);
  private static void printArr(int[] arr, int index) {
    // base case
    if (index == arr.length) {
      return;
    }

    System.out.println(arr[index]);

    // 按顺序打印 arr[index + 1] 以及之后的所有元素
    printArr(arr, index + 1);
  }
}
