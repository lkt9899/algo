import java.io.*;
import java.util.*;

public class Main_11286 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
        int abs1 = Math.abs(o1);
        int abs2 = Math.abs(o2);
        if (abs1 == abs2)
            return o1 > o2 ? 1 : -1;
        return abs1 - abs2;
    });

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) {
                pq.add(num);
            } else {
                if (pq.isEmpty())
                    num = 0;
                else
                    num = pq.poll();
                sb.append(num + "\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}
