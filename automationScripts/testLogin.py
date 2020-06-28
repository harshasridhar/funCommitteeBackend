import requests
import pandas as pd
import sys
if len(sys.argv) != 2:
	print('Usage: python3 '+sys.argv[0]+" test|prod")
	exit(1)
mode = sys.argv[1]
loginData = '{"username":"<username>","password":"<password>"}'
def getCustomData(template, fieldValueMap):
	data = template
	for field,value in fieldValueMap.items():
		data = data.replace("<"+field+">",str(value))
	return data
headers = {'Content-Type': 'application/json'}

data = pd.read_csv(mode+'/emailIdPasswd.csv',header=None)
for row in data.iterrows():
	response = requests.post(url = "http://localhost:8081/authenticate",headers= headers, data = getCustomData(loginData,{'username':row[1][0],'password':row[1][2]}))
	print(response.status_code)
