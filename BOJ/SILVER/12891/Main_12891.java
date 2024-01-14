import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 평소에 문자열을 가지고 노는 것을 좋아하는 민호는 DNA 문자열을 알게 되었다.
 * DNA 문자열은 모든 문자열에 등장하는 문자가 {‘A’, ‘C’, ‘G’, ‘T’} 인 문자열을 말한다.
 * 예를 들어 “ACKA”는 DNA 문자열이 아니지만 “ACCA”는 DNA 문자열이다.
 * 이런 신비한 문자열에 완전히 매료된 민호는 임의의 DNA 문자열을 만들고 만들어진 DNA 문자열의 부분문자열을 비밀번호로 사용하기로 마음먹었다.
 * 하지만 민호는 이러한 방법에는 큰 문제가 있다는 것을 발견했다.
 * 임의의 DNA 문자열의 부분문자열을 뽑았을 때 “AAAA”와 같이 보안에 취약한 비밀번호가 만들어 질 수 있기 때문이다.
 * 그래서 민호는 부분문자열에서 등장하는 문자의 개수가 특정 개수 이상이여야 비밀번호로 사용할 수 있다는 규칙을 만들었다.
 * 임의의 DNA문자열이 “AAACCTGCCAA” 이고 민호가 뽑을 부분문자열의 길이를 4라고 하자.
 * 그리고 부분문자열에 ‘A’ 는 1개 이상, ‘C’는 1개 이상, ‘G’는 1개 이상, ‘T’는 0개 이상이 등장해야 비밀번호로 사용할 수 있다고 하자.
 * 이때 “ACCT” 는 ‘G’ 가 1 개 이상 등장해야 한다는 조건을 만족하지 못해 비밀번호로 사용하지 못한다.
 * 하지만 “GCCA” 은 모든 조건을 만족하기 때문에 비밀번호로 사용할 수 있다.
 * 민호가 만든 임의의 DNA 문자열과 비밀번호로 사용할 부분분자열의 길이,
 * 그리고 {‘A’, ‘C’, ‘G’, ‘T’} 가 각각 몇번 이상 등장해야 비밀번호로 사용할 수 있는지 순서대로 주어졌을 때
 * 민호가 만들 수 있는 비밀번호의 종류의 수를 구하는 프로그램을 작성하자.
 * 단 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같다고 하더라도 다른 문자열로 취급한다.
 * 
 * @author 이경태
 *
 */
public class Main_12891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int S, P, ans = 0;			// 문자열 길이, 비밀번호 길이, 유효 갯수
	static String string;				// 문자열
	static char[] chars = {'A', 'C', 'G', 'T'};
	static int[] count = new int[26];	// 충족 조건
	static int[] cnt = new int[26];		// 현재 조건
	
	public static void main(String[] args) throws Exception {
		// 입력
		input();
		// 처리
		run();
	}

	/**
	 * 입력 함수
	 * @throws Exception
	 */
	static void input() throws Exception {
		String[] tmp = br.readLine().split(" ");
		S = Integer.parseInt(tmp[0]);	// 문자열 길이
		P = Integer.parseInt(tmp[1]);	// 비밀번호 길이
		
		string = "B" + br.readLine();	// 문자열 전체 입력
		
		tmp = br.readLine().split(" ");
		for(int i = 0; i < 4; i++) {
			count[chars[i] - 'A'] = Integer.parseInt(tmp[i]);	// 충족 조건 입력
		}
		br.close();
	}
	
	/**
	 * 처리 함수
	 * 0~P-1까지의 카운트를 세주고
	 * 이 후로는 앞에껄 -, 뒤에껄 + 해주는 식으로 조건 확인
	 * @throws Exception
	 */
	static void run() throws Exception {
		// 0~P까지의 부분문자열 체크
		for(int i = 1; i < P; i++) {
			cnt[string.charAt(i) - 'A']++;
		}
		
		// 부분 문자열의 맨 앞 index
		int s = 0;
		for(int e = P; e <= S; e++) {
			cnt[string.charAt(e) - 'A']++;		// 맨 뒤 하나 붙이고
			if(check()) ans++;					// 조건 확인
			cnt[string.charAt(++s) - 'A']--;	// 맨 앞 하나 뺌
		}
		
		bw.write(ans + "");
		bw.flush();
		bw.close();
	}
	
	
	/**
	 * 해당 부분 문자열이 유효한지 확인하는 함수
	 * @return
	 */
	static boolean check() {
		for(int i = 0; i < 4; i++) {
			if(cnt[chars[i] - 'A'] < count[chars[i] - 'A'])		// 최소 갯수보다 현재 갯수가 작으면 false
				return false;
		}
		return true;
	}
}
