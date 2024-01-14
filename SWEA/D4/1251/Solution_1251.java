import java.io.*;
import java.util.*;

public class Solution_1251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final double INF = Double.MAX_VALUE;

    static int N, nodeCnt;
    static double E, ans;

    static int[] X;
    static int[] Y;
    static double[] minEdge;

    static class Edge implements Comparable<Edge> {
        int to;
        double weight;

        public Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        print();
    }

    static void run() throws Exception {
        input();
        init();
        Prim();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        X = new int[N];
        Y = new int[N];
        minEdge = new double[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Y[i] = Integer.parseInt(st.nextToken());
        }

        E = Double.parseDouble(br.readLine());
    }

    static void init() {
        ans = 0;
        nodeCnt = 0;
        for (int i = 0; i < N; i++)
            minEdge[i] = INF;
    }

    static void Prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N];
        pq.add(new Edge(0, 0));
        minEdge[0] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visit[cur.to])
                continue;

            ans += cur.weight;
            visit[cur.to] = true;

            if (++nodeCnt == N)
                break;

            for (int i = 0; i < N; i++) {
                if (i == cur.to)
                    continue;

                double tax = getTax(cur.to, i);
                if (!visit[i] && minEdge[i] > tax) {
                    minEdge[i] = tax;
                    pq.add(new Edge(i, tax));
                }
            }
        }

        sb.append(Math.round(ans) + "\n");
    }

    static double getTax(int startNum, int endNum) {
        double powDist = Math.pow(Math.abs(X[startNum] - X[endNum]), 2)
                + Math.pow(Math.abs(Y[startNum] - Y[endNum]), 2);

        return E * powDist;
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
