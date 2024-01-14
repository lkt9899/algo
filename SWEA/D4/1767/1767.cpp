#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);
#define MAX 12
#define INF 1000000007

using namespace std;

struct Axis {
    int x, y;
} cores[MAX];

int N, coreCnt, connected, ans;
int arr[MAX][MAX];
int p[MAX];
int di[4] = {0, 1, 0, -1};
int dj[4] = {1, 0, -1, 0};

void init() {
    ans = INF;
    connected = 0;
    coreCnt = 0;
}

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> arr[i][j];
            if (arr[i][j]) {
                if (i * j == 0 || i == N - 1 || j == N - 1) {
                    connected++;
                } else {
                    cores[coreCnt++] = {i, j};
                }
            }
        }
    }
}

bool isValid(int i, int j) { return (i >= 0 && i < N && j >= 0 && j < N); }

void go(vector<Axis>& toGo, Axis cur, int dir) {
    int ni = cur.x + di[dir];
    int nj = cur.y + dj[dir];
    while (isValid(ni, nj) && !arr[ni][nj]) {
        toGo.push_back({ni, nj});
        ni += di[dir];
        nj += dj[dir];
    }

    if (isValid(ni, nj)) {
        toGo.clear();
    }
}

void fill(const vector<Axis>& toGo, int val) {
    for (Axis a : toGo) arr[a.x][a.y] = val;
}

void search(int idx, int total_len, int cnt, int maxCnt) {
    if (cnt == maxCnt) {
        ans = ans < total_len ? ans : total_len;
        return;
    }

    if (p[idx] == 0) search(idx + 1, total_len, cnt, maxCnt);

    int len;
    for (int i = 0; i < 4; i++) {
        vector<Axis> toGo;
        go(toGo, cores[idx], i);
        len = toGo.size();

        if (len == 0) continue;

        fill(toGo, -1);
        search(idx + 1, total_len + len, cnt + 1, maxCnt);
        fill(toGo, 0);
    }
}

void sol() {
    for (int i = coreCnt; i > 0; i--) {
        memset(p, 0, sizeof(p));
        for (int j = coreCnt - 1; j >= coreCnt - i; j--) {
            p[j] = 1;
        }

        do {
            search(0, 0, 0, i);
        } while (next_permutation(p, p + coreCnt));

        if (ans != INF) break;
    }
}

void run() {
    init();
    input();
    sol();
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}