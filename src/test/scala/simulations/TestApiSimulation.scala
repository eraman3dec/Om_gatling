package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestApiSimulation extends Simulation{

  //http conf
  val httpConf = http.baseUrl("http://localhost:9003")
    .header("Accept",value="application/json")
    .header(name="content-type", value ="application/json")

  //scenario
  val scn = scenario("Get all customer")
    .exec(http("get all customer details")
      .get("/rvy/api/cm/v1/customers")
      .check(status is 200)

     )
  //setup
  setUp(scn.inject(atOnceUsers(100))).protocols(httpConf)


}