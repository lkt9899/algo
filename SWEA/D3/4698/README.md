# [SWEA D3] 테네스의 특별한 소수 - 4698

### 문제

```
테네스는 소수를 좋아한다. 소수란 1과 자기 자신만으로 나뉘어 떨어지는 숫자로 작은 것부터 나열하면 2, 3, 5, 7, 11, 13, 17, 19, 23, …같은 수들이 있다.

또한 테네스는 D를 포함하는 숫자도 좋아한다. 그렇기에 소수가 D를 포함하면 더욱 더 좋아하여 특별한 소수라고 부르기로 했다.

예를 들어 D = 3이면 3, 13, 23, … 같은 소수들이 3을 포함하였으므로 테네스는 이런 숫자들을 특별한 소수라고 부를 것이다.

D가 주어질 때, A이상 B이하의 수 중에서 특별한 소수인 것들의 개수를 구하는 프로그램을 작성하라.
```

### 문제 접근

- [에라토스테네스의 체](https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4) 이용

## 풀이

### 소수 목록 초기화

```cpp
void PrimeNumberSieve() {
    fill(&arr[0], &arr[MAX], 1);
    for (int i = 2; i < sqrt(MAX); i++) {
        if (arr[i] == 0) continue;

        for (int j = i * i; j <= MAX; j += i) {
            arr[j] = 0;
        }
    }
}
```

### D 포함 여부 체크

```cpp
void sol() {
    if (A < 2) A = 2;   // A가 1인 경우 예외처리
    for (int i = A; i <= B; i++) {
        if (arr[i] == 0) continue;

        int tmp = i;
        while (tmp != 0) {      // 10씩 나누면서 자릿수에 D가 있는지 판별
            if (tmp % 10 == D) {
                ans++;
                break;
            }
            tmp /= 10;
        }
    }
}
```

