import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 수빈이가 세상에서 가장 좋아하는 것은 소수이고, 취미는 소수를 가지고 노는 것이다.
 * 요즘 수빈이가 가장 관심있어 하는 소수는 7331이다.
 * 7331은 소수인데, 신기하게도 733도 소수이고, 73도 소수이고, 7도 소수이다.
 * 즉, 왼쪽부터 1자리, 2자리, 3자리, 4자리 수 모두 소수이다! 수빈이는 이런 숫자를 신기한 소수라고 이름 붙였다.
 * 수빈이는 N자리의 숫자 중에서 어떤 수들이 신기한 소수인지 궁금해졌다.
 * N이 주어졌을 때, 수빈이를 위해 N자리 신기한 소수를 모두 찾아보자.
 * 
 * @author 이경태
 *
 */
public class Main_2023 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	// 자리수
	static int N;
	// 맨 앞 자리수는 소수로 시작해야한다.
	static int[] start = {2, 3, 5, 7};
	
	public static void main(String[] args) throws Exception {
		// 입력
		input();
		// 처리
		run();
	}
	
	/**
	 * 자리수 입력
	 * @throws Exception
	 */
	static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		br.close();
	}
	
	/**
	 * 처리
	 * @throws Exception
	 */
	static void run() throws Exception {
		// 소수로 시작하는 N자리수 수를 만든다
		for(int i = 0; i < start.length; i++) {
			make(start[i], N-1);
		}
		
		bw.flush();
		bw.close();
	}
	
	/**
	 * N자리 수 만드는 함수
	 * @param num		// 현재 만들어진 숫자
	 * @param cnt		// 현재 숫자의 자리 수
	 * @throws Exception
	 */
	static void make(int num, int cnt) throws Exception {
		// N자리수 소수가 만들어졌으면 출력
		if(cnt == 0) {
			bw.write(num + "\n");
		}
		// 뒤에 붙는 숫자가 짝수면 소수가 아니기 때문에 홀수만 더해준다.
		for(int i = 1; i < 10; i += 2) {
			int tmp = num * 10 + i;
			// 소수인지 체크하고 소수면 자리 수 증가
			if(isPrime(tmp)) {
				make(tmp, cnt - 1);
			}
		}
	}
	
	/**
	 * 입력받은 수가 소수인지 확인하는 함수
	 * @param num	// 현재 숫자
	 * @return
	 */
	static boolean isPrime(int num) {
		// i로 나눠지는지 체크
		// sqrt(num)까지만 확인하면 된다.
		// 에라토스테네스의 체 참고
		for(int i = 2; i <= (int)Math.sqrt(num); i++) {
			if(num % i == 0)
				return false;
		}
		
		return true;
	}

}
