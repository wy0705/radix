package com.company;

import java.util.ArrayList;

class Node{
    public char name;
    private Node parent;
    private Node son;

    public Node(){}
    public Node(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getSon() {
        return son;
    }

    public void setSon(Node son) {
        this.son = son;
    }

    public Node getFriend() {
        return friend;
    }

    public void setFriend(Node friend) {
        this.friend = friend;
    }

    private Node friend;
}
class SkipTree {
    private Node md = null;    //根节点

    public SkipTree(Node md) {
        this.md = md;
    }

    //遍历
    public void traversal(ArrayList<Node> al,Node node){
        al.add(node);
        traversal(al,node.getSon());
        traversal(al,node.getFriend());
    }
    public ArrayList<Node> traversal(){
        ArrayList<Node> target =new ArrayList<Node>();
        traversal(target,md);
        return target;
    }


    //寻找节点
    public Node find(char c){
        ArrayList<Node> al=traversal();
        for (Node node:al){
            if (node.getName()==c) return node;
        }
        return null;
    }
    //插入
    public void insert(String s){
        Node node=new Node();
        node=md;
        int temp=0;
        char[] aa=s.toCharArray();
        if (aa[0]!=md.getName()){
            System.out.println("首字母不是对应的树");
            return;
        }
        if (node.getName()!=0&&aa!=null) {
            for (int i = 0; i < aa.length; i++) {
                if (aa[i] == node.getName()) {      //看当前节点是否与单词的字母对应
                    node = node.getSon();
                    continue;
                }
                while (node.getFriend() != null) {         //如果没有看看他的兄弟有无
                    if (aa[i] == node.getFriend().getName()) {
                        node = node.getFriend();
                        break;
                    }
                    node = node.getFriend();
                    continue;
                }
                temp = i;          //如果无对应字母，记录字母当前位置，退出循环
                break;
            }
            if (temp == aa.length - 1) {
                System.out.println("当前单词已存在");
                return;
            }
            for (int i = temp; i < aa.length; i++) {
                Node node1 = new Node(aa[i]);
                node1.setParent(find(aa[i - 1]));
            }
        }
    }
}
