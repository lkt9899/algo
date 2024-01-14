import java.io.*;

public class Solution_1288 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
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
        int N = Integer.parseInt(br.readLine());

        int bit = 0, cnt = 1;
        while (bit != (1 << 10) - 1) {
            int tmp = N * cnt;
            while (tmp != 0) {
                int used = tmp % 10;
                tmp /= 10;
                bit |= (1 << used);
            }
            cnt++;
        }

        sb.append(N * (cnt - 1) + "\n");
    }

    static void print() throws Exception {

    }
}
