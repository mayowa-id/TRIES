package com.TreeImplementation;

import java.util.ArrayList;

public class Tree {
    private class Node {
        public int height;
        private int value;
        private Node leftNode;

        private Node rightNode;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        } else {
            var current = root;
            while (true) {
                if (value < current.value) {
                    if (current.leftNode == null) {
                        current.leftNode = new Node(value);
                        break;
                    }
                    current = current.leftNode;
                } else {
                    if (current.rightNode == null) {
                        current.rightNode = new Node(value);
                        break;
                    }
                    current = current.rightNode;
                }
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value < current.value)
                current = current.leftNode;
            else if (value > current.value)
                current = current.rightNode;
            else
                return true;
        }
        return false;
    }

    //Pre-Order Traversal
    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        traversePreOrder(root.leftNode);
        traversePreOrder(root.rightNode);
    }

    //In-Order Traversal
    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {
        traverseInOrder(root.leftNode);
        System.out.println(root.value);
        traverseInOrder(root.rightNode);
    }

    //Post-Order Traversal
    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        traversePostOrder(root.leftNode);
        traversePostOrder(root.rightNode);
        System.out.println(root.value);
    }

    public int height() {

        return height(root);
    }

    private int height(Node root) {
        if (root == null) {
            return -1;
        }
        if (isLeaf(root)) {
            return 0;
        }
        return 1 + Math.max(
                height(root.leftNode),
                height(root.rightNode)
        );
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        if (isLeaf(root)) {
            return root.value;
        }
        var leftMin = min(root.leftNode);
        var rightMin = min(root.rightNode);

        return Math.min(Math.min(leftMin, rightMin), root.value);
    }

    private boolean isLeaf(Node node) {
        return root.leftNode == null && root.rightNode == null;
    }

    public boolean equals(Tree other) {

        return equals(root, other.root);
    }

    //To compare two Trees and check if they are equal
    private boolean equals(Node tree1, Node tree2) {
        if (tree1 == null & tree2 == null)
            return true;
        if (tree1 != null & tree2 != null)
            return tree1.value == tree2.value
                    && equals(tree1.leftNode, tree2.leftNode)
                    && equals(tree1.rightNode, tree2.rightNode);
        return false;
    }

    //Method to check if a binary Tree is a Binary Search Tree
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //Firstly we check if the root node is in bound
    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null)
            return true;
        if (root.value < min || root.value > max)
            return false;
//if  root node is in bound, we recursively check the left and right
        //nodes as roots of their own
        return
                isBinarySearchTree(root.leftNode, min, root.value - 1)
                        && isBinarySearchTree(root.rightNode, root.value + 1, max);
    }


    //Method to return a list of Node(s) at K distance from
    // the root of a Tree
    public ArrayList<Integer> getNodeAtKDistance(int K) {
        var list = new ArrayList<Integer>();
        nodeAtKDistance(root, K, list);
        return list;
    }

    //The above public method uses the private method below to
    //get nodes at a height "K", stores them in an array list
    //then returns the list.
    private void nodeAtKDistance(Node rootNode, int K, ArrayList<Integer> list) {
        if (rootNode == null) {
            return;
        }
        if (K == 0) {
            list.add(rootNode.value);
            return;
        }
        nodeAtKDistance(root.leftNode, K - 1, list);
        nodeAtKDistance(root.rightNode, K - 1, list);
    }

    //This method demonstrates Level order traversing, as
    //it prints items(integers in this case) in the nodes
    //on the same level/height, from top to bottom.
    public void levelorderTraverse() {
        for (var i = 0; i <= height(); i++) {
            var sampleList = getNodeAtKDistance(i);
            for (var value : sampleList)
                System.out.println(value);
        }
    }
    //Insert method for AVL Trees using recursion
}