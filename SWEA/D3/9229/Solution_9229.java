import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_9229 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX = 1000;
    static int ans, N, M;
    static List<Integer> weight = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case + " ");
            run();
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static void run() throws Exception {
        input();
        solve();
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        Collections.reverse(weight);
        int left = 0, right = N - 1;
        int max = -1;
        while (left < right) {
            int sum = weight.get(left) + weight.get(right);
            if (sum > M) {
                left++;
            } else {
                max = max < sum ? sum : max;
                if (max == M)
                    break;
                right--;
            }
        }
        ans = max;
    }

    static void print() throws Exception {
        sb.append(ans + "\n");
        bw.write(sb.toString());
    }
}
