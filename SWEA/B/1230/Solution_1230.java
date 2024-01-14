import java.io.*;
import java.util.*;

public class Solution_1230 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = 10;
        long start = System.nanoTime();
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
            sb.append("\n");
        }
        System.out.print(sb);
        long end = System.nanoTime();
        System.out.println((end - start) / 1_000_000.0 + "ms");
        br.close();
    }

    static void run() throws Exception {
        // init state
        list = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        // cmd line
        int C = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            int idx, cnt;
            char cmd = st.nextToken().charAt(0);
            switch (cmd) {
                case 'I':
                    idx = Integer.parseInt(st.nextToken());
                    cnt = Integer.parseInt(st.nextToken());
                    for (int k = 0; k < cnt; k++) {
                        list.add(idx++, Integer.parseInt(st.nextToken()));
                    }
                    break;

                case 'D':
                    idx = Integer.parseInt(st.nextToken());
                    cnt = Integer.parseInt(st.nextToken());
                    for (int k = 0; k < cnt; k++) {
                        list.remove(idx);
                    }
                    break;

                case 'A':
                    cnt = Integer.parseInt(st.nextToken());
                    for (int k = 0; k < cnt; k++) {
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                    break;

                default:
                    break;
            }
        }
        for (int i = 0; i < 10; i++) {
            sb.append(list.get(i) + " ");
        }
    }
}
