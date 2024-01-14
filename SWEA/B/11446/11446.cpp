#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);
#define ull unsigned long long
#define MAX 10e+18 + 1

using namespace std;

int N;
ull M, ans, _max;
ull candies[100];

void init() { 
    ans = 0;
    _max = MAX;
}

void input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> candies[i];
        _max = _max > candies[i] ? _max : candies[i];
    }
}

void sol() {
    ull s = 1, e = _max;
    while (s <= e) {
        ull mid = (s + e) >> 1;
        ull sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (candies[i] / mid);
        }

        if (sum >= M) {
            s = mid + 1;
            ans = mid;
        } else
            e = mid - 1;
    }
}

void run() {
    init();
    input();
    sol();
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}