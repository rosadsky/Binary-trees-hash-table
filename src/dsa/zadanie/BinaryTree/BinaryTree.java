package dsa.zadanie.BinaryTree;

class BinaryTree {

    Node root;
    int pocetVstupov = 0;
    int pocetNajdeni = 0;
    public BinaryTree() {

    }

    Node insert(Node node, int vek,String key) {

        //System.out.println("VKLADAM: (" + key + ")");
        int balance;

        if (node == null) {  // najdeme prázdny node -> vytvoríme nový node v strome
            pocetVstupov++;
            return new Node(vek,key);
        }

        /*
            rekurzívna podmienka kde postupne porovnávame hodnotu ktoru chceme vložiť
            s hodnotami, ktoré už v strome sú a podla toho sa postupne dostávame
            v strome nižšie a nižšie  až natrafíme na volný node a tam hodnotu zapíšeme
         */

        if (key.compareTo(node.name) < 0){
           // System.out.println("vlavo " + key + "<" + node.name );
            node.left= insert(node.left,vek,key);

        }

        if( key.compareTo(node.name) > 0){
           // System.out.println("pravo " + key + ">" + node.name );
            node.right = insert(node.right,vek,key);
        }

        //ak sa nachádza dané meno v už v strome tak si zapíšem počet vstupov, ktoré som načítal
        if( key.compareTo(node.name) == 0){
            pocetVstupov++;
        }

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1; //počítam si priebežne výšku každého node
        balance = getBalance(node);  // počítam si balance daného nodu, vďaka čomu môžem neskor zistiť či ho treba rotovať

       // System.out.println("["+ node.name +"] h - > " + node.height + " b -> " + balance);

        // ak chcem strom rotovať doprava
        if (balance > 1 ){
            if (( key.compareTo(node.left.name) < 0)){
                //System.out.println("-> Right Rotate");
                return rightRotate(node);
            }

        }

        // ak chcem strom rotovať dolava
        if (balance < -1){
            if ((key.compareTo(node.right.name) > 0)){
              //  System.out.println("-> Left Rotate");
                return leftRotate(node);
            }
        }


        // ak chcem strom rotovať dolava a potom ešte doprava
        if (balance > 1) {
            if ((key.compareTo(node.left.name) > 0)){
               // System.out.println("-> Left Right Rotate");
                return leftRightRotate(node);
            }

        }
        // ak chcem strom rotovať doprava a potom ešte dolava
        if (balance < -1) {
            if ( key.compareTo(node.right.name) < 0) {
               // System.out.println("-> Right Left Rotate");
                return rightLeftRotate(node);
            }


        }

        return node;
    }

    public Node searchKey(Node node, String key, int vek){

        Node vysledok = search(node,key,vek);

        if (vysledok== null){
            System.out.println("Nenašiel som " + key + "...");
        } else  {
            pocetNajdeni++;
            //System.out.println("NAŠIEL KEY: " + vysledok.name + " | VEK : " + vysledok.vek + " | VÝŠKA " + vysledok.height );
        }

        return vysledok;
    }

    Node search(Node node, String key, int vek){
       /*
       Search funkcia má rovnaký postup prechádzania v strome ako ked ich vkladám
       to znamená že rekruzívne porovnávam dané nody až dokým nenájdem hladaný kľúč
        */


        if (node == null) {
            return null;
        }

        if (key.compareTo(node.name) == 0 && vek == node.vek){
            return node;
        }

        if (key.compareTo(node.name) < 0){
            // System.out.println("LEFT - [ " + key+  " ] < [" + node.name + "]");
            node = search(node.left,key,vek);

        } else if (key.compareTo(node.name)>0)  {
            //System.out.println("RIGHT- [ " + key+  " ] > [" + node.name + "]");
            node = search(node.right,key,vek);
        }

        return node;

    }

    Node leftRightRotate(Node node){

         /*
        Otáčanie dolava/doprava - som znázornil na obrázku v mojej dokumentácií
            - keď a dané nody zrotuju tak im prepočítam výšku aj balanca a zapíšem ju do nich
         */

        //System.out.println("LEFT-RIGHT");

        node.left = leftRotate(node.left);

        Node rotationNode = node.left;
        node.left = rotationNode.right;
        rotationNode.right = node;

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        rotationNode.height = Math.max(sumHeight(rotationNode.left), sumHeight(rotationNode.right)) + 1;

        node.balance = getBalance(node);
        rotationNode.balance = getBalance(rotationNode);

        return rotationNode;
    }

    Node rightLeftRotate(Node node){
         /*
        Otáčanie doprava/dolava - som znázornil na obrázku v mojej dokumentácií
            - keď a dané nody zrotuju tak im prepočítam výšku aj balanca a zapíšem ju do nich
         */

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
        /*
        Otáčanie doprava - som znázornil na obrázku v mojej dokumentácií
            - keď a dané nody zrotuju tak im prepočítam výšku aj balanca a zapíšem ju do nich
         */

       // System.out.println("RIGHT");
        Node rotationNode = node.left;
        node.left = rotationNode.right;
        rotationNode.right = node;

        node.height = Math.max(sumHeight(node.left), sumHeight(node.right)) + 1;
        rotationNode.height = Math.max(sumHeight(rotationNode.left), sumHeight(rotationNode.right)) + 1;

        node.balance = getBalance(node);
        rotationNode.balance = getBalance(rotationNode);

        return rotationNode;
    }

    Node leftRotate(Node node){

         /*
        Otáčanie dolava - som znázornil na obrázku v mojej dokumentácií
            - keď a dané nody zrotuju tak im prepočítam výšku aj balanca a zapíšem ju do nich
         */


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


    //vypocet výšky daného nodu ak node je prázdny vráti sa -1 a ak nieje tak v nom je zapísaná výška a tá sa vráti
    public int sumHeight(Node node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }
    // výpočet balancu sa počíta tak že odpočítam výšku pravého nodu od ľavého
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
            return balance;
        }

    }


    public Node getRoot() {
        return root;
    }
}

