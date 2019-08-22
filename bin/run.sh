#!/usr/bin/env bash

SCRIPT_DIR=$(dirname $(readlink -f $0))
echo "Usage: $0 <inputFilePath> [outputFilePath]"
java $JVM_OPTS -jar $SCRIPT_DIR/hedgehog.jar $@
RC=$?
echo "Done"
exit $RC
