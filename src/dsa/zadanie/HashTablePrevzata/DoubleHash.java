package dsa.zadanie.HashTablePrevzata;

// Java Program to implement hashtable in
// double hashing

// Importing input output classes
import java.io.*;

// Class 1
// Helper Class
// LinkedHashEntry
class ValueEntry {

    // Member variables of the class
    String name;
    int vek;

    // Constructor of this class
    // Parametrized constructor
    ValueEntry(String name, int vek)
    {
        // This keyword refers to current object
        // for assigning values to same object itself
        this.name = name;

        // this operator is pointer which contains location
        // of that container that have key and value pairs
        this.vek = vek;
    }
}

// Class 2
// Helper Class
// HashTable
class HashTable {

    // Member variable of this class
    private int HASH_TABLE_SIZE;
    private int size;
    private ValueEntry[] table;
    private int totalprimeSize;

    // Constructor of this class
    // Parametrized constructor
    public HashTable(int ts)
    {
        // Initializing the member variables
        size = 0;
        HASH_TABLE_SIZE = ts;
        table = new ValueEntry[HASH_TABLE_SIZE];

        // Iterating using for loop over table
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
        totalprimeSize = getPrime();
    }

    // Method 1
    // To check for the prime number
    public int getPrime()
    {
        // Iterating using for loop in reverse order
        for (int i = HASH_TABLE_SIZE - 1; i >= 1; i--) {

            // Initially assigning count to zero
            int cnt = 0;

            // Now, iterating from 2 upto the desired number
            // to be checked by dividing it with all no
            // in betwenn [2 - no]
            for (int j = 2; j * j <= i; j++)

                // If number is divisible
                // means not a prime number
                if (i % j == 0)

                    // So simpy move to next number
                    // to check for divisibility by
                    // incrementing the count variable
                    cnt++;

            // By now number is not divisible
            // hence count holds 0 till last
            if (cnt == 0)

                // It means it is a prime number so
                // return the number as it is a prime number
                return i;
        }

        // Returning a prime number
        return 3;
    }

    // Method 2
    // To get number of key-value pairs
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }

    //
    /* Function to clear hash table */
    public void makeEmpty()
    {
        size = 0;
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }

    int pocetNajdeni;
    // Method 3
    // To get value of a key
    public int getkey(String key)
    {
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);

        while (table[hash1] != null
                && !table[hash1].name.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }

        pocetNajdeni++;
        return table[hash1].vek;
    }

    int pocetVlozeni=0;
    // Method 4
    // To insert a key value pair
    public void insert(String key, int value)
    {

        System.out.println("VKLADAM " + key);
        // checking the size of table and
        // comparing it with users input value
        if (size == HASH_TABLE_SIZE) {

            // Display message
            System.out.println("Table is full");
            return;
        }

        int hashing1 = myhash1(key);
        int hashing2 = myhash2(key);
        while (table[hashing1] != null) {
            hashing1 += hashing2;
            hashing1 %= HASH_TABLE_SIZE;
        }

        table[hashing1] = new ValueEntry(key, value);
        pocetVlozeni++;
        size++;
    }

    // Method 5
    // To remove a key
    public void remove(String key)
    {
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);
        while (table[hash1] != null
                && !table[hash1].name.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }
        table[hash1] = null;
        size--;
    }

    // Method 6
    // Function gives a hash value for a given
    // string basically it is linear probing
    private int myhash1(String y)
    {
        int myhashVal1 = y.hashCode();
        myhashVal1 %= HASH_TABLE_SIZE;
        if (myhashVal1 < 0)
            myhashVal1 += HASH_TABLE_SIZE;
        return myhashVal1;
    }

    // Method 7
    // Remember that the above function namely 'myhash'
    // is used for double hashing

    // Now after linear probing, quadratic probing
    // is used in which two myhash functions are used
    // as it is double chaining
    private int myhash2(String y)
    {
        int myhashVal2 = y.hashCode();
        myhashVal2 %= HASH_TABLE_SIZE;
        if (myhashVal2 < 0)
            myhashVal2 += HASH_TABLE_SIZE;
        return totalprimeSize - myhashVal2 % totalprimeSize;
    }

    // Method 8
    // To print the hash table
    public void printHashTable()
    {
        // Display message
        System.out.println("\nHash Table");

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].name + " "
                        + table[i].vek);
    }
}

// Class 3
// Main class
// Class for DoubleHashingHashTableTest
class GFG {

    // Main driver method
    public static void main(String[] args)
    {
        // Display message
        System.out.println("Hash Table Testing");

        // Creating an object of HashTable
        // in the main() method
        // Custom hashtable of size 100
        // means 100 key-value pairs the
        // above hashtable can hold
        HashTable ht = new HashTable(155000);

        // Inserting custom values to the hashtable
        // that is key and value pairs
        // Custom inputs
        //ht.insert("Jozef Pidík", 12);
        //ht.insert("Peter Gerco", 13);
        //ht.insert("Roman Osadsky", 14);

        vkladanieDoTabulky(ht);
        vyhadavanieVTabulke(ht);
        System.out.println(ht.pocetVlozeni +"   " +   ht.pocetNajdeni);
        // Printing hash table after insertion of
        // key value pairs and calling function
        // which prints out the hashtable.
        //
        // Calling the function as usual
        // with the help of objects

        //ht.getkey("Roman Osadsky");
        //System.out.println( ht.getkey("Roman Osadsky"));

        //ht.printHashTable();
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
                table.getkey(values[0]);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
