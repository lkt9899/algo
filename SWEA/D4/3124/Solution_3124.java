import java.io.*;
import java.util.*;

public class Solution_3124 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E;
    static Edge[] edgeList;
    static int[] parents;

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
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
        print();
    }

    static void run() throws Exception {
        input();
        make();
        solve();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        edgeList = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList, (o1, o2) -> {
            return o1.weight - o2.weight;
        });
    }

    static void make() {
        for (int i = 1; i <= V; i++)
            parents[i] = i;
    }

    static void solve() {
        long result = 0;
        int count = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                if (++count == V - 1)
                    break;
            }
        }
        sb.append(result + "\n");
    }

    static int find(int node) {
        if (parents[node] == node)
            return node;

        return parents[node] = find(parents[node]);
    }

    static boolean union(int p, int c) {
        int rootP = find(p);
        int rootC = find(c);
        if (rootP == rootC)
            return false;

        parents[rootC] = rootP;
        return true;
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
