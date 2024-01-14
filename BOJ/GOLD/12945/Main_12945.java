import java.io.*;
import java.util.*;

public class Main_12945 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, max, tar;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        max = arr[N - 1];
    }

    static void run() {
        int mid = N >> 1;
        int left = 0;
        int right = N >> 1;
        int ans = 0;
        while (left < mid && right < N) {
            if (arr[left] * 2 <= arr[right]) {
                ans++;
                left++;
            }
            right++;
        }
        System.out.println(N - ans);
    }
}