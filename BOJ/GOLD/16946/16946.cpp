#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define fi(a) for (int i = 0; i < a; i++)
#define fj(a) for (int j = 0; j < a; j++)
#define chk(x, mx, y, my) if (x < 0 || x >= mx || y < 0 || y >= my)

#define MAX_N 1000
#define WALL 1

using namespace std;

struct Axis {
    int x, y;
};

int N, M;

queue<Axis> walls;
int arr[MAX_N][MAX_N];
int groups[MAX_N][MAX_N];

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void init() { memset(groups, -1, sizeof(groups)); }

void input() {
    i2(N, M);
    fi(N) {
        string s;
        i1(s);
        fj(M) { arr[i][j] = s[j] - '0'; }
    }
}

void bfs(int x, int y, int idx) {
    int cnt = 0;
    queue<Axis> q;
    q.push({x, y});
    groups[x][y] = idx;
    while (!q.empty()) {
        Axis cur = q.front();
        q.pop();

        cnt++;
        fi(4) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            chk(nx, N, ny, M) continue;
            if (groups[nx][ny] == idx) continue;
            if (arr[nx][ny] != 0) {
                walls.push({nx, ny});
                groups[nx][ny] = idx;
                continue;
            }

            groups[nx][ny] = idx;
            q.push({nx, ny});
        }
    }

    while (!walls.empty()) {
        Axis wall = walls.front();
        walls.pop();

        arr[wall.x][wall.y] += cnt;
    }
}

void sol() {
    int idx = 0;
    fi(N) {
        fj(M) {
            if (arr[i][j] == 0 && groups[i][j] == -1) {
                bfs(i, j, idx);
                idx++;
            }
        }
    }

    fi(N) {
        fj(M) { cout << arr[i][j] % 10; }
        cout << "\n";
    }
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