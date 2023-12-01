#!/bin/bash

YEAR=$(($(date '+%Y')))
DAY=$(($(date '+%d')))
TOKEN=$(cat ./cookie.txt)

curl -b session=${TOKEN} https://adventofcode.com/${YEAR}/day/${DAY}/input > inputs/day${DAY}.txt

