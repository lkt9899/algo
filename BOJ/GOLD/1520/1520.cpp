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

#define MAX_N 501

using namespace std;

struct Axis {
    int x, y;
};

int N, M;
int _map[MAX_N][MAX_N];
int dp[MAX_N][MAX_N];

int di[4] = {0, 1, 0, -1};
int dj[4] = {1, 0, -1, 0};

void init() { memset(dp, -1, sizeof(dp)); }

void input() {
    i2(N, M);
    fi1(N) {
        fj1(M) { i1(_map[i][j]); }
    }
}

bool outOfBound(int x, int y) { return x < 1 || x > N || y < 1 || y > M; }

void print() {
    fi1(N) {
        fj1(M) { cout << dp[i][j] << " "; }
        cout << "\n";
    }
    cout << "\n";
}

int dfs(int x, int y) {
    if (x == N && y == M) return 1;
    if (dp[x][y] != -1) return dp[x][y];

    dp[x][y] = 0;
    fi0(4) {
        int nx = x + di[i];
        int ny = y + dj[i];

        if (outOfBound(nx, ny)) continue;
        if (_map[x][y] <= _map[nx][ny]) continue;

        dp[x][y] = dp[x][y] + dfs(nx, ny);
    }

    return dp[x][y];
}

void sol() { cout << dfs(1, 1); }

void run() {
    init();
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}