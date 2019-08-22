#!/usr/bin/env bash

echo "Usage: $0 <inputFilePath> [outputFilePath]"
java $JVM_OPTS -jar bin/hedgehog.jar $@
RC=$?
echo "Done"
exit $RC
