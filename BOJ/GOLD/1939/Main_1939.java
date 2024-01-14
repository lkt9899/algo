import java.io.*;
import java.util.*;

public class Main_1939 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static final int INF = Integer.MAX_VALUE;

    static int N, M, start, end;
    static long ans;

    static List<Edge>[] list;
    static int[] dist;

    static class Edge implements Comparable<Edge> {
        int e, c;

        public Edge(int e, int c) {
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return o.c - this.c;
        }
    }
    

    public static void main(String[] args) throws Exception {
        run();
    }
    
    static void run() throws Exception {
        input();
        dijkstra();
        System.out.println(dist[end]);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[start] = INF;
        pq.add(new Edge(start, INF));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.c < dist[cur.e])
                continue;
            
            for (Edge next : list[cur.e]) {
                int nextCost = cur.c < next.c ? cur.c : next.c;
                if (nextCost > dist[next.e]) {
                    dist[next.e] = nextCost;
                    pq.add(new Edge(next.e, nextCost));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        Arrays.fill(dist, 0);

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

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
}
