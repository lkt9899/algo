import java.io.*;
import java.util.*;

public class Solution_7465 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        print();
    }

    static void run() throws IOException {
        input();
        makeGroup();
    }

    static void makeGroup() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            unionParent(s, e);
        }
        
        int ans = 0;
        for (int i = 1; i <= N; i++)
            if (parent[i] == i)
                ans++;

        sb.append(ans + "\n");
    }

    static void unionParent(int s, int e) {
        s = find(s);
        e = find(e);

        if(s == e)
            return;
        
        parent[s] = e;
    }

    static int find(int n) {
        if (parent[n] == n)
            return parent[n];

        return parent[n] = find(parent[n]);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;
    }

    static void print() throws IOException {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
