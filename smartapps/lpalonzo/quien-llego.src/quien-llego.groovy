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
 *  Lock It When I Leave
 *
 *  Author: SmartThings
 *  Date: 2013-02-11
 */

definition(
    name: "Quien llego?",
    namespace: "lpalonzo",
    author: "LP",
    description: "Notifies when access code opens the lock",
    category: "Safety & Security",
    iconUrl: "http://www.iconsdb.com/icons/preview/orange/lock-xxl.png",
    iconX2Url: "http://www.iconsdb.com/icons/preview/orange/lock-xxl.png",
    oauth: true
)


preferences
{
	section("Which door locks?") 
    {
		input "lock1", "capability.lock", multiple: true
    }

	section("Which switches?") 
    {
		input "switches", "capability.switch", multiple: true, required: false
	}
    
	section("Turn on or off?") 
    {
		input "turnon", "bool", title: "Turn on when door unlocks?", required: false
		input "turnoff", "bool", title: "Turn off when door locks?", required: false
	}
    
	section("Change mode?")
    {
		input "unlockmode", "mode", title: "Change to this mode when the door unlocks", required: false
		input "lockmode", "mode", title: "Change to this mode when the door locks", required: false
	}
    
	section("Only between these times...") 
    {
		input "startTime", "time", title: "Start Time", required: false
		input "endTime", "time", title: "End Time", required: false
	}
    
	section(title: "More Options", hidden: hideOptions(), hideable: true)
    {
		input "turnoffdelay", "number", title: "Delay turning off (Minutes)", required: false
	}
}

def installed() 
{
	subscribe(lock1, "lock", checkTime)
}

def updated() 
{
	unsubscribe()
	subscribe(lock1, "lock", checkTime)
}

def turniton(evt) 
{
	if(evt.value == "unlocked") 
    {
		if(turnon && switches)
        {
			log.trace "Turning on switches: $switches"
			switches.on()
		}
        
		if(unlockmode) 
        {
			changeMode(unlockmode)
		}
	}
    
	if(evt.value == "locked") 
    {
		if(turnoff && switches)
        {
			if(turnoffdelay) 
            {
				log.trace "Waiting to turn off switches for $turnoffdelay minutes"
				def turnOffDelaySeconds = 60 * turnoffdelay
				runIn(turnOffDelaySeconds, turnitoff)
			}
			else 
            {
				turnitoff()
			}
		}
        
		if(lockmode)
        {
			changeMode(lockmode)
        }
	}
}

def turnitoff() 
{
	log.trace "Turning off switches: $switches"
	switches.off()
}

def changeMode(newMode) 
{
	if (newMode && location.mode != newMode)
    {
		if (location.modes?.find{it.name == newMode}) 
        {
			setLocationMode(newMode)
			log.trace "Mode changed to '${newMode}'"
		}
		else
        {
			log.trace "Undefined mode '${newMode}'"
		}
	}
}

def checkTime(evt)
{
	if(startTime && endTime) 
    {
		def currentTime = new Date()
		def startUTC = timeToday(startTime)
        def endUTC = timeToday(endTime)
		if((currentTime > startUTC && currentTime < endUTC && startUTC < endUTC) || (currentTime > startUTC && startUTC > endUTC) || (currentTime < endUTC && endUTC < startUTC))
        {
			turniton(evt)
		}
	}
	else 
    {
		turniton(evt)
	}
}

def hideOptions() 
{
	false
}