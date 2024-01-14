#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 12;
const int INF = 1000000007;

struct Axis {
    int x, y;
} v[MAX];

int N;
int dp[MAX][1 << MAX];

void init() { memset(dp, -1, sizeof(dp)); }

int getDist(Axis a, Axis b) { return abs(a.x - b.x) + abs(a.y - b.y); }

int TSP(int st, int visit) {
    if (visit == (1 << N + 1) - 1) return getDist(v[st], v[N + 1]);

    int &ret = dp[st][visit];

    if (ret != -1) return ret;

    ret = INF;
    for (int i = 1; i <= N; i++) {
        if (!(visit & (1 << i))) ret = min(ret, TSP(i, (visit | (1 << i))) + getDist(v[st], v[i]));
    }
    return ret;
}

void input() {
    cin >> N;
    cin >> v[0].x >> v[0].y >> v[N + 1].x >> v[N + 1].y;

    for (int i = 1; i <= N; i++) {
        cin >> v[i].x >> v[i].y;
    }
}

void output(const int &t) { cout << "#" << t << " " << TSP(0, 1) << "\n"; }

int main(int argc, char **argv) {
    fio;
    int T, test_case;

    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        init();
        input();
        output(test_case);
    }
    return 0;
}