package specs

import org.scalatest.{Matchers, fixture}

class TestTt extends fixture.FlatSpec with fixture.ConfigMapFixture with Matchers {configMap =>

//  println(configMap.)

  "The config map" should "contain hello" in { configMap =>
    // Use the configMap passed to runTest in the test
    configMap should contain key "hello"
  }

  it should "contain world" in { configMap =>
    configMap should contain key "world"
  }
}
