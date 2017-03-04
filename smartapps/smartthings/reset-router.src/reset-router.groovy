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

preferences {
	section("Which switch is the router plugged?") {
		input "theSwitch", "capability.switch"
	}
	section("Delay required? (Secs)") {
		input "secondsLater", "number", title: "How many?"
	}
}

def installed() {
	subscribe(app, appTouch)
    subscribe(location, "routineExecuted", routineChanged)
}

def updated() {
    unsubscribe()
	subscribe(app, appTouch)
    subscribe(location, "routineExecuted", routineChanged)
}

def appTouch(evt)
{
	resetRouter()
}

def resetRouter()
{
    turnOffSwitch()
	runIn(secondsLater, turnOnSwitch)
}

def turnOnSwitch() {
	theSwitch.on()
}

def turnOffSwitch() {
	theSwitch.off()
}

def routineChanged(evt)
{
   def name = evt.displayName
   if (name == 'The Internet')
   {
   		resetRouter()
   }

}