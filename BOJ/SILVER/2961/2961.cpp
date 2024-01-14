#include <cmath>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0)

using namespace std;

const int MAX = 10;
int N, S, B, ans;
int sour[MAX], bitter[MAX];

void init() { ans = 1 << 30; }

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> sour[i] >> bitter[i];
    }
}

void mix(int idx, int s, int b) {
    ans = min(ans, abs(b - s));
    for (int i = idx; i < N; i++) {
        mix(i + 1, s * sour[i], b + bitter[i]);
    }
}

void sol() {
    for (int i = 0; i < N; i++) {
        mix(i + 1, sour[i], bitter[i]);
    }
    cout << ans;
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