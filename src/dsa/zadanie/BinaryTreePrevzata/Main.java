package dsa.zadanie.BinaryTreePrevzata;

/*
* https://www.geeksforgeeks.org/splay-tree-set-2-insert-delete/
*
*  */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class SplayTree{
    static int pocetVstupov = 0;
    static int pocetNajdeni = 0;

    static class node {
        int vek;
        String name;
        node left, right;
    };

    static node newNode(int key,String name) {
        //System.out.println("NEW NODE :(" + name+ ")");
        node Node = new node();
        Node.vek = key;
        Node.name = name;
        Node.left = Node.right = null;
        pocetVstupov++;
        return (Node);
    }

    static node rightRotate(node x) {
        //System.out.println("RIGHT ROTATION");
        node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    static node leftRotate(node x) {
      //  System.out.println("LEFT ROTATION");
        node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    /*
        Metóda posiela klúč na root do search metódy
     */

    static node splay(node root,String name)
    {


        // ak je prázdna alebo sa už nachádza v strome
        if (root == null)
            return root;

        if(root.name.compareTo(name)==0){
            pocetNajdeni++;
            return root;
        }


        // Keď sa klúč nachádza na lavej strane
        if (root.name.compareTo(name) > 0)
        {
            // Nájdeme prázdny node tak ho vrátime do insertu
            if (root.left == null) return root;

            // Situácia -> Zig-Zig
            if (root.left.name.compareTo(name) > 0) {
                //System.out.println("ZIG-ZIG");
                root.left.left = splay(root.left.left, name);

                // Prvýkrat zrotujeme doprava potom ešte raz  na riadku 96
                // second rotation is done after else
                root = rightRotate(root);
            }

            // Situácia - Zig-Zag (Left Right)
            else if (root.left.name.compareTo(name) < 0)
                {
                   // System.out.println("ZIG-ZAG - left right");

                root.left.right = splay(root.left.right, name);

                // Prvá rotácia doprava potom ešte jedna na riadku 96
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            // posledná rotácia
            return (root.left == null)? root: rightRotate(root);
        }
        else // Keď sa klúč nachádza na pravej strane
        {
            // Nájdeme prázdny node tak ho vrátime do insertu
            if (root.right == null) return root;

            // Situácia - Zig-Zag (Right Left)
            if (root.right.name.compareTo(name) > 0)
            {
               // System.out.println("ZIG-ZAG - right left");

                root.right.left = splay(root.right.left, name);

                // Prvá rotácia doprava potom dalšia na riadku 125
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }

            // Zag-Zag (Right Right)
            else if (root.right.name.compareTo(name) < 0 )
            {
               // prvá rotácia a potom rotacia na 124
                root.right.right = splay(root.right.right, name);
                root = leftRotate(root);
            }


            return (root.right == null)? root: leftRotate(root);
        }
    }


    static node insert(node root, int vek, String name)
    {
        // Ak je strom prázdny
        if (root == null) return newNode(vek,name);

        // Splay nam vráti najbližši možný volný node
        root = splay(root,name);

        // Ak sa klúč nachádza v poli
        if (root.name == name) return root;


        node newnode = newNode(vek,name);

        //Ak je root vačší tak sa zapíše na pravú stranu nového nodu
        if (root.name.compareTo(name) > 0)
        {
            newnode.right = root;
            newnode.left = root.left;
            root.left = null;
        }

        // Ak to je menšie tak sa zapíše na lavú stranu nového nodu
        else
        {
            newnode.left = root;
            newnode.right = root.right;
            root.right = null;
        }


        return newnode; // nový node sa stane root
    }

    // Najdeny node v našom prípade sa presunie na root
    static node search(node root, String key)
    {
        return splay(root, key);
    }


    public static void deleteTree(node deletenode){
        if(deletenode==null)
            return;
        deleteTree(deletenode.left);
        deleteTree(deletenode.right);
        //System.out.println("The deleted node is " + deletenode.name);
        deletenode = null;
    }


    public static void main(String[] args) {

        // 1 - VSTUP 50
        // 2 - VSTUP 20 000
        // 3 - VSTUP 100 000

        r1_testovac(1);
        r1_testovac(2);
        r1_testovac(3);


    }

    public static node vkladanieStromu(String path){
        String line = "";
        int counter = 0;
        node prvyNode = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                counter++;
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );

                prvyNode=insert(prvyNode,Integer.parseInt(values[1]),values[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prvyNode;
    }

    public static void r1_testovac(int velkostVstupu){

        long start = System.currentTimeMillis();
        node prvyNode = null;
        node najdenynode = null;
        int pocetNajdeni;
        String key = null;
        String path = null;
        if(velkostVstupu == 3) {
            System.out.println("-------------------------\n--> VSTUP | 100 000 | <--");
            path = "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup100k.csv";

        } else if(velkostVstupu == 2){
            System.out.println("-------------------------\n--> VSTUP | 20 000 | <--");
            path = "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup20k.csv";

        }else if (velkostVstupu == 1){
            System.out.println("-------------------------\n--> VSTUP | 50 | <--");
            path = "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup50.csv";
        }

        prvyNode=vkladanieStromu(path);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time of inserting " + timeElapsed + "ms...");



        long startSearch = System.currentTimeMillis();
        prvyNode=vyhladavanieVStrome(prvyNode,path);
        long finishSearch = System.currentTimeMillis();
        long timeElapsedSearch = finishSearch - startSearch;
        System.out.println("Time of searching " + timeElapsedSearch + "ms...");
        System.out.println("Tree height: "+ maxDepth(prvyNode));
        System.out.println("Number inserted nodes: " +SplayTree.pocetVstupov );
        System.out.println("Number searched nodes: " + SplayTree.pocetNajdeni );
        SplayTree.pocetVstupov = SplayTree.pocetNajdeni = 0;
        long startSingle = System.currentTimeMillis();
        if(velkostVstupu == 3) {
            //Evie Villiger,73
            System.out.println("Searching for: Evie Villiger,73" );
            prvyNode=search(prvyNode,"Evie Villiger");
            System.out.println("Loaded node: "+ prvyNode.name +
                    " age " +  prvyNode.vek); //+
                    //"\nbalance " + najdenynode.balance +
                    //" height of node " + najdenynode.height);


        } else if(velkostVstupu == 2){
            System.out.println("Searching for: Alan Parker,86" );
            prvyNode=search(prvyNode,"Alan Parker");
            System.out.println("Loaded node: "+ prvyNode.name +
                    " age " +  prvyNode.vek );//+
                   // "\nbalance " + najdenynode.balance +
                  //  " height of node " + najdenynode.height);

        }else if (velkostVstupu == 1){
            System.out.println("Searching for: Sonya Jenkin, 63 " );
            prvyNode=search(prvyNode,"Sonya Jenkin");
            System.out.println("Loaded node: "+ prvyNode.name +
                    " age " +  prvyNode.vek);// +
                  //  "\nbalance " + najdenynode.balance +
                   // " height of node " + najdenynode.height);
        }

        long finishSingle = System.currentTimeMillis();
        long timeElapsedSingle = finishSingle - startSingle;
        System.out.println("search time 1 node " + timeElapsedSingle + "ms...");


        deleteTree(prvyNode);
    }

    public static node vyhladavanieVStrome(node node,String path){
        int pocetNajdeni=0;
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
                node = search(node,values[0]);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return node;
    }

    static int maxDepth(node root)
    {
        if (root == null)
            return 0;
        else
        {

            int lDepth = maxDepth(root.left);
            int rDepth = maxDepth(root.right);

            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

}




