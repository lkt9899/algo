package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 입력 배열
    static int[] arr = new int[8];
    // 연산 숫자 배열
    static int[] num = { 1, 2, 3, 4, 5 };
    // 위 두 배열의 인덱스
    static int aIdx, nIdx;

    public static void main(String[] args) throws Exception {
        for (int t = 1; t <= 10; t++) {
            // 테케 번호
            t = Integer.parseInt(br.readLine());
            // 인덱스 초기화
            aIdx = nIdx = -1;
            // 입력
            input();
            // 처리
            run();
            // 출력
            print(t);
        }

        br.close();
        bw.close();
    }

    /**
     * 입력 함수
     * 
     * @throws Exception
     */
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++)
            arr[i] = Integer.parseInt(st.nextToken());
    }

    /**
     * 처리 함수
     * 앞에서 빼고 뒤로 넣고를 직접 할 필요 없이
     * 인덱스 별로 연산을 진행 후 0이 나오면 그 이 후부터 출력한다.
     */
    static void run() {
        while (true) {
            aIdx = ++aIdx % 8;
            nIdx = ++nIdx % 5;
            arr[aIdx] -= num[nIdx];
            if (arr[aIdx] <= 0) {
                arr[aIdx] = 0;
                aIdx = ++aIdx % 8;
                break;
            }
        }
    }

    /**
     * 출력 함수
     * 
     * @param t // 테케 번호
     * @throws Exception
     */
    static void print(int t) throws Exception {
        bw.write("#" + t + " ");
        for (int i = 0; i < 8; i++) {
            bw.write(arr[aIdx] + " ");
            aIdx = ++aIdx % 8;
        }
        bw.newLine();
        bw.flush();
    }
}
