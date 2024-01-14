#include <algorithm>
#include <cstring>
#include <iostream>
#include <vector>
#define pii pair<int, int>

using namespace std;

// 한 칸에 나무 여러개 => 어린 애부터
// left < age => die
// 봄	: 양분 += 나이, 나이++
// 여름	: 양분 += 봄에 죽은 나무 나이 / 2
// 가을	: if 나무 나이 % 5 == 0, 주변에 1짜리 8개 +
// 겨울	: 양분[r][c] += A[r][c]

const int N_MAX = 11;
int ans, N, M, K;

int A[N_MAX][N_MAX];
int map[N_MAX][N_MAX];
vector<pair<int, pii> > dead;
vector<pii> five;
vector<int> trees[N_MAX][N_MAX];

void init() {
    ans = 0;
    fill(&map[0][0], &map[N_MAX - 1][N_MAX], 5);
    memset(trees, 0, sizeof(trees));
}

void input() {
    cin >> N >> M >> K;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> A[i][j];
        }
    }

    for (int i = 0; i < M; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        trees[x][y].push_back(z);
    }
}

void yearLater() {
    // 봄 & 여름
    int x, y, z;
    vector<pair<int, pii> > alive;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (trees[i][j].size() == 0) continue;

            sort(trees[i][j].begin(), trees[i][j].end());
            int toAdd = 0;
            for (int k = 0; k < trees[i][j].size(); k++) {
                z = trees[i][j][k];
                if (map[i][j] < z) {
                    toAdd += z / 2;
                } else {
                    map[i][j] -= z;
                    z++;
                    alive.push_back({z, make_pair(i, j)});
                    if (z % 5 == 0) {
                        five.push_back({i, j});
                    }
                }
            }
            trees[i][j].clear();
            map[i][j] += toAdd;
        }
    }

    while (!alive.empty()) {
        x = alive.back().second.first;
        y = alive.back().second.second;
        z = alive.back().first;
        alive.pop_back();

        trees[x][y].push_back(z);
    }

    int dx[8] = {0, 1, 1, 1, 0, -1, -1, -1};
    int dy[8] = {1, 1, 0, -1, -1, -1, 0, 1};
    // 가을
    while (!five.empty()) {
        x = five.back().first;
        y = five.back().second;
        five.pop_back();
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
            trees[nx][ny].push_back(1);
        }
    }

    // 겨울
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            map[i][j] += A[i][j];
        }
    }
}

void sol() {
    for (int i = 0; i < K; i++) {
        yearLater();
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (trees[i][j].size() == 0) continue;
            ans += trees[i][j].size();
        }
    }
    cout << ans << "\n";
}

void run() {
    init();
    input();
    sol();
}

int main(int argc, char** argv) {
    ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);
    run();
    return 0;  // 정상종료시 반드시 0을 리턴해야합니다.
}