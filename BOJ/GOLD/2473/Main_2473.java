import java.io.*;
import java.util.*;

/**
 * 용액들 중 3개를 골라 합이 0에 제일 가까운 경우의 수 찾기
 * 정답은 용액의 값을 오름 차순으로 정렬 후 출력
 */
public class Main_2473 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 최소값 초기화
    static long min = 3_000_000_001L;
    // 용액의 수
    static int N;
    // 용액 배열
    static long[] solutions;
    // 정답 인덱스 배열
    static int[] ans = new int[3];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        // 입력
        input();
        // 탐색
        find();
        // 출력
        print();
    }

    /**
     * 탐색 함수
     * left = 0, right = N-1로 초기화 후
     * mid를 1~N-2까지 옮기며 연산
     */
    static void find() {
        // mid 1부터 N-2까지
        // ex) 5개면 0 1 2 3 4에서 1~3
        // 0, 4는 left, right의 시작점
        for (int mid = 1; mid < N - 1; mid++) {
            // left right 초기화
            int left = 0, right = N - 1;
            // left는 mid보다 작고, right는 mid보다 커야한다.
            while (left < mid && mid < right) {
                // 각각 인덱스 별 용액을 더한 값
                long val = sum(left, mid, right);
                // 절대값
                long a_val = Math.abs(val);

                // 최소 값 갱신
                if (min > a_val) {
                    min = a_val;
                    // 갱신 후 그 때 사용한 용액의 인덳스 저장
                    ans[0] = left;
                    ans[1] = mid;
                    ans[2] = right;
                }

                // 용액의 합이 음수면 커져야하므로 left++
                if (val < 0)
                    left++;
                // 용액의 합이 양수면 작아져야하므로 right--
                else
                    right--;
            }
        }
    }

    /**
     * 용액의 합 구하는 함수
     * 
     * @param l : 첫 번째 용액의 인덱스
     * @param m : 두 번째 용액의 인덱스
     * @param r : 세 번째 용액의 인덱스
     * @return : 용액의 합
     */
    static long sum(int l, int m, int r) {
        return solutions[l] + solutions[m] + solutions[r];
    }

    // 입력
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        // 용액 배열 초기화
        solutions = new long[N];

        // 용액 값 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }

        // 용액 배열 오름차순 정렬
        Arrays.sort(solutions);
    }

    /**
     * 출력 함수
     * 
     * @throws Exception
     */
    static void print() throws Exception {
        // 정답 출력
        for (int n : ans)
            sb.append(solutions[n] + " ");
        System.out.println(sb);
    }
}