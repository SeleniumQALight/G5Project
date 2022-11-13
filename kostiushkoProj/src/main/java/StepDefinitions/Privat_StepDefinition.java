package StepDefinitions;


import api.ApiHelperPrivate;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import privatePage.PrivateHomePage;


public class Privat_StepDefinition {

    ApiHelperPrivate apiHelperPrivat = new ApiHelperPrivate();
    PrivateHomePage privatHomePage = new PrivateHomePage(DriverHelper.getWebDriver());

    @Given("^Getting values via API for '(.*)' currency and save$")
    public void getValuesViaApiAndSave(String currency){
        apiHelperPrivat.getValuesViaAPI(currency);
    }
    @When("^User open PrivatBank Home page$")
    public void openPrivatHomePage(){
        privatHomePage.openPrivatHomePage();
    }
    @Then("^Getting values via UI for '(.*)' currency and save$")
    public void getValuesCurrencyViaUI(String currency){
        privatHomePage.getValuesRateByCurrencyViaUI(currency);
    }
    @Then("^Check API and UI currency$")
    public void checkIfRatesEqual() {
        privatHomePage.checkRatesEquality();
    }
}


