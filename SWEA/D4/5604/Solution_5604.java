import java.io.*;
import java.util.*;

public class Solution_5604 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long s, e, ans;
    static HashMap<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        // br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            input();
            getAns();
        }
        print();
    }

    static void getAns() throws Exception {
        if (s > 0)
            sb.append(F(e) - F(s - 1)).append("\n");
        else
            sb.append(F(e)).append("\n");
    }

    static long F(long n) {
        if (dp.containsKey(n))
            return dp.get(n);

        if (n < 10) {
            dp.put(n, n * (n + 1) / 2);
            return n * (n + 1) / 2;
        }

        long v = getDigit(n);
        long g = (n / v) * (n % v + 1) + F(n % v);
        long f = F(n - n % v - 1);

        long res = f + g;
        dp.put(n, res);

        return res;
    }

    static long getDigit(long n) {
        long res = 1;
        while (n > 9) {
            res *= 10;
            n /= 10;
        }
        return res;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        s = Long.parseLong(st.nextToken());
        e = Long.parseLong(st.nextToken());
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
