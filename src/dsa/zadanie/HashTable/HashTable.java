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



    public void firstArray (int dlzkaPola){
        for (int i = 0; i < dlzkaPola; i++) {

            if (array[i]== null) {
                array[i] = new ArrayList<Node>();
            }
        }
    }

    public void insert(String name, int vek){
    int hash;

    if(insertion == 0){
        firstArray(dlzkaPola);
    }

    insertion++;
    if(dlzkaPola == insertion){
        biggerArrayList(dlzkaPola);
    }

    System.out.println("HASH CALC "+ hash(name) + " % " + dlzkaPola);
    hash = ((hash(name)^dlzkaPola)%dlzkaPola);
    System.out.println("HASH NA INSERT [" +hash + "] na insert " + name);

    if(array[hash].isEmpty()){

    } else {
        opakovania++;
    }

    array[hash].add(new Node(name,vek));


   // array[hash].add(new Node(name,vek));
    System.out.println(array[hash].get(0).name +" "+ array[hash].get(0).vek + " HASH: " + hash);

    }

    public void biggerArrayList(int size){
        System.out.println("NEW SIZE ARRAY: "+ size +" + "+ size/2 );
        int sizeNew = size + size/2;

        //Node arrayNew[] = new Node[sizeNew];
        ArrayList<Node>[] arrayNew = new ArrayList[size];

        System.arraycopy(array, 0, arrayNew, 0,size);
        System.out.println(Arrays.toString(arrayNew));
        array = new ArrayList[sizeNew];
        System.arraycopy(arrayNew, 0, array, 0, size);


        dlzkaPola = sizeNew;

        firstArray(dlzkaPola);

        System.out.println(Arrays.toString(array));
    }

    public int hash(String name){
        int hash = 0;
        int dlzkaStringu = name.length();

        //System.out.println("Dlzka stringu "+ dlzkaStringu);

        for(int i=0; i<name.length(); i++)
        {
            int asciiValue = name.charAt(i);

            hash = hash + asciiValue;
            //System.out.println(name.charAt(i) + "=" + asciiValue);
        }
       // System.out.println("ASCII of " + name + "=" + hash);

        return hash;

    }

    public void vypisHashTable(){
        int size = 0;
        int counter= 0;
        int neobsadene = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] != null){
                size = array[i].size();
            }
            for (int j = 0; j < size ; j++) {
                if (array[i].get(j) != null) {
                    System.out.print(array[i].get(j).name + " ");
                    if (j>counter){
                        counter++;

                    }
                } else {

                }
            }
            System.out.println("X");
        }
        System.out.println("OPAKOVANIA: " + opakovania +" DLZKA" + dlzkaPola + " COUNTER " + counter);
    }

}
//HashTable table = new HashTable();