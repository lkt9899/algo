#include <iostream>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const int MAX = 1001;
int N;
int arr[MAX];
int dp[MAX];

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }
}

void sol() {
    int max_v = 1;
    int idx = 0;
    dp[0] = 1;
    for (int i = 1; i < N; i++) {
        int max = 0;
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i]) {
                max = max > dp[j] ? max : dp[j];
            }
        }
        dp[i] = max + 1;
        if (max_v < dp[i]) {
            max_v = dp[i];
            idx = i;
        }
    }

    cout << max_v << "\n";
    vector<int> v;
    for (int i = idx; i >= 0; i--) {
        if (dp[i] == max_v) {
            v.push_back(arr[i]);
            max_v--;
        }
    }

    if (v.empty())
        cout << "1\n" << arr[0];
    else
        for (int i = v.size() - 1; i >= 0; i--) cout << v[i] << " ";
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