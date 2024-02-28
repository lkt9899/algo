#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j < e; j++)

#define M_MAX 701

using namespace std;

int M, N;
int cmd[M_MAX * 2];

void input() {
    i2(M, N);
    fi0(N) {
        int idx = 0;
        fj0(3) {
            int cnt;
            i1(cnt);
            while (cnt--) {
                cmd[idx++] += j;
            }
        }
    }
}

void sol() {
    for (int i = M - 1; i >= 0; i--) {
        cout << cmd[i] + 1 << " ";
        for (int j = M; j < 2 * M - 1; j++) {
            cout << cmd[j] + 1 << " ";
        }
        cout << "\n";
    }
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