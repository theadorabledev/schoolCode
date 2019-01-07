#!/usr/bin/env bash
#Gets the pid of the running java proccess
jpid=`(jcmd -l | grep -v "jcmd" | grep -oP "(.*) ")`
jcmd $jpid help

