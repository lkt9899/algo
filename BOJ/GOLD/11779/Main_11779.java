import java.io.*;
import java.util.*;

public class Main_11779 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int INF = 987654321;

    static int N, M, start, end;
    static List<Edge>[] list;
    static Dist[] dist;

    static class Edge implements Comparable<Edge> {
        int e, c;

        public Edge(int e, int c) {
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }

    static class Dist {
        int prev, c;

        public Dist(int prev, int c) {
            this.prev = prev;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        dijkstra();
        print();
    }

    static void dijkstra() throws Exception {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start].c = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.e == end)
                continue;

            if (cur.c > dist[cur.e].c)
                continue;

            for (Edge next : list[cur.e]) {
                int nextCost = cur.c + next.c;

                if (nextCost < dist[next.e].c) {
                    dist[next.e].prev = cur.e;
                    dist[next.e].c = nextCost;
                    pq.add(new Edge(next.e, nextCost));
                }
            }
        }

        Deque<Integer> dq = new ArrayDeque<>();
        int cur = end;
        while (cur != start) {
            dq.addFirst(cur);
            cur = dist[cur].prev;
        }
        dq.addFirst(start);

        sb.append(dist[end].c + "\n");
        sb.append(dq.size() + "\n");
        for (int n : dq)
            sb.append(n + " ");
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        dist = new Dist[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            dist[i] = new Dist(i, INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[s].add(new Edge(e, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void print() throws Exception {
        System.out.println(sb);
    }
}
