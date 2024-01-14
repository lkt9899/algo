#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 49;
int N, ans;
int map[MAX][MAX];

void init() { ans = 0; }

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < N; j++) {
            map[i][j] = s[j] - '0';
        }
    }
}

void sol() {
    int mid = N / 2;
    for (int i = 0, j = N - 1; i != mid; i++, j--) {
        int isum = map[i][mid], jsum = map[j][mid];
        for (int k = i; k != 0; k--) {
            isum += map[i][mid - k] + map[i][mid + k];
            jsum += map[j][mid - k] + map[j][mid + k];
        }
        ans += isum + jsum;
    }
    for (int i = 0; i < N; i++) {
        ans += map[mid][i];
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