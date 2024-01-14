#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 5001;
int N, P, s, e;
int bus[MAX];
int ans[500];

void init() { fill(&bus[0], &bus[MAX], 0); }

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> s >> e;
        for (int j = s; j <= e; j++) {
            bus[j]++;
        }
    }

    cin >> P;
    for (int i = 0; i < P; i++) {
        int tmp;
        cin >> tmp;
        ans[i] = bus[tmp];
    }
}

void sol() {
    for (int i = 0; i < P; i++) {
        cout << ans[i] << " ";
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
        cout << "#" << test_case << " ";
        run();
        cout << "\n";
    }
    return 0;
}