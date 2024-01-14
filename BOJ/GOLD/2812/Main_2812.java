import java.io.*;
import java.util.*;

public class Main_2812 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static char[] num;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!dq.isEmpty() && K > 0 && dq.peekLast() < num[i]) {
                dq.pollLast();
                K--;
            }
            dq.add(num[i]);
        }

        for (char c : dq)
            sb.append(c);
        System.out.println(sb.toString()
                .substring(0, sb.length() - K));
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = br.readLine().toCharArray();
    }
}
