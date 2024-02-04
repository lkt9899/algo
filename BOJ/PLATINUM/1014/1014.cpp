#include <cstring>
#include <iostream>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 10

using namespace std;

int T, N, M;
char classroom[MAX_N][MAX_N];
int dp[MAX_N][1 << MAX_N];
vector<int> lines;

void init() {
    lines.clear();
    memset(dp, -1, sizeof(dp));
}

void input() {
    i2(N, M);
    fi0(N) fj0(M) i1(classroom[i][j]);
}

int check(int lineNum, int beforeLine) {
    if (lineNum == N) return 0;

    if (dp[lineNum][beforeLine] != -1) return dp[lineNum][beforeLine];

    int ans = 0;
    for (int line : lines) {
        int bit = 0;
        int cnt = 0;

        fi0(M) {
            if ((line & 1 << i) == 0) continue;
            if (classroom[lineNum][i] == 'x') continue;
            if (0 < i && beforeLine & (1 << (i - 1))) continue;
            if (i < M && beforeLine & (1 << (i + 1))) continue;

            cnt++;
            bit |= (1 << i);
        }

        ans = max(ans, check(lineNum + 1, bit) + cnt);
    }

    dp[lineNum][beforeLine] = ans;
    return ans;
}

void getLines(int line, int idx) {
    if (idx == M) {
        lines.push_back(line);
        return;
    }

    getLines(line, idx + 1);
    if (0 < idx && line & (1 << (idx - 1))) return;

    getLines(line | (1 << idx), idx + 1);
}

void sol() {
    getLines(0, 0);
    cout << check(0, 0) << "\n";
}

void run() {
    init();
    input();
    sol();
}

int main() {
    fio;
    i1(T);
    fi0(T) run();
    return 0;
}