### [GOLD V] 암호 만들기 - 1759

## 풀이
- 모음 체크
    ```cpp
    bool aeCheck(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    ```

- 문자열 탐색   
  모음 개수 cflag와 자음 개수 mflag를 통해 조건 충족 시 ans에 추가
    ```cpp
    void check(string s, int idx, int cflag, int mflag) {
        // 길이 체크
        if (s.length() == L) {
            // 자음 모음 조건 체크
            if (cflag != 0 && mflag >= 2) ans.push_back(s);
            return;
        }

        // 사전 순으로 문자열에 한글자씩 추가
        for (int i = idx; i < C; i++) {
            if (aeCheck(all[i])) {
                check(s + all[i], i + 1, cflag + 1, mflag);
            } else
                check(s + all[i], i + 1, cflag, mflag + 1);
        }
    }
    ```