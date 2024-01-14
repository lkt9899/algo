#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

void run() {
    int N;
    cin >> N;
    int ans = N / 5;

    if (N == 4 || N == 7)
        cout << -1;
    else if (N % 5 == 0)
        cout << ans;
    else if (N % 5 == 1 || N % 5 == 3)
        cout << ans + 1;
    else if (N % 5 == 2 || N % 5 == 4)
        cout << ans + 2;
}

int main() {
    fio;
    run();
    return 0;
}