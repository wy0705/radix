package yyy;

public class Test {
    public static void main(String[] args) {
        RadixTree radixTree=new RadixTree();
        radixTree.add("have");
        radixTree.add("had");
        radixTree.add("hid");
        System.out.println("当前头节点为：");
        System.out.println(radixTree.root.getName());
        System.out.println("have是否存在：");
        radixTree.find("have");
        System.out.println("输出所有单词：");
        radixTree.print();
    }
}
