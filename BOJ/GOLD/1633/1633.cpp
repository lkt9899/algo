#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i2(a, b) cin >> a >> b

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)
#define fk0(e) for (int k = 0; k < e; k++)
#define fk1(e) for (int k = 1; k <= e; k++)

#define MAX_P 1000
#define LIM 15 + 1

using namespace std;

int n = 0;
int player[MAX_P][2];
int dp[MAX_P][LIM][LIM];

void input() { while (i2(player[n][0], player[n++][1])); }

void sol() {
    dp[0][1][0] = player[0][0];
    dp[0][0][1] = player[0][1];

    int ans = 0;
    fi1(n - 1) {
        fj0(LIM) {
            fk0(LIM) {
                int tmp1 = 0, tmp2 = 0;

                if (j > 0) tmp1 = dp[i - 1][j - 1][k] + player[i][0];
                if (k > 0) tmp2 = dp[i - 1][j][k - 1] + player[i][1];

                dp[i][j][k] = max(max(tmp1, tmp2), dp[i - 1][j][k]);
                if (j == LIM - 1 && k == LIM - 1) ans = max(ans, dp[i][j][k]);
            }
        }
    }
    cout << ans;
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