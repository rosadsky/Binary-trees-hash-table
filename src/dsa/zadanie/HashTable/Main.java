package dsa.zadanie.HashTable;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        r1_testovac(1);
        r1_testovac(2);
        r1_testovac(3);
    }

/*
        ArrayList<Node>[] al = new ArrayList[10];

        for (int i = 0; i < 10; i++) {
            al[i] = new ArrayList<Node>();
        }


        al[0].add(new Node("roman osadsky",12));
        al[1].add(new Node("Peter Friesey",12));
        al[1].add(new Node("Pindo Pidikos",12));
        al[2].add(new Node("Peter Gerat",12));




        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < al[i].size(); j++) {
                System.out.print(al[i].get(j).name + " ");
            }
            System.out.println();
        }

*/

    public static void r1_testovac(int velkostVstupu){
        String path = null;
        Node najdenynode= null;
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
        long start = System.currentTimeMillis();
        HashTable table = new HashTable();
        vkladanieDoTabulky(table,path);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time of inserting " + timeElapsed + "ms...");
        long startSearch = System.currentTimeMillis();
        vyhadavanieVTabulke(table,path);
        long finishSearch = System.currentTimeMillis();
        long timeElapsedSearch = finishSearch - startSearch;
        System.out.println("Time of searching " + timeElapsedSearch + "ms...");
        table.vypisHashTable();
        long startSingle = System.currentTimeMillis();
        if(velkostVstupu == 3) {
            //Evie Villiger,73
            System.out.println("Searching for: Evie Villiger,73" );
            najdenynode=table.searchNode("Evie Villiger",73);
            System.out.println("Loaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek);// +
                    //"\nbalance " + najdenynode.balance +
                    //" height of node " + najdenynode.height);


        } else if(velkostVstupu == 2){
            System.out.println("Searching for: Alan Parker,86" );
            najdenynode=table.searchNode("Alan Parker",86);
            System.out.println("SLoaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek );// +
            //"\nbalance " + najdenynode.balance +
            //" height of node " + najdenynode.height);

        }else if (velkostVstupu == 1){
            System.out.println("Searching for: Sonya Jenkin, 63 " );
            najdenynode=table.searchNode("Sonya Jenkin",63);
            System.out.println("Loaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek );// +
            //"\nbalance " + najdenynode.balance +
            //" height of node " + najdenynode.height);
        }

        long finishSingle = System.currentTimeMillis();
        long timeElapsedSingle = finishSingle - startSingle;
        System.out.println("search time 1 node " + timeElapsedSingle + "ms...");

    }

    public static void vkladanieDoTabulky(HashTable table,String path){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
               table.insert(values[0], Integer.parseInt(values[1]));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void vyhadavanieVTabulke(HashTable table, String path){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                //System.out.println("Name " + values[0] + " age " + values[1] );
                table.searchNode(values[0], Integer.parseInt(values[1]));


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
