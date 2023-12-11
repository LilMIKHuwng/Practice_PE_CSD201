/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xForest, int xFood, int xRate) {
        //You should write here appropriate statements to complete this function.
        if (xForest.charAt(0) != 'A') {
            Rabbit r = new Rabbit(xForest, xFood, xRate);
            Node n = new Node(r);
            if (head == null) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Rabbit x, y;
        x = new Rabbit("X", 1, 2);
        y = new Rabbit("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        insert(x);
        insert(y);

        ftraverse(f);
        f.close();
    }

//==================================================================
    void insert(Rabbit rabbit) {
        Node n = new Node(rabbit);
        if (head == null) {
            head = tail = n;
        } else {
            n.next = head;
            head = n;
        }
    }

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        if (isEmpty()) {
            return;
        } else {

            int maxRate = Integer.MIN_VALUE;
            Node cur = head;
            while (cur != null) {
                if (maxRate < cur.info.rate) {
                    maxRate = cur.info.rate;
                }
                cur = cur.next;
            }
            Node temp = null;
            cur = head;
            while (cur != null) {
                if (cur.info.rate == maxRate) {
                    temp = cur;
                    break;
                }
                cur = cur.next;
            }
            cur = head;
            Node pre = null;
            int count = 0;
            // xoa node
            while (cur != null) {
                if (cur.info.rate == maxRate) {
                    if (pre != null) {
                        pre.next = cur.next;
                    }
                    cur = cur.next;
                }
                pre = cur;
                cur = cur.next;
            }
//              (C,8,6) (D,3,5) (E,5,7) (F,9,6) (G,9,9) (H,6,9) (I,7,8) 
//              (C,8,6) (D,3,5) (G,9,9) (E,5,7) (F,9,6) (H,6,9) (I,7,8)
            int pos = 2;
            int index = 0;
            cur = head;
            pre = null;
            while (cur!=null) {
                if(index==pos){
                    pre.next = temp;
                    temp.next = cur;
                }
                
                index++;
                pre = cur;
                cur = cur.next;
            }

        }

        ftraverse(f);
        f.close();
    }

//==================================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        if (isEmpty()) {
            return;
        } else {
            List<Node> headiing = new ArrayList<>();
            List<Node> ending = new ArrayList<>();
            List<Node> fr = new ArrayList<>();

            Node cur = head;
            int count = 0;
            while (cur != null) {
                if (count <= 3) {
                    headiing.add(cur);
                    count++;
                    cur = cur.next;
                } else {
                    ending.add(cur);
                    count++;
                    cur = cur.next;
                }

            }
            Collections.sort(headiing, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.info.rate - o2.info.rate;
                }
            });
            fr.addAll(headiing);
            fr.addAll(ending);
            clear();

            head = fr.get(0);
            for (int i = 1; i < fr.size(); i++) {
                fr.get(i - 1).next = fr.get(i);
            }
            tail = fr.get(fr.size() - 1);
            tail.next = null;

        }

        ftraverse(f);
        f.close();
    }

}
