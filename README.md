# Hedgehog problem
Hedgehog path task

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