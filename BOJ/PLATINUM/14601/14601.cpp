#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cout.tie(0)
#define MAX 1 << 7 + 1

using namespace std;

int K, tx, ty, cnt = 0;
int tile[MAX][MAX];

void init() { memset(tile, 0, sizeof(tile)); }

void input() {
    cin >> K >> ty >> tx;
    tile[tx][ty] = -1;
}

bool isIncludeTarget(int x, int y, int len) {
    for (int i = x; i < x + len; i++)
        for (int j = y; j < y + len; j++)
            if (tile[i][j] != 0) return false;

    return true;
}

void check(int x, int y, int len) {
    cnt++;
    int half = len >> 1;
    if (isIncludeTarget(x, y, half)) tile[x + half - 1][y + half - 1] = cnt;
    if (isIncludeTarget(x + half, y, half)) tile[x + half][y + half - 1] = cnt;
    if (isIncludeTarget(x, y + half, half)) tile[x + half - 1][y + half] = cnt;
    if (isIncludeTarget(x + half, y + half, half)) tile[x + half][y + half] = cnt;

    if (len == 2) return;

    check(x, y, half);
    check(x + half, y, half);
    check(x, y + half, half);
    check(x + half, y + half, half);
}

void print() {
    for (int i = 1 << K; i >= 1; i--) {
        for (int j = 1; j <= 1 << K; j++) cout << tile[i][j] << " ";
        cout << "\n";
    }
}

void run() {
    init();
    input();
    check(1, 1, 1 << K);
    print();
}

int main() {
    fio;
    run();
    return 0;
}