package com.paddingleft.edocleet;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements Iterable<E> {
  private static class Node<E> {
    E val;
    Node<E> next;
    Node<E> prev;
    Node(E val) {
      this.val = val;
    } 
  }

  private Node<E> head;
  private Node<E> tail;
  private int size;
  public DoubleLinkedList(){
    this.head = new Node<>(null);
    this.tail = new Node<>(null);
    head.next = tail;
    tail.prev = head;
    this.size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      Node<E> p = head;
      @Override
      public boolean hasNext() {
        return p.next != tail;
      }

      @Override
      public E next() {
        p = p.next;
        return p.val;
      }
      
    };
  }

  public int size() {
    return size;
  }

  public E get(int index) {
    checkElementIndex(index);
    return getNode(index).val;
  }

  private void checkElementIndex(int index) {;
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
  }

  private void checkModificationPosition(int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
  }

  private Node<E> getNode(int index) {
    Node<E> p = head.next;
    for (int i = 0; i < index; i++) {
      p = p.next; 
    }
    return p;
  }

  public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> node = getNode(index);
    E val = node.val;
    node.val = element;
    return val;
  }

  public void add(int index, E element) {
    checkModificationPosition(index);
    Node<E> newNode = new Node<>(element);
    Node<E> node = getNode(index);
    Node<E> prev = node.prev;
    newNode.next = node;
    newNode.prev = prev;
    prev.next = newNode;
    node.prev = newNode;
    size++;
  }

  public void remove(int index) {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    checkElementIndex(index);
    Node<E> node = getNode(index);
    node.prev.next = node.next;
    node.next.prev = node.prev;
    size--;
  }

  public void addAtHead(E val) {
    Node<E> newNode = new Node<>(val);
    Node<E> next = head.next;
    head.next = newNode;
    newNode.next = next;
    newNode.prev = head;
    next.prev = newNode;
    size++;
  }
  
  public void addAtTail(E val) {
    Node<E> newNode = new Node<>(val);
    Node<E> prev = tail.prev;
    tail.prev = newNode;
    newNode.next = tail;
    newNode.prev = prev;
    prev.next = newNode;
    size++;
  }
}
