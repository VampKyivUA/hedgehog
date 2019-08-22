#!/usr/bin/env bash

javac -d out/ \
    -sourcepath src/main/java/net/vampkyivua/hedgehog \
    src/main/java/net/vampkyivua/hedgehog/*.java

jar cfm bin/hedgehog.jar src/main/resources/MANIFEST.MF \
    -C out/ net \
    -C src/main/resources logger.properties
