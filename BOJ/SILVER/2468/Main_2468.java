import java.io.*;
import java.util.*;

public class Main_2468 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 100;
    static int N, maxL, ans;
    static int[][] arr = new int[MAX][MAX];
    static boolean[][] visited;
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        while (maxL-- >= 0) {
            int max = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] <= maxL || visited[i][j] == true)
                        continue;
                    bfs(i, j);
                    max++;
                }
            }
            ans = ans > max ? ans : max;
        }
        System.out.println(ans);
    }

    static void bfs(int si, int sj) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[si][sj] = true;
        q.add(new int[] { si, sj });
        while (!q.isEmpty()) {
            int ci = q.peek()[0];
            int cj = q.peek()[1];
            q.poll();

            for (int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] == true || arr[ni][nj] <= maxL)
                    continue;

                visited[ni][nj] = true;
                q.add(new int[] { ni, nj });
            }
        }
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (maxL < arr[i][j])
                    maxL = arr[i][j];
            }
        }
        br.close();
    }
}
