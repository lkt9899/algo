import java.io.*;
import java.util.*;

public class Main_19637 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static Style[] styles;

    static class Style implements Comparable<Style> {
        int limit;
        String style;

        public Style(int limit, String style) {
            this.limit = limit;
            this.style = style;
        }

        @Override
        public int compareTo(Style o) {
            return this.limit - o.limit;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
        System.out.println(sb);
    }

    static void run() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        styles = new Style[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String style = st.nextToken();
            int limit = Integer.parseInt(st.nextToken());

            styles[i] = new Style(limit, style);
        }

        Arrays.sort(styles);
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            int idx = findIdx(0, N - 1, num);
            sb.append(styles[idx].style + "\n");
        }
    }

    static int findIdx(int start, int end, int tar) {
        if(start == end)
            return start;

        int mid = (start + end) >> 1;
        if(styles[mid].limit < tar)
            return findIdx(mid + 1, end, tar);
        else
            return findIdx(start, mid, tar);
    }
}