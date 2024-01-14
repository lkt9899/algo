# [[SWEA D3] 1225. 암호 생성기](https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYmrJ936XWMDFAUe&contestProbId=AV14uWl6AF0CFAYD&probBoxId=AYmwJW1aqZ4DFAUe+&type=PROBLEM&problemBoxTitle=Daily05&problemBoxCnt=++2+)

### 문제 설명

```
다음 주어진 조건에 따라 n개의 수를 처리하면 8자리의 암호를 생성할 수 있다.
1. 	8개의 숫자를 입력 받는다.
2. 	첫 번째 숫자를 1 감소한 뒤, 맨 뒤로 보낸다. 
3. 	다음 첫 번째 수는 2 감소한 뒤 맨 뒤로, 그 다음 첫 번째 수는 3을 감소하고 맨 뒤로, 그 다음 수는 4, 그 다음 수는 5를 감소한다. 
	이와 같은 작업을 한 사이클이라 한다.
4.	숫자가 감소할 때 0보다 작아지는 경우 0으로 유지되며, 프로그램은 종료된다. 이 때의 8자리의 숫자 값이 암호가 된다.
```

### 입력

```
각 테스트 케이스의 첫 줄에는 테스트 케이스의 번호가 주어지고,
그 다음 줄에는 8개의 데이터가 주어진다.
```   

### 출력

```
#부호와 함께 테스트케이스의 번호를 출력하고, 공백 문자 후 테스트 케이스의 답을 출력한다.
```

### 문제접근

```
1 2 3 4 5를 배열의 데이터에 순차적으로 연산해준 후
최초로 0이 나오는 인덱스의 다음 인덱스부터 출력한다.
```

## 코드 설명

### Main

```java
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
```

### 입력

```java
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
```

### run()

```java
static void run() {
	while (true) {
		aIdx = ++aIdx % 8;		// %로 인덱스 넘어가는거 방지
		nIdx = ++nIdx % 5;		// 
		arr[aIdx] -= num[nIdx];	// 연산
		if (arr[aIdx] <= 0) {	// 0이하면 0으로 수정 후 종료
			arr[aIdx] = 0;
			aIdx = ++aIdx % 8;
			break;
		}
	}
}
```

### 출력

```java
/**
 * 출력 함수
 * 
 * @param t // 테케 번호
 * @throws Exception
 */
static void print(int t) throws Exception {
	bw.write("#" + t + " ");
	// 0 다음 인덱스부터 출력
	// ex) 3번이 0
	// => 출력 : 4 5 6 7 0 1 2 3 순서
	for (int i = 0; i < 8; i++) {
		bw.write(arr[aIdx] + " ");
		aIdx = ++aIdx % 8;
	}
	bw.newLine();
	bw.flush();
}
```