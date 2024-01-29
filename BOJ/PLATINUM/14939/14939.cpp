#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)
#define fk0(e) for (int k = 0; k < e; k++)
#define fk1(e) for (int k = 1; k <= e; k++)

#define MAX 10

using namespace std;

const char ON = 'O';
const char OFF = '#';
int origin[MAX][MAX];
int temp[MAX][MAX];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void input() {
    fi0(MAX) {
        fj0(MAX) {
            char c;
            i1(c);
            if (c == ON) origin[i][j] = 1;
        }
    }
}

void init() { fi0(MAX) fj0(MAX) temp[i][j] = origin[i][j]; }

bool outOfBound(int x, int y) { return x < 0 || x >= MAX || y < 0 || y >= MAX; }

int toggle(int x, int y) {
    fi0(4) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (outOfBound(nx, ny)) continue;
        temp[nx][ny] ^= 1;
    }
    temp[x][y] ^= 1;
    return 1;
}

bool isAllOff() {
    fi0(MAX) fj0(MAX) if (temp[i][j]) return false;
    return true;
}

void sol() {
    int ans = 1e9;
    fi0(1 << MAX) {
        int cnt = 0;
        init();
        fj0(MAX) if (i & (1 << j)) cnt += toggle(0, j);

        fj1(MAX - 1) { fk0(MAX) if (temp[j - 1][k]) cnt += toggle(j, k); }
        if (isAllOff()) ans = ans < cnt ? ans : cnt;
    }

    cout << ans;
}

void run() {
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}