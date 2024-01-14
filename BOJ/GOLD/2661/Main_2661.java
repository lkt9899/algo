import java.io.*;
import java.util.*;

public class Main_2661 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws Exception {
        input();
        find("", 0);
    }

    static void find(String s, int cnt) {
        for (int i = 1; i <= cnt / 2; i++) {
            if (s.substring(cnt - i, cnt).equals(s.substring(cnt - 2 * i, cnt - i)))
                return;
        }

        if (cnt == N) {
            System.out.println(s);
            System.exit(0);
        }

        find(s + "1", cnt + 1);
        find(s + "2", cnt + 1);
        find(s + "3", cnt + 1);
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
    }
}
