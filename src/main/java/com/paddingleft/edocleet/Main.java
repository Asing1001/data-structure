package com.paddingleft.edocleet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add(0, "hey");
        System.out.println(list.get(0));
    }
}