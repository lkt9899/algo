import java.io.*;
import java.util.*;

public class Main_10655 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Axis[] checkpointAxis;

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        int idx = findToSkip();
        int sum = 0;
        for (int i = 1; i < N; i++) {
            if (i == idx) {
                i++;
                sum += getDist(checkpointAxis[i], checkpointAxis[i - 2]);
            } else
                sum += getDist(checkpointAxis[i], checkpointAxis[i - 1]);
        }
        System.out.println(sum);
    }

    static int findToSkip() {
        int res = 1;
        int max = 0;
        for (int i = 2; i < N; i++) {
            int dist1 = getDist(checkpointAxis[i], checkpointAxis[i - 2]);
            int dist2 = getDist(checkpointAxis[i - 2], checkpointAxis[i - 1])
                + getDist(checkpointAxis[i - 1], checkpointAxis[i]);
            int diff = Math.abs(dist2 - dist1);
            if (max < diff) {
                max = diff;
                res = i - 1;
            }
        }

        return res;
    }

    static int getDist(Axis s, Axis e) {
        return (Math.abs(s.x - e.x) + Math.abs(s.y - e.y));
    }
    

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        checkpointAxis = new Axis[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            checkpointAxis[i] = new Axis(x, y);
        }
    }
}
