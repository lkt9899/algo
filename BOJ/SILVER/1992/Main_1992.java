import java.io.*;
import java.util.*;

public class Main_1992_이경태 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 64;
    static int N;
    static char[][] arr = new char[MAX][MAX];
    static String ans;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        ans = check(0, 0, N);
        System.out.println(ans);
    }

    static String check(int x, int y, int size) {
        if (size == 1)
            return arr[x][y] + "";

        String tmp = "";
        for (int i = x; i < x + size; i += size / 2) {
            for (int j = y; j < y + size; j += size / 2) {
                tmp += check(i, j, size / 2);
            }
        }

        if (tmp.equals("1111")) {
            tmp = "1";
        } else if (tmp.equals("0000")) {
            tmp = "0";
        } else {
            tmp = "(" + tmp + ")";
        }

        return tmp;
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            char[] nums = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                arr[i][j] = nums[j];
            }
        }

        br.close();
    }
}
