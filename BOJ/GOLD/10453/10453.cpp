#include <algorithm>
#include <iostream>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

string originA, originB;

void input() { cin >> originA >> originB; }

bool check() {
    string A = originA;
    string B = originB;

    sort(A.begin(), A.end());
    sort(B.begin(), B.end());

    int cnt1 = A.find('b');
    int cnt2 = B.find('b');

    return cnt1 == cnt2;
}

void swap(int i, int j) {
    char tmp = originA[i];
    originA[i] = originA[j];
    originA[j] = tmp;
}

void sol() {
    int ans = 0;
    if (!check()) {
        ans = -1;
    } else {
        for (int i = 0; i < originA.length(); i++) {
            if (originA[i] != originB[i]) {
                int j = i + 1;
                while (originA[i] == originA[j]) {
                    j++;
                }
                ans += j - i;
                swap(i, j);
            }
        }
    }

    cout << ans << "\n";
}

void run() {
    input();
    sol();
}

int main() {
    fio;

    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        run();
    }

    return 0;
}