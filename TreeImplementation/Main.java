package com.TreeImplementation;

public class Main {
    public static void main(String[]args){
Tree myTree = new Tree();
myTree.insert(1);
        myTree.insert(2);
        myTree.insert(3);
        myTree.insert(4);
        myTree.insert(5);
        myTree.insert(6);
        myTree.insert(7);

        Tree myTree2 = new Tree();
        myTree2.insert(1);
        myTree2.insert(2);
        myTree2.insert(3);
        myTree2.insert(4);
        myTree2.insert(5);
        myTree2.insert(6);
        myTree2.insert(7);
    myTree.equals(myTree2);

        System.out.println(myTree.isBinarySearchTree());

     var sampleList = myTree.getNodeAtKDistance(3);
     for(var num :sampleList)
         System.out.println(num);
    }
}
