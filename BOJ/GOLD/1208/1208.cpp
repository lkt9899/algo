#include <algorithm>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 40

using namespace std;

int N, S, P = -1;
int sums[40];

void init() {}

void input() {
    i2(N, S);
    fi0(N) i1(sums[i]);
    sort(sums, sums + N);
    fi1(N - 1) {
        sums[i] += sums[i - 1];
        if (P == -1 && sums[i] > sums[i - 1]) P = i;
    }
}

bool leftbs(int start, int end, int target) {
    if (start == end) return target - sums[start] == S;

    int mid = ((start + end) >> 1);
    if (target - sums[mid] == S) return true;
    if (target - sums[mid] < S) return bs();
}

void sol() { fi0(N) cout << sums[i] << " "; }

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