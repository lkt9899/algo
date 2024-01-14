import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_9843 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            long N = Long.parseLong(br.readLine()) * 2;
            long ans = (long) Math.sqrt(N);
            if (N != ans * (ans + 1))
                ans = -1;
            sb.append(ans + "\n");
        }

        System.out.print(sb);
        br.close();
    }
}
