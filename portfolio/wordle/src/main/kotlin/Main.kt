fun main() 
{
  val words = readWordList("data/words.txt")
  val target = pickRandomWord(words)

  println("Wordle!")
  println("Guess the five letter word. You have six attempts.")

  for (attempt in 1..6)
  {
    val guess = obtaingGuess(attempt)
    val matches = evaluateGuess(guess, target)
    displayGuess(guess, matches)

    if (matches.all{it == 2})
    {
      println("Congratulations! You correctly guessed: $target")
      return
    }
  }
  
  println("All attempts used. The word was: $target")
  
}
