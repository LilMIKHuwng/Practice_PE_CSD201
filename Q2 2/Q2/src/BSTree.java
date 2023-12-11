
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    // Default constructor
    BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clear() {
        this.root = null;
    }

    public void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.getInfo() + " ");
        }
    }

    public void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.getInfo() + " ");
        }
    }

    public void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);

            if (r.left != null) {
                q.enqueue(r.left);
            }

            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    public void preOrder(Node p, RandomAccessFile f) throws Exception {

        if (p == null) {
            return;
        }

        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    /**
     * Do NOT modify this method Load 3 lines of data from file: line k (for
     * owner), and line k+1 (for price), and line k+2 (for color)
     *
     * @param k the k-th line where data is started to be loaded
     */
    void loadData(int k) {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        double[] b = Lib.readLineToDoubleArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);

        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]); // insert the new Node(a[i], b[i], c[i]) into the BST
        }
    }

    void helpFunction(int questionNo) throws Exception {
        clear();
        loadData(4 * questionNo - 3);

        String fname = "f" + Integer.toString(questionNo) + ".txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //breadth(root,f);
        postOrder(root, f);

        f.writeBytes("\r\n");

        if (questionNo == 1) {
            postOrder(root, f);
        }

        if (questionNo == 2) {
            bfs_WithConstraint(this.root, f, "o");
        }

        if (questionNo == 3) {
            addOneNode();
            postOrder(root, f);

        }

        if (questionNo == 4) {
            updateTree();
            postOrder(root, f);

        }

        f.writeBytes("\r\n");
        f.close();
    }

    void f1() throws Exception {
        helpFunction(1);
    }

    void f2() throws Exception {
        helpFunction(2);
    }

    void f3() throws Exception {
        helpFunction(3);
    }

    void f4() throws Exception {
        helpFunction(4);
    }

    /**
     * Luy y: 1. SV KHONG su dung tieng Viet co dau trong bai lam de tranh Error
     * khi run chuong trinh. 2. Neu khong tuan thu se nhan diem 0 (khong).
     * Question 2.1: use Laptop’s name as the key attribute when building a BST.
     * implement the 'insert' method that inserts a new Node into the BST if the
     * price is < 20.0. The output of this method will be written into the file
     * 'f1.txt'. Therefore you should open this file to see/test your code
     * output. Example: with the content given in the file 'data.txt', the
     * content of 'f1.txt' after insertion should be (HP,6.5,7) (Dell,18.5,5)
     * (Asus,5.5,4) (Sony,9.5,-7) (Macbook,7.5,6) (Lenovo,8.5,9) (HP,6.5,7)
     * (Dell,18.5,5) (Asus,5.5,4) (Sony,9.5,-7) (Macbook,7.5,6) (Lenovo,8.5,9)
     * @param xName the name of the input Laptop @param xPrice the price of the
     * input Laptop @param xColor the color of the input Laptop
     */
    /**
     * Hint: 1. Use the method compareTo() in Java to lexicographically compare
     * two strings. This method returns an int value (0, positive, or negative).
     * For example: "hello".compareTo("hello") // returns 0
     * "hello".compareTo("Hello") // returns 32 "Hello".compareTo("hello") //
     * returns -32 2. You may implement an auxiliary method (e.g.,
     * insertRec(...)) to recursively insert a new node into the tree. Then the
     * method insert() will call insertRec() like this: void insert(String
     * xName, int xPrice, int xColor) { if (... < 20.0) this.root =
     * insertRec(this.root, ...); }
     *
     * 3. The hint for method insertRec() is given below. If you want to use it,
     * just uncomment the block and fill in the '...' with your code
     */
//    void insert(String xName, int xPrice, int xColor) {
//        if (... < 20.0)
//            this.root = insertRec(this.root, ...);
//    }
//    
//    /**
//     * A recursive function to insert a new node with data (e) into BST 
//     * @param root : the root of the current sub-tree
//     * @param e: data field
//     * @return root node of the tree after insertion
//     */
//    private Node insertRec(Node root, Laptop data){
//        /* If the tree is empty, then return a new node */
//        if (root == ...){ 
//            return new Node(...);
//        }
//        /**
//         * If the string of the data being inserted is less than the string of the current root node,
//         * then traverse to the left node of the current root, 
//         * and set the current left node to whatever gets returned from the insert method
//         */
//        else if (data.getName().compareTo(...) < 0 )
//        {
//              root.... = insertRec(...);
//        }
//        /**
//         * If the value of the data being inserted is greater than the value of the current root node,
//         * then traverse to the right node of the current root, 
//         * and set the current right node to whatever gets returned from the insert method 
//         */
//        else if (... > 0)
//        {
//              root.... = insertRec(...);
//        }
//        else
//        {
//            // This is empty to explicitly state that we do NOT 
//            // allow insert duplicate keys into the tree.
//            
//        }
//        
//        /* return the (unchanged) node pointer */
//        return ...;
//    }
    void insert(String xName, double xPrice, int xColor) {
        //------ Start your code here-------------------------------------------------------

        if (xPrice < 20.0) {
            Laptop lap = new Laptop(xName, xPrice, xColor);

            // If the tree is empty, make the new Bird the root
            if (root == null) {
                root = new Node(lap);
            }

            // Use a recursive helper function to insert the new Bird
            insertRec(root, lap);
        }

        //------ End your code here---------------------------------------------------------
    }

    void insertRec(Node currentNode, Laptop lap) {
        // Compare the new Bird's price with the current node's Bird price
        if (lap.name.compareTo(currentNode.getInfo().name) < 0) {
            // If the new Bird's price is less, go left
            if (currentNode.left == null) {
                // If there's no left child, insert the new Bird as the left child
                currentNode.left = new Node(lap);
            } else {
                // Otherwise, recursively insert in the left subtree
                insertRec(currentNode.left, lap);
            }
        } else if (lap.name.compareTo(currentNode.getInfo().name) > 0) {
            // If the new Bird's price is greater, go right
            if (currentNode.right == null) {
                // If there's no right child, insert the new Bird as the right child
                currentNode.right = new Node(lap);
            } else {
                // Otherwise, recursively insert in the right subtree
                insertRec(currentNode.right, lap);
            }
        }
    }

    /**
     * Question 2.2: Perform breadth-first-search on the BST, but ONLY visit
     * nodes that Laptop's name contains "o". Hint: This method is similar to
     * the method 'breadth' (provided in this class already). You should create
     * a new method which body is similar to 'breadth' for doing BFS but
     * considering only nodes that Laptop's name contains "o". The output f2()
     * will be written into the file "f2.txt". Therefore you should open this
     * file to see/test your code output. Example: With the data provided in
     * "data.txt", the content of 'f2.txt' after running this method is
     * (Acer,9.5,3) (HP,6.5,7) (Dell,18.5,5) (Asus,5.5,4) (Sony,9.5,-7)
     * (Macbook,7.5,6) (Lenovo,8.5,9) (Lenovo,8.5,9) (Macbook,7.5,6)
     * (Sony,9.5,-7)
     */
    private void bfs_WithConstraint(Node p, RandomAccessFile f, String subtring) throws Exception {
        //------ Start your code here-------------------------------------------------------
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.getInfo().name.contains("o")){
                fvisit(r, f);
            }

            if (r.left != null) {
                q.enqueue(r.left);
            }

            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        //------ End your code here---------------------------------------------------------
    } // end bfs_WithConstraint(...)

    /**
     * Question 2.3: Implement the method addOneNode() that inserts into the
     * current tree a new Laptop which name = "Macbook_pro", price = 10.5, color
     * = k, where k is total number of nodes in the tree before insertion Hint:
     * (1) Implement a method to count the tree's nodes (2) Insert the new
     * Laptop("Macbook_pro", 10.5, Number of Tree's Nodes) into the current tree
     * The output f3() will be written into the file "f3.txt". Therefore you
     * should open this file to see/test your code output. Example: With the
     * data given in "data.txt", the content of "f3.txt" after running this
     * method is (Acer,9.5,3) (HP,6.5,7) (Dell,18.5,5) (Asus,5.5,4)
     * (Sony,9.5,-7) (Macbook,7.5,6) (Lenovo,8.5,9) (Acer,9.5,3) (HP,6.5,7)
     * (Dell,18.5,5) (Asus,5.5,4) (Macbook_pro,10.5,7) (Sony,9.5,-7)
     * (Macbook,7.5,6) (Lenovo,8.5,9)
     */
    void addOneNode() {
        //------ Start your code here------------------------------------------------------------
        insert("Macbook_pro", 10.5, countNode(root));
        //------ End your code here--------------------------------------------------------------
    } // end addOneNode()
    
    int countNode(Node node){
        if (node == null){
            return 0;
        }
        
        return countNode(node.left) + countNode(node.right) + 1;
    }

    /**
     * Question 2.4: Increase the Laptop's price by 3.0 if a node in the tree
     * satisfies following conditions: 1. It is a leaf node 2. Laptop's color is
     * less than 8 Hint: Leaf nodes have neither left child nor right child The
     * output f4() will be written into the file 'f4.txt'. Therefore you should
     * open this file to see/test your code output. Example: With the data
     * provided in 'data.txt', the content of 'f4.txt' after running this method
     * is (HP,6.5,7) (Dell,8.5,5) (Sony,9.5,17) (Macbook,7.5,6) (HP,9.5,7)
     * (Dell,8.5,5) (Sony,9.5,17) (Macbook,7.5,6)
     */
    public void updateTree() {
        //------ Start your code here------------------------------------------------------------
        resetTree(root);
        //------ End your code here--------------------------------------------------------------
    } // end updateTree()
    
    void resetTree(Node node){
        if (node == null) return;
        
        if (node.left == null && node.right == null && node.getInfo().color < 8 ){
            node.getInfo().setColor(node.getInfo().color + 3);
        }
        
        resetTree(node.left);
        resetTree(node.right);
    }

} // End BST
