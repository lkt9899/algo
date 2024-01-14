import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1228 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    static LinkedList list = new LinkedList();
    static Node cur;

    public static void main(String[] args) throws Exception {
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case + " ");
            run();
        }
    }

    static void run() throws Exception {
        init();
        input();
        print();
    }

    static void init() throws Exception {
        int N = Integer.parseInt(br.readLine());
        cur = list.head;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cur = list.addNode(cur, num);
        }
    }

    static void input() throws Exception {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String cmd = st.nextToken();

            int x = Integer.parseInt(st.nextToken());
            cur = list.head;
            while (x > 0) {
                cur = cur.next;
                x--;
            }

            int y = Integer.parseInt(st.nextToken());
            for (int j = 0; j < y; j++) {
                int data = Integer.parseInt(st.nextToken());
                cur = list.addNode(cur, data);
            }
        }
    }

    static void print() {
        cur = list.head.next;
        for (int i = 0; i < 10; i++) {
            sb.append(cur.data + " ");
            cur = cur.next;
        }
        System.out.println(sb);
    }

    static class Node {
        Node next = null;
        Node prev = null;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    static class LinkedList {
        private Node head = new Node(0);

        public Node addNode(Node cur, int data) {
            Node node = new Node(data);
            if (cur.next == null) {
                cur.next = node;
                node.prev = cur;
            } else {
                cur.next.prev = node;
                node.next = cur.next;
                cur.next = node;
                node.prev = cur;
            }

            return node;
        }
    }
}
