import java.io.*;
import java.util.*;

public class Main_2668 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static final int MAX_N = 101;

    static int N;
    static int[] nums = new int[MAX_N];
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static Deque<Integer> dq;
    static boolean[] visited = new boolean[MAX_N];;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }
    
    static void run() throws Exception {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dq = new ArrayDeque<>();
                dfs(i, i);
            }
        }
        print();
    }

    static void dfs(int start, int cur) {
        visited[cur] = true;
        dq.add(cur);

        if (visited[nums[cur]]) {
            if (start == nums[cur]) {
                while (!dq.isEmpty()) {
                    pq.add(dq.poll());
                }
                return;
            }
            visited[cur] = false;
            return;
        }
        
        dfs(start, nums[cur]);
        visited[cur] = false;
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
    }
    
    static void print() throws Exception {
        sb.append(pq.size() + "\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll() + "\n");
        }
        System.out.println(sb);
    }
}