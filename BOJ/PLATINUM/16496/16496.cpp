#include <iostream>
#include <queue>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 1000

using namespace std;

struct compare {
    bool operator()(string s1, string s2) {
        int len = s1.length() < s2.length() ? s1.length() : s2.length();
        for (int i = 0; i < len; i++) {
            if (s1[i] != s2[i]) return s1[i] < s2[i];
        }

        string tmp1 = "", tmp2 = "";
        tmp1 += s1 + s2;
        tmp2 += s2 + s1;
        return tmp1 < tmp2;
    }
};

int N;
priority_queue<string, vector<string>, compare> pq;

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        pq.push(s);
    }
}

void run() {
    if (pq.top() == "0") {
        cout << 0;
        return;
    }
    
    while (!pq.empty()) {
        cout << pq.top();
        pq.pop();
    }
}

int main() {
    fio;
    input();
    run();
    return 0;
}