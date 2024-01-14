import java.io.*;
import java.util.*;

public class Solution_5658 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static char[] str;
    static HashMap<String, Integer> map = new HashMap<>();
    static List<Integer> ans = new ArrayList<>();

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
        init();
        input();
        getCases();
        sb.append(getAns() + "\n");
    }

    static void init() {
        map.clear();
        ans.clear();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        str = br.readLine().toCharArray();
    }

    static void getCases() {
        int cnt = N / 4;
        String tmp = "";
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < N; j++) {
                tmp += str[(i + j) % N];
                if (j % cnt == cnt - 1) {
                    if (!map.containsKey(tmp)) {
                        int val = Integer.parseInt(tmp, 16);
                        map.put(tmp, val);
                        ans.add(val);
                    }
                    tmp = "";
                }
            }
        }
    }

    static int getAns() {
        Collections.sort(ans, Collections.reverseOrder());
        return ans.get(K - 1);
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
