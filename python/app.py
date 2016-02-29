import requests
import secret

def get_locations(latitude, longitude):
		parameters = {
				'location' : str(latitude) + ',' + str(longitude),
				'rankby' : 'distance',
				'type' : 'restaurant',
				'key' : secret.GOOGLE_API_KEY
		}

		headers = {}

		response = requests.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json', params=parameters, headers=headers)

		return response.json()

# print get_locations(40.4995488,-74.4443186)
print get_locations(40.4995488, -74.4443186)['status']

