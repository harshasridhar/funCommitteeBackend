#!/bin/bash
if [ "$#" -ne 1 ]; then
		echo "Usage: $0 test|prod"
			exit 1
fi
mode=$1
dbname="funcommitteebackend"
echo "Deleting local database...";
echo "drop database $dbname;"| mysql -uroot -proot
echo "Creating local database $dbname..."
echo "create database $dbname;"| mysql -uroot -proot
initialVersion=0
finalVersion=$(sed -n 1p versionInformation.txt)
echo "Upgrading db"
sh upgradeDB.sh $initialVersion $finalVersion