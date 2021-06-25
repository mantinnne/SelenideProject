## UI autotests for cism

### Technology stack

Java, Gradle, Junit5, Selenide, Allure Report, Allure  Jenkins, Selenoid,  Bot notification, Rest Assured

### Report

java  \
"-DprojectName=${PROJECT_NAME}" \
"-Dconfig.file=${PATH_TO_FILE}" \
"-Denv=${ENVIRONMENT}" \
"-DreportLink=${BUILD_URL}" \
-jar allure-notifications-3.0.1.jar

### Here you can find config file structure for lib configuration.

    {
    "app": {
    "bot": {
    "token": "",
    "chat": "",
    "replyTo": ""
    },
    "base": {
    "lang": "",
    "messenger": "",
    "allureFolder": "",
    "mattermostUrl": "",
    "chart": false,
    "chartName": "",
    "project": ""
    },
    "mail": {
    "host": "",
    "port": "",
    "username": "",
    "password": "",
    "enableSSL": false,
    "from": "",
    "recipient": ""
    },
    "proxy": {
    "host": "",
    "port": 0,
    "username": "",
    "password": ""
    },
    "skype": {
    "appId": "",
    "appSecret": "",
    "serviceUrl": "",
    "conversationId": "",
    "botId": "",
    "botName": ""
    }
    }
    }

### You only need to feel needed options in bot, base, mail or proxy section. Please, be careful, lang and messenger fields are required!

# USAGE examples

### For run remote tests need fill remote.properties or to pass value:

* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid )
* videoStorage (url address where you should get video)
* threads (number of threads, default 1)

Run tests with filled remote.properties:

```bash
gradle clean test
```

Run tests with not filled remote.properties:

```bash
gradle clean -DremoteDriverUrl=https://SelenoidURL/wd/hub/ -DvideoStorage=https://SelenoidURL/video/ -Dthreads=1 or more test
```

Serve report:

```bash
allure serve build/allure-results
```

For further development there are some example tests in src/test/java/e2e/tests/demowebshop


```bash
gradle clean demowebshop
```




