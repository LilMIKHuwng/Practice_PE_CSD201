/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
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

    void breadth(Node p, RandomAccessFile f) throws Exception {
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

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xMaker, int xType, int xRadius) {
        //You should insert here statements to complete this function
        if (xMaker.charAt(0) == 'A') {
            return;
        }

        Basket b = new Basket(xMaker, xType, xRadius);
        Node n = new Node(b);
        if (root == null) {
            root = n;
        } else {
            Node pre = null;
            Node cur = root;
            while (cur != null) {
                if (cur.info.radius == n.info.radius) {
                    return;
                }
                if (n.info.radius < cur.info.radius) {
                    pre = cur;
                    cur = cur.left;
                } else {
                    pre = cur;
                    cur = cur.right;
                }
            }

            if (pre != null) {
                if (n.info.radius < pre.info.radius) {
                    pre.left = n;
                } else {
                    pre.right = n;
                }
            }
        }

    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        postOrdera(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void postOrdera(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        postOrdera(p.left, f);
        postOrdera(p.right, f);
        if (p.info.type < 5) {
            fvisit(p, f);
        }
    }

//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
//          (C,6,3) (D,4,2) (E,7,5) (F,2,4) (G,5,7) (H,3,6) 
//          (C,6,3) (D,4,2) (F,2,4) (G,5,7) (H,3,6) 
        
        Node num = null;
        Node cur = root;
        Node pre = null;
        while (cur!=null) {            
            if(cur.info.radius>4){
                num = cur;
                break;
            }
            if(4<cur.info.radius){
                pre = cur;
                cur = cur.left;
            }else{
                pre = cur;
                cur = cur.right;
            }
        }
        deleByCopy(num);
        
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void inOrdera(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrdera(p.left, f);
        fvisit(p, f);
        inOrdera(p.right, f);
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
//        inorderTraversal(root);
        Node pre, cur;
        pre = null;
        cur = root;
        int x = 5;
        while (cur != null) {
            if (cur.info.radius > x) {
                deleByCopy(cur);
                break;
            }
            pre = cur;
            if (x < cur.info.radius) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void inOrderSSS(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        inOrder(p.left, f);
        inOrder(p.right, f);
    }

    void deleByCopy(Node x) {
        Node parent = null;
        Node cur = root;
        while (cur != null) {
            if (cur.info.radius == x.info.radius) {
                break;
            }
            if (x.info.radius < cur.info.radius) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        if (cur == null) {
            return;
        }
        if (cur.left == null && cur.right == null) {
            if (cur == root) {
                root = null;
            } else if (parent.left == cur) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }else if(cur.left != null && cur.right != null){
            Node sucParent = cur;
            Node suc = cur.left;
            while (suc.right!=null) {                
                sucParent = suc;
                suc = suc.right;
            }
            cur.info = suc.info;
            if(sucParent == cur){
                cur.left = suc.left;
            }else{
                sucParent.right = suc.left;
            }
        }else {
            Node child = cur.left != null ? cur.left : cur.right;
            if(root == cur){
                root = child;
            }else if(parent.left==cur){
                parent.left = child;
            }else{
                parent.right = child;
            }
        }
    }

    void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        if (root.info.radius > 4) {
            root = rotateLeft(root);
        }
        inorderTraversal(root.right);
    }

    Node search(Node root) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            if (cur.info.radius > 4) {
                return cur;
            } else if (4 < cur.info.radius) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return null;
    }

    Node rotateLeft(Node k1) {
        Node k2 = k1.left;
        k1.left = k2.right;
        k2.right = k1;
        return k2;
    }

}
