import java.io.*;
import java.util.*;

/**
 * Z
 */
public class Main_1074 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 배열의 크기, 찾아야할 좌표 R, C, 정답
    static int N, R, C, ans = 0;

    public static void main(String[] args) throws Exception {
        // 처리
        run();
    }

    // 처리 함수
    static void run() throws Exception {
        // 입력
        input();
        // 좌표 찾기
        find(N, pow(2, N - 1), pow(2, N - 1));
        // 정답 출력
        System.out.println(ans);
    }

    /**
     * 좌표 찾기 함수
     * 해당 배열의 4사분면부터 탐색
     * 
     * @param n  // array size
     * @param tr // pivot r
     * @param tc // pivot c
     */
    static void find(int n, int tr, int tc) {
        // size 1 * 1 종료
        if (n == 0)
            return;
        // 4분할한 배열의 크기
        int mLen = pow(2, n - 1);

        // 찾았으면
        if (tr == R && tc == C) {
            // 분할된 부분의 왼쪽 위는 mLen * mLen * 3이다.
            ans += (mLen * mLen * 3);
            return;
        }
        // 찾는 좌표가 2사분면이면 2사분면의 4사분면으로 이동
        else if (R < tr && C < tc) {
            // 2사분면의 시작 점은 0
            find(n - 1, tr - mLen / 2, tc - mLen / 2);
        }
        // 찾는 좌표가 1사분면이면 1사분면의 4사분면으로 이동
        else if (R < tr && C >= tc) {
            // 1사분면의 시작 점은 mLen * mLen
            ans += (mLen * mLen);
            find(n - 1, tr - mLen / 2, tc + mLen / 2);
        }
        // 찾는 좌표가 3사분면이면 3사분면의 4사분면으로 이동
        else if (R >= tr && C < tc) {
            // 3사분면의 시작 점은 mLen * mLen * 2
            ans += (mLen * mLen * 2);
            find(n - 1, tr + mLen / 2, tc - mLen / 2);
        }
        // 찾는 좌표가 4사분면이면 4사분면의 4사분면으로 이동
        else if (R >= tr && C >= tc) {
            // 4사분면의 시작 점은 mLen * mLen * 3
            ans += (mLen * mLen * 3);
            find(n - 1, tr + mLen / 2, tc + mLen / 2);
        }

    }

    // 제곱 함수
    static int pow(int num, int n) {
        return (int) Math.pow(num, n);
    }

    /**
     * 입력 함수
     * 
     * @throws Exception
     */
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        // 배열 크기
        N = Integer.parseInt(st.nextToken());
        // 찾는 좌표의 R
        R = Integer.parseInt(st.nextToken());
        // 찾는 좌표의 C
        C = Integer.parseInt(st.nextToken());

        br.close();
    }
}
