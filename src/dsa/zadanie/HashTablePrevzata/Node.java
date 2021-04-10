package dsa.zadanie.HashTablePrevzata;


class Node
{
    int balance,height,vek;
    Node left, right;
    String name;

    public Node(int vekPerson, String namePerson){
        name = namePerson;
        vek = vekPerson;
        left = right = null;
    }
}
