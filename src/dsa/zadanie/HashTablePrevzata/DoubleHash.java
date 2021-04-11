package dsa.zadanie.HashTablePrevzata;

/*
    https://www.geeksforgeeks.org/java-program-to-implement-hash-tables-with-double-hashing/
 */
import java.io.*;

class ValueEntry {


    String name;
    int vek;

    ValueEntry(String name, int vek) {
        this.name = name;
        this.vek = vek;
    }
}


class HashTable {

    private int HASH_TABLE_SIZE;
    private int size;
    private ValueEntry[] table;
    private int totalprimeSize;


    public HashTable(int ts) {

        size = 0;
        HASH_TABLE_SIZE = ts;
        table = new ValueEntry[HASH_TABLE_SIZE];


        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;

        totalprimeSize = getPrime();
    }

    //Metóda na získanie prvočísla
    public int getPrime() {
        for (int i = HASH_TABLE_SIZE - 1; i >= 1; i--) {
            int cnt = 0;
            for (int j = 2; j * j <= i; j++)
                if (i % j == 0)
                    cnt++;


            if (cnt == 0)
                return i;
        }

        // Vrátenie prvočísla
        return 3;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //
    /* Vymazanie HashTable */
    public void makeEmpty() {
        size = 0;
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }

    public int pocetNajdeni;

    // nájdenie kľúča
    public ValueEntry getkey(String key) {
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);

        while (table[hash1] != null
                && !table[hash1].name.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }

        pocetNajdeni++;

        return table[hash1];
    }

    public int pocetVlozeni = 0;


    //Vloženie prvku
    public void insert(String key, int value) {

       //zistenie či tabulka je plná
        if (size == HASH_TABLE_SIZE) {
            System.out.println("Table is full");
            return;
        }

        int hashing1 = myhash1(key); // prvé hashovanie
        int hashing2 = myhash2(key); // druhý hash

        while (table[hashing1] != null) {
            hashing1 += hashing2;
            hashing1 %= HASH_TABLE_SIZE;
        }

        table[hashing1] = new ValueEntry(key, value);
        pocetVlozeni++;
        size++;
    }

   //prvé hashovanie
    private int myhash1(String y) {
        int myhashVal1 = y.hashCode();
        myhashVal1 %= HASH_TABLE_SIZE;
        if (myhashVal1 < 0)
            myhashVal1 += HASH_TABLE_SIZE;
        return myhashVal1;
    }

    //druhé hashovanie
    private int myhash2(String y) {
        int myhashVal2 = y.hashCode();
        myhashVal2 %= HASH_TABLE_SIZE;
        if (myhashVal2 < 0)
            myhashVal2 += HASH_TABLE_SIZE;
        return totalprimeSize - myhashVal2 % totalprimeSize; // finálny výpočet hashu
    }

    // metóda moja
    public int zistitPocetVolnych() {
        int pocetVolnych = 0;
        for (int i = 0; i < HASH_TABLE_SIZE; i++) {
            if (table[i] == null) {
                pocetVolnych++;
            }
        }
        return pocetVolnych;
    }

}


class HashTableMain {
    public static void main(String[] args)
    {

        r1_testovac(1);
        r1_testovac(2);
        r1_testovac(3);

    }


    public static void r1_testovac(int velkostVstupu){
        String path = null;
        int velkostPola = 100000;
        ValueEntry najdenynode;

        if(velkostVstupu == 3) {
            System.out.println("-------------------------\n--> VSTUP | 100 000 | <--");
            velkostPola = 160000;
            path = "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup100k.csv";

        } else if(velkostVstupu == 2){
            velkostPola = 30000;
            System.out.println("-------------------------\n--> VSTUP | 20 000 | <--");
            path = "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup20k.csv";

        }else if (velkostVstupu == 1){
            velkostPola = 75;
            System.out.println("-------------------------\n--> VSTUP | 50 | <--");
            path = "/Users/romanosadsky/Documents/LS 2021/OOP/DSA-ZADANIE-2/src/dsa/zadanie/csv/Vstup50.csv";
        }


        long start = System.currentTimeMillis();
        HashTable table = new HashTable(velkostPola);
        vkladanieDoTabulky(table,path);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time of inserting " + timeElapsed + "ms...");
        long startSearch = System.currentTimeMillis();
        vyhadavanieVTabulke(table,path);
        long finishSearch = System.currentTimeMillis();
        long timeElapsedSearch = finishSearch - startSearch;
        System.out.println("Time of searching " + timeElapsedSearch + "ms...");
        System.out.println(
                "Inserted nodes:"+ table.pocetVlozeni +
                "\nLenght of array: " + velkostPola +
                "\nFree array indexes: " + table.zistitPocetVolnych() +
                "\nSearched nodes: " + table.pocetNajdeni);

        long startSingle = System.currentTimeMillis();
        if(velkostVstupu == 3) {
            //Evie Villiger,73
            System.out.println("Searching for: Evie Villiger,73" );
            najdenynode=table.getkey("Evie Villiger");
            System.out.println("Loaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek);// +
            //"\nbalance " + najdenynode.balance +
            //" height of node " + najdenynode.height);


        } else if(velkostVstupu == 2){
            System.out.println("Searching for: Alan Parker,86" );
            najdenynode=table.getkey("Alan Parker");
            System.out.println("SLoaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek );// +
            //"\nbalance " + najdenynode.balance +
            //" height of node " + najdenynode.height);

        }else if (velkostVstupu == 1){
            System.out.println("Searching for: Sonya Jenkin, 63 " );
            najdenynode=table.getkey("Sonya Jenkin");
            System.out.println("Loaded node: "+ najdenynode.name +
                    " age " +  najdenynode.vek );// +
            //"\nbalance " + najdenynode.balance +
            //" height of node " + najdenynode.height);
        }

        long finishSingle = System.currentTimeMillis();
        long timeElapsedSingle = finishSingle - startSingle;
        System.out.println("search time 1 node " + timeElapsedSingle + "ms...");
    }

    public static void vkladanieDoTabulky(HashTable table, String path){
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
                table.getkey(values[0]);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

