package Labb1;

public class Main{

    public class Array {
        Array prev;
        int height;
        Node root;

        public Array(Array prev, int height, Node root){
            this.prev = prev;
            this.height = height;
            this.root = root;

        }

        public Array newArray(){
            return new Array(null, 0, null);

        }

        public void set(Array a, int i, Integer value){

            if (i < 0){
                return;
            }

        Array newArray = this.newArray();
        newArray.prev = a;
        newArray.height = a.height;

        if(a.root == null){
            newArray.root = new Node(null, null, null);
        }

        if (Math.pow(2, a.height) <= i){
            int count = (int)(Math.ceil(Math.log(i+1))) - a.height;
            newArray.root = this.increaseHeight(a.root, count);
            newArray.height = a.height + count;
        }




        }

        public Array recursive(Node current, int i, int height){


        }

        public Node increaseHeight(Node current, int count){

            if(count == 0){
                return current;
            }

            Node newNode = new Node(null, current, null);
            return increaseHeight(newNode, count--);
            

        }

        public class Node {
            Node right;
            Node left;
            Integer value;

            public Node(Node right, Node left, Integer value){
                this.right = right;
                this.left = left;
                this.value = value;
                
            }




        }

    
    }

    public static void main(String[] args){

    }


    
}
