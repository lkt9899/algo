#include <cstring>
#include <iostream>
#include <string>
#include <vector>

#define MAX_N 5

using namespace std;

int cpy[MAX_N][MAX_N];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void init(vector<vector<int>> board) {
    memset(cpy, 0, sizeof(cpy));
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[0].size(); j++) {
            cpy[i][j] = board[i][j];
        }
    }
}

bool outOfBound(int x, int y) { return x < 0 || x >= MAX_N || y < 0 || y >= MAX_N; }

// mod 2 = 0 : B 승리
// mod 2 = 1 : A 승리
int backTracking(int ax, int ay, int bx, int by) {
    if (!cpy[ax][ay]) return 0;

    int cur = 0;
    for (int d = 0; d < 4; d++) {
        int nx = ax + dx[d];
        int ny = ay + dy[d];

        if (outOfBound(nx, ny)) continue;
        if (!cpy[nx][ny]) continue;

        cpy[ax][ay] = 0;
        int next = backTracking(bx, by, nx, ny) + 1;
        cpy[ax][ay] = 1;

        // 이길 수 있으면 이기는 곳으로
        if (cur % 2 == 0 && next % 2 == 1) cur = next;
        // 어차피 지면 최대한 버티기
        else if (cur % 2 == 0 && next % 2 == 0)
            cur = max(cur, next);
        // 어차피 이기면 최대한 빠르게
        else if (cur % 2 == 1 && next % 2 == 1)
            cur = min(cur, next);
    }

    return cur;
}

int solution(vector<vector<int>> board, vector<int> aloc, vector<int> bloc) {
    init(board);
    return backTracking(aloc[0], aloc[1], bloc[0], bloc[1]);
}

int main() {
    freopen("input.txt", "r", stdin);
    int T;
    cin >> T;
    for (int t = 0; t < T; t++) {
        int N, M;
        cin >> N >> M;
        vector<vector<int>> board;
        vector<int> aloc;
        vector<int> bloc;
        for (int i = 0; i < N; i++) {
            vector<int> temp;
            for (int j = 0; j < M; j++) {
                int v;
                cin >> v;
                temp.push_back(v);
            }
            board.push_back(temp);
        }

        int x, y;
        cin >> x >> y;
        aloc.push_back(x);
        aloc.push_back(y);

        cin >> x >> y;
        bloc.push_back(x);
        bloc.push_back(y);

        cout << solution(board, aloc, bloc) << "\n";
    }
}