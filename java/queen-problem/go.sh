#!/bin/bash -v

for i in {17..17}
do
  java -XX:CompileThreshold=2 -jar target/QueenProblem-1.0-SNAPSHOT.jar "$i" PS PR > "out/queens-$ix$i-PS-PR.lst"
done
