#include <iostream>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

int MAX;
string arr[5];

void init() { MAX = 0; }

void input() {
    for (int i = 0; i < 5; i++) {
        cin >> arr[i];
        MAX = MAX > arr[i].length() ? MAX : arr[i].length();
    }
}

void sol() {
    for (int j = 0; j < MAX; j++) {
        for (int i = 0; i < 5; i++) {
            if (j >= arr[i].length()) continue;
            cout << arr[i][j];
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
        cout << "#" << test_case << " ";
        run();
        cout << "\n";
    }
    return 0;
}