package com.clover;

public class Main {

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
        redBlackTree.insert(1, 1);
        redBlackTree.insert(3, 3);
        redBlackTree.insert(2, 2);
        redBlackTree.insert(8, 8);
        redBlackTree.insert(4, 4);
        redBlackTree.printTreeLevel();
        redBlackTree.remove(4);
        redBlackTree.printTreeLevel();
    }
}
