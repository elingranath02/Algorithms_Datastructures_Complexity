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

        public void set(Array a, int i, int value){

            if (i < 0){
                return;
            }

        Array newArray = this.newArray();
        newArray.prev = a;
        newArray.height = a.height;

        if(a.root == null){
            newArray.root = new Node(null, null, 0);
        }

        if (Math.pow(2, a.height) <= i){
            int count = (int)(Math.ceil(Math.log(i+1))) - a.height;
            newArray.root = this.increaseHeight(a.root, count);
            newArray.height = a.height + count;
            recursive(newArray.root, i, newArray.height);
        } else {
            recursiveChange(newArray.root, a.root, i, newArray.height, value);
        }


        }

        private void recursive(Node current, int i, int height, int value){
            if (height == 0){
                current.value = value;
                return;
            }
            int bit = (1<<height) & i;
            if (bit == 1){
                current.right = new Node(null, null, 0);
                return recursive(current.right, i, height-1);
            } else {
                current.left = new Node(null, null, 0);
                return recursive(current.left, i, height-1);
            }
        }
        private void recursiveChange(Node current, Node old, int i, int height, int value){
            if (height == 0){
                current.value = value;
                return;
            }
            int bit = (1<<height) & i;
            if (bit == 1){
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
           return recursiveGet(a.root, i, a.height);
        }

        private int recursiveGet(Node current, int i, int height){
            if (height == 0){
                return current.value;
            }
            int bit = (1<<height) & i;
            if (bit == 1){
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
        Array a = newArray();
    }

    
}
