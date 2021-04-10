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

    static node splay(node root,String name)
    {
        // Base cases: root is null or
        // key is present at root
        if (root == null || root.name == name)
            return root;

        //(root.key > key)

        if (root.name.compareTo(name) > 0)
        {
            // Key is not in tree, we are done
            if (root.left == null) return root;

            // Zig-Zig (Left Left)
            // root.left.key > key


            if (root.left.name.compareTo(name) > 0) {
                //System.out.println("ZIG-ZIG");
                // First recursively bring the
                // key as root of left-left
                root.left.left = splay(root.left.left, name);

                // Do first rotation for root,
                // second rotation is done after else
                root = rightRotate(root);
            }
            //root.left.key < key
            else if (root.left.name.compareTo(name) < 0) // Zig-Zag (Left Right)
                {
                   // System.out.println("ZIG-ZAG - left right");
                // First recursively bring
                // the key as root of left-right
                root.left.right = splay(root.left.right, name);

                // Do first rotation for root.left
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            // Do second rotation for root
            return (root.left == null)? root: rightRotate(root);
        }
        else // Key lies in right subtree
        {
            // Key is not in tree, we are done
            if (root.right == null) return root;

            // Zig-Zag (Right Left)
            //root.right.key > key

            //s1 == s2 :0
            //s1 > s2   :positive value
            //s1 < s2   :negative value

            if (root.right.name.compareTo(name) > 0)
            {
               // System.out.println("ZIG-ZAG - right left");
                // Bring the key as root of right-left
                root.right.left = splay(root.right.left, name);

                // Do first rotation for root.right
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            //root.right.key < key

            else if (root.right.name.compareTo(name) < 0 )// Zag-Zag (Right Right)
            {
                // Bring the key as root of
                // right-right and do first rotation
                root.right.right = splay(root.right.right, name);
                root = leftRotate(root);
            }

            // Do second rotation for root
            return (root.right == null)? root: leftRotate(root);
        }
    }

    // Function to insert a new key k
// in splay tree with given root
    static node insert(node root, int vek, String name)
    {
        // Simple Case: If tree is empty
        if (root == null) return newNode(vek,name);

        // Bring the closest leaf node to root
        root = splay(root,name);

        // If key is already present, then return
        if (root.name == name) return root;

        // Otherwise allocate memory for new node
        node newnode = newNode(vek,name);

        // If root's key is greater, make
        // root as right child of newnode
        // and copy the left child of root to newnode

        //root.name > k

        //s1 == s2 :0
        //s1 > s2   :positive value
        //s1 < s2   :negative value

        if (root.name.compareTo(name) > 0)
        {
            newnode.right = root;
            newnode.left = root.left;
            root.left = null;
        }

        // If root's key is smaller, make
        // root as left child of newnode
        // and copy the right child of root to newnode
        else
        {
            newnode.left = root;
            newnode.right = root.right;
            root.right = null;
        }

        return newnode; // newnode becomes new root
    }

    // A utility function to search a given key in BST
    static node search(node root, String name)
    {
        // Traverse untill root reaches to dead end
        while (root != null) {
            // pass right subtree as new tree

            //s1 == s2 :0
            //s1 > s2   :positive value
            //s1 < s2   :negative value
            //
            //(key > root.data)
            if (name.compareTo(root.name) > 0)
                root = root.right;

                // pass left subtree as new tree
                //(key < root.data)
            else if (name.compareTo(root.name) < 0)
                root = root.left;
            else
                return root; // if the key is found return 1
        }
        return root;
    }
    // A utility function to print
// preorder traversal of the tree.
// The function also prints height of every node
    static void preOrder(node root)
    {
        if (root != null)
        {
            System.out.print(root.name+" \n");
            preOrder(root.left);
            preOrder(root.right);
        }
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
        pocetNajdeni=vyhladavanieVStrome(prvyNode,path);
        long finishSearch = System.currentTimeMillis();
        long timeElapsedSearch = finishSearch - startSearch;
        System.out.println("Time of searching " + timeElapsedSearch + "ms...");
        System.out.println("Tree height: "+ maxDepth(prvyNode));
        System.out.println("Number inserted nodes: " +SplayTree.pocetVstupov );
        System.out.println("Number searched nodes: " + pocetNajdeni );
        SplayTree.pocetVstupov = 0;
        deleteTree(prvyNode);
    }

    public static int vyhladavanieVStrome(node node,String path){
        int pocetNajdeni=0;
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
                if(search(node,values[0]) != null){
                   pocetNajdeni++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pocetNajdeni;
    }

    static int maxDepth(node root)
    {
        if (root == null)
            return 0;
        else
        {
            //System.out.println("Pocitanie " + root.left.name);
            /* compute the depth of each subtree */
            int lDepth = maxDepth(root.left);
            int rDepth = maxDepth(root.right);


            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }




    /* Driver code
    public static void main(String[] args)
    {
        node root = null;

        String path =  "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup50.csv";

        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
                root = insert(root,Integer.parseInt(values[1]),values[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Preorder traversal of the modified Splay tree is \n");
        preOrder(root);
        System.out.println("Print vyšky: " + maxDepth(root));

        System.out.println("Našiel som: "+search(root,"Jocelyn Vallins").name);
    }
    static int maxDepth(node node)
    {
        if (node == null)
            return 0;
        else
        {

            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);


            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

     */



}




