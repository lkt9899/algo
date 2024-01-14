import java.io.*;
import java.util.*;

public class Solution_1263 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int INF = 12345678;

    static int N;
    static int[][] arr;
    static long dt, ft1, ft2;

    static class Node implements Comparable<Node> {
        int n, c;

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        sb.append("Dijkstra : " + dt + "ms\n")
                .append("Floid with cond : " + ft1 + "ms\n")
                .append("Floid no cond : " + ft2 + "ms\n");
        print();
    }

    static void run() throws Exception {
        input();
        long start, end;
        start = System.currentTimeMillis();
        DK();
        end = System.currentTimeMillis();
        dt += (end - start);
        start = System.currentTimeMillis();
        FW();
        end = System.currentTimeMillis();
        ft1 += (end - start);
        start = System.currentTimeMillis();
        FW2();
        end = System.currentTimeMillis();
        ft2 += (end - start);
    }

    static void FW() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < N; k++) {
                    if (j == k || i == k)
                        continue;
                    if (arr[j][k] > arr[j][i] + arr[i][k]) {
                        arr[j][k] = arr[j][i] + arr[i][k];
                    }
                }
            }
        }
    }

    static void FW2() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }
    }

    static void DK() {
        int ans = INF;
        for (int i = 0; i < N; i++)
            ans = Math.min(Dijkstra(i), ans);
        sb.append(ans + "\n");
    }

    static int Dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.n] < cur.c)
                continue;

            for (int i = 0; i < N; i++) {
                if (arr[cur.n][i] == 1) {
                    int nextC = arr[cur.n][i] + cur.c;

                    if (nextC < dist[i]) {
                        dist[i] = nextC;
                        pq.add(new Node(i, nextC));
                    }
                }
            }
        }

        int sum = 0;
        for (int s : dist)
            sum += s;

        return sum;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (i != j && arr[i][j] == 0)
                    arr[i][j] = INF;
            }
        }
    }

    static int getAns() {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;
                sum += arr[i][j];
            }
            ans = ans < sum ? ans : sum;
        }
        return ans;
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
