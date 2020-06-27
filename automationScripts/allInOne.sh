#!/bin/bash
if [ "$#" -ne 1 ]; then
		echo "Usage: $0 test|prod"
			exit 1
fi
mode=$1
python3 addUsers.py $mode
python3 testLogin.py $mode
python3 prepareAnswerData.py $mode
