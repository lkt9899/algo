#include <iostream>
#include <map>
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

class Ant {
   private:
    map<string, Ant*> child;

   public:
    void insert(vector<string>& foods, int idx) {
        if (idx == foods.size()) return;

        if (child.find(foods[idx]) == child.end()) child[foods[idx]] = new Ant();
        child[foods[idx]]->insert(foods, idx + 1);
    }

    void print(int depth) {
        for (auto node : child) {
            fi0(depth) cout << "--";
            cout << node.first << "\n";
            node.second->print(depth + 1);
        }
    }
};

void run() {
    int N;
    Ant* root = new Ant;

    i1(N);
    fi0(N) {
        int M;
        i1(M);

        vector<string> v(M);
        fj0(M) i1(v[j]);

        root->insert(v, 0);
    }

    root->print(0);
    delete root;
}

int main() {
    fio;
    run();
    return 0;
}