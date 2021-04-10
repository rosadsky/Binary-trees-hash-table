package dsa.zadanie.BinaryTree;

class BinaryTree {

    Node root;

    public BinaryTree() {
        System.out.println("Vytvaram strom");
    }

    Node insert(Node node, int vek,String key) {

        //System.out.println("VKLADAM: (" + key + ")");
        int balance;

        if (node == null) {
            //node.balance = height(node.right) - height(node.left);
            //System.out.println(height(node.right) - height(node.left));
            return new Node(vek,key);
        }
        // System.out.println(s3.compareTo(s1));//-1(because s3 < s1 )
        // vek.compareTo(node.getValue())<0
        if (key.compareTo(node.name) < 0){
           // System.out.println("vlavo " + key + "<" + node.name );
            node.left= insert(node.left,vek,key);

        }

        if( key.compareTo(node.name) > 0){
           // System.out.println("pravo " + key + ">" + node.name );
            node.right = insert(node.right,vek,key);
        }

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        balance = getBalance(node);
       // System.out.println("["+ node.name +"] h - > " + node.height + " b -> " + balance);


        //if s1 > s2, it returns positive number
        //if s1 < s2, it returns negative number

        //System.out.println(s3.compareTo(s1));//-1(because s3 < s1 )
        // vek < node.left.value




        if (balance > 1 ){
            if (( key.compareTo(node.left.name) < 0)){
                //System.out.println("-> Right Rotate");
                return rightRotate(node);
            }

        }


        //System.out.println(s1.compareTo(s3));//1(because s1>s3)
        //vek > node.right.value
        if (balance < -1){
            if ((key.compareTo(node.right.name) > 0)){
              //  System.out.println("-> Left Rotate");
                return leftRotate(node);
            }
        }


        // Left Right Case
        //if (balance > 1 && value > node.left.value)

        //((s1<s2) < 0)
        //((s1>s2) > 0)

        if (balance > 1) {
            if ((key.compareTo(node.left.name) > 0)){
               // System.out.println("-> Left Right Rotate");
                return leftRightRotate(node);
            }

        }

        //if (balance < -1 && value < node.right.value)
        if (balance < -1) {
            if ( key.compareTo(node.right.name) < 0) {
               // System.out.println("-> Right Left Rotate");
                return rightLeftRotate(node);
            }


        }

        return node;
    }

    public void searchKey(Node node, String key){

        Node vysledok = search(node,key);

        if (vysledok== null){
            System.out.println("Nenašiel som " + key + "...");
        } else  {
            System.out.println("NAŠIEL KEY: " + vysledok.name + " | VEK : " + vysledok.vek + " | VÝŠKA " + vysledok.height );
        }

    }

    Node search(Node node, String key){
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.name) == 0){
            return node;
        }

        // System.out.println(s3.compareTo(s1));//-1(because s3 < s1 )
        // vek.compareTo(node.getValue())<0
        if (key.compareTo(node.name) < 0){
            // System.out.println("LEFT - [ " + key+  " ] < [" + node.name + "]");
            node = search(node.left,key);

        } else if (key.compareTo(node.name)>0)  {
            //System.out.println("RIGHT- [ " + key+  " ] > [" + node.name + "]");
            node = search(node.right,key);
        }
        // System.out.println(s1.compareTo(s2))

        //System.out.println("94r ->> "+ node.name );

        return node;

    }

    Node leftRightRotate(Node node){

        //System.out.println("LEFT-RIGHT");

        node.left = leftRotate(node.left);

        Node rotationNode = node.left; //<-----
        node.left = rotationNode.right;
        rotationNode.right = node;

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        rotationNode.height = Math.max(sumHeight(rotationNode.left), sumHeight(rotationNode.right)) + 1;

        node.balance = getBalance(node);
        rotationNode.balance = getBalance(rotationNode);

        return rotationNode;
    }

    Node rightLeftRotate(Node node){
        //System.out.println("RIGHT-LEFT [" + node.name + "]");

        node.right = rightRotate(node.right);

        Node rotationNode = node.right;
        node.right = rotationNode.left;
        rotationNode.left = node;

        node.balance = getBalance(node);
        rotationNode.balance = getBalance(rotationNode);

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        rotationNode.height = Math.max(sumHeight(rotationNode.left), sumHeight(rotationNode.right)) + 1;



        return rotationNode;

    }

    Node rightRotate(Node node){
       // System.out.println("RIGHT");
        Node rotationNode = node.left;
        node.left = rotationNode.right;
        rotationNode.right = node;

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        rotationNode.height = Math.max(sumHeight(rotationNode.left), sumHeight(rotationNode.right)) + 1;

        node.balance = getBalance(node);
        rotationNode.balance = getBalance(rotationNode);
        //System.out.println("ZROTOVAL SOM HAHAHA");
        return rotationNode;
    }

    Node leftRotate(Node node){

        //System.out.println("LEFT");
        Node rotationNode = node.right;
        node.right = rotationNode.left;
        rotationNode.left = node;


        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        rotationNode.height = Math.max(sumHeight(rotationNode.left), sumHeight(rotationNode.right)) + 1;

        node.balance = getBalance(node);
        rotationNode.balance = getBalance(rotationNode);

        return rotationNode;

    }

    public int sumHeight(Node node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }

    private int getBalance(Node node) {
        int balance;
        int left;
        int right;
        if (node == null) {
            return 0;
        } else {
            left = sumHeight(node.left);
            right = sumHeight(node.right);

            balance = left - right;
           // System.out.println("getBalance NODE: [" + node.getName() + "] ");
           // System.out.println(sumHeight(node.left) +" - " + sumHeight(node.right) );
            return balance;
        }

    }
    public static void preOrder(Node node)
    {
        if (node != null)
        {
            System.out.print(node.name+" \n");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public Node getRoot() {
        return root;
    }
}

