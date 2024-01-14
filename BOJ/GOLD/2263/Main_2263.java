import java.io.*;
import java.util.*;

public class Main_2263 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 100001;

    static int N;
    static int[] inOrder = new int[MAX];
    static int[] postOrder = new int[MAX];
    static int[] index = new int[MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        getRoot(1, N, 1, N);
        print();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            index[inOrder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRoot(int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight) {
            return;
        }

        int root = postOrder[postRight];
        int rootIdx = index[root];
        int postPivot = postLeft + rootIdx - inLeft;
        sb.append(root + " ");

        getRoot(inLeft, rootIdx - 1, postLeft, postPivot - 1);
        getRoot(rootIdx + 1, inRight, postPivot, postRight - 1);
    }

    static void print() throws Exception {
        System.out.println(sb);
    }
}