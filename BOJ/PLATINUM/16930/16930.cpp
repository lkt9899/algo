#include <iostream>
#include <queue>

#define fio ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);

#define fi(s, N) for(int i = s; i < N; i++)
#define fj(s, N) for(int j = s; j < N; j++)

#define i1(i1) cin >> i1;
#define i2(i1, i2) cin >> i1 >> i2;
#define i3(i1, i2, i3) cin >> i1 >> i2 >> i3;

#define INF 987654321

using namespace std;

const int MAX = 1001;

struct Axis {
    int x, y;
};

int N, M, K;
int arr[MAX][MAX];
int cost[MAX][MAX];
queue<Axis> q;
Axis s, e;

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void init() {
    fi(1, N + 1) fj(1, M + 1) cost[i][j] = INF;
}

void input() {
    char c;
    i3(N, M, K);
    fi(1, N + 1) {
        fj(1, M + 1) {
            i1(c);
            arr[i][j] = c == '.' ? 0 : 1;
        }
    }
    i2(s.x, s.y);
    i2(e.x, e.y);
}

bool OOB(int x, int y) {
    return x <= 0 || y <= 0 || x > N || y > M || arr[x][y] == 1;
}

void solve() {
    cost[s.x][s.y] = 0;
    q.push(s);
    while(!q.empty()) {
        Axis cur = q.front();
        q.pop();

        if(cur.x == e.x && cur.y == e.y) break;

        fi(0, 4) {
            fj(1, K + 1) {
                int nx = cur.x + dx[i] * j, ny = cur.y + dy[i] * j;
                // Out Of Bound
                if(OOB(nx, ny)) break;
                
                // cost compare
                if(cost[cur.x][cur.y] >= cost[nx][ny]) break;

                // isVisit
                if(cost[nx][ny] != INF) continue;

                cost[nx][ny] = cost[cur.x][cur.y] + 1;
                q.push({nx, ny});
            }
        }
    }

    cout << (cost[e.x][e.y] == INF ? -1 : cost[e.x][e.y]) << "\n";
}

void run() {
    input();
    init();
    solve();
}

int main() {
	fio;
    run();
}