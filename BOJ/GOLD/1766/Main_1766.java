import java.io.*;
import java.util.*;

public class Main_1766 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static List<Integer>[] list;
    static int[] degree;

    public static void main(String[] args) throws Exception {
        input();
        topologySort();
        System.out.println(sb);
    }

    static void topologySort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0)
                pq.add(i);
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");

            if (list[cur] == null)
                continue;
            for (int next : list[cur]) {
                degree[next]--;

                if (degree[next] == 0)
                    pq.add(next);
            }
        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        degree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (list[A] == null)
                list[A] = new ArrayList<>();

            list[A].add(B);
            degree[B]++;
        }
    }
}