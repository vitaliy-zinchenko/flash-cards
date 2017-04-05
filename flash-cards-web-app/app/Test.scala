import java.sql.DriverManager
import java.util.Properties

/**
  * Created by Vitalii Zinchenko
  */
object Test {
  def main(args: Array[String]): Unit = {
    val p = new Properties()
//    p.setProperty("user", "postres")
//    p.setProperty("password", "password")
    val url = "jdbc:postgresql://testfc100.cejd7wkwcswk.us-west-2.rds.amazonaws.com/postgres?user=postres&password=password"
    val driver = DriverManager.getDriver(url)
    val c = driver.connect(url, p)
    println(c)
  }
}
