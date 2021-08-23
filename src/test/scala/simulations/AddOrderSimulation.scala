package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class AddOrderSimulation extends Simulation {

  //conf
  val value_conf = http.baseUrl("http://localhost:8083")
    .header("Accept",value="application/json")
    .header(name="content-type", value ="application/json")


  //scenario
  val scn = scenario("Add Order")
    .exec(http("Post Order")
      .post("/rvy/api/oms/v1/orders")
      .body(RawFileBody(filePath = "./src/test/resources/bodies/addOrder.json")).asJson
      .header(name="content-type",value = "application/json")
      .check(status is 200))
    .pause(3)

    .exec(http("Get OrderDetails By Id")
      .get("/rvy/api/oms/v1/orders/561")
      .check(status is 200)
    )



  //setup
  setUp(scn.inject(atOnceUsers(users=30))).protocols(value_conf)



}
