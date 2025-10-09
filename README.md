# UIBDDFramework

Commands for allure report upload
mvn clean test
allure generate target/allure-results --clean -o target/allure-report
allure serve target/allure-results
this will open http://192.168.0.107:58599/index.html
