package dsa.zadanie.HashTable;

public class HashTable {

    Node root;

    public Node insert(Node node){


        return node;
    }

    public int hash(String name){
        int hash = 0;
        int dlzkaStringu = name.length();

        System.out.println("Dlzka stringu "+ dlzkaStringu);

        for(int i=0; i<name.length(); i++)
        {
            int asciiValue = name.charAt(i);

            hash = hash + asciiValue;
            //System.out.println(name.charAt(i) + "=" + asciiValue);
        }
        //System.out.println("ASCII of " + name + "=" + hash);

        return hash;

    }

    /*
     int hash (String name){
         int dlzkaStringu, hash = 0;

         for (int i = 0; i < dlzkaStringu; i++){
             hash = 31*hash + name[i];
         }

         return hash;
     }
     *
 */

}
//HashTable table = new HashTable();