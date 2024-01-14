import java.io.*;
import java.util.*;

public class Solution_1233 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int ans, N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case + " ");
            run();
            System.out.println(sb);
        }
    }

    static void run() throws Exception {
        int left, right;
        ans = 1;

        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            char cmd = st.nextToken().charAt(0);

            if (i <= N / 2) {
                if (i == N / 2 && N % 2 == 0)
                    left = Integer.parseInt(st.nextToken());
                else {
                    left = Integer.parseInt(st.nextToken());
                    right = Integer.parseInt(st.nextToken());
                }

                if (cmd >= '0' && cmd <= '9')
                    ans = 0;
            } else {
                if (cmd <= '0' && cmd >= '9')
                    ans = 0;
            }
        }
        sb.append(ans + "");
    }

    static void input() throws Exception {

    }

    static void print() throws Exception {

    }
}
