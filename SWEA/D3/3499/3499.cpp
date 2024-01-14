#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 1000;
int N, mid;
string deck1[MAX];
string deck2[MAX];
string ans[MAX];

void input() {
    cin >> N;
    mid = (N + 1) / 2;
    for (int i = 0; i < N; i++) {
        if (i < (N + 1) / 2)
            cin >> deck1[i];
        else
            cin >> deck2[i - mid];
    }
}

void sol() {
    for (int i = 0; i < mid; i++) {
        ans[2 * i] = deck1[i];
        ans[2 * i + 1] = deck2[i];
    }
}

void run() {
    input();
    sol();
}

void print() {
    for (int i = 0; i < N; i++) {
        cout << ans[i] << " ";
    }
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " ";
        print();
        cout << "\n";
    }
    return 0;
}