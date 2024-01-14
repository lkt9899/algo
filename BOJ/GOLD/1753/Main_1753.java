import java.io.*;
import java.util.*;

public class Main_1753 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int INF = Integer.MAX_VALUE;

    static int V, E, start;
    static int[] minEdge;
    static List<Edge>[] edgeList;

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        input();
        Dijkstra();
        print();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        minEdge = new int[V + 1];
        for (int i = 1; i <= V; i++)
            minEdge[i] = INF;

        edgeList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            edgeList[i] = new ArrayList<>();

        start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[from].add(new Edge(to, weight));
        }
    }

    static void Dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        minEdge[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            for (Edge next : edgeList[cur.to]) {
                if (minEdge[next.to] > next.weight + cur.weight) {
                    minEdge[next.to] = next.weight + cur.weight;
                    pq.add(new Edge(next.to, minEdge[next.to]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (minEdge[i] == INF)
                sb.append("INF\n");
            else
                sb.append(minEdge[i] + "\n");
        }
    }

    static void print() throws IOException {
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
