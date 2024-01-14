#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pii pair<int, int>

using namespace std;

const int EMPTY = 0;
const int TRAP = 1;
const int WALL = 2;

const int MAX_L = 41;
const int MAX_N = 30;
const int MAX_Q = 100;

// r, c : 위치  h : 세로 길이  w : 가로 길이  k : 체력
struct Knight {
    int r, c, h, w, k, dmg;
    bool exist = true;

    void move(int d) {
        r += di[d];
        c += dj[d];
    }

    bool check() {
        if (dmg >= k) {
            exist = false;
        }

        return exist;
    }

    void damage() {
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (map[i][j] == 2) dmg++;
            }
        }
    }

} knights[MAX_N];

// 명령
// (i, d) : i번 기사 d 방향 이동
//   0
// 3   1
//   2

int L, N, Q;
int map[MAX_L][MAX_L];
int knights_pos[MAX_L][MAX_L];
pii commands[MAX_Q];

int di[4] = {0, 1, 0, -1};
int dj[4] = {1, 0, -1, 0};

void init() { memset(map, 0, sizeof(map)); }

void input() {
    cin >> L >> N >> Q;

    for (int i = 1; i <= L; i++) {
        for (int j = 1; j <= L; j++) {
            cin >> map[i][j];
        }
    }

    for (int i = 0; i < N; i++) {
        int r, c, h, w, k;
        cin >> r >> c >> h >> w >> k;
        knights[i] = {r, c, h, w, k, 0};
    }

    for (int i = 0; i < Q; i++) {
        int id, dir;
        cin >> id >> dir;
        commands[i] = {id, dir};
    }
}

void execute(int cmd_id) { pii cmd = commands[cmd_id]; }

void set_knights_pos() {
    for (int k = 0; k < N; k++) {
        Knight *knight = &knights[k];
        for (int i = knight->r; i < knight->r + knight->h; i++) {
            for (int j = knight->c; j < knight->c + knight->w; j++) {
                knights_pos[i][j] = k;
            }
        }
    }
}

void sol() {
    set_knights_pos();
    for (int i = 0; i < Q; i++) {
        execute(i);
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