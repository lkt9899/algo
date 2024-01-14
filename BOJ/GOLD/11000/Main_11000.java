import java.io.*;
import java.util.*;

public class Main_11000 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N;
    static PriorityQueue<Class> pq = new PriorityQueue<>();
    static PriorityQueue<Class> ing = new PriorityQueue<>((o1, o2) -> {
        return o1.end - o2.end;
    });

    static class Class implements Comparable<Class> {
        int start, end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(check());
    }

    static int check() {
        int ans = 0;

        while (!pq.isEmpty()) {
            Class c = pq.poll();
            if (ing.isEmpty()) {
                ans++;
                ing.add(c);
            } else {
                if (ing.peek().end <= c.start) {
                    ing.poll();
                } else {
                    ans++;
                }
                ing.add(c);
            }
        }

        return ans;
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new Class(start, end));
        }
    }
}
