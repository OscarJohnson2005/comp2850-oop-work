// Implement the six required functions here
import java.io.File
import kotlin.random.Random

//1. word validity
fun isValid(word: String): Boolean=
  word.length == 5 && word.all { it.isLetter() }

//2. Read words from file into list
fun readWordList(filename: String): MutableList<String> =
  File(filename).readlines().map { it.trim().lowercase() }.toMutableList()

//3. pick a word, remove it from list
fun pickRandomWord(words: MutableList<String>): String
  {
  val word = words.random()
  words.remove(word)
  return word
  }

//4. prompt user for guess
fun obtainGuess(attempt: Int): String
{
while(true)
  {
    print("Attempt $attempt: ")
    if (isValid(guess)) return guess
    println("Invalid guess. Please enter a 5-letter word.")
  }
}

//5. guess evaluation (with full wordle rules!!!)
fun evaluateGuess(guess: String, target: String): List<Int>
  {
    val result = MutableList(5) {0}
    val targetCharCounts = target.groupingBy {it}.eachCount().toMutableMap()
    //checking exact matches
    for (i in guess.indices)
    {
      if (guess[i] == target[i])
      {
        result[i] = 2
        targetCharCounts[guess[i]] = targetCharCounts[guess[i]]!! - 1
      }
    }
    
    //2nd pass, wrong position right letter
    for (i in guess.indices)
    {
      if (result[i] == 0 && targetCharCounts.getOrDefault(guess[i], 0) > 0)
      {
        result[i] = 1
        targetCharCounts[guess[i]] = targetCharCounts[guess[i]]!! - 1
      }
    }
    
    return result
  }

  //6. Display colour coded output
  fun displayGuess(guess: String, matches: List<Int>)
{
  for (i in guess.indices)
  {
    when(matches[i])
    {
      2 -> print("\u001B[32m${guess[i]}\u001B[0m")//green
      1 -> print("\u001B[33m${guess[i]}\u001B[0m")//yellow
      0 -> print(guess[i])
    }
  }
  println()
}
  
