import java.io.*;
import java.util.*;

public class Solution_1238 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 101;

    static int N, start;
    static List<Integer>[] list = new ArrayList[MAX];
    static List<Integer> last;
    static boolean[] visit = new boolean[MAX];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }

        print();
    }

    static void run() throws Exception {
        input();
        bfs(start);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        for (int i = 1; i < MAX; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N / 2; i++) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[from].add(to);
        }
    }

    static void bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        Arrays.fill(visit, false);
        visit[start] = true;
        dq.add(start);

        while (!dq.isEmpty()) {
            int len = dq.size();
            last = new ArrayList<>();
            while (len-- > 0) {
                int cur = dq.poll();
                last.add(cur);

                for (int next : list[cur]) {
                    if (!visit[next]) {
                        visit[next] = true;
                        dq.add(next);
                    }
                }
            }
        }

        Collections.sort(last, Collections.reverseOrder());
        sb.append(last.get(0) + "\n");
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}