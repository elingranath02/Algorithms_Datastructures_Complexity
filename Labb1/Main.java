package Labb1;

import java.util.Scanner;

public class Main{

    public static class Array {
        Array prev;
        int height;
        Node root;

        public Array(Array prev, int height, Node root){
            this.prev = prev;
            this.height = height;
            this.root = root;

        }

        public static Array newArray(){
            return new Array(null, 0, null);

        }

        public void printLeaves(Node node) {
            if (node == null) return;
        
            if (node.left == null && node.right == null) {
                System.out.println(node.value);
            } else {
                if (node.left != null) printLeaves(node.left);
                if (node.right != null) printLeaves(node.right);
            }
        }

        public Array set(Array a, int i, int value){

            if (i < 0){
                return null;
            }

        Array newArray = newArray();
        newArray.prev = a;
        newArray.height = a.height;

        

        if (Math.pow(2, a.height) <= i){
            int count = (int)(Math.ceil(Math.log(i+1)/Math.log(2))) - a.height;
            newArray.root = this.increaseHeight(a.root, count);
            newArray.height = a.height + count;
            recursive(newArray.root, i, newArray.height, value);
        } else {
            newArray.root = new Node(null, null, 0);
            recursiveChange(newArray.root, a.root, i, newArray.height, value);
        }

        return newArray;

        }

        private void recursive(Node current, int i, int height, int value){
            if (height == 0){
                current.value = value;
                return;
            }
            int bit = (1<<height - 1) & i;
            if (bit != 0){
                current.right = new Node(null, null, 0);
                recursive(current.right, i, height-1, value);
            } else {
                current.left = new Node(null, null, 0);
                recursive(current.left, i, height-1, value);
            }
            return;
        }
        private void recursiveChange(Node current, Node old, int i, int height, int value){
            if (height == 0){
                current.value = value;
                return;
            }
            int bit = (1<<height - 1) & i;
            if (bit != 0){
                current.right = new Node(null, null, 0);
                current.left = old.left;
                if (old.right == null){
                    recursive(current.right, i, height-1, value);
                } else {
                    recursiveChange(current.right, old.right, i, height-1, value);
                }
            } else {
                current.left = new Node(null, null, 0);
                current.right = old.right;
                if (old.left == null){
                    recursive(current.left, i, height-1, value);
                } else {
                    recursiveChange(current.left, old.left, i, height-1, value);
                }
            }
            return;
        }

        public int get(Array a, int i){
            if (i > Math.pow(2,a.height) -1 ){
                return 0;
            }
           return recursiveGet(a.root, i, a.height);
        }

        private int recursiveGet(Node current, int i, int height){

            if (height == 0){
                return current.value;
            }
            int bit = (1<<height-1) & i;
            if (bit != 0){
                if (current.right == null){
                    return 0;
                }
                return recursiveGet(current.right, i, height-1);
            } else {
                if (current.left == null){
                    return 0;
                }
                return recursiveGet(current.left, i, height-1);
            }
        }
        

        public Node increaseHeight(Node current, int count){

            if(count == 0){
                return current;
            }

            Node newNode = new Node(null, current, 0);
            return increaseHeight(newNode, count-1);
            
        }

        public class Node {
            Node right;
            Node left;
            int value;

            public Node(Node right, Node left, Integer value){
                this.right = right;
                this.left = left;
                this.value = value;
                
            }

        }

    
    }

    public static void main(String[] args){
        Array aInstance = Array.newArray();
        Array a = aInstance;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");

            switch(parts[0]){
                case "set":
                    int i = Integer.parseInt(parts[1]);
                    int value = Integer.parseInt(parts[2]);
                    a = a.set(a, i, value);
                    break;

                case "get":
                    int indexGet = Integer.parseInt(parts[1]);
                    int val = a.get(a, indexGet);
                    System.out.println(val);
                    a.printLeaves(a.root);
                    break;

                case "unset":
                    a = a.prev;
                    if (a == null) {
                        a = aInstance;
                    }
                    break;

                case "print":
                    a.printLeaves(a.root);
                    break;

                default:
                    System.out.println("Okänt kommando: " + line);
                    break;
            }
        }

        sc.close();
    }
    
}
