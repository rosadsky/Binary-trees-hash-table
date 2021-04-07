package dsa.zadanie.redblack;

import java.util.*;

class GFG{
    static class node {
        int vek;
        String name;
        node left, right;
    };

    static node newNode(int key,String name) {
        node Node = new node();
        Node.vek = key;
        Node.name = name;
        Node.left = Node.right = null;
        return (Node);
    }

    static node rightRotate(node x) {
        node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    static node leftRotate(node x) {
        node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    static node splay(node root, int vek,String name)
    {
        // Base cases: root is null or
        // key is present at root
        if (root == null || root.name == name)
            return root;

        // Key lies in left subtree

        //s1 == s2 :0
        //s1 > s2   :positive value
        //s1 < s2   :negative value

        //System.out.println(s1.compareTo(s2))

        if (root.key > key)
        {
            // Key is not in tree, we are done
            if (root.left == null) return root;

            // Zig-Zig (Left Left)
            if (root.left.key > key) {
                // First recursively bring the
                // key as root of left-left
                root.left.left = splay(root.left.left, key);

                // Do first rotation for root,
                // second rotation is done after else
                root = rightRotate(root);
            }
            else if (root.left.key < key) // Zig-Zag (Left Right)
                {
                // First recursively bring
                // the key as root of left-right
                root.left.right = splay(root.left.right, key);

                // Do first rotation for root.left
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            // Do second rotation for root
            return (root.left == null)? root: rightRotate(root);
        }
        else // Key lies in right subtree
        {
            // Key is not in tree, we are done
            if (root.right == null) return root;

            // Zig-Zag (Right Left)
            if (root.right.key > key)
            {
                // Bring the key as root of right-left
                root.right.left = splay(root.right.left, key);

                // Do first rotation for root.right
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            else if (root.right.key < key)// Zag-Zag (Right Right)
            {
                // Bring the key as root of
                // right-right and do first rotation
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }

            // Do second rotation for root
            return (root.right == null)? root: leftRotate(root);
        }
    }

    // Function to insert a new key k
// in splay tree with given root
    static node insert(node root, int k)
    {
        // Simple Case: If tree is empty
        if (root == null) return newNode(k);

        // Bring the closest leaf node to root
        root = splay(root, k);

        // If key is already present, then return
        if (root.key == k) return root;

        // Otherwise allocate memory for new node
        node newnode = newNode(k);

        // If root's key is greater, make
        // root as right child of newnode
        // and copy the left child of root to newnode
        if (root.key > k)
        {
            newnode.right = root;
            newnode.left = root.left;
            root.left = null;
        }

        // If root's key is smaller, make
        // root as left child of newnode
        // and copy the right child of root to newnode
        else
        {
            newnode.left = root;
            newnode.right = root.right;
            root.right = null;
        }

        return newnode; // newnode becomes new root
    }

    // A utility function to print
// preorder traversal of the tree.
// The function also prints height of every node
    static void preOrder(node root)
    {
        if (root != null)
        {
            System.out.print(root.key+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /* Driver code*/
    public static void main(String[] args)
    {
        node root = newNode(1);

        for (int i = 2; i < 500000; i++){
            root = insert(root,i);
        }

        System.out.print("Preorder traversal of the modified Splay tree is \n");
        //preOrder(root);
    }
}


// This code is contributed by Rajput-Ji

