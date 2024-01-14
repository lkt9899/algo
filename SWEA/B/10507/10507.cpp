#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 200000;
int N, P, ans;
int studyLog[200000];

void init() { ans = 0; }

void input() {
    cin >> N >> P;
    for (int i = 0; i < N; i++) cin >> studyLog[i];
}

void sol() {
    for (int i = 0; i < N; i++) {
        int s = i;
        int e = N - 1;
        int mid;

        while (s <= e) {
            mid = (s + e) >> 1;
            int entire = studyLog[mid] - studyLog[i] + 1;
            int study = mid - i + 1;
            int blank = entire - study;

            if (blank > P)
                e = mid - 1;
            else {
                int left = P - blank;
                entire += left;
                s = mid + 1;
                ans = ans > entire ? ans : entire;
            }
        }
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