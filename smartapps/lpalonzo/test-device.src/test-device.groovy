/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Power Allowance
 *
 *  Author: SmartThings
 */
definition(
    name: "Test Device",
    namespace: "lpalonzo",
    author: "LP",
    description: "Test Component",
    category: "Convenience",
    iconUrl: "https://cdn2.iconfinder.com/data/icons/app-icons-2/100/Dice_6-512.png",
    iconX2Url: "https://cdn2.iconfinder.com/data/icons/app-icons-2/100/Dice_6-512.png",
     oauth: [displayName: "OneTap", displayLink: ""]
)

preferences {

		section("Controlar esta mera:")
        {
		  input "motion", "capability.motionSensor", title: "Monitorear estos sensores", required: false,  multiple: true, submitOnChange: true, description: "Seleccionar..", image: "https://maxcdn.icons8.com/Share/icon/Animals//running_rabbit1600.png"
		}
		
      
}

def installed() {
	subscribe(app, appTouch)
}

def updated() {
    unsubscribe()
	subscribe(app, appTouch)
}

def appTouch(evt)
{   
	
	device.configure()
}