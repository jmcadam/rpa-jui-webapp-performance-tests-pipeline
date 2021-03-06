package uk.gov.hmcts.jui.sscs.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.session._
import uk.gov.hmcts.jui.sscs.scenario.utils._
import uk.gov.hmcts.jui.sscs.scenario._
import scala.concurrent.duration._

class JUI_SSCSCaseJourney extends Simulation {

 val JUIBaseUrl = scala.util.Properties.envOrElse("URL_TO_TEST", Environment.URL_TO_TEST).toLowerCase()


  val httpSSCSProtocol = Environment.HttpSSCSProtocol
    .baseUrl(JUIBaseUrl)
    .proxy(Proxy("proxyout.reform.hmcts.net", 8080))
   // .disableAutoReferer



  val JUISSCSSCN = scenario("SCN_JUI_SSCSJourney")
    .exec(
      Logout.logout,
      Browse.landingLoginPage,
      JUILogin.submitLogin,
     // JUICases.pickRandomCase,
     // JUIQuestion.sendQuestion,
     // JUIDecision.submitDecision,
      Logout.logout
    )

    setUp(JUISSCSSCN.inject(atOnceUsers(1))).protocols(httpSSCSProtocol)

}
