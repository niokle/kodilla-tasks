#!/usr/bin/env bash

openChromeBrowser() {
    /usr/bin/open -a "/Applications/Google Chrome.app" 'http://localhost:8080/crud/v1/task/getTasks'
}

openDefaultBrowser() {
    /usr/bin/open http://localhost:8080/crud/v1/task/getTasks
}

openBrowser() {
    if openChromeBrowser; then
        echo "Opened by Google Chrome browser"
    else
        openDefaultBrowser
        echo "Opened by default browser"
    fi
}

fail() {
    echo "There were errors"
}

if sh ./runcrud.sh; then
    openBrowser
else
   fail
fi
