#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_K 100

using namespace std;

struct Star {
    int x, y;
} stars[MAX_K];

int N, M, L, K;

void init() {}

void input() {
    i2(N, M);
    i2(L, K);
    fi0(K) i2(stars[i].y, stars[i].x);
}

int check(Star s1, Star s2) {
    int cnt = 0;
    int lx = min(s1.x, s2.x);
    int ly = min(s1.y, s2.y);
    fi0(K) if (lx <= stars[i].x && stars[i].x <= lx + L && ly <= stars[i].y && stars[i].y <= ly + L) cnt++;
    return cnt;
}

void sol() {
    int ans = 0;
    fi0(K) fj0(K) ans = max(ans, check(stars[i], stars[j]));
    cout << K - ans;
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