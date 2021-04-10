package dsa.zadanie.BinaryTree;

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

    public int getVek() {
        return vek;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }
}