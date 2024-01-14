# [[SWEA D4] 1218. 괄호 짝짓기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD)

### 문제

4 종류의 괄호문자들 '()', '[]', '{}', '<>' 로 이루어진 문자열이 주어진다.

이 문자열에 사용된 괄호들의 짝이 모두 맞는지 판별하는 프로그램을 작성한다.

예를 들어 아래와 같은 문자열은 유효하다고 판단할 수 있다.

![image](https://github.com/lkt9899/PS/assets/80976609/3fe2832b-3e64-4c89-a5c4-92430086e060)

아래와 같은 문자열은 유효하지 않은 문자열이다. 붉은색으로 표시된 괄호의 짝을 찾을 수 없기 때문이다.

![image](https://github.com/lkt9899/PS/assets/80976609/9eaf4747-f63b-4086-8362-dc5b0cadfd79)

아래 문자열은 열고 닫는 괄호의 개수는 유효하나 짝이 맞지 않는 괄호가 사용 되었기 때문에 유효하지 않다.

![image](https://github.com/lkt9899/PS/assets/80976609/7b227f94-f7a5-46e9-9fed-9cca2148d51d)

### 문제 접근



## 풀이

### 변수

```java
// 정답, 길이
static int ans, len;
// 괄호 넣을 스택
static Stack<Character> s = new Stack<>();
// 입력 문자열
static String parenthesis;
// 체크를 위한 문자열
static String openParenthesis = "{([<";
static String closeParenthesis = "})]>";
```

### main

```java
public static void main(String[] args) throws Exception {
    for (int t = 1; t <= 10; t++) {
        // 입력
        input();
        // 처리
        run();
        // 출력
        print(t);
    }
    bw.flush();
    bw.close();
    br.close();
}
```

### input

```java
static void input() throws Exception {
    // 문자열 길이
    len = Integer.parseInt(br.readLine());
    // 괄호 문자열
    parenthesis = br.readLine();
}
```

### run

```java
/**
 * 처리 함수
 */
static void run() {
    // 가능 시 1, 불가능 시 0
    ans = 1;
    // 한글자씩 탐색
    for (int i = 0; i < len; i++) {
        // 문자열의 i번째 문자
        char tmp = parenthesis.charAt(i);
        // 여는 괄호 중 하나이면 스택에 추가
        if (openParenthesis.indexOf(tmp) >= 0) {
            s.add(tmp);
        }
        // 닫는 괄호일 시
        else {
            // 스택 맨 위의 여는 괄호와 짝인지 판별
            if (check(tmp)) {
                s.pop();
            } else {
                ans = 0;
                break;
            }
        }
    }
}

static boolean check(char c) {
    return closeParenthesis.indexOf(c) == openParenthesis.indexOf(s.peek());
}
```