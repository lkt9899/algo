import java.io.*;
import java.util.*;

public class Solution_14510 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, sum, max, ans;
    static int[] trees;
    static Deque<Integer> odd = new ArrayDeque<>();
    static Deque<Integer> even = new ArrayDeque<>();

    static int[] water = { 2, 1 };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        print();
    }

    static void run() throws Exception {
        input();
        water();
    }

    static void water() {

        int ans = 0;
        while (sum != 0) {
            ans++;
            int wIdx = ans % 2;
            if (wIdx == 0) {
                if (even.isEmpty())
                    pour(odd, wIdx);
                else
                    pour(even, wIdx);
            } else {
                if (odd.isEmpty())
                    pour(even, wIdx);
                else
                    pour(odd, wIdx);
            }
        }

        sb.append(ans + "\n");
    }

    static void pour(Deque<Integer> dq, int wIdx) {
        int len = dq.size();
        while (len-- != 0) {
            int diff = dq.poll();
            int tmp = diff;

            if (sum > 2) {
                if (wIdx == 0) {
                    if (diff > 1) {
                        sum -= water[wIdx];
                        diff -= water[wIdx];
                    }
                } else {
                    sum -= water[wIdx];
                    diff -= water[wIdx];
                }
            } else {
                if (diff == water[wIdx]) {
                    sum -= water[wIdx];
                    diff -= water[wIdx];
                }
            }

            if (diff != 0) {
                if (diff % 2 == 0)
                    even.add(diff);
                else
                    odd.add(diff);
            }

            if (tmp != diff)
                break;
        }
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        trees = new int[N];

        max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (max < trees[i])
                max = trees[i];
        }

        sum = 0;
        for (int i = 0; i < N; i++) {
            int diff = max - trees[i];
            if (diff > 0) {
                sum += diff;
                if (diff % 2 == 0)
                    even.add(diff);
                else
                    odd.add(diff);
            }
        }
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}