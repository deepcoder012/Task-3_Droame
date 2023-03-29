import java.util.*; 

  

public class DronePathFinder { 

  

    private static final int M = 20; // grid width 

    private static final int N = 20; // grid height 

    private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}; // 8-adjacency 

    private static final int INF = Integer.MAX_VALUE; 

     

    // define a priority queue class for storing paths 

    private static class PriorityQueue { 

        private final List<int[]> elements = new ArrayList<>(); 

  

        public void enqueue(int[] item, int priority) { 

            elements.add(new int[]{priority, item[0], item[1]}); 

            Collections.sort(elements, Comparator.comparingInt(a -> a[0])); 

        } 

  

        public int[] dequeue() { 

            return elements.remove(0); 

        } 

  

        public boolean isEmpty() { 

            return elements.isEmpty(); 

        } 

    } 

     

    // define a function to estimate the distance between two points 

    private static double distance(int x1, int y1, int x2, int y2) { 

        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)); 

    } 

     

    // run A* algorithm to find the path from start to target for a given drone 

    private static int[][] findPath(int x1, int y1, int x2, int y2, int t) { 

        int[][][] grid = new int[M][N][2]; // initialize the grid with cost and time 

        for (int i = 0; i < M; i++) { 

            for (int j = 0; j < N; j++) { 

                grid[i][j][0] = INF; 

                grid[i][j][1] = INF; 

            } 

        } 

        PriorityQueue pq = new PriorityQueue(); 

        pq.enqueue(new int[]{x1, y1}, 0); 

        grid[x1][y1][0] = 0; 

        grid[x1][y1][1] = t; 

  

        while (!pq.isEmpty()) { 

            int[] curr = pq.dequeue(); 

            int x = curr[0], y = curr[1]; 

            if (x == x2 && y == y2) { 

 // reached the target 

                List<int[]> path = new ArrayList<>(); 

                path.add(new int[]{x, y});  

int[] prev = curr;  

while (prev[0] != x1 || prev[1] != y1) 

 { 

 // backtrack to the start 

 int px = prev[1]; int py = prev[2]; 

 path.add(new int[]{px, py}); 

 prev = new int[]{grid[px][py][2], px, py}; 

 }  

Collections.reverse(path); 

 return path.toArray(new int[0][]); 

 } 

 for (int[] dir : DIRECTIONS)  

{ int nx = x + dir[0], ny = y + dir[1]; 

 if (nx < 0 || ny < 0 || nx >= M || ny >= N)  

{ continue; 

 // out of bounds } 

 int cost = (int) distance(x, y, nx, ny);  

int time = cost + 1; // assume speed of 1 unit per second 

 if (grid[x][y][1] + time > t)  

{ continue;  

// not enough time left 

 }  

int newCost = grid[x][y][0] + cost; 

 if (newCost < grid[nx][ny][0]) 

 { grid[nx][ny][0] = newCost; grid[nx][ny][1] = grid[x][y][1] + time; pq.enqueue(new int[]{nx, ny}, newCost); grid[nx][ny][2] = x; }  

}  

}  

return null; // no path found 

 } 

public static void main(String[] args) { 

    Scanner scanner = new Scanner(System.in); 

    System.out.print("Enter the starting point x-coordinate: "); 

    int x1 = scanner.nextInt(); 

    System.out.print("Enter the starting point y-coordinate: "); 

    int y1 = scanner.nextInt(); 

    System.out.print("Enter the target point x-coordinate: "); 

    int x2 = scanner.nextInt(); 

    System.out.print("Enter the target point y-coordinate: "); 

    int y2 = scanner.nextInt(); 

    System.out.print("Enter the time available (in seconds): "); 

    int t = scanner.nextInt(); 

    int[][] path = findPath(x1, y1, x2, y2, t); 

    if (path == null) { 

        System.out.println("No path found!"); 

    } else { 

        System.out.println("Path:"); 

        for (int[] point : path) { 

            System.out.println(Arrays.toString(point)); 

        } 

    } 

} 
