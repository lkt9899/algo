#include <cstring>
#include <iostream>
#include <queue>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 1001
#define MAX_M 100001
#define pii pair<int, int>

using namespace std;

struct Axis {
    int x, y, idx;
};

struct Ladder {
    int idx = 0;
    int nextX = 0;
} ladder[MAX_N][MAX_N];

struct Info {
    int start, score;
};

struct comp {
    bool operator()(const Info& a, const Info& b) { return a.start > b.start; }
};

int N, M, H, K;
int scores[MAX_N];
int origin[MAX_N];
priority_queue<Info, vector<Info>, comp> sum[MAX_M];
queue<Axis> q;

void init() { memset(sum, 0, sizeof(sum)); }

void input() {
    cin >> N >> M >> H >> K;
    for (int i = 1; i <= N; i++) {
        int score;
        cin >> score;
        scores[i] = score;
    }

    for (int i = 1; i <= M; i++) {
        int x, y;
        cin >> x >> y;
        ladder[x][y] = {i, x + 1};
        ladder[x + 1][y] = {i, x};
    }
}

void sol() {
    for (int i = 1; i <= K; i++) {
        q.push({i, 0, 0});
        while (!q.empty()) {
            Axis cur = q.front();
            q.pop();

            if (cur.y == H) {
                if (cur.idx == 0)
                    origin[i] = scores[cur.x];
                else
                    sum[cur.idx].push({i, scores[cur.x]});
                continue;
            }

            Ladder l = ladder[cur.x][cur.y];
            if (l.nextX == 0)
                q.push({cur.x, cur.y + 1, cur.idx});
            else {
                if (cur.idx == 0) {
                    q.push({cur.x, cur.y + 1, l.idx});
                    q.push({l.nextX, cur.y + 1, cur.idx});
                } else {
                    q.push({l.nextX, cur.y + 1, cur.idx});
                }
            }
        }
    }

    int ans = 0;
    for (int i = 1; i <= K; i++) {
        ans += origin[i];
    }

    for (int i = 1; i <= M; i++) {
        int res = 0;
        for (int j = 1; j <= K; j++) {
            if (!sum[i].empty() && sum[i].top().start == j) {
                res += sum[i].top().score;
                sum[i].pop();
            } else {
                res += origin[j];
            }
        }

        ans = min(ans, res);
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
    run();
    return 0;
}