import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 도영이는 짜파구리 요리사로 명성을 날렸었다. 이번에는 이전에 없었던 새로운 요리에 도전을 해보려고 한다.
 * 지금 도영이의 앞에는 재료가 N개 있다. 도영이는 각 재료의 신맛 S와 쓴맛 B를 알고 있다.
 * 여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다.
 * 시거나 쓴 음식을 좋아하는 사람은 많지 않다. 도영이는 재료를 적절히 섞어서 요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다.
 * 또, 물을 요리라고 할 수는 없기 때문에, 재료는 적어도 하나 사용해야 한다.
 * 재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램을 작성하시오.
 * @author 이경태
 *
 */
public class Main_2961 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static final int MAX = 10;			// 최대 재료 수
	static int N;						// 재료 수
	static int[] sour = new int[MAX];	// 신맛
	static int[] bitter = new int[MAX];	// 쓴맛
	static int ans = Integer.MAX_VALUE;	// 최소 차이
	
	public static void main(String[] args) throws Exception {
		// 입력
		input();
		// 처리
		run();
	}
	
	/**
	 * 재료의 수와 각 재료의 신 맛과 쓴 맛을 입력받는 함수
	 * @throws Exception
	 */
	static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			sour[i] = Integer.parseInt(s[0]);	// i번째 재료의 신맛
			bitter[i] = Integer.parseInt(s[1]);	// i번째 재료의 쓴맛			
		}
		br.close();
	}
	
	/**
	 * 재료 섞기
	 * 조합 가능한 모든 경우의 수를 연산
	 * @throws Exception
	 */
	static void run() throws Exception {
		for(int i = 0; i < N; i++) {
			mix(i, sour[i], bitter[i]);
		}
		
		bw.write(ans + "");
		bw.flush();
		bw.close();
	}
	
	/**
	 * 조합 재귀 함수
	 * N개의 재료에서 1개 이상의 재료를 조합하는 모든 경우의 수를 탐색하면서
	 * 차이가 최소인 경우를 찾는다.
	 * @param idx	현재까지 사용한 재료의 index
	 * @param s		재료들을 섞은 음식의 신 맛
	 * @param b		재료들을 섞은 음식의 쓴 맛
	 */
	static void mix(int idx, int s, int b) {
		ans = Math.min(ans, Math.abs(b - s));	// 신맛과 쓴맛의 최소 차이
		// 0~i까지 섞은 음식에 그 다음 i+1, i+2... 재료들을 순서대로 섞어본다.
		for(int i = idx + 1; i < N; i++) {
			mix(i, s * sour[i], b + bitter[i]);
		}
	}
}
