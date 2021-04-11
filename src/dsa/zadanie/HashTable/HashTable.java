package dsa.zadanie.HashTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HashTable {

    Node root;
    ArrayList<Node>[] array = new ArrayList[10]; // základný array kde sa vytvárajú potom pod arraylisty
    int insertion = 0;
    int dlzkaPola = 10;
    int opakovania = 0;
    int zvacsenia = 0;

    int najdenia = 0;

    // do našho array vkladáme arraylist
    public void firstArray(int dlzkaPola) {
        for (int i = 0; i < dlzkaPola; i++) {

            if (array[i] == null) {
                array[i] = new ArrayList<Node>();
            }
        }
    }

    //funkcia insert na vkladanie prvok do hašovacej tabulky
    public void insert(String name, int vek) {
        int hash;

        //hned na začiatku si vložíme do pola, arraylist na chaining
        if (insertion == 0) {
            firstArray(dlzkaPola);
        }

        insertion++; // pocet vložení

        // zvačšovanie poľa, keď sa dlžka pola == počet vložení
        if (dlzkaPola == insertion) {
            biggerArrayList(dlzkaPola);
        }

        //System.out.println("1. HASH CALC " + hash(name) + " % " + dlzkaPola);

        hash = (hash(name) % dlzkaPola); // vytvárame hash ASCII hodnota nášho klúča modulo dlžka poľa

        //System.out.println("2. HASH NA INSERT [" + hash + "] na insert " + name);

        if (array[hash].isEmpty()) {

        } else {
            opakovania++; // vypočet opakovaní
        }

        array[hash].add(new Node(name, vek)); // vytvorenie prku


        // array[hash].add(new Node(name,vek));
       // System.out.println("3. " + array[hash].get(0).name + " " + array[hash].get(0).vek + " HASH: " + hash);

    }

    //zvačšovanie poľa
    public void biggerArrayList(int size) {

        zvacsenia++; // počítam si počet zvačšaní, ktore využívam pri hladaní
        int sizeNew = size * 2; // veľkost sa zvečšuje vždy o 100%



        ArrayList<Node>[] arrayNew = new ArrayList[size]; //vytvorím si dočastný arralist a tie medzi sebou vymením

        System.arraycopy(array, 0, arrayNew, 0, size);
        //System.out.println(Arrays.toString(arrayNew));
        array = new ArrayList[sizeNew];
        System.arraycopy(arrayNew, 0, array, 0, size);


        dlzkaPola = sizeNew;

        firstArray(dlzkaPola);

        //System.out.println(Arrays.toString(array));
    }

    //výpočet hashu ascii hodnota
    public int hash(String name) {
        int hash = 0;
        int dlzkaStringu = name.length();

        //System.out.println("Dlzka stringu "+ dlzkaStringu);

        for (int i = 0; i < name.length(); i++) {
            int asciiValue = name.charAt(i);

            hash = 31 * hash + asciiValue;
        }
        // System.out.println("ASCII of " + name + "=" + hash);

        return Math.abs(hash);

    }

    //vypis tabulky tu si meriam hodnoty dublikaty, obsadené neobsadené indexy
    public void vypisHashTable() {
        int size = 0;
        int counter = 0;
        int neobsadene = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                size = array[i].size();
            }
            for (int j = 0; j < size; j++) {
                if (array[i].get(j) != null) {
                    //System.out.print(array[i].get(j).name + " ");
                    if (j > counter) {
                        counter++;

                    }
                }
            }

            if (array[i].isEmpty()) {
                neobsadene++;
            }
            //System.out.println("X");
        }
        System.out.println("Duplicates in chains: " + opakovania + "\nLenght of array: " + dlzkaPola + "\nFree array indexes: " + neobsadene + "\nLonges chain: " + counter + "\nSearched nodes: " + najdenia);
    }

    public Node searchNode(String name, int vek) {

        int hladanyHash = 0;
        int velkostPola = 10;

        //search funkcia funguje tak že si postupne mením hash s počtom zvačšaní skúšam všetky možnosti


        int hash = hash(name); //zapisem si hash

        //System.out.println("ZVACSENIA " + zvacsenia);

        for (int i = 1; i < zvacsenia*2 ; i++) {


            if(i>1){
                velkostPola = velkostPola*2;
            }

                hladanyHash = hash % velkostPola; // zvavšujem hash aby som si to mohol vyskúšať
            if(dlzkaPola<velkostPola){

                return null;
            }

            //for loope hladám správny klúč
            for (int j = 0; j < array[hladanyHash].size(); j++) {
                if (array[hladanyHash].get(j) != null) {
                //System.out.println("SOM TU:" +array[hladanyHash].get(j).name );
                    if (array[hladanyHash].get(j).name.compareTo(name) == 0) {
                        if (array[hladanyHash].get(j).vek == vek) {
                           // System.out.println("Našiel som: " + array[hladanyHash].get(j).name);
                            najdenia++;
                            return array[hladanyHash].get(j);
                        }
                    }

                }

            }

        }
        return null;
    }

}
