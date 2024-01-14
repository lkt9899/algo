#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);
#define ull unsigned long long
using namespace std;

ull N, ans;

int run() {
    cin >> N;
    N = (ull)(N << 1);
    ans = sqrt(N);
    if (N != ans * (ans + 1)) return -1;

    return ans;
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        cout << "#" << test_case << " " << run() << "\n";
    }
    return 0;
}