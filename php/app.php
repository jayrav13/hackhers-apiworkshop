<?php
	require 'vendor/autoload.php';
	require 'secret.php';

	function get_locations($latitude, $longitude, $key) {

		$base_url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

		$client = new GuzzleHttp\Client();
		$response = $client->request('GET', $base_url, [
			'query' => [
				"location" => $latitude . "," . $longitude,
				"rankby" => "distance",
				"type" => "restaurant",
				"key" => $key
			]
		]);

		echo $GOOGLE_API_KEY;

		return $response->getBody();

	}

	$locations = get_locations(40.4995488, -74.4443186, $GOOGLE_API_KEY);
	echo $locations;

	/*
	$response_json = json_decode($locations, true);
	echo $response_json['status'];
	*/
?>
