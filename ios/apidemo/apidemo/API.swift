//
//  API.swift
//  apidemo
//
//  Created by Jay Ravaliya on 2/25/16.
//  Copyright © 2016 JRav. All rights reserved.
//

import Alamofire
import SwiftyJSON

class API {
    
    static let baseURL : String = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"
    
    static func getLocations(latitude : Double, longitude : Double, completion : (success : Bool, data : JSON) -> Void) -> Void {
        
        let parameters : [String : AnyObject] = [
            "location" : "\(latitude),\(longitude)",
            "rankby" : "distance",
            "type" : "restaurant",
            "key" : Secret.GOOGLE_API_KEY
        ]
        
        Alamofire.request(Method.GET, self.baseURL, parameters: parameters, encoding: ParameterEncoding.URL, headers: nil).responseJSON { (response) -> Void in
            
            if(response.response?.statusCode == 200) {
                completion(success: true, data: JSON(response.result.value!))
            }
            else {
                completion(success: true, data: nil)
            }
            
        }
        
    }
    
}