#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 101

using namespace std;

struct Axis {
    int x, y;
};

struct Switch {
    vector<Axis> sv;
} sw[MAX_N][MAX_N];

int N, M;
int rooms[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void init() {
    memset(rooms, 0, sizeof(rooms));
    rooms[1][1] = 1;
}

void input() {
    i2(N, M);
    fi0(M) {
        int x, y, tx, ty;
        i2(x, y);
        i2(tx, ty);
        sw[x][y].sv.push_back({tx, ty});
    }
}

bool outOfBound(int x, int y) { return x < 1 || x > N || y < 1 || y > N; }

void sol() {
    int ans = 1;
    bool isChanged = true;
    queue<Axis> q;
    while (isChanged) {
        memset(visited, false, sizeof(visited));
        isChanged = false;
        q.push({1, 1});
        visited[1][1] = true;
        while (!q.empty()) {
            Axis cur = q.front();
            q.pop();

            while (!sw[cur.x][cur.y].sv.empty()) {
                if (!isChanged) isChanged = true;

                Axis tar = sw[cur.x][cur.y].sv.back();
                sw[cur.x][cur.y].sv.pop_back();

                if (rooms[tar.x][tar.y] == 0) ans++;
                rooms[tar.x][tar.y] = 1;
            }

            fi0(4) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (outOfBound(nx, ny) || visited[nx][ny] || rooms[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.push({nx, ny});
            }
        }
    }
    cout << ans;
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