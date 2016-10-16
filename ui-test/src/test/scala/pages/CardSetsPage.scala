package pages

import org.openqa.selenium.WebDriver
import org.scalatest.concurrent.Eventually._

class CardSetsPage(implicit val webDriver: WebDriver) extends BasePage {

  override val url: String = host + "/"

  def clickNewCardSet = {
    eventually {
      click on cssSelector("[value=new-set]")
    }
    new EditCardSet
  }

  def title = pageTitle


}
