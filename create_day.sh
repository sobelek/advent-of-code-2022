#!/bin/bash

if [ $# -eq 0 ];
then
  echo "$0: Missing day number. Provide day number like: './create_day.sh 8'"
  exit 1
else

  cp ./src/main/templateDay.kt ./src/main/Day$1.kt
  touch ./src/main/Day$1_test.txt
  touch ./src/main/Day$1.txt
fi