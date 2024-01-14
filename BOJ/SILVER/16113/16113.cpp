#include <iostream>
#include <string>
#include <unordered_map>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int N;
unordered_map<string, int> um;
string digit[20000];

void init() {
    um.insert({"######...######", 0});
    um.insert({"#####", 1});
    um.insert({"#.####.#.####.#", 2});
    um.insert({"#.#.##.#.######", 3});
    um.insert({"###....#..#####", 4});
    um.insert({"###.##.#.##.###", 5});
    um.insert({"######.#.##.###", 6});
    um.insert({"#....#....#####", 7});
    um.insert({"######.#.######", 8});
    um.insert({"###.##.#.######", 9});
}

void input() {
    cin >> N;
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < N / 5; j++) {
            char c;
            cin >> c;
            digit[j] += c;
        }
    }
}

void sol() {
    string s = "";

    for (int i = 0; i < N / 5; i++) {
        if (digit[i] == ".....") {
            if (s == "") continue;
            int n = um[s];
            cout << um[s];
            s = "";
        } else
            s += digit[i];
    }

    if (s != "") cout << um[s];
}

void run() {
    init();
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}