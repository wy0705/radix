package hash;

import javax.rmi.CORBA.Util;
import java.util.*;

public class TestHashCircle {
    private static final String Ip="192.168.1.";

    public static String RandomString(int i){
        char[] chars=new char[i];
        char[] tt=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l'
                ,'m','n','o','p','q','r','s','t','u','v','w','x','y','z'
                ,'0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F','G','H','I','J','K','L','M'
                ,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        Random r=new Random();
        for (int j=0;j<i;j++){
            chars[j]=tt[r.nextInt(1000)%62];
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<String, Integer>();
        ArrayList<Node<String>> nodes=new ArrayList<Node<String>>();
        //创建10个真机
        for (int i = 0; i < 10; i++) {
            map.put(Ip,10);
            Node<String> node=new Node<String>(Ip+i,"node"+i);
            nodes.add(node);
        }
        IHashService iHashService=new HashService();
        ConsistentHash<Node<String>> consistentHash=new ConsistentHash<>(iHashService,500,nodes);

        for (int i = 0; i < 5000; i++) {
            String filename=RandomString(10)+ i;
            Node<String> node = consistentHash.get(filename);
            map.put(node.getIp(), map.get(node.getIp()) + 1);
        }

        // 打印每台真实机器节点保存的记录条数
        for (int i = 1; i <= 10; i++) {
            System.out.println(Ip + i + "节点记录条数：" + map.get(Ip + i));
        }
    }
//    public static void main(String[] args) {
//        // 每台真实机器节点上保存的记录条数
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        // 真实机器节点, 模拟10台
//        ArrayList<Node<String>> nodes = new ArrayList<Node<String>>();
//        for (int i = 1; i <= 10; i++) {
//            map.put(Ip + i, 0); // 初始化记录
//            Node<String> node = new Node<String>(Ip + i, "node" + i);
//            nodes.add(node);
//        }
//
//        IHashService iHashService = new HashService();
//        // 每台真实机器引入100个虚拟节点
//        ConsistentHash<Node<String>> consistentHash = new ConsistentHash<Node<String>>(iHashService, 500, nodes);
//
//        // 将5000条记录尽可能均匀的存储到10台机器节点上
//        for (int i = 0; i < 5000; i++) {
//            // 产生随机一个字符串当做一条记录，可以是其它更复杂的业务对象,比如随机字符串相当于对象的业务唯一标识
//            String data = UUID.randomUUID().toString() + i;
//            // 通过记录找到真实机器节点
//            Node<String> node = consistentHash.get(data);
//            // 再这里可以能过其它工具将记录存储真实机器节点上，比如MemoryCache等
//            // ...
//            // 每台真实机器节点上保存的记录条数加1
//            map.put(node.getIp(), map.get(node.getIp()) + 1);
//        }
//
//        // 打印每台真实机器节点保存的记录条数
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(Ip + i + "节点记录条数：" + map.get(Ip + i));
//        }
//    }
}
