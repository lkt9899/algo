# [[GOLD III] 16236. 아기 상어](https://www.acmicpc.net/problem/16236)
<!-- # [[SWEA_난이도] 문제번호. 문제이름(https://www.) -->

### 문제 설명

```
N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
```

### 입력

```
첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.
```

### 출력

```
첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.
```

### 문제접근

```
- 먹이의 우선순위
    1. 거리가 가까운 순
    2. rowNum이 작은 순
    3. colNum이 작은 순

- BFS를 통해 위, 왼쪽, 오른쪽, 아래 순으로 탐색
- 먹을 수 있다면 toEat List에 저장
- 현재 거리가 toEat에 저장된 먹이의 거리보다 멀다면 BFS 종료
- toEat에 저장된 먹이들을 우선 순위에 따라 정렬 후 먹고 그 자리에서 다시 BFS 시작.
```

## 코드 설명

### Main

```java
// 배열 최대 사이즈
static final int MAX = 20;
// 배열 크기, 나이, 걸린 시간
static int N, age, ans;
// 배열
static int[][] map = new int[MAX][MAX];
// BFS에 필요한 방문 여부
static int[][] visited = new int[MAX][MAX];
// 사방 탐색
static int[] di = { -1, 0, 0, 1 };
static int[] dj = { 0, -1, 1, 0 };
// 상어의 초기 위치
static Axis start;

public static void main(String[] args) throws IOException {
    run();
}
```

### run() - 처리 함수

```java
static void run() throws IOException {
    init();
    input();
    find(start, 0);

    System.out.println(new StringBuilder(ans + "").toString());
}
```

### 입력

```java
static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());

            if (map[i][j] == 9) {
                start = new Axis(i, j, 0);
                map[i][j] = 0;
            }
        }
    }
    br.close();
}
``` 


### 먹이 탐색(BFS)

```java
static void find(Axis cur, int cnt) {
    // 먹이를 나이만큼 먹으면 나이 + 1
    if (cnt == age) {
        cnt = 0;
        age++;
    }

    // 방문여부 초기화
    initVisited();

    // 가까운 먹이 List
    ArrayList<Axis> toEat = new ArrayList<>(50);

    // BFS
    Queue<Axis> q = new ArrayDeque<>();
    q.add(cur);
    visited[cur.x][cur.y] = 1;
    while (!q.isEmpty()) {
        Axis c = q.poll();

        // 거리가 같은 먹이 후보들을 모두 담으면 BFS 종료
        if (!toEat.isEmpty() && c.t > toEat.get(0).t)
            break;

        for (int i = 0; i < 4; i++) {
            int ni = c.x + di[i];
            int nj = c.y + dj[i];

            // Index Bounding
            if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] != 0)
                continue;
            // 나이보다 큰 먹이는 먹을 수 없음.
            if (map[ni][nj] > age)
                continue;

            visited[ni][nj] = 1;
            Axis tmp = new Axis(ni, nj, c.t + 1);

            // 먹이가 존재하고 먹잇감의 크기가 나이보다 작으면 후보군에 추가
            if (map[ni][nj] != 0 && map[ni][nj] < age) {
                toEat.add(tmp);
            } else {
                q.add(tmp);
            }
        }
    }

    // 후보군에 먹이가 존재하면 가장 우선순위가 높은 먹이를 선택해 먹음
    if (!toEat.isEmpty()) {
        // 먹이감 우선순위에 따라 정렬
        Collections.sort(toEat);

        // 가장 높은 우선순위의 먹이
        Axis fish = toEat.get(0);
        // 먹었으니 map에서 삭제
        map[fish.x][fish.y] = 0;
        // 먹은 먹이 수 + 1
        cnt++;
        // 걸린 시간 갱신
        ans += fish.t;
        // 초기화 후 그 자리에서 다시 먹이 탐색 진행
        fish.t = 0;
        find(fish, cnt);
    }
}

// 방문 여부 초기화
static void initVisited() {
    for (int[] a : visited)
        Arrays.fill(a, 0);
}
```

### 좌표 점 객체

```java
static class Axis implements Comparable<Axis> {
    public Integer x, y, t;

    public Axis(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }

    // 비교 함수
    @Override
    public int compareTo(Axis o) {
        if (t == o.t) {
            if (x == o.x) {
                return y.compareTo(o.y);
            }
            return x.compareTo(o.x);
        }
        return t.compareTo(o.t);
    }
}
```