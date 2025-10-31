Spelling Bee (Java Console Game)
================================

A command-line recreation of the New York Times "Spelling Bee" game written in Java.

Players are given seven random letters (the hive) and must find as many valid words as possible that:
- Are at least 4 letters long
- Contain only letters from the hive
- Always include the center letter

-----------------------------------
Features
-----------------------------------
- Weighted letter generation using English letter frequencies
- Hive validation to guarantee at least one vowel
- Dictionary filtering using the enable1 word list
- Fast bitmask-based letter checks
- Automatic scoring:
  • 4-letter words = 1 point
  • 5+ letters = word length points
  • Pangram (uses all 7 letters) = +7 bonus
- Tracks found words and prevents duplicates

-----------------------------------
Project Layout
-----------------------------------
SpellingBeeRemake/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── SpellingBee.java
│       └── resources/
│           └── enable1.txt
│
├── README.txt
└── LICENSE

-----------------------------------
Requirements
-----------------------------------
- Java 17 or newer (earlier versions likely work)
- enable1.txt file located at src/main/resources/enable1.txt

-----------------------------------
How to Compile and Run
-----------------------------------

1. Open a terminal or PowerShell window in the project root
   (the folder that contains the "src" directory).

2. Compile the program into an "out" directory:

   Windows / PowerShell:
       javac -d out src\main\java\SpellingBee.java

   macOS / Linux:
       javac -d out src/main/java/SpellingBee.java

3. Run the program and include both compiled classes and resources in the classpath:

   Windows / PowerShell:
       java -cp "out;src\main\resources" SpellingBee

   macOS / Linux:
       java -cp "out:src/main/resources" SpellingBee

   (Quotes are required in PowerShell because the semicolon ';' separates commands.)

4. Play the game in the console!

-----------------------------------
Example Session
-----------------------------------
[H, I, L, E, A, T, S]  Center letter is: E
Maximum possible score is: 265

Score: 0
Guess a word
> LATE
Correct! Score +4

Score: 4
Guess a word
> TILES
Correct! Score +5

-----------------------------------
Common Issues
-----------------------------------
- If you see "Error reading resource enable1.txt":
  Make sure the file is located at src/main/resources/enable1.txt
  and that you are running from the project root with the correct classpath.

- If you see "The module 'src' could not be loaded" in PowerShell:
  Use quotes around the classpath as shown above.

-----------------------------------
License
-----------------------------------
This project is released under the MIT License. You are free to use, modify,
and distribute this software with attribution.

-----------------------------------
Author
-----------------------------------
Developed by Thomas Klenk
