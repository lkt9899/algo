import java.io.*;
import java.util.*;

public class Solution_13501 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void run() throws Exception {
        // init state
        list = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(i, Integer.parseInt(st.nextToken()));
        }

        // cmd line
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx;
            char cmd = st.nextToken().charAt(0);
            switch (cmd) {
                case 'I':
                    idx = Integer.parseInt(st.nextToken());
                    list.add(idx, Integer.parseInt(st.nextToken()));
                    break;

                case 'D':
                    idx = Integer.parseInt(st.nextToken());
                    list.remove(idx);
                    break;

                case 'C':
                    idx = Integer.parseInt(st.nextToken());
                    list.set(idx, Integer.parseInt(st.nextToken()));
                    break;

                default:
                    break;
            }
        }
        if (L > list.size())
            sb.append("-1\n");
        else
            sb.append(list.get(L) + "\n");
    }
}
