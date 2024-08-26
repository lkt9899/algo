#include <algorithm>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 100
#define MAX_C 100 * 100 + 1

using namespace std;

struct App {
    int mem, cost;

    void print() { cout << mem << " " << cost << "\n"; }
} app[MAX_N];

int compare(const App& a1, const App& a2) {
    if (a1.cost == a2.cost) return a1.mem > a2.mem;
    return a1.cost < a2.cost;
}

int N, M, lim;
int dp[MAX_C];

void input() {
    i2(N, M);
    fi0(N) i1(app[i].mem);
    fi0(N) i1(app[i].cost);
    lim = MAX_N * N;
}

void sol() {
    sort(app, app + N, compare);
    fi0(N) {
        int c = app[i].cost;
        for (int j = lim; j >= c; j--) dp[j] = max(dp[j], dp[j - c] + app[i].mem);
    }

    fi0(lim + 1) {
        if (dp[i] >= M) {
            cout << i;
            break;
        }
    }
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