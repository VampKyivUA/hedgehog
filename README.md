# Hedgehog problem

Imagine the rectangular garden sized M x N. Garden consists of square zones with one
apple-tree in each zone. There can be several apples under each tree.
There is an hedgehog in upper left square of the garden. 

It moves to lower right corner with
some restrictions:
* the hedgehog can only move to the next right or to the next lower square.

## Tech conditions
* The garden plan is given as a table "apples" witch consists of M rows and N
columns.
* Table element apples[i,j] indicates a number of apples under the tree with
  coordinates i,j.

# Problem
This is an inverse of shortest path problem in a weighted directed graph.

## Solution
Current solution uses _Dijkstra SPF algorithm_ with conditions being inverted to find more "costly" path.

Time complexity using binary heap is O(|E| + |V| * log|V|)

With a given hedgehog restrictions => `|E| <= 2|V|`, 
which approximates into `O(|V| * log|V|)` time complexity 

## Alternatives
_A* algorithm_. Modification of Dijkstra's SPF. 

However, in current case, where all paths between opposite corners are constant length, A* becomes an SPF/


# Execute
```./bin/run.sh <inputFile> [outputFile]```

# Test examples are located under data/input
```
$ cd <download dir>
$ mkdir ./data/output
$ JVM_OPTS=-Dhedgehog.verbose ./bin/run.sh ./data/input/3x5_pre_1.txt ./data/output/3x5_pre_1.txt
Usage: ./bin/run.sh <inputFilePath> [outputFilePath]
Grid: 
[1, 2, 4, 2, 2]
[1, 3, 2, 3, 9]
[1, 4, 3, 4, 6]
 
Cost: 23
Path:
(0, 0)
(1, 0)
(1, 1)
(1, 2)
(2, 2)
(3, 2)
(4, 2) 
Done
$ cat ./data/output/3x5_pre_1.txt && echo
23
$ 
```