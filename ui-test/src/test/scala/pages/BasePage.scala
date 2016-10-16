package pages

import org.scalatest.selenium.{Page, WebBrowser}

trait BasePage extends Page with WebBrowser {

  def host: String = System.getProperty("fc.host")

}
