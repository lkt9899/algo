import java.io.*;
import java.util.*;

public class Solution_5643 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 501;

    static int N, M;
    static Node[] nodeList = new Node[MAX];

    static class Node {
        List<Integer> shortList;
        List<Integer> tallList;

        public Node() {
            shortList = new ArrayList<>();
            tallList = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
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
        sb.append(getAns() + "\n");
    }

    static void init() {
        for (int i = 1; i <= N; i++)
            nodeList[i] = new Node();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodeList[from].tallList.add(to);
            nodeList[to].shortList.add(from);
        }
    }

    static int getAns() {
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            int s = getShort(i, new boolean[N + 1]);
            int t = getTall(i, new boolean[N + 1]);
            if (s + t == N - 1)
                ans++;
        }

        return ans;
    }

    static int getShort(int to, boolean[] visited) {
        int cnt = 0;
        for (int n : nodeList[to].shortList) {
            if (!visited[n]) {
                visited[n] = true;
                cnt += getShort(n, visited) + 1;
            }
        }

        return cnt;
    }

    static int getTall(int from, boolean[] visited) {
        int cnt = 0;
        for (int n : nodeList[from].tallList) {
            if (!visited[n]) {
                visited[n] = true;
                cnt += getTall(n, visited) + 1;
            }
        }
        return cnt;
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
