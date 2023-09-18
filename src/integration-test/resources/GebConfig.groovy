import org.openqa.selenium.firefox.FirefoxDriver

environments {

    // run via “./gradlew -Dgeb.env=chrome iT”
/*
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via “./gradlew -Dgeb.env=chromeHeadless iT”
    chromeHeadless {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            new ChromeDriver(o)
        }
    }
*/

    // run via “./gradlew -Dgeb.env=firefox iT”
    firefox {
        driver = { new FirefoxDriver() }
    }
}
