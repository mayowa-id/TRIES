package com.TriesImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tries {
    private class Node {
        public Node(char value) {
            this.value = value;
        }

        private char value;
        //There are 26 English Alphabets, each Child node represents a letter

        private HashMap<Character, Node> childrenNodes = new HashMap<>();
        private boolean isLastLetter;

        @Override
        public String toString() {
            return "value=" + value ;
        }
        public boolean hasChild(char ch){
            return childrenNodes.containsKey(ch);
        }
        public void addChild (char ch){
            childrenNodes.put(ch, new Node(ch));
        }
        public Node getChild(char ch){
            return childrenNodes.get(ch);
        }
        public Node[] getChildrenNodes(){
            return childrenNodes.values().toArray(new Node[0]);
        }
        public boolean hasChildren(){
            return  !childrenNodes.isEmpty();
        }
        public void removeChild (char ch){
            childrenNodes.remove(ch);
        }
    }

    private Node root = new Node(' ');

    public void insert(String wordToBeInserted) {
        var current = root;
        for (var ch : wordToBeInserted.toCharArray()) {
            if(!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isLastLetter = true;
    }
    public boolean  contains(String word){
        if(word == null)
            return false;

        var current = root;
        for(var ch: word.toCharArray() ){
            if(!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isLastLetter;
    }
    public void traverse(){
        traverse(root);
    }
    //Post-Order Traversal that prints out the root node and then
    //recursively trasverses through the Tries's Children Nodes
    private void traverse (Node root){
        var current = root;
        System.out.println(current);
for (var child : root.getChildrenNodes())
    traverse(child);
    }

    public void remove(String word){
        remove(root, word, 0);
    }
    private void remove (Node root, String word, int index){
        if (index == word.length()){
            System.out.println(root.value);
            return;
        }
var ch = word.charAt(index);
        var child = root.getChild(ch);
        if(child == null)
            return;
        remove(child,word, index+1);

        if(!child.hasChildren()
        && !child.isLastLetter){
            root.removeChild(ch);
        }
    }
    public List<String> findWords(String prefix){
        List<String> words = new ArrayList<>();
    var lastLetter = findLastLetterOf(prefix);
    findWords(lastLetter, prefix, words);

    return words;
    }
    //The method below is demonstration of the implementation of
    //auto-completion using the Tries data structure.
    private void findWords(Node root, String prefix, List<String> words){
    if(root.isLastLetter)
        words.add(prefix);

    for(var child : root.getChildrenNodes())
        findWords(child, prefix+child.value, words);
    }
    private Node findLastLetterOf(String prefix){
        if(prefix == null)
            return null;

        var current = root;
        for(var ch: prefix.toCharArray()){
            var child = current.getChild(ch);
            if(child == null)
                return null;
            current = child;
        }
        return current;
}
}
