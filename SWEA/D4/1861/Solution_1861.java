import java.io.*;
import java.util.*;

public class Solution_1861 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX = 1000;
    static int N, maxCnt;
    static int ans;
    static int[][] arr = new int[MAX][MAX];
    static int[][] dp = new int[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case + " ");
            run();
        }
        print();
    }

    static void dfs(int x, int y) {
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);

        dq.addLast(new int[] { x, y });
        visited[x][y] = true;
        dp[x][y] = 1;
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int ci = cur[0];
            int cj = cur[1];

            for (int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N || arr[ni][nj] - arr[ci][cj] != 1)
                    continue;

                if (visited[ni][nj] && dp[ni][nj] > dp[ci][cj] + 1)
                    continue;

                visited[ni][nj] = true;
                dp[ni][nj] = dp[ci][cj] + 1;
                dq.addLast(new int[] { ni, nj });
                if (maxCnt < dp[ni][nj] || (maxCnt == dp[ni][nj] && ans > arr[x][y])) {
                    maxCnt = dp[ni][nj];
                    ans = arr[x][y];
                }
            }
        }
    }

    static void run() throws Exception {
        input();
        init();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j])
                    dfs(i, j);
            }
        }
        sb.append(ans + " " + maxCnt + "\n");
        bw.write(sb.toString());
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void print() throws Exception {
        bw.flush();
        br.close();
        bw.close();
    }

    static void init() {
        maxCnt = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 0);
        }
    }
}
