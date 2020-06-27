import requests
import pandas as pd
import json
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
token = ''
#username = input('Enter username: ')
#password = input('Enter password: ')
username='admin@gigsky.com'
password='Gsadmin123'
response = requests.post(url = 'http://localhost:8081/authenticate',headers = headers, data = getCustomData(loginData,{'username':username,'password':password}))
if response.status_code == 200:
	token = response.headers['Authorization']
	headers['Authorization'] = token
else:
	print('Login Failed!')

questionQidMap = {}
response = requests.get(url = 'http://localhost:8081/question',headers=headers)
if response.status_code == 200:
	questions = json.loads(response.text)["list"]
	for question in questions:
		questionQidMap[question['question']] = question['id']

#print(questionQidMap)
data = pd.read_csv(mode+'/responses.csv')
cols = data.columns
cols = cols[1:]
for i in range(len(data)):
	answers = {'type' : 'answers'}
	answers['username'] = data.loc[i,cols[0]]
	answers['list']= []
	for j in range(1,len(cols)):
		answers['list'].append({'questionId':questionQidMap[cols[j]],'answer':data.loc[i,cols[j]]})
	
	#print(answers)
	response = requests.post("http://localhost:8081/question/answer",headers = headers, data = json.dumps(answers))
	#print(response.text)
	print(response.status_code)
	#exit(0)
