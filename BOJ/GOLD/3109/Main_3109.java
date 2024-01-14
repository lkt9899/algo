import java.io.*;
import java.util.*;

public class Main_3109 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final char EMPTY = '.';
    static final char BUILD = 'x';
    static final char VISIT = 'v';

    static final int R_MAX = 10000;
    static final int C_MAX = 500;

    static int R, C, ans;
    static char[][] map = new char[R_MAX][C_MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        for (int row = 0; row < R; row++) {
            if (check(row, 0))
                ans++;
        }
        System.out.println(ans);
    }

    static boolean check(int row, int col) {
        map[row][col] = VISIT;

        if (col == C - 1)
            return true;

        if (row > 0 && map[row - 1][col + 1] == EMPTY && check(row - 1, col + 1))
            return true;

        if (map[row][col + 1] == EMPTY && check(row, col + 1))
            return true;

        if (row + 1 < R && map[row + 1][col + 1] == EMPTY && check(row + 1, col + 1))
            return true;

        return false;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp[j];
            }
        }
    }
}
