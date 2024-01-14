#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 1025;
int N, M;
int arr[MAX][MAX];

void input() {
    cin >> N >> M;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            int num;
            cin >> num;
            arr[i][j] = arr[i][j - 1] + arr[i - 1][j] + num - arr[i - 1][j - 1];
        }
    }
}

void sol() {
    for (int t = 0; t < M; t++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        int sum = arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1];

        cout << sum << "\n";
    }
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