import java.io.*;
import java.util.*;

public class Main_1744 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MAX = 50;

    static int N, ans, pi, mi;
    static Integer[] pls = new Integer[MAX];
    static Integer[] mns = new Integer[MAX];

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        for (int i = 0; i < mi; i += 2) {
            if (i == mi - 1) {
                ans += mns[i];
                break;
            }

            ans += mns[i] + mns[i + 1] > mns[i] * mns[i + 1] ? mns[i] + mns[i + 1] : mns[i] * mns[i + 1];
        }

        for (int i = 0; i < pi; i += 2) {
            if (i == pi - 1) {
                ans += pls[i];
                break;
            }

            ans += pls[i] + pls[i + 1] > pls[i] * pls[i + 1] ? pls[i] + pls[i + 1] : pls[i] * pls[i + 1];
        }

        System.out.println(ans);
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num < 1)
                mns[mi++] = num;
            else
                pls[pi++] = num;
        }
        Arrays.sort(mns, 0, mi);
        Arrays.sort(pls, 0, pi, Collections.reverseOrder());
    }
}