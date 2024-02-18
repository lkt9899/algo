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

using namespace std;

string S, T;

void input() { i2(S, T); }

void sol() {
    int ans = 0;
    while (T.length() > S.length()) {
        char last = T[T.length() - 1];
        T.erase(T.length() - 1, 1);

        if (last == 'B') reverse(T.begin(), T.end());
        if (T == S) ans = 1;
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