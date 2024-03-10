#include <iostream>
#include <queue>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 1001
#define MAX_K 10
#define EMPTY 0
#define WALL 1

using namespace std;

struct Axis {
    int x, y, w;
};

int N, M, K;
int room[MAX_N][MAX_N];
int visited[MAX_N][MAX_N][MAX_K];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void input() {
    i3(N, M, K);
    fi1(N) {
        string s;
        i1(s);
        fj1(M) { room[i][j] = s[j - 1] - '0'; }
    }
}

bool OOB(int x, int y) { return x < 1 || x > N || y < 1 || y > M; }

int bfs() {
    queue<Axis> q;
    q.push({1, 1, 0});
    visited[1][1][0] = 1;
    while (!q.empty()) {
        Axis cur = q.front();
        q.pop();

        if (cur.x == N && cur.y == M) return visited[N][M][cur.w];

        int cost = visited[cur.x][cur.y][cur.w];
        for (int d = 0; d < 4; d++) {
            int nx = cur.x + dx[d];
            int ny = cur.y + dy[d];

            if (OOB(nx, ny)) continue;
            if (visited[nx][ny][cur.w]) continue;

            if (room[nx][ny] == WALL) {
                if (cur.w == K || visited[nx][ny][cur.w + 1]) continue;
                visited[nx][ny][cur.w + 1] = cost + 1;
                q.push({nx, ny, cur.w + 1});
            } else {
                visited[nx][ny][cur.w] = cost + 1;
                q.push({nx, ny, cur.w});
            }
        }
    }

    return -1;
}

void sol() { cout << bfs(); }

void run() {
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}