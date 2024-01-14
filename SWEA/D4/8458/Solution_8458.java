import java.io.*;
import java.util.*;

public class Solution_8458 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static Deque<Integer> dq = new ArrayDeque<>();
    static int N, max, idx;
    static int[] dist;
    static boolean[] flag;
    
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
        check();
        sb.append(idx).append("\n");
    }

    static void check() {
        if (idx < 1)
            return;

        while (true) {
            max -= idx;
            if (max <= 0 && max % 2 == 0)
                break;
            idx++;
        }
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        dist = new int[N];
        flag = new boolean[2];
        
        max = -1;
        idx = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
            if(d == 0)
                continue;
            
            max = Math.max(max, d);
            if(!flag[d % 2])
                flag[d % 2] = true;
        }

        if (max == -1)
            idx = 0;

        if((flag[0] & flag[1]))
            idx = -1;
    }
    
    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
