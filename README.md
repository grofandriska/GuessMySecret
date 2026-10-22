# 🔮 GuessMySecret

> *"I'm thinking of a number..."* — the computer, smugly.

A tiny Java console game where the computer picks a secret number and you and your friends try to guess it before your dignity runs out. No graphics. No microtransactions. Just you, a blinking cursor, and the crushing weight of "Secret number is bigger!"

---

## 🎲 How it works

1. **Gather your victims** — er, players. You need **at least 2**. Playing alone is just called "thinking."
2. **Everyone picks a name.** The game knights you on the spot: type `Anna` and you become **Curious Anna**. Names must be unique (and yes, `anna` counts as taken). Typing nothing gets you a gentle *"What is your Name, come on!"*
3. **Set the maximum value** (at least **10**). The secret number will be somewhere between `1` and that maximum. Type letters instead of numbers and the game will politely judge you, then ask again.
4. **Count your guesses.** Everyone gets **max ÷ 10** guesses (at least 1). Max 20? Two guesses. Max 100? Ten. Max 1,000,000? That's 100,000 guesses. Pack a lunch.
5. **Take turns guessing.** After every wrong guess the game drops a hint:
   - `Secret number is bigger!` → aim higher, dreamer.
   - `Secret number is smaller!` → easy there, ambition.
6. **Already tried that number?** The game remembers. Repeating yourself doesn't cost a guess — it just gets you a knowing look.
7. **First correct guess wins** and the game proudly announces the champion. If nobody gets it, the secret is revealed and everyone pretends they were *so close*.

> 💡 **Pro strategy:** Players go one after another, and everyone can see the hints. So the real winning move is... to be last in line and pay attention. You didn't hear it from us.

---

## 🚀 Running it

You'll need **Java 8 or newer**.

**Build a jar and play**, from the `guess` folder:

```bash
mkdir out
javac -d out game/GameLoader.java
jar cfm GuessMySecret.jar Manifest.txt -C out .
java -jar GuessMySecret.jar
```

**Or just run it without a jar:**

```bash
javac game/GameLoader.java
java game.GameLoader
```

That's it. The cursor awaits your wisdom.

---

## 🖥️ A typical game night

```
Type in the number of the Players! (min 2)
2
Please add 1. player name!
Anna
Hello Curious Anna!
Please add 2. player name!
anna
There is another anna! Choose another nickname please!
Please add 2. player name!
Bence
Hello Curious Bence!
Please add the maximum value of Secret Number! (min 10)
20

Starting the game! The secret number is between 1 and 20. Everyone has 2 guesses.

It's your turn player called: Curious Anna
Please type in the number of your guess! You have 2 guesses left
10

Secret number is bigger!
Please type in the number of your guess! You have 1 guess left
10
You tried 10 before! This one is on the house, try again.
...
The winner is : Curious Bence! The Secret number was: 17
```

*(Anna has requested a rematch. Anna always requests a rematch.)*

---

## 📁 Project structure

```
guess/
├── game/
│   └── GameLoader.java   # the whole game: brains, rules, and attitude
└── Manifest.txt          # tells the .jar where the game lives (Main-Class: game.GameLoader)
```

---

## 🧠 Fun facts

- The secret number is chosen by `java.util.Random`, which is completely fair and has no favorites. Probably.
- The game is fair-ish: bigger range, more guesses. With a max of 1,000,000 you get 100,000 guesses, which is roughly 99,980 more than a binary search needs. Use them wisely. Or don't.
- No secret numbers were harmed in the making of this game.

---

## 🤝 Contributing

Found a bug? Have an idea? Think the computer is cheating? Open an issue or a pull request — the secret number won't tell anyone.

---

Made with ☕ and exactly one `Scanner` (there used to be a whole crowd of them) by [grofandriska](https://github.com/grofandriska).
