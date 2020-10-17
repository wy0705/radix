package yyy;

import java.util.ArrayList;

class Node{
    private String name;
    private ArrayList<Node> next;
    private boolean end;
    public Node(){}
    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getNext() {
        return next;
    }

    public void setNext(ArrayList<Node> next) {
        this.next = next;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    //节点分离
    public void spil(Node node,int i){
        node.getName();

    }
}
public class RadixTree {
    private Node root;
    //添加
    public void add(String s){
        if (root==null){
            Node node=new Node(s);
            node.setEnd(true);
            root=node;
            return;
        }
        char[] a=s.toCharArray();
        Node node=root;
        do{
            if (s.startsWith(node.getName())){

            }
        }while (!node.isEnd());
    }
}
