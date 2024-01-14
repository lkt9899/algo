import java.io.*;
import java.util.*;

public class Main_2252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 32001;

    static int N, M;
    static List<Integer>[] list;
    static int[] inDegree;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        topologySort();
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];
        visit = new boolean[N + 1];
        list = new LinkedList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list[A].add(B);
            inDegree[B]++;
        }
        br.close();
    }

    static void topologySort() {
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0)
                dq.add(i);
        }

        while (!dq.isEmpty()) {
            int x = dq.poll();
            visit[x] = true;
            sb.append(x + " ");
            for (int y : list[x]) {
                if (!visit[y] && --inDegree[y] == 0)
                    dq.add(y);
            }
        }
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
