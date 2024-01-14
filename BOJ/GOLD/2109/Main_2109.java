import java.io.*;
import java.util.*;

public class Main_2109 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;

    static PriorityQueue<Info> pq = new PriorityQueue<>();
    static Info[] infoArr;

    static class Info implements Comparable<Info> {
        int day;
        int price;

        public Info(int day, int price) {
            this.day = day;
            this.price = price;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(o.price, this.price);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(calc());
    }

    static int calc() {
        int sum = 0;
        int idx = 0;
        for (int day = infoArr[0].day; day > 0; day--) {
            while (idx < N && infoArr[idx].day >= day)
                pq.add(infoArr[idx++]);

            if (!pq.isEmpty())
                sum += pq.poll().price;
        }

        return sum;
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        if (N == 0) {
            System.out.println(0);
            System.exit(0);
        }

        infoArr = new Info[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            infoArr[i] = new Info(d, p);
        }

        Arrays.sort(infoArr, (e1, e2) -> {
            return e2.day - e1.day;
        });
    }
}
