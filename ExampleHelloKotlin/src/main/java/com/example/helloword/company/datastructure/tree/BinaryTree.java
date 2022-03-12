package com.company.datastructure.tree;

public class BinaryTree {
    private static final int DEFAULT_SIZE = 20;
    public Tree[] nodes = new Tree[DEFAULT_SIZE];



    class Tree {
        int data;
        public Tree rChild, lChild;
    }
}
