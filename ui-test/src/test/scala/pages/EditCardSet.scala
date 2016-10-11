package pages

import org.openqa.selenium.WebDriver
import org.scalatest.concurrent.Eventually._

class EditCardSet(implicit val webDriver: WebDriver) extends BasePage {

  override val url: String = ""

  def isLoaded: Boolean = eventually {
    find(className("cards-container")).isDefined //TODO wrap whole body to the one container
  }

}
