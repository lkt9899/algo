#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)
#define fk0(e) for (int k = 0; k < e; k++)
#define fk1(e) for (int k = 1; k <= e; k++)

#define N_MAX 20

using namespace std;

int N, ans;
int cost[N_MAX][N_MAX];
int res[N_MAX][N_MAX];

void input() {
    i1(N);
    fi0(N) fj0(N) {
        i1(cost[i][j]);
        res[i][j] = cost[i][j];
    }
}

void FW() {
    fk0(N) fi0(N) fj0(N) {
        if (i == j || j == k || k == i) continue;
        if (cost[i][j] == cost[i][k] + cost[k][j]) res[i][j] = 0;

        if (cost[i][j] > cost[i][k] + cost[k][j]) {
            ans = -1;
            return;
        }
    }
}

int calc() {
    ans = 0;
    fi0(N) fj0(N) ans += res[i][j];

    return ans / 2;
}

void sol() {
    FW();
    if (ans == -1)
        cout << ans;
    else
        cout << calc();
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