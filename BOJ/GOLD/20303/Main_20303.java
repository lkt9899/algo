import java.io.*;
import java.util.*;

public class Main_20303 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K, ans;
    static int[] dp;
    static Group[] groups;
    static int[] parents;
    static List<Group> list = new ArrayList<>();

    static class Group {
        int candy;
        int people;

        public Group(int candy, int people) {
            this.candy = candy;
            this.people = people;
        }

        public void add(Group g) {
            this.candy += g.candy;
            this.people += g.people;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        knapsack();
    }

    static void knapsack() {
        for (Group g : list) {
            int candy = g.candy;
            int people = g.people;
            for (int j = K - 1; j >= people; j--) {
                dp[j] = Math.max(dp[j], dp[j - people] + candy);
            }
        }
        System.out.println(dp[K - 1]);
    }

    static int find(int a) {
        if (parents[a] == a)
            return a;

        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB)
            return;

        groups[parentA].add(groups[parentB]);
        parents[parentB] = parentA;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[Math.min(N + 1, 3001)];
        groups = new Group[N + 1];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            groups[i] = new Group(Integer.parseInt(st.nextToken()), 1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            union(A, B);
        }

        for (int i = 1; i <= N; i++) {
            if (parents[i] == i) {
                list.add(groups[i]);
            }
        }
    }
}