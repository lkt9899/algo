#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

list<int> l;
list<int>::iterator it;
int N, M, L, ans;

void init() { l.clear(); }

void input() {
    cin >> N >> M >> L;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        l.push_back(num);
    }
}

void sol() {
    for (int i = 0; i < M; i++) {
        char cmd;
        int idx, num;
        cin >> cmd;

        it = l.begin();
        switch (cmd) {
            case 'I':
                cin >> idx >> num;
                advance(it, idx);
                l.insert(it, num);
                break;
            case 'D':
                cin >> idx;
                advance(it, idx);
                l.erase(it);
                break;
            case 'C':
                cin >> idx >> num;
                advance(it, idx);
                *it = num;
                break;

            default:
                break;
        }
    }

    if (L > l.size())
        ans = -1;
    else {
        it = l.begin();
        advance(it, L);
        ans = *it;
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