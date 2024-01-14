import java.io.*;
import java.util.*;

public class Main_1168 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, K;
    static int[] seg = new int[1 << 18];

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder("<");
        run();
    }

    static void run() throws Exception {
        input();
        init(1, 1, N);

        int index = 0;
        for (int size = N; size > 0; size--) {
            index += K;

            if (index % size == 0)
                index = size;
            else if (index > size)
                index %= size;

            int toRemove = query(1, 1, N, index);
            update(1, 1, N, toRemove);

            if (size > 1)
                sb.append(toRemove + ", ");
            else
                sb.append(toRemove + ">\n");

            index--;
        }

        System.out.println(sb);
    }

    static int init(int root, int start, int end) {
        if (start == end)
            return seg[root] = 1;

        int mid = (start + end) >> 1;
        return seg[root] = init(root << 1, start, mid) + init((root << 1) + 1, mid + 1, end);
    }

    static int query(int root, int start, int end, int order) {
        if (start == end)
            return start;

        int mid = (start + end) >> 1;
        if (order <= seg[root << 1])
            return query(root << 1, start, mid, order);
        else
            return query((root << 1) + 1, mid + 1, end, order - seg[root << 1]);
    }

    static int update(int root, int start, int end, int toRemove) {
        seg[root]--;
        if (start == end)
            return 0;

        int mid = (start + end) >> 1;
        if (toRemove <= mid)
            return update(root << 1, start, mid, toRemove);
        else
            return update((root << 1) + 1, mid + 1, end, toRemove);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        br.close();
    }
}
