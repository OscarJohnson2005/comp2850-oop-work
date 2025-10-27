import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
@Suppress("unused")
class WordleTest : StringSpec({
"isValid should return true when word is five letters only"
    {
        withClue("valid five letter word")
        {
            isValid("bones") shouldBe true
        }
        withClue("too short")
        {
            isValid("cat") shouldBe false
        }
        withClue("contains number")
        {
            isValid("12345") shouldBe false
        }
        withClue("too long")
        {
            isValid("supercalifragilisticexpialidocious") shouldBe false
        }
    }

    "reading the wordslist"
    {
        val words = readWordList()
        withClue("List shouldn't be empty")
        {
            words.isNotEmpty() shouldBe true
        }
        withClue("words should all be five letters long")
        {
            words.all{isValid(it)} shouldBe true
        }
    }
    
    
})
