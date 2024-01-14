#include <cstring>
#include <iostream>
#include <stack>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

string str;
stack<char> s;

void input() { getline(cin, str); }

void sPrint() {
    while (!s.empty()) {
        cout << s.top();
        s.pop();
    }
}

void sol() {
    for (int i = 0; i < str.size(); i++) {
        if (str[i] == '<') {
            sPrint();
            while (str[i] != '>') {
                cout << str[i++];
            }
            cout << str[i];
        } else if (str[i] == ' ') {
            sPrint();
            cout << " ";
        } else {
            s.push(str[i]);
        }
    }
    sPrint();
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