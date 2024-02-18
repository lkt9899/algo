#include <cstring>
#include <iostream>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define INF 987654321

using namespace std;

int T, N, W, ans;
// 0 : outside, 1 : inside
int base[2][10001];
// 0 : only outside, 1 : only inside, 2 : both empty
// col 0 is end, 10001 is start
int dp[3][10002];

void init() { memset(dp, 0, sizeof(dp)); }

void input() {
    i2(N, W);
    fi0(2) fj1(N) i1(base[i][j]);
    ans = INF;
}

void check(int start) {
    for (int i = start; i <= N; i++) {
        dp[2][i + 1] = min(dp[0][i] + 1, dp[1][i] + 1);

        // bind vertical
        if (base[0][i] + base[1][i] <= W) dp[2][i + 1] = min(dp[2][i + 1], dp[2][i] + 1);

        // bind horizon both
        if (i > 0 && base[0][i - 1] + base[0][i] <= W && base[1][i - 1] + base[1][i] <= W) dp[2][i + 1] = min(dp[2][i + 1], dp[2][i - 1] + 2);

        // if end break
        if (i == N) break;

        dp[0][i + 1] = dp[2][i + 1] + 1;
        dp[1][i + 1] = dp[2][i + 1] + 1;

        // bind or + 1 outside only
        if (base[0][i] + base[0][i + 1] <= W) dp[0][i + 1] = min(dp[0][i + 1], dp[1][i] + 1);
        // bind or + 1 inside only
        if (base[1][i] + base[1][i + 1] <= W) dp[1][i + 1] = min(dp[1][i + 1], dp[0][i] + 1);
    }
}

void sol() {
    // both empty = 0
    dp[2][1] = 0;
    // only upside or downside case 1
    dp[0][1] = dp[1][1] = 1;
    // check form col 1
    check(1);
    ans = dp[2][N + 1];

    if (N > 2) {
        // outside start link to end
        // [  | *]
        //  * | *
        if (base[0][1] + base[0][N] <= W) {
            // [*  ]
            //  * 1
            dp[2][2] = 1;
            // [*  ] 2
            //  * 1
            dp[0][2] = 2;
            // [* |  ]    [* |   ]
            //  * | 1  2   * | [1   ]
            dp[1][2] = base[1][1] + base[1][2] <= W ? 1 : 2;
            check(2);
            ans = min(ans, dp[1][N] + 1);
        }

        // inside start link to end
        //  * | *
        // [* |  ]
        if (base[1][1] + base[1][N] <= W) {
            dp[2][2] = 1;
            dp[0][2] = base[0][1] + base[0][2] <= W ? 1 : 2;
            dp[1][2] = 2;
            check(2);
            ans = min(ans, dp[0][N] + 1);
        }

        // link both
        // [* | ]
        // [* | ]
        if (base[0][1] + base[0][N] <= W && base[1][1] + base[1][N] <= W) {
            dp[2][2] = 0;
            dp[0][2] = 1;
            dp[1][2] = 1;
            check(2);
            ans = min(ans, dp[2][N] + 2);
        }
    }
    cout << ans << "\n";
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