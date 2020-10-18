package yyy;

import javax.swing.*;
import java.util.ArrayList;

class Node{
    private char indexx;

    private String name;
    private ArrayList<Node> next=new ArrayList<Node>();
    private ArrayList<Character> index=new ArrayList<Character>();
    private boolean end;
    public ArrayList<Character> getIndex(){
        if (this.next!=null) {
            for (Node n : next) {
                if (n.name!=null) {
                    index.add(n.name.charAt(0));
                }
            }
            return this.index;
        }
        return null;
    }
    public Node(){}
    public Node(String name) {
        this.name = name;
    }

    public char getIndexx() {
        return indexx;
    }

    public void setIndexx(char indexx) {
        this.indexx = indexx;
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
        if (this.getNext()!=null){
            this.end=false;
            return end;
        }
        this.end=true;
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    //节点分离
    public ArrayList<Node> spil(int i){
        String s=this.getName();
        Node node1=new Node(s.substring(0,i));
        Node node2=new Node(s.substring(i,s.length()));
        node2.setEnd(this.isEnd());
        node2.setNext(this.getNext());
        node1.setEnd(false);
        ArrayList<Node> al=new ArrayList<Node>();
        al.add(node2);
        node1.setNext(al);
        ArrayList<Node> aa=new ArrayList<Node>();
        aa.add(node1);
        aa.add(node2);
        return aa;

    }
}
public class RadixTree {
    public static Node root=new Node();
    //添加
    public void add(String s){
        if (root.getName()==null){
            Node node=new Node(s);
            node.setEnd(true);
            root=node;
            return;
        }
        char[] a=s.toCharArray();
        //Node node=root;
        if (root.getName()!=null) {
            char[] temp = root.getName().toCharArray();
            int l = temp.length > a.length ? temp.length : a.length;
            ArrayList<Node> arrayList = new ArrayList<Node>();
            for (int i = 0; i < l; i++) {
                if (a[i] != temp[i] && i != l - 1) {
                    arrayList = root.spil(i);
                    root = arrayList.get(0);
                    break;
                }
                if (i == l - 1) {
                    System.out.println("单词已存在");
                }
            }
            Node newnode = arrayList.get(1);
            if (a.length > l) {
                char[] chars = new char[a.length - l];
                for (int i = a.length; i < l; i++) {
                    chars[i - a.length] = a[i];
                }
                add_fin(newnode, chars);
            }
        }
        System.out.println("添加成功");
    }
    private void add_fin(Node node,char[] temp){
        ArrayList<Character> al=node.getIndex();
        int k;
        for (k = 0; k < al.size(); k++) {
            if (al.get(k) == temp[0]) {
                char[] a=node.getName().toCharArray();
                int l=a.length>temp.length?a.length:temp.length;
                ArrayList<Node> arrayList=new ArrayList<Node>();
                for (int i = 0; i < l; i++) {
                    if (a[i]!=temp[i]&&i!=l-1){
                        arrayList=node.spil(i);
                        break;
                    }
                    if (i==l-1){
                        System.out.println("单词已存在");
                    }
                }
                Node newnode=arrayList.get(1);
                if (a.length>l){
                    char[] chars=new char[a.length-l];
                    for (int i = a.length; i < l; i++) {
                        chars[i-a.length]=a[i];
                    }
                    add_fin(newnode,chars);
                }
                return;

            }

        }
        if (k==al.size()) {
            Node node1 = new Node(temp.toString());
            ArrayList<Node> arrayList = node.getNext();
            arrayList.add(node1);
            node.setNext(arrayList);
        }

    }
    public void find(String s){
        char[] a=s.toCharArray();
        Node node=root;
        if (node.getName()!=null) {
            char[] temp = node.getName().toCharArray();
            int l = temp.length < a.length ? temp.length : a.length;
            int i = 0;
            for (; i < l; i++) {
                if (a[i] != temp[i]) {
                    System.out.println(5);
                    System.out.println("单词不存在");
                    return;
                }
            }
            if (i == a.length) {
                System.out.println(6);
                System.out.println("单词不存在");
                return;
            }
            char[] target = new char[s.length() - i];
            for (int j = i; j < s.length(); j++) {
                target[j - i] = a[i];
            }
            find(target, node);
        }
    }
    private void find(char[] temp,Node node){
        ArrayList<Character> al=node.getIndex();
        int k;
        for (k = 0; k < al.size(); k++) {
            if (al.get(k) == temp[0]) {
                node=find_index(node,al.get(k));
                if (node!=null) {
                    char[] a = node.getName().toCharArray();
                    int l = a.length < temp.length ? a.length : temp.length;
                    ArrayList<Node> arrayList = new ArrayList<Node>();
                    for (int i = 0; i < l; i++) {
                        if (a[i] != temp[i]) {
                            System.out.println(1);
                            System.out.println("单词不存在");
                            return;
                        }
                        if (i == temp.length - 1) {
                            System.out.println(2);
                            System.out.println("单词不存在");
                            return;
                        }
                    }
                    if (l==a.length){
                        if (node.isEnd() == true) {
                            System.out.println(3);
                            System.out.println("单词存在");
                            return;
                        }
                    }
                }

            }
        }

        System.out.println("单词存在");
    }
    public Node find_index(Node node,char a){
        Node node1=new Node();
        for (Node n:node.getNext()){
            if (n.getIndexx()==a)
                return n;
        }
        return null;
    }
    public void print(){

    }
}
