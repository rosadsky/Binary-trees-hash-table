package dsa.zadanie.A;
import java.io.*;

import dsa.*;

import dsa.zadanie.*;

class Main{


    public static void main(String[] args) {

        //((s1<s2) < 0)
        //((s1>s2) > 0)

        //test_0();
        //test_1();
        test_2();
       // miniTest();

    }



    public static void test_1(){

        System.out.println("------------------------\n--> VSTUP | 20 000 | <--");
        long start = System.currentTimeMillis();
        BinaryTree strom = new BinaryTree();
        String key = null;

        String path =  "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/ExportCSV-2.csv";
        vkladanieStromu(path,strom);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Time " + timeElapsed + "ms...");

        strom.searchKey(strom.root,"Gil Mcnally");
        strom.searchKey(strom.root,"Jacob Gibbons");


    }


    public static void test_0(){

        System.out.println("------------------------\n--> VSTUP | 50 | <--");
        long start = System.currentTimeMillis();
        BinaryTree strom = new BinaryTree();
        String key = null;

        String path =  "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/ExportCSV.csv";
        vkladanieStromu(path,strom);


        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Time " + timeElapsed + "ms...");

        strom.searchKey(strom.root,"Gil Mcnally");
        strom.searchKey(strom.root,"Henry Robe");
        strom.searchKey(strom.root,"Roman Osadsky");


    }

    public static void test_2(){

        System.out.println("------------------------\n--> VSTUP | 100 000 | <--");
        long start = System.currentTimeMillis();
        BinaryTree strom = new BinaryTree();
        String key = null;

        String path =  "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup100k.csv";
        vkladanieStromu(path,strom);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Time " + timeElapsed + "ms...");

        strom.searchKey(strom.root,"Gil Mcnally");
        strom.searchKey(strom.root,"Jacob Gibbons");


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

    public static void miniTest(){
        System.out.println("------------------------\n--> VSTUP | MOJ VLASTNY | <--");
        long start = System.currentTimeMillis();
        BinaryTree strom = new BinaryTree();
        String key = null;


        strom.root = strom.insert(strom.root, 14,"a");
        strom.root = strom.insert(strom.root, 14,"d");
        strom.root = strom.insert(strom.root, 14,"c");



        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Time " + timeElapsed + "ms...");

        //strom.searchKey(strom.root,"Gil Mcnally");
       // strom.searchKey(strom.root,"Jacob Gibbons");


    }



}

