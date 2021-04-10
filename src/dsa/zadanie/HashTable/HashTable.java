package dsa.zadanie.HashTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HashTable {

    Node root;
    //Node array[] = new Node[10];
    ArrayList<Node>[] array = new ArrayList[10];
    int insertion = 0;
    int dlzkaPola = 10;
    int opakovania = 0;
    int zvacsenia = 0;

    int najdenia = 0;


    public void firstArray(int dlzkaPola) {
        for (int i = 0; i < dlzkaPola; i++) {

            if (array[i] == null) {
                array[i] = new ArrayList<Node>();
            }
        }
    }

    public void insert(String name, int vek) {
        int hash;

        if (insertion == 0) {
            firstArray(dlzkaPola);
        }

        insertion++;
        if (dlzkaPola == insertion) {
            biggerArrayList(dlzkaPola);
        }

        System.out.println("1. HASH CALC " + hash(name) + " % " + dlzkaPola);
        hash = (hash(name) % dlzkaPola);
        System.out.println("2. HASH NA INSERT [" + hash + "] na insert " + name);

        if (array[hash].isEmpty()) {

        } else {
            opakovania++;
        }

        array[hash].add(new Node(name, vek));


        // array[hash].add(new Node(name,vek));
        System.out.println("3. " + array[hash].get(0).name + " " + array[hash].get(0).vek + " HASH: " + hash);

    }

    public void biggerArrayList(int size) {
        zvacsenia++;
        System.out.println("NEW SIZE ARRAY: " + size + " + " + size);
        int sizeNew = size * 2;

        //OPAKOVANIA: 47712 DLZKA 106710 NEOBSADENE 54430 COUNTER 15
        // OPAKOVANIA: 45605 DLZKA 163840 NEOBSADENE 109453 COUNTER 15

        //Node arrayNew[] = new Node[sizeNew];
        ArrayList<Node>[] arrayNew = new ArrayList[size];

        System.arraycopy(array, 0, arrayNew, 0, size);
        System.out.println(Arrays.toString(arrayNew));
        array = new ArrayList[sizeNew];
        System.arraycopy(arrayNew, 0, array, 0, size);


        dlzkaPola = sizeNew;

        firstArray(dlzkaPola);

        System.out.println(Arrays.toString(array));
    }

    public int hash(String name) {
        int hash = 0;
        int dlzkaStringu = name.length();

        //System.out.println("Dlzka stringu "+ dlzkaStringu);

        for (int i = 0; i < name.length(); i++) {
            int asciiValue = name.charAt(i);

            hash = 31 * hash + asciiValue;
            //System.out.println(name.charAt(i) + "=" + asciiValue);
        }
        // System.out.println("ASCII of " + name + "=" + hash);

        return Math.abs(hash);

    }

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
                    System.out.print(array[i].get(j).name + " ");
                    if (j > counter) {
                        counter++;

                    }
                }
            }

            if (array[i].isEmpty()) {
                neobsadene++;
            }
            System.out.println("X");
        }
        System.out.println("OPAKOVANIA: " + opakovania + " DLZKA " + dlzkaPola + " NEOBSADENE " + neobsadene + " COUNTER " + counter + "NAJDENIA " + najdenia);
    }

    public Node searchNode(String name, int vek) {

        int hladanyHash = 0;
        int velkostPola = 10;

        int hash = hash(name);

        //System.out.println("ZVACSENIA " + zvacsenia);

        for (int i = 1; i < zvacsenia*2 ; i++) {


            if(i>1){
                velkostPola = velkostPola*2;
            }

                hladanyHash = hash % velkostPola;
            if(dlzkaPola<velkostPola){
                System.out.println("WHAT + " + name);
                return null;
            }
            //System.out.println("HASH " + hash + " % " + velkostPola + " | HLADANY HASH");

            //System.out.println("H:" + hladanyHash);
            //System.out.println("HLADANY HASH JE:" + hladanyHash);
            for (int j = 0; j < array[hladanyHash].size(); j++) {
                if (array[hladanyHash].get(j) != null) {
              //      System.out.println("P: ->" + array[hladanyHash].get(j).name);
                    if (array[hladanyHash].get(j).name.compareTo(name) == 0) {
                        if (array[hladanyHash].get(j).vek == vek) {
                            //System.out.println("Našiel som------------------: " + array[hladanyHash].get(j).name);
                            najdenia++;
                            return array[hladanyHash].get(j);
                        }
                    }

                }

            }

        }
        System.out.println("nahovno" + name);
        return null;
    }

}
//HashTable table = new HashTable();