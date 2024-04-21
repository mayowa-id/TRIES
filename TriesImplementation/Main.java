package com.TriesImplementation;
public class Main {
    public static void main (String [] args){
var myTrie = new Tries();
myTrie.insert("boy");
myTrie.insert("boyscout");
        myTrie.insert("boymeal");
        myTrie.insert("boyant");
myTrie.remove("boy");

var words = myTrie.findWords("boy");
        System.out.println(words);
    }
}
