package com.mzh.dataStructures.tree.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCodeDemo {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//
//        byte[] bytes = huffmanZip(contentBytes);
//
//
//        System.out.println(Arrays.toString(bytes));
//        System.out.println("编码前的长度" + contentBytes.length);
//        System.out.println("编码后的长度" + bytes.length);
//        System.out.println("压缩比" + (bytes.length / Double.valueOf(contentBytes.length)));
//
//        byte[] decode = decode(bytes, huffmanCodes);
//
//
//        System.out.println("解码的：" + new String(decode));

//
//        fileZip("./data/src.bmp","./data/dest.zip");
//        System.out.println("压缩成功");

        unZipFile("./data/dest.zip","./data/src1.bmp");
        System.out.println("解压成功");

    }

    public static void unZipFile(String zipFile, String destFile){
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            byte[] byts = (byte[]) ois.readObject();

            Map<Byte,String> codes = (Map<Byte,String>) ois.readObject();

            //System.out.println(codes);

            byte[] decodeBytes = decode(byts, codes);

            os = new FileOutputStream(destFile);
            os.write(decodeBytes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                ois.close();
                is.close();
                os.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    public static void fileZip(String srcFile, String destFile) {
        FileInputStream is = null;

        FileOutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            is = new FileInputStream(srcFile);

            byte[] bytes = new byte[is.available()];

            is.read(bytes);

            byte[] zipBytes = huffmanZip(bytes);

            os = new FileOutputStream(destFile);

            oos = new ObjectOutputStream(os);

            oos.writeObject(zipBytes);

            oos.writeObject(huffmanCodes);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static byte[] decode(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            boolean flag = (i != (bytes.length - 1));
            stringBuilder.append(byteToString(bytes[i], flag));
        }


        Map<String, Byte> huffmanDecodes = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            huffmanDecodes.put(entry.getValue(), entry.getKey());
        }

        StringBuilder key = new StringBuilder();

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            key.append(stringBuilder.charAt(i));

            Byte value = huffmanDecodes.get(key.toString());

            if (value != null) {
                list.add(value);
                key = new StringBuilder();
            }

        }


        byte[] bytesArray = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytesArray[i] = list.get(i);
        }


        return bytesArray;
    }


    /**
     * @param b
     * @param flag 标识是否补位
     * @return
     */
    public static String byteToString(byte b, boolean flag) {
        String res = "";

        int temp = b;

        if (flag) {
            temp |= 256;
        }

        res = Integer.toBinaryString(temp);

        if (flag) {
            res = res.substring(res.length() - 8);
        }


        return res;

    }


    public static byte[] huffmanZip(byte[] contentBytes) {
        //将字节数组转换成节点
        List<Node> nodes = getNode(contentBytes);
        //创建哈夫曼数
        Node root = createHuffmanTree(nodes);
        //前序遍历
        //preOrder(root);

        //获取哈夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(root);
        //将字节数组转换成哈夫曼编码数组
        return zip(contentBytes, huffmanCodes);
    }

    public static byte[] zip(byte[] contentBytes, Map<Byte, String> huffmanCodes) {

        StringBuilder stringBuilder = new StringBuilder();

        for (byte contentByte : contentBytes) {
            stringBuilder.append(huffmanCodes.get(contentByte));
        }

        System.out.println(stringBuilder);

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];


        int index = 0;
        for (int i = 0; i < len * 8; i += 8) {
            int end = i + 8;
            if (end > stringBuilder.length()) {
                end = stringBuilder.length();
            }


            String bt = stringBuilder.substring(i, end);
            huffmanCodeBytes[index] = (byte) Integer.parseInt(bt, 2);
            index++;
        }


        return huffmanCodeBytes;
    }


    private static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    private static StringBuilder path = new StringBuilder();

    public static Map<Byte, String> getCodes(Node root) {
        if (root != null) {
            getCodes(root.left, "0", path);
            getCodes(root.rigth, "1", path);
        }

        return huffmanCodes;
    }


    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.rigth, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    public static List<Node> getNode(byte[] contentBytes) {
        List<Node> nodes = new ArrayList<Node>();

        Map<Byte, Integer> map = new HashMap<>();

        for (byte contentByte : contentBytes) {
            Integer weigth = map.getOrDefault(contentByte, 0);
            map.put(contentByte, weigth + 1);
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.weigth + rightNode.weigth);
            parent.left = leftNode;
            parent.rigth = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        return nodes.get(0);
    }


    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空！");
        }
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weigth;
    Node left;
    Node rigth;

    public Node(Byte data, int weigth) {
        this.data = data;
        this.weigth = weigth;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weigth=" + weigth +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        int res = this.weigth - o.weigth;
        return res;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.rigth != null) {
            this.rigth.preOrder();
        }
    }

}
