# 2048 Game Solver
AI Course Project  

This repository contains the implementation of the popular **2048 game**, along with various tree search algorithms to explore and evaluate strategies for achieving the highest score. This project was developed as part of an AI course and focuses on demonstrating the application of search techniques in game strategy optimization.  

---

## **Game Overview**  

The 2048 game is a sliding tile puzzle played on a `4x4` grid. The goal is to merge tiles of the same value by sliding them in one of four directions (up, down, left, or right) to create tiles with larger values. The game ends when no valid moves remain.  

---

## **Project Details**  

### **Key Features**:  
1. **2048 Game Implementation**:  
   - Full implementation of the game logic, including merging mechanics, score calculation, and move validation.  
2. **AI Solver**:  
   - Implemented various tree search algorithms to determine optimal game strategies.  

### **Search Algorithms Implemented**:  
- **Breadth-First Search (BFS)**: Explores all possible moves at each depth level.  
- **Depth-First Search (DFS)**: Explores each possible move path to its conclusion before backtracking.  
- **Iterative Deepening Search (IDS)**: Combines the benefits of BFS and DFS by incrementally deepening the search depth.  
- **Uniform Cost Search (UCS)**: Considers the cost of each move while exploring the game tree.  
- **A* Search (A\*)**: Uses a heuristic to prioritize moves that maximize tile merging or grid space.  
- **Greedy Search**: Focuses on immediate rewards without considering long-term consequences.  
- **Iterative Deepening A* (IDA\*)**: A memory-efficient variation of A* that uses iterative deepening to explore the game tree.  
- **Optimal IDA\***: A refined version of IDA\* with further optimization for move evaluation.  

---

## **Analysis and Results**  

- Compared the performance of various search algorithms in terms of achieved score, computational efficiency, and memory usage.  
- A\* and IDA\* demonstrated superior performance by effectively balancing search depth and heuristic quality.  
- BFS and UCS provided comprehensive solutions but were less efficient for larger search spaces.  
