import requests
import pandas as pd
import sys
if len(sys.argv) != 2:
	print('Usage: python3 '+sys.argv[0]+" test|prod")
	exit(1)
mode = sys.argv[1]
loginData = '{"username":"<username>","password":"<password>"}'
addUser = '{"username" : "<username>", "password" : "<password>", "name" : "<name>", "role": "USER"}'
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
	exit(1)

#filename = input('Enter filename: ')
filename = mode+"/emailIds.csv"
f = open(mode+'/emailIdPasswd.csv','w')
data = pd.read_csv(filename,header=None)
for row in data.iterrows():
	email,name = row[1][0], row[1][1]
	#print('Email '+email+ 'Name: '+name) 
	password = pd.util.testing.rands_array(15, 1)[0]
	response = requests.post(url = 'http://localhost:8081/user/register',headers = headers, data = getCustomData(addUser,{'username':email,'password':password,'name':name}))
	f.write(email+","+password+"\n")
	print(response.status_code)
	print(response.text)
	print('\n')

f.close()
