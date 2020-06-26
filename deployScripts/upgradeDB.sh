#!/bin/bash
if [ "$#" -ne 2 ]; then
		echo "Usage: $0 oldVersion newVersion"
			exit 1
fi
currentVersion=$1
finalVersion=$2
echo "Upgrading db to version $finalVersion"
while [ $currentVersion -lt $finalVersion ]
do
	currentVersion=`expr $currentVersion + 1`
	cat db_schemas/v$currentVersion/schema.sql | mysql -uroot -proot funcommitteebackend
	echo "Upgraded to version v$currentVersion"
done