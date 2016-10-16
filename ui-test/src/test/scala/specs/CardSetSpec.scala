package specs

import org.scalatest._
import org.scalatest.selenium.WebBrowser
import pages.CardSetsPage

class CardSetSpec extends FlatSpec with BaseSpec with Matchers with WebBrowser {

  val cardSetsPage = new CardSetsPage

  "Flash Cards page" should "have proper title" in {
    go to cardSetsPage

    cardSetsPage.title should be ("Flashcards")
  }

  it should "move to Card Set creation page after clicking 'new' button" in {
    go to cardSetsPage

    val editCardSetPage = cardSetsPage.clickNewCardSet

    editCardSetPage.isLoaded should be (true)
  }

}
