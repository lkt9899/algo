#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 100001;
int N;
int heap[MAX];
int heap_cnt = 0;

void push(int val) {
    heap[heap_cnt] = val;
    int cur = heap_cnt;
    while (cur > 0 && heap[cur] > heap[(cur - 1) / 2]) {
        int tmp = heap[(cur - 1) / 2];
        heap[(cur - 1) / 2] = heap[cur];
        heap[cur] = tmp;
        cur = (cur - 1) / 2;
    }

    heap_cnt++;
}

int pop() {
    if(heap_cnt == 0) {
        return -1;
    }
    
    int value = heap[0];
    heap_cnt--;
    heap[0] = heap[heap_cnt];

    int cur = 0;
    while (cur * 2 + 1 < heap_cnt) {
        int c;
        if (cur * 2 + 2 == heap_cnt) {
            c = cur * 2 + 1;
        } else {
            c = heap[cur * 2 + 1] > heap[cur * 2 + 2] ? cur * 2 + 1 : cur * 2 + 2;
        }

        if (heap[cur] > heap[c]) break;

        int tmp = heap[cur];
        heap[cur] = heap[c];
        heap[c] = tmp;
        cur = c;
    }

    return value;
}

void init() { heap_cnt = 0; }

void input() { cin >> N; }

void sol() {
    for (int i = 0; i < N; i++) {
        int cmd;
        cin >> cmd;
        if(cmd == 1) {
            int val;
            cin >> val;
            push(val);
        } else {
            cout << pop() << " ";
        }
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
        cout << "#" << test_case << " ";
        run();
        cout << "\n";
    }
    return 0;
}