#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

int arr[8], num[5];
int aidx, nidx;

void init() {
    aidx = nidx = -1;
    for (int i = 0; i < 5; i++) {
        num[i] = i + 1;
    }
}

void input() {
    for (int i = 0; i < 8; i++) cin >> arr[i];
}

void sol() {
    while (1) {
        ++aidx %= 8;
        ++nidx %= 5;
        arr[aidx] -= num[nidx];
        if (arr[aidx] <= 0) {
            arr[aidx] = 0;
            ++aidx %= 8;
            break;
        }
    }
}

void run() {
    init();
    input();
    sol();
}

void print() {
    for (int i = 0; i < 8; i++) {
        cout << arr[aidx] << " ";
        ++aidx %= 8;
    }
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    T = 10;
    for (test_case = 1; test_case <= T; ++test_case) {
        cin >> test_case;
        run();
        cout << "#" << test_case << " ";
        print();
        cout << "\n";
    }
    return 0;
}