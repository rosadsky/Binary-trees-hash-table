package dsa.zadanie.BinaryTree;
import java.io.*;



class Main{


    public static void main(String[] args) {

        // 1 - VSTUP 50
        // 2 - VSTUP 20 000
        // 3 - VSTUP 100 000

         r1_testovac(1);
         r1_testovac(2);
         r1_testovac(3);


    }

    public static void vkladanieStromu(String path,BinaryTree strom){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
                strom.root = strom.insert(strom.root,Integer.parseInt(values[1]),values[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void r1_testovac(int velkostVstupu){

        long start = System.currentTimeMillis();
        BinaryTree strom = new BinaryTree();
        Node najdenynode = null;
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

        vkladanieStromu(path, strom);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time of inserting " + timeElapsed + "ms...");



        long startSearch = System.currentTimeMillis();
        vyhladavanieVStrome(strom,path);
        long finishSearch = System.currentTimeMillis();
        long timeElapsedSearch = finishSearch - startSearch;
        System.out.println("Time of searching " + timeElapsedSearch + "ms...");
        System.out.println("Tree height: "+maxDepth(strom.root));
        System.out.println("Number inserted nodes: " + strom.pocetVstupov);
        System.out.println("Number searched nodes: " + strom.pocetNajdeni);
        long startSingle = System.currentTimeMillis();
        if(velkostVstupu == 3) {
            //Evie Villiger,73
            System.out.println("Searching for: Evie Villiger,73" );
            najdenynode=strom.searchKey(strom.root,"Evie Villiger",73);
            System.out.println("Loaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek +
                    "\nbalance " + najdenynode.balance +
                    " height of node " + najdenynode.height);


        } else if(velkostVstupu == 2){
            System.out.println("Searching for: Alan Parker,86" );
            najdenynode=strom.searchKey(strom.root,"Alan Parker",86);
            System.out.println("SLoaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek +
                    "\nbalance " + najdenynode.balance +
                    " height of node " + najdenynode.height);

        }else if (velkostVstupu == 1){
            System.out.println("Searching for: Sonya Jenkin, 63 " );
            najdenynode=strom.searchKey(strom.root,"Sonya Jenkin",64);
            System.out.println("Loaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek +
                    "\nbalance " + najdenynode.balance +
                    " height of node " + najdenynode.height);
        }

        long finishSingle = System.currentTimeMillis();
        long timeElapsedSingle = finishSingle - startSingle;
        System.out.println("search time 1 node " + timeElapsedSingle + "ms...");


    }

    public static void vyhladavanieVStrome(BinaryTree strom,String path){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
                strom.searchKey(strom.root,values[0],Integer.parseInt(values[1]));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static int maxDepth(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }



}

