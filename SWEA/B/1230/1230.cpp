#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

list<int> l;
list<int>::iterator it;

int N, C;

void init() { l.clear(); }

void run() {
    init();
    cin >> N;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        l.push_back(num);
    }

    cin >> C;
    for (int i = 0; i < C; i++) {
        char cmd;
        int idx, cnt, num;

        it = l.begin();
        cin >> cmd;
        switch (cmd) {
            case 'I':
                cin >> idx >> cnt;
                advance(it, idx);
                for (int k = 0; k < cnt; k++) {
                    cin >> num;
                    l.insert(it, num);
                }
                break;
            case 'D':
                cin >> idx >> cnt;
                advance(it, idx);
                for (int k = 0; k < cnt; k++) {
                    it = l.erase(it);
                }
                break;
            case 'A':
                cin >> cnt;
                for (int i = 0; i < cnt; i++) {
                    cin >> num;
                    l.push_back(num);
                }
                break;

            default:
                break;
        }
    }
}

void print() {
    it = l.begin();
    for (int i = 0; i < 10; i++) {
        cout << *it++ << " ";
    }
}

int main(int argc, char** argv) {
    fio;
    int T = 10, test_case;

    freopen("input.txt", "r", stdin);
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " ";
        print();
        cout << "\n";
    }
    return 0;
}