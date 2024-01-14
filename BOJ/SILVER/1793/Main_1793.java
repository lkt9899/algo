import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main_1793 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static final int MAX = 251;
    static final BigInteger TWO = new BigInteger("2");

    static int N;
    static BigInteger[] dp = new BigInteger[MAX];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");

        String str = br.readLine();
        while (str != null) {
            N = Integer.parseInt(str);
            bw.write(get(N) + "\n");
            str = br.readLine();
        }
        bw.flush();
    }

    static BigInteger get(int n) {
        if (n < 2 || dp[n] != null)
            return dp[n];

        return dp[n] = get(n - 1).add(get(n - 2).multiply(TWO));
    }
}
