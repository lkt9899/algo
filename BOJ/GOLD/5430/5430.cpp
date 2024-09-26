#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

using namespace std;

bool flag;
int T, N, l, r, dir;
string cmd, arr;
vector<int> v;

void init() {
    dir = 0;
    flag = true;
    l = 0, r = v.size() - 1;
}

void split() {
    istringstream iss(arr);
    string buf;
    while (getline(iss, buf, ',')) {
        v.push_back(stoi(buf));
    }
}

void input() {
    v.clear();
    i1(cmd);
    i1(N);
    i1(arr);
    arr = arr.substr(1, arr.size() - 2);
    split();
}

void reverse() { dir ^= 1; }
void del() {
    if (l > r)
        flag = false;
    else
        dir ? r-- : l++;
}

void sol() {
    for (char c : cmd) {
        if (!flag) break;
        switch (c) {
            case 'R':
                reverse();
                break;
            case 'D':
                del();
                break;
        }
    }

    if (!flag)
        cout << "error\n";
    else {
        cout << "[";
        if (dir) {
            for (; r >= l; r--) {
                cout << v[r];
                if (r != l) cout << ",";
            }
        } else {
            for (; l <= r; l++) {
                cout << v[l];
                if (l != r) cout << ",";
            }
        }
        cout << "]\n";
    }
}

void run() {
    i1(T);
    fi0(T) {
        input();
        init();
        sol();
    }
}

int main() {
    fio;
    run();
    return 0;
}