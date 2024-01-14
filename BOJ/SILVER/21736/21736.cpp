#include <cstring>
#include <iostream>
#include <queue>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pii pair<int, int>

using namespace std;

const int MAX = 600;
int N, M, ans;
pii start;
char map[MAX][MAX];
bool visited[MAX][MAX];
int di[4] = {0, 1, 0, -1};
int dj[4] = {1, 0, -1, 0};

void init() {
    ans = 0;
    memset(visited, false, sizeof(visited));
}

void input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> map[i][j];
            if (map[i][j] == 'I') start = make_pair(i, j);
        }
    }
}

bool NotValid(int i, int j) { return i < 0 || i >= N || j < 0 || j >= M || visited[i][j] || map[i][j] == 'X'; }

void sol() {
    deque<pii> dq;
    visited[start.first][start.second] = true;
    dq.push_back(start);
    while (!dq.empty()) {
        pii cur = dq.front();
        dq.pop_front();

        for (int d = 0; d < 4; d++) {
            int ni = cur.first + di[d];
            int nj = cur.second + dj[d];

            if (NotValid(ni, nj)) continue;
            if (map[ni][nj] == 'P') ans++;

            visited[ni][nj] = true;
            dq.push_back({ni, nj});
        }
    }

    if (ans)
        cout << ans << "\n";
    else
        cout << "TT\n";
}

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