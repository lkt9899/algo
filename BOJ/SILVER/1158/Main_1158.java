import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main_1158 {
    static int N, K;
    static DLinkedList list = new DLinkedList();

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        for (int i = 1; i <= N; i++) {
            list.addLast(i);
        }
    }

    static void run() throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("<");
        while (list.size() > 1) {
            Node tmp = list.head;

            for (int i = 1; i < K; i++) {
                tmp = tmp.next;
            }
            list.head = tmp.next;
            bw.write(list.removeNode(tmp) + ", ");
        }

        bw.write(list.head.data + ">");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public static class DLinkedList {
        private Node head, tail;
        private int size;

        public DLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public void addLast(int data) {
            Node node = new Node(data);
            if (size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                node.prev = this.tail;
                this.tail.next = node;
                node.next = this.head;
                this.head.prev = node;
                this.tail = node;
            }
            size++;
        }

        public int removeNode(Node remove) {
            if (this.size < 0)
                return -1;

            Node left = remove.prev;
            Node right = remove.next;

            if (size > 1) {
                left.next = right;
                right.prev = left;
                size--;
                return remove.data;
            } else {
                return this.head.data;
            }
        }

        public int size() {
            return this.size;
        }
    }

    public static class Node {
        Node next = null;
        Node prev = null;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }
}