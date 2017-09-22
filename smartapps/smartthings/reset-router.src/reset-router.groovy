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
    name: "Reset Router",
    namespace: "smartthings",
    author: "LP",
    description: "Reset the rooter when it loose signal",
    category: "Convenience",
    iconUrl: "https://cdn3.iconfinder.com/data/icons/ikooni-flat-devices-technologies/128/devices-15-512.png",
    iconX2Url: "https://cdn3.iconfinder.com/data/icons/ikooni-flat-devices-technologies/128/devices-15-512.png"
)

def action_image_bullet      = "http://ogdcl.com/images/arrow-left.png"
    
preferences 
{

    

	section("Which switch is the router plugged?") 
    {
        input "timeOfDaySelection", "enum", title: "En un momento especifico del dia:", required: false, multiple: false, submitOnChange:true, options: ["Al amanecer", "Al atardecer"], description: "Seleccionar..", image: "http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/clock-icon.png"
        input "OffsetValue", "number", title: "Retrasar o Adelantar este tiempo:", required: false, submitOnChange:true, description: "Minutos", image: action_image_bullet
        input "OffsetDir"  , "enum", title: "Escoge la acción:", description: "Seleccionar..", required: OffsetValue, submitOnChange:true, options: ["Retrasar","Adelantar"], image: action_image_bullet
    }

        
	
}

def installed() {
	subscribe(app, appTouch)
    subscribeToEvents()
}

def updated() {
    unsubscribe()
	subscribe(app, appTouch)
    subscribeToEvents()
    
}

def subscribeToEvents()
{
    if (timeOfDaySelection)
    {
		TimeforEventsHandler()
    }
}


def appTouch(evt)
{
	sunriseSunsetTimeHandler()
}


def TimeforEventsHandler()
{
    switch (timeOfDaySelection) 
    {
    	case "Al amanecer":
        	subscribe(location, "sunriseTime", sunriseSunsetTimeHandler )
            break
        case "Al atardecer":
        	subscribe(location, "sunsetTime", sunriseSunsetTimeHandler)
            break
        case "Especificar":
        	if (timeOfDay) 
    		{
				schedule(timeOfDay, scheduledTimeHandler)
			}
            break
        default:
        	break
    }
}

def sunriseSunsetTimeHandler(evt) 
{
	 
	 def checkSun = getSunriseAndSunset()
	 def riseTime = getSunriseAndSunset().sunrise.time
	 def setTime  = getSunriseAndSunset().sunset.time 
	 def Now      = new Date().format("HH:mm", location.timeZone)
     
     
     
     def newRise		=  new Date(riseTime).format("HH:mm", location.timeZone)
     def newRiseWOffset =  new Date(riseTime - (OffsetValue*60*1000 )).format("HH:mm", location.timeZone)
     def newSet      	=  new Date(setTime ).format("HH:mm", location.timeZone)
     def newNow      	=  Now
	 
     log.debug " "
     log.trace "riseTime = $newRise   "
     log.trace "riseTimeWO = $newRiseWOffset   "
     log.trace "setTime  = $newSet    "
     log.trace "Now      = $newNow    "
	 
	 
}