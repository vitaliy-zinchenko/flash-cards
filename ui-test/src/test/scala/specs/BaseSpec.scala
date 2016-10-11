package specs

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


trait BaseSpec {

  implicit val webDriver: WebDriver = new ChromeDriver //TODO test not only chrome

  def host: String = System.getProperty("fc.host")

}
