# 🐝 Spelling Bee (Java Console Game)

A command-line recreation of the **New York Times “Spelling Bee”** word game, written entirely in Java.  
Players are given seven random letters (the *hive*) and must find as many valid words as possible that:
- Are **at least 4 letters long**  
- Contain **only** letters from the hive  
- Always include the **center letter**

---

## ✨ Features
- **Weighted letter generation** using real English frequency data — vowels are more common, Zs are rare.  
- **Hive validation**: each hive is guaranteed to contain at least one vowel.  
- **Dictionary filtering** powered by the [enable1 word list](https://github.com/dwyl/english-words/blob/master/words.txt) (a large public domain English word set).  
- **Bitmask-based word checking** for fast letter inclusion tests.  
- **Automatic scoring**:
  - 4-letter words → 1 point  
  - 5+ letters → 1 point per letter  
  - *Pangram* (uses all 7 letters) → +7 bonus points  
- Tracks found words, prevents duplicates, and displays your score in real time.

---

## 🧩 How It Works
1. **Hive generation** – seven random letters are chosen based on frequency weighting; at least one vowel is guaranteed.  
2. **Dictionary filtering** – `enable1.txt` is scanned, keeping only words that can be built from the hive and include the center letter.  
3. **Gameplay loop** – users input guesses in the console; guesses are validated, scored, and added to the found set.  
4. **Bitmasking** – each word is represented as a 26-bit integer mask to check legality in O(1).

---

## 🖥️ Running the Game
### Prerequisites
- Java 17+ (earlier versions likely work)
- `enable1.txt` word list file (place it in the same directory as the `.java` file)

### Compile and run
```bash
javac SpellingBee.java
java SpellingBee
