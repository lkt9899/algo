import java.io.*;
import java.util.*;

public class Main_9205 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static final String P = "happy\n";
    static final String NP = "sad\n";

    static String ans;

    static int N;

    static Axis[] convs;
    static Axis home, fest;

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDist(Axis b) {
            return Math.abs(this.x - b.x) + Math.abs(this.y - b.y);
        }

        public boolean calc(Axis b) {
            return getDist(b) <= 1000;
        }
    }
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            sb.append(bfs() ? P : NP);
        }
        print();
    }

    static boolean bfs() {
        Deque<Axis> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        dq.add(home);
        while (!dq.isEmpty()) {
            Axis cur = dq.poll();

            if (cur.calc(fest)) {
                return true;
            }

            for (int next = 0; next < N; next++) {
                if (cur == convs[next])
                    continue;

                if (!cur.calc(convs[next]) || visited[next])
                    continue;

                visited[next] = true;
                dq.add(convs[next]);
            }
        }
        
        return false;
    }
    
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        convs = new Axis[N];

        home = getAxis();
        for (int i = 0; i < N; i++)
            convs[i] = getAxis();
        fest = getAxis();
    }

    static Axis getAxis() throws IOException {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Axis(x, y);
    }

    static void print() throws IOException {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}