#!/usr/bin/env bash

if [ $# -ne 2 ]; then
  echo "Usage:"
  echo "./prep.sh <year> <day>"
  echo "For instance, for day 1 in 2020:"
  echo "./prep 2020 1"
  echo "This will create the appropriate program and test files, start a browser page and"
  echo "load the files in IntelliJ."

  exit 1
fi

DAYSTRING=$(printf "%02d" "$2")

mkdir -p src/main/kotlin/solution/_"$1"/
mkdir -p src/test/kotlin/solution/_"$1"/
mkdir -p src/test/resources/"$1"/day"$DAYSTRING"/


#SESSION_COOKIE=$(cat .session_cookie_github)
SESSION_COOKIE=$(cat .session_cookie)
curl "https://adventofcode.com/$1/day/$2/input" \
    -H "cookie: session=$SESSION_COOKIE" > src/test/resources/"$1"/day"$DAYSTRING"/day"$DAYSTRING".input
touch src/test/resources/"$1"/day"$DAYSTRING"/day"$DAYSTRING".sample1

inputfile="src/test/resources/$1/day${DAYSTRING}/day${DAYSTRING}.input"

if [ -e "$inputfile" ]; then
    # Get the number of lines in the file
    num_lines=$(wc -l < "$inputfile")

    # Check if the number of lines is 1
    if [ "$num_lines" -eq 1 ]; then
        cp src/main/resources/template/DayNSingleString.template src/main/kotlin/solution/_"$1"/Day"$DAYSTRING".kt
        cp src/test/resources/template/DayNsingleStringTest.template src/test/kotlin/solution/_"$1"/Day"$DAYSTRING"Test.kt
    else
        cp src/main/resources/template/DayN.template src/main/kotlin/solution/_"$1"/Day"$DAYSTRING".kt
        cp src/test/resources/template/DayNTest.template src/test/kotlin/solution/_"$1"/Day"$DAYSTRING"Test.kt
    fi
else
    echo "File doesn't exist"
fi

sed -i "s/#YEAR#/$1/g" src/main/kotlin/solution/_"$1"/Day"$DAYSTRING".kt
sed -i "s/#DAY#/$DAYSTRING/g" src/main/kotlin/solution/_"$1"/Day"$DAYSTRING".kt
sed -i "s/#YEAR#/$1/g" src/test/kotlin/solution/_"$1"/Day"$DAYSTRING"Test.kt
sed -i "s/#DAY#/$DAYSTRING/g" src/test/kotlin/solution/_"$1"/Day"$DAYSTRING"Test.kt

sensible-browser "https://adventofcode.com/$1/day/$2"

/mnt/c/Program\ Files/JetBrains/IntelliJ\ IDEA\ 2025.2.1/bin/idea64.exe ./src/test/resources/"$1"/day"${DAYSTRING}"/day"${DAYSTRING}".input
/mnt/c/Program\ Files/JetBrains/IntelliJ\ IDEA\ 2025.2.1/bin/idea64.exe ./src/test/resources/"$1"/day"${DAYSTRING}"/day"${DAYSTRING}".sample1
/mnt/c/Program\ Files/JetBrains/IntelliJ\ IDEA\ 2025.2.1/bin/idea64.exe ./src/main/kotlin/solution/_"$1"/Day"${DAYSTRING}".kt
/mnt/c/Program\ Files/JetBrains/IntelliJ\ IDEA\ 2025.2.1/bin/idea64.exe ./src/test/kotlin/solution/_"$1"/Day"${DAYSTRING}"Test.kt

gradle test --tests "solution._$1.Day${DAYSTRING}Test"
