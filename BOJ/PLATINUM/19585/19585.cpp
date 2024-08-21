#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define fi0(e) for (int i = 0; i < e; i++)

#define MAX_N 4000
#define MAX_Q 20000

using namespace std;

unordered_set<string> name;
string s;

struct Trie {
    unordered_map<char, Trie *> ch;
    bool isEnd;

    void push(int i) {
        if (i == s.size()) isEnd = true;
        else {
            char c(s[i]);
            if (!ch.count(c)) ch[c] = new Trie();
            ch[c]->push(i + 1);
        }
    }

    int find(int i) {
        if (!ch.count(s[i]) || i == s.size()) return 0;
        if (ch[s[i]]->isEnd && name.count(s.substr(i + 1))) return 1;
        return ch[s[i]]->find(i + 1);
    }
};

int C, N, Q;
Trie color;

void input() {
    i2(C, N);
    fi0(C) {
        i1(s);
        color.push(0);
    }
    fi0(N) {
        i1(s);
        name.insert(s);
    }
    i1(Q);
}

void sol() {
    fi0(Q) {
        i1(s);
        cout << (color.find(0) ? "Yes" : "No") << "\n";
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