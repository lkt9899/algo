#include <iostream>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi(a) for (int i = 0; i < a; i++)

#define MAX_N 1001

using namespace std;

struct Rgb {
    int r, g, b;
} houses[MAX_N];

int N;
int start[3];
int dp[MAX_N][3];

void input() {
    i1(N);
    fi(N) { i3(houses[i].r, houses[i].g, houses[i].b); }
}

void sol() {
    fi(N) {
        if (i == 0) {
            start[0] = 0;
            start[1] = 1;
            start[2] = 2;

            dp[i][0] = houses[i].r;
            dp[i][1] = houses[i].g;
            dp[i][2] = houses[i].b;
        } else {
        }
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