# Task-3_Droame
To solve this problem, we can use the A* algorithm which is a popular pathfinding algorithm. The algorithm is guaranteed to find the shortest path from the start point to the destination point. We can use the priority queue data structure to implement the algorithm efficiently. 

Algorithm: 

Define the grid size and create an empty grid. 

Create a Priority Queue for each drone which will store the paths with their priority value. 

For each drone in the input list, initialize the start point, target point and start time. 

For each drone, run the A* algorithm by adding the start point to the Priority Queue with the priority value as 0. 

While the Priority Queue is not empty, dequeue the path with the minimum priority value. 

Check if the current point is the target point. If yes, return to the path. 

Otherwise, get the neighbors of the current point and add them to the Priority Queue with their priority value as the sum of the cost to reach the current point and the estimated cost to reach the target point. 

Continue the algorithm until all drones reach their target point. 
