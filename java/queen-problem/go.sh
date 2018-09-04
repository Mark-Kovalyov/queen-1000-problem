#!/bin/bash -v

for i in {5..8}
do
  java -XX:CompileThreshold=2 -jar target/QueenProblem-1.0-SNAPSHOT.jar "$i" PS PR > "out/queens-$ix$i-PS-PR.lst"
done
