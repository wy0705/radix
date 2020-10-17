package com.company;

public class Skiptest {
    public static void main(String[] args) {
        Node node=new Node('h');
        SkipTree skipTree=new SkipTree(node);
        skipTree.insert("head");
        skipTree.insert("have");
        skipTree.insert("head");
        for (Node n:skipTree.traversal()){
            System.out.println(n.getName());
        }
        System.out.println(skipTree.find('a').getName());
        System.out.println(skipTree.find('t').getName());
    }
}
