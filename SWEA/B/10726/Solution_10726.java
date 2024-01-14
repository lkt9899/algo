import java.io.*;
import java.util.*;

public class Solution_10726 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < N; i++) {
            if ((M & (1 << i)) == 0) {
                sb.append("OFF\n");
                return;
            }
        }
        sb.append("ON\n");
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
}
