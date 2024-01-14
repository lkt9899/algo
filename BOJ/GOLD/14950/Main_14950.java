import java.io.*;
import java.util.*;

public class Main_14950 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int N, M, T;

    static List<Edge>[] list;
    static boolean[] selected;
    static PriorityQueue<Edge> dist = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int end, cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(prim());
    }

    static long prim() {
        long ans = 0;
        dist.add(new Edge(1, T));

        for (int i = 0; i < N; i++) {
            int now = -1, min_dist = INF;
            while (!dist.isEmpty()) {
                Edge cur = dist.poll();
                now = cur.end;
                if (!selected[cur.end]) {
                    min_dist = cur.cost;
                    break;
                }
            }

            if (min_dist == INF)
                return INF;
            ans += min_dist + (T * (i - 1));
            selected[now] = true;
            for (Edge next : list[now])
                dist.add(next);
        }

        return ans;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        selected = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[s].add(new Edge(e, c));
            list[e].add(new Edge(s, c));
        }
    }
}
