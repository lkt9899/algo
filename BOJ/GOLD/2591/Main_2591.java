import java.util.*;
import java.io.*;

public class Main_2591 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static final int MAX = 40;

    static String sss;
    static int size;
    static int[] nums;
    static int[] dp = new int[MAX];

    public static void main(String[] args) throws Exception {
        run();
    }
    
    static void run() throws Exception {
        input();
        System.out.println(get(0));
    }
    
    static void input() throws Exception {
        sss = br.readLine();
        char[] s = sss.toCharArray();
        size = s.length;

        nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = s[i] - '0';
        }

        Arrays.fill(dp, -1);
    }

    static int get(int idx) {
        if(idx == size)
            return 1;

        if(dp[idx] != -1)
            return dp[idx];
        
        if (nums[idx] == 0)
            return 0;

        dp[idx] = 0;
        int tmp1 = 0;
        int tmp2 = 0;
        if (idx < size - 1) {
            int ten = 10 * nums[idx];
            int one = nums[idx + 1];
            if (ten + one <= 34) {
                tmp2 += get(idx + 2);
            }
        }
        tmp1 += get(idx + 1);

        dp[idx] += (tmp1 + tmp2);
        // System.out.printf("dp[%s] = dp[%s] + dp[%s]\n", sss.substring(0, idx), (idx + 1 < size ? sss.substring(0, idx + 1) : "over"), (idx + 2 < size ? sss.substring(0, idx + 2) : "over"));
        // System.out.printf("dp[%s] = %d + %d\n", sss.substring(0, idx), tmp1, tmp2);
        // System.out.printf("dp[%s] = %d\n", sss.substring(0, idx), dp[idx]);
        // System.out.println("=====================================================");

        return dp[idx];
    }
}
