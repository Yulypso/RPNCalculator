#!/usr/local/bin/zsh

javac --enable-preview --release 17 src/*.java src/Models/*.java src/Utils/*.java -d out
java --enable-preview -cp out Richalculator