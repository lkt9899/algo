# [SILVER V] 크로아티아 알파벳 - 2941

### 문제

![image](https://github.com/lkt9899/PS/assets/80976609/24e4db2c-089f-4e08-af9e-81ac6b3361df)

단어가 주어졌을 때, 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.

dž는 무조건 하나의 알파벳으로 쓰이고, d와 ž가 분리된 것으로 보지 않는다.

lj와 nj도 마찬가지이다. 위 목록에 없는 알파벳은 한 글자씩 센다.

### 문제 접근

```
- 크로아티아 알파벳에 해당하는 문자열들을 카운팅
```

### 풀이

```cpp
void sol() {
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == 'c' && (s[i + 1] == '=' || s[i + 1] == '-')) {
            i++;
        } else if (s[i] == 'd') {
            if (s[i + 1] == 'z' && s[i + 2] == '=')
                i += 2;
            else if (s[i + 1] == '-')
                i++;
        } else if (s[i] == 'l' && s[i + 1] == 'j') {
            i++;
        } else if (s[i] == 'n' && s[i + 1] == 'j') {
            i++;
        } else if (s[i] == 's' && s[i + 1] == '=') {
            i++;
        } else if (s[i] == 'z' && s[i + 1] == '=') {
            i++;
        }
        ans++;
    }
    cout << ans;
}
```