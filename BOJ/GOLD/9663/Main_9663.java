import java.io.*;
import java.util.*;

public class Main_9663 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MAX = 15;
    static int N, ans, full;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        full = ~(1 << N);
        nQueen(0, 0, 0, 0);
        System.out.println(ans);
    }

    static void nQueen(int left, int center, int right, int cnt) {
        if (cnt == N) {
            ans++;
            return;
        }

        left <<= 1;
        right >>= 1;
        int board = (left | center | right);

        if ((full & board) == full) {
            return;
        }

        for (int col = 0; col < N; col++) {
            int tmp = 1 << col;
            if ((board & tmp) == 0)
                nQueen(left | tmp, center | tmp, right | tmp, cnt + 1);
        }
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        br.close();
    }
}
