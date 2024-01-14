#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 14;
int S[MAX], C[MAX], H[MAX], D[MAX];
int sCnt, cCnt, hCnt, dCnt, flag;
string info;

void init() {
    sCnt = cCnt = hCnt = dCnt = 0;
    memset(S, 0, sizeof(S));
    memset(C, 0, sizeof(C));
    memset(H, 0, sizeof(H));
    memset(D, 0, sizeof(D));
}

void input() { cin >> info; }

void sol() {
    for (int i = 0; i < info.size(); i += 3) {
        flag = 0;
        int num = 10 * (info[i + 1] - '0') + (info[i + 2] - '0');
        if (info[i] == 'S') {
            if (S[num] == 1) {
                return;
            }
            S[num] = 1;
            sCnt++;
        } else if (info[i] == 'C') {
            if (C[num] == 1) {
                return;
            }
            C[num] = 1;
            cCnt++;
        } else if (info[i] == 'H') {
            if (H[num] == 1) {
                return;
            }
            H[num] = 1;
            hCnt++;
        } else {
            if (D[num] == 1) {
                return;
            }
            D[num] = 1;
            dCnt++;
        }
        flag = 1;
    }
}

void run() {
    init();
    input();
    sol();
}

void print() {
    if (flag) {
        cout << 13 - sCnt << " " << 13 - dCnt << " " << 13 - hCnt << " " << 13 - cCnt << "\n";
    } else
        cout << "ERROR\n";
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
    }
    return 0;
}