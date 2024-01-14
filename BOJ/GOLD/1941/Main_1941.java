import java.io.*;
import java.util.*;

public class Main_1941 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 5;

    static int ans;

    static char[][] map = new char[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static boolean[] comb = new boolean[1 << 25];

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        input();
        check();
        System.out.println(ans);
    }

    static void check() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                visited[i][j] = true;
                if (map[i][j] == 'S')
                    dfs(1, 0, 1 << (i * MAX + j));
                else
                    dfs(0, 1, 1 << (i * MAX + j));
                visited[i][j] = false;
            }
        }
    }

    static void dfs(int sCnt, int yCnt, int visit) {
        if (yCnt > 3 || comb[visit])
            return;

        comb[visit] = true;
        if (sCnt + yCnt == 7) {
            ans++;
            return;
        }

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (!visited[i][j])
                    continue;

                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
        
                    if (ni < 0 || ni >= MAX || nj < 0 || nj >= MAX || visited[ni][nj])
                        continue;
        
                    visited[ni][nj] = true;
                    if (map[ni][nj] == 'S')
                        dfs(sCnt + 1, yCnt, visit | 1 << (ni * MAX + nj));
                    else
                        dfs(sCnt, yCnt + 1, visit | 1 << (ni * MAX + nj));
                    visited[ni][nj] = false;
                }
            }
        }
    }

    static void input() throws Exception {
        for (int i = 0; i < MAX; i++)
            map[i] = br.readLine().toCharArray();
    }
}
