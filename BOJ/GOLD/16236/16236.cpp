#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pii pair<int, int>

using namespace std;

struct Axis {
    int x, y, t;
};

bool compare(Axis a, Axis b) {
    if (a.t == b.t) {
        if (a.x == b.x) {
            return a.y < b.y;
        }
        return a.x < b.x;
    }
    return a.t < b.t;
}

/* 0    : 빈칸
 * 1~6  : 물고기
 * 9    : 아기 상어
 */
const int MAX = 20;
int N, age, ans;
int map[MAX][MAX];
int visited[MAX][MAX];
int di[4] = {-1, 0, 0, 1};
int dj[4] = {0, -1, 1, 0};
Axis start;
vector<Axis> toEat;

void init() {
    age = 2;
    ans = 0;
}

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> map[i][j];
            if (map[i][j] == 9) {
                start = {i, j, 0};
                map[i][j] = 0;
            }
        }
    }
}

void find(Axis cur, int cnt) {
    if (cnt == age) {
        cnt = 0;
        age++;
    }

    toEat.clear();
    memset(visited, 0, sizeof(visited));

    queue<Axis> q;
    q.push(cur);
    visited[cur.x][cur.y] = 1;
    while (!q.empty()) {
        Axis c = q.front();
        q.pop();

        if (!toEat.empty() && c.t > toEat[0].t) break;

        for (int i = 0; i < 4; i++) {
            int ni = c.x + di[i];
            int nj = c.y + dj[i];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] != 0) continue;
            if (map[ni][nj] > age) continue;

            visited[ni][nj] = 1;
            if (map[ni][nj] != 0 && map[ni][nj] < age) {
                toEat.push_back({ni, nj, c.t + 1});
            } else {
                q.push({ni, nj, c.t + 1});
            }
        }
    }

    if (!toEat.empty()) {
        sort(toEat.begin(), toEat.end(), compare);

        map[toEat[0].x][toEat[0].y] = 0;
        cnt++;
        ans += toEat[0].t;
        toEat[0].t = 0;
        find(toEat[0], cnt);
    }
}

void sol() {
    find(start, 0);
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