package dsa.zadanie.HashTable;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        HashTable table = new HashTable();
        vkladanieDoTabulky(table);
        System.out.println("---VYPIS---");
        vyhadavanieVTabulke(table);
        table.vypisHashTable();
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

    public static void r1_testovac(int vstup){

        HashTable table = new HashTable();
        vkladanieDoTabulky(table);
        System.out.println("---VYPIS---");
        vyhadavanieVTabulke(table);
        table.vypisHashTable();
    }


    public static void vkladanieDoTabulky(HashTable table){
        String line = "";
        String path =  "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup100k.csv";
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

    public static void vyhadavanieVTabulke(HashTable table){
        String line = "";
        String path =  "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup100k.csv";
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
