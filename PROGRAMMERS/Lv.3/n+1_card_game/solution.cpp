#include <set>
#include <vector>

using namespace std;

int N, C, answer;
set<int> hands, pick;

bool check(set<int> &f, set<int> &m) {
    for (auto card : f) {
        int toFind = N + 1 - card;
        if (m.find(toFind) != m.end()) {
            f.erase(card);
            m.erase(toFind);
            return true;
        }
    }
    return false;
}

int solution(int coin, vector<int> cards) {
    // init
    answer = 1;
    N = cards.size();
    C = coin;

    // draw
    int idx = N / 3;
    for (int i = 0; i < idx; i++) {
        hands.insert(cards[i]);
    }

    while (idx < N) {
        // pick
        pick.insert(cards[idx]);
        pick.insert(cards[idx + 1]);
        idx += 2;

        // check
        if (hands.size() >= 2 && check(hands, hands)) {
            answer++;
        } else if (C >= 1 && hands.size() >= 1 && pick.size() >= 1 && check(hands, pick)) {
            answer++;
            C--;
        } else if (C >= 2 && pick.size() >= 2 && check(pick, pick)) {
            answer++;
            C -= 2;
        } else
            break;
    }

    return answer;
}

#include <iostream>

/* test input
4
4
12
3 6 7 2 1 10 5 9 8 12 11 4
3
12
1 2 3 4 5 8 6 7 9 10 11 12
2
12
5 8 1 2 9 4 12 11 3 10 6 7
10
18
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
*/
int main() {
    int T, n, coin;
    vector<int> v;
    freopen("input.txt", "r", stdin);
    cin >> T;
    while (T--) {
        cin >> coin >> n;
        for (int i = 0; i < n; i++) {
            int num;
            cin >> num;
            v.push_back(num);
        }
        cout << solution(coin, v) << "\n";
        v.clear();
        hands.clear();
        pick.clear();
    }
}