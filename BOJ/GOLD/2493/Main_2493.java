import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 500001;
    static int[] ans = new int[MAX];

    static int N;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        print();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Stack<int[]> s = new Stack<>();
        for (int i = 1; i <= N; i++) {
            int h = Integer.parseInt(st.nextToken());
            if (!s.empty()) {
                while (!s.empty()) {
                    if (s.peek()[1] < h) {
                        s.pop();
                    } else {
                        ans[i] = s.peek()[0];
                        break;
                    }
                }
            } else {
                ans[i] = 0;
            }
            s.add(new int[] { i, h });
        }
    }

    static void print() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i] + " ");
        }
        System.out.println(sb);
    }
}
