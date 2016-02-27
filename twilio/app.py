# Flask Boilerplate
# By Jay Ravaliya

from flask import Flask, make_response, jsonify, request, abort, render_template
import requests
import twilio.twiml
from twilio.rest import TwilioRestClient
from models import db, Numbers
import os

account_sid = os.environ.get('TWILIO_ACCOUNT_SID')
auth_token = os.environ.get('TWILIO_AUTH_TOKEN')
client = TwilioRestClient(account_sid, auth_token)

app = Flask(__name__)

@app.route("/", methods=['POST'])
def home():
	db.create_all()
	print request.values.get('From') + " said: " + request.values.get('Body')
	number = Numbers(request.values.get('From'))
	resp = twilio.twiml.Response()
	resp.message("Thx for stopping by Intro to API's at HackHERS! For all material, check out: https://goo.gl/2knLba")
	return str(resp)


if __name__ == "__main__":
	app.run(host='0.0.0.0', debug=True)
