import java.io.*;
import java.util.*;

public class Main_17143 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int R, C, M;

    static int[] di = { 0, -1, 1, 0, 0 };
    static int[] dj = { 0, 0, 0, 1, -1 };
    static HashMap<Integer, Shark>[] pool;
    static PriorityQueue<Integer>[] pos;

    static class Shark {
        int r;
        int c;
        int s;
        int d;
        int z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        solve();
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pool = new HashMap[C + 1];
        pos = new PriorityQueue[C + 1];
        for (int i = 1; i <= C; i++) {
            pool[i] = new HashMap<>();
            pos[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            pool[c].put(r, new Shark(r, c, s, d, z));
            pos[c].add(r);
        }
    }

    static void solve() {
        int myPos = 0;
        int ans = 0;
        while (myPos != C) {
            myPos++;
            ans += fishing(myPos);
            move();
        }
        sb.append(ans + "");
    }

    static int fishing(int myPos) {
        if (pool[myPos].isEmpty())
            return 0;

        int row = pos[myPos].poll();
        return pool[myPos].remove(row).z;
    }

    static void move() {
        Deque<Shark> sharks = new ArrayDeque<>();
        for (int i = 1; i <= C; i++) {
            int pre = 0;
            while (!pos[i].isEmpty()) {
                int row = pos[i].poll();
                if (pre == row)
                    continue;
                Shark shark = pool[i].remove(row);
                shark = getNextPos(shark);
                sharks.add(shark);
                pre = row;
            }
        }

        while (!sharks.isEmpty()) {
            Shark shark = sharks.poll();
            if (pool[shark.c].containsKey(shark.r)) {
                Shark ori = pool[shark.c].remove(shark.r);
                if (ori.z < shark.z)
                    pool[shark.c].put(shark.r, shark);
                else
                    pool[shark.c].put(shark.r, ori);
            } else {
                pool[shark.c].put(shark.r, shark);
                pos[shark.c].add(shark.r);
            }

        }
    }

    static Shark getNextPos(Shark shark) {
        int r = shark.r;
        int c = shark.c;
        int dir = shark.d;
        int speed = shark.s;
        if (speed == 0)
            return shark;

        int ni = r;
        int nj = c;
        while (speed != 0) {
            if (ni == 1 && dir == 1)
                dir++;
            else if (ni == R && dir == 2)
                dir--;
            else if (nj == 1 && dir == 4)
                dir--;
            else if (nj == C && dir == 3)
                dir++;

            ni += di[dir];
            nj += dj[dir];
            speed--;
        }

        return new Shark(ni, nj, shark.s, dir, shark.z);
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
