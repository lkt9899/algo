#include <iostream>
#include <stack>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pii pair<int, int>

using namespace std;

const int MAX = 100;
char arr[MAX][MAX];
int visited[MAX][MAX];
int N, M;
int di[4] = {0, 1, 0, -1};
int dj[4] = {1, 0, -1, 0};
vector<pii> wolf;

void init() {}

void input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 'W')
                wolf.push_back({i, j});
            else if (arr[i][j] == '.')
                arr[i][j] = 'P';
        }
    }
}

void dfs(pii start) {
    fill(&visited[0][0], &visited[N - 1][M], 0);
    int ci, cj;
    stack<pii> s;
    s.push(start);
    visited[start.first][start.second] = 1;
    while (!s.empty()) {
        ci = s.top().first;
        cj = s.top().second;
        s.pop();
        for (int i = 0; i < 4; i++) {
            int ni = ci + di[i];
            int nj = cj + dj[i];

            if (ni < 0 || ni >= N || nj < 0 || nj >= M || arr[ni][nj] == '#') continue;
            if (arr[ni][nj] == '+') {
                while (arr[ni][nj] == '+') {
                    ni += di[i];
                    nj += dj[i];
                }
                if (arr[ni][nj] == '#') {
                    ni -= di[i];
                    nj -= dj[i];
                }
            }
            if (visited[ni][nj] == 1) continue;
            if (arr[ni][nj] == 'P') arr[ni][nj] = '.';
            visited[ni][nj] = 1;
            s.push({ni, nj});
        }
    }
}

void sol() {
    for (int i = 0; i < wolf.size(); i++) {
        if (visited[wolf[i].first][wolf[i].second] == 0) dfs(wolf[i]);
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cout << arr[i][j];
        }
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