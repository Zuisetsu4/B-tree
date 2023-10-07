package vsu.oop.task1;

public class Btree {
    private BTreeNode root; // Pointer to root node
    private final int t; // Minimum degree

    // Constructor (Initializes tree as empty)
    public Btree(int t)
    {
        this.root = null;
        this.t = t;
    }

    // function to traverse the tree
    public void traverse()
    {
        if (this.root != null) {
            this.root.traverse();
        }
        System.out.println();
    }

    // function to search a key in this tree
    public BTreeNode search(int k)
    {
        if (this.root == null)
            return null;
        else
            return this.root.search(k);
    }

    public void insert(int k) {
        // If tree is empty
        if (root == null)
        {
            // Allocate memory for root
            root = new BTreeNode(t, true);
            root.keys[0] = k;  // Insert key
            root.n = 1;  // Update number of keys in root
        }
        else // If tree is not empty
        {
            // If root is full, then tree grows in height
            if (root.n == 2*t-1)
            {
                // Allocate memory for new root
                BTreeNode s = new BTreeNode(t, false);

                // Make old root as child of new root
                s.C[0] = root;

                // Split the old root and move 1 key to the new root
                s.splitChild(0, root);

                // New root has two children now.  Decide which of the
                // two children is going to have new key
                int i = 0;
                if (s.keys[0] < k) {
                    i++;
                }
                s.C[i].insertNonFull(k);

                // Change root
                root = s;
            }
            else { // If root is not full, call insertNonFull for root
                root.insertNonFull(k);
            }
        }
    }
    public void remove(int k) {
        // If tree is empty
        if (root == null)
        {
            // Allocate memory for root
            root = new BTreeNode(t, true);
            root.keys[0] = k;  // Insert key
            root.n = 1;  // Update number of keys in root
        }
        else // If tree is not empty
        {
            int idx = root.findKey(k);
            // The key to be removed is present in this node
            if (idx < root.n && root.keys[idx] == k) {
                // If the node is a leaf node - removeFromLeaf is called
                // Otherwise, removeFromNonLeaf function is called
                if (root.leaf) {
                    root.removeFromLeaf(idx);
                } else {
                    root.removeFromNonLeaf(idx);
                }
            } else {
                // If this node is a leaf node, then the key is not present in tree
                if (root.leaf) {
                    System.out.println("The key " + k + "is does not exist in the tree\n");
                }
                // The key to be removed is present in the sub-tree rooted with this node
                // The flag indicates whether the key is present in the sub-tree rooted
                // with the last child of this node
                boolean flag = (idx == root.n);
                // If the child where the key is supposed to exist has less that t keys,
                // we fill that child
                if (root.C[idx].n < t) {
                    root.fill(idx);
                }
                // If the last child has been merged, it must have merged with the previous
                // child and so we recurse on the (idx-1)th child. Else, we recurse on the
                // (idx)th child which now has at least t keys
                if (flag && idx > root.n) {
                    root.C[idx - 1].remove(k);
                } else {
                    root.C[idx].remove(k);
                }
            }
        }
    }
}

