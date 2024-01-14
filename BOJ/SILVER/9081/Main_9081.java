import java.io.*;
import java.util.*;

public class Main_9081 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws Exception {
        input();
        run();
        System.out.print(sb);
    }

    static void run() throws Exception {
        for (int i = 0; i < N; i++) {
            np();
        }
    }

    static void np() throws Exception {
        char[] s = br.readLine().toCharArray();
        int len = s.length;
        int i = len - 1;
        while (i > 0 && s[i - 1] >= s[i])
            i--;

        if (i == 0) {
            sb.append(String.valueOf(s) + "\n");                
            return;
        }

        int j = len - 1;
        while (s[i - 1] >= s[j])
            j--;

        char tmp = s[i - 1];
        s[i - 1] = s[j];
        s[j] = tmp;

        int k = len - 1;
        while (i < k) {
            tmp = s[i];
            s[i] = s[k];
            s[k] = tmp;
            i++;
            k--;
        }

        sb.append(String.valueOf(s) + "\n");
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
    }
}