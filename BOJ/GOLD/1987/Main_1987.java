import java.io.*;
import java.util.*;

public class Main_1987_이경태 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 21;
    static int R, C, ans;
    static char[][] arr = new char[MAX][MAX];
    static boolean[] used = new boolean[26];
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        used[arr[1][1] - 'A'] = true;
        dfs(1, 1, 1);
        System.out.println(ans);
    }

    static void dfs(int si, int sj, int max) {
        ans = max > ans ? max : ans;
        for (int i = 0; i < 4; i++) {
            int ni = si + di[i];
            int nj = sj + dj[i];
            if (check(ni, nj))
                continue;

            used[arr[ni][nj] - 'A'] = true;
            dfs(ni, nj, max + 1);
            used[arr[ni][nj] - 'A'] = false;
        }
    }

    static boolean check(int i, int j) {
        return (i < 1 || i > R || j < 1 || j > C || used[arr[i][j] - 'A'] == true);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 1; j <= C; j++) {
                arr[i][j] = tmp[j - 1];
            }
        }
        br.close();
    }
}