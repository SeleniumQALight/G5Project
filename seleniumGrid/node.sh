#!/bin/bash
java -jar selenium-server-standalone-2.44.0.jar -role node -nodeConfig nodeconfig.json -Dwebdriver.chrome.driver="../seleniumGrid/drivers/107.0.5304.62/chromedriver_mac64/chromedriver"
sleep 10000