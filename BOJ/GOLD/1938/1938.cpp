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

#define MAX_N 50
using namespace std;

const char TREE = '1';
const char EMPTY = '0';
const char END = 'E';
const char LOG = 'B';

struct Axis {
    int x = -1, y = -1;
    void operator+=(const Axis& op) {
        x += op.x;
        y += op.y;
    }

    bool operator==(const Axis& op) { return x == op.x && y == op.y; }
};

struct Log {
    Axis c, lu, rd;
    int state = 0, dist = 0;

    bool operator==(const Log& op) { return c == op.c && state == op.state; }
};

int N;
// move
Axis dir[4] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
// turn
Axis t[4] = {{-1, 1}, {1, -1}, {1, 1}, {-1, -1}};
char flat[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N][2];
Log S, E;

void input() {
    i1(N);
    int sIdx = 0;
    int eIdx = 0;
    fi0(N) {
        char c;
        fj0(N) {
            i1(c);
            if (c == LOG) {
                if (sIdx == 0)
                    S.lu = {i, j};
                else if (sIdx == 1)
                    S.c = {i, j};
                else
                    S.rd = {i, j};
                sIdx++;
            } else if (c == END) {
                if (eIdx == 0)
                    E.lu = {i, j};
                else if (eIdx == 1)
                    E.c = {i, j};
                else
                    E.rd = {i, j};
                eIdx++;
            }
            flat[i][j] = c;
        }
    }

    if (S.lu.x != S.c.x) S.state = 1;
    if (E.lu.x != E.c.x) E.state = 1;
}

// check func
bool OOB(Axis a) { return a.x < 0 || a.x >= N || a.y < 0 || a.y >= N || flat[a.x][a.y] == TREE; }
bool OOB(Log l) { return OOB(l.lu) || OOB(l.c) || OOB(l.rd); }
bool canTurn(Log l) {
    Axis c = l.c;
    return flat[c.x - 1][c.y - 1] == TREE || flat[c.x - 1][c.y] == TREE || flat[c.x - 1][c.y + 1] == TREE || flat[c.x][c.y - 1] == TREE || flat[c.x][c.y + 1] == TREE ||
           flat[c.x + 1][c.y - 1] == TREE || flat[c.x + 1][c.y] == TREE || flat[c.x + 1][c.y + 1] == TREE;
}

// action func
Log move(Log cur, int d) {
    cur.lu += dir[d];
    cur.c += dir[d];
    cur.rd += dir[d];
    return cur;
}

Log turn(Log cur) {
    int idx = 0;
    if (cur.state) idx += 2;
    cur.lu += t[idx];
    cur.rd += t[++idx];
    if (cur.state) {
        Axis tmp = cur.lu;
        cur.lu = cur.rd;
        cur.rd = tmp;
    }
    cur.state ^= 1;
    return cur;
}

// bfs
int sol() {
    int ans = 0;
    queue<Log> q;
    q.push(S);
    visited[S.c.x][S.c.y][S.state] = 1;
    while (!q.empty()) {
        Log cur = q.front();
        q.pop();

        if (cur == E) {
            ans = cur.dist;
            break;
        }
        for (int d = 0; d < 5; d++) {
            Log next;
            if (d == 4) {
                if (!canTurn(cur)) next = turn(cur);
            } else
                next = move(cur, d);
            if (OOB(next)) continue;
            if (visited[next.c.x][next.c.y][next.state]) continue;

            visited[next.c.x][next.c.y][next.state] = 1;
            next.dist = cur.dist + 1;
            q.push(next);
        }
    }

    return ans;
}

void run() {
    input();
    cout << sol();
}

int main() {
    fio;
    run();
    return 0;
}