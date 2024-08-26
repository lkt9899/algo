#include <iostream>
#include <stack>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 1001

using namespace std;

stack<char> result;
string s1, s2;
int sz1, sz2;
int dp[MAX_N][MAX_N];

void input() {
    i2(s1, s2);
    sz1 = s1.size();
    sz2 = s2.size();

    s1 = '*' + s1;
    s2 = '*' + s2;
}

void LCS() {
    fi1(sz1) {
        fj1(sz2) {
            if (s1[i] == s2[j]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = dp[i - 1][j] >= dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
            }
        }
    }
}

void getLCS() {
    int i = sz1, j = sz2;
    while (i * j) {
        int cur = dp[i][j];
        if (cur == dp[i - 1][j])
            i--;
        else if (cur == dp[i][j - 1])
            j--;
        else {
            result.push(s1[i]);
            i--, j--;
        }
    }
}

void print() {
    cout << dp[sz1][sz2] << "\n";
    while (!result.empty()) {
        cout << result.top();
        result.pop();
    }
}

void sol() {
    LCS();
    getLCS();
    print();
}

void run() {
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}