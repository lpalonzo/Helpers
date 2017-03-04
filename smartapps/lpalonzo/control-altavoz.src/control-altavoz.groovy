definition(
    name: "Control Altavoz",
    namespace: "lpalonzo",
    author: "LP",
    description: "Coloca una pista cuando ocurre un evento",
    category: "SmartThings Labs",
    iconUrl: "https://cdn3.iconfinder.com/data/icons/communication-3/512/horn-512.png",
    iconX2Url: "https://cdn3.iconfinder.com/data/icons/communication-3/512/horn-512.png"
)

preferences {
	page(name: "mainPage", title: "Control your Bose® SoundTouch® when something happens", install: true, uninstall: true)
	page(name: "timeIntervalInput", title: "Only during a certain time") {
		section {
			input "starting", "time", title: "Starting", required: false
			input "ending", "time", title: "Ending", required: false
		}
	}
}

def mainPage() {
	dynamicPage(name: "mainPage") {
		
		section("Perform this action"){
			input "actionType", "enum", title: "Action?", required: true, defaultValue: "play", options: [
				"Turn On & Play",
				"Turn Off",
				"Toggle Play/Pause",
				"Skip to Next Track",
				"Skip to Beginning/Previous Track",
                "Play Preset 1",
                "Play Preset 2",
                "Play Preset 3",
                "Play Preset 4",
                "Play Preset 5",
                "Play Preset 6"
			]
		}
		section {
			input "bose", "capability.musicPlayer", title: "Bose® SoundTouch® music player", required: true
		}
		section("More options", hideable: true, hidden: true) 
        {
			input "volume", "number", title: "Set the volume volume", description: "0-100%", required: false

		}
}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	subscribeToEvents()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	unschedule()
	subscribeToEvents()
}

def subscribeToEvents() 
{
	log.trace "subscribeToEvents()"
	subscribe(app, dothis)
	


}

def eventHandler(evt) {
	if (allOk) {
		def lastTime = state[frequencyKey(evt)]
		if (oncePerDayOk(lastTime)) {
			if (frequency) {
				if (lastTime == null || now() - lastTime >= frequency * 60000) {
					takeAction(evt)
				}
				else {
					log.debug "Not taking action because $frequency minutes have not elapsed since last action"
				}
			}
			else {
				takeAction(evt)
			}
		}
		else {
			log.debug "Not taking action because it was already taken today"
		}
	}
}

def modeChangeHandler(evt) {
	log.trace "modeChangeHandler $evt.name: $evt.value ($triggerModes)"
	if (evt.value in triggerModes) {
		eventHandler(evt)
	}
}

def scheduledTimeHandler() {
	eventHandler(null)
}

def appTouchHandler(evt) {
	takeAction(evt)
}

private takeAction(evt) {
	log.debug "takeAction($actionType)"
	def options = [:]
	if (volume) {
		bose.setLevel(volume as Integer)
		options.delay = 1000
	}

	switch (actionType) {
		case "Turn On & Play":
			options ? bose.on(options) : bose.on()
			break
		case "Turn Off":
			options ? bose.off(options) : bose.off()
			break
		case "Toggle Play/Pause":
			def currentStatus = bose.currentValue("playpause")
			if (currentStatus == "play") {
				options ? bose.pause(options) : bose.pause()
			}
			else if (currentStatus == "pause") {
				options ? bose.play(options) : bose.play()
			}
			break
		case "Skip to Next Track":
			options ? bose.nextTrack(options) : bose.nextTrack()
			break
		case "Skip to Beginning/Previous Track":
			options ? bose.previousTrack(options) : bose.previousTrack()
			break
        case "Play Preset 1":
			options ? bose.preset1(options) : bose.preset1()
			break
        case "Play Preset 2":
			options ? bose.preset2(options) : bose.preset2()
			break 
        case "Play Preset 3":
			options ? bose.preset3(options) : bose.preset3()
			break
        case "Play Preset 4":
			options ? bose.preset4(options) : bose.preset4()
			break
        case "Play Preset 5":
			options ? bose.preset5(options) : bose.preset5()
			break
        case "Play Preset 6":
			options ? bose.preset6(options) : bose.preset6()
			break
		default:
			log.error "Action type '$actionType' not defined"
	}

}

def dothis(evt)
{
	def options = [:]
    options ? bose.preset1(options) : bose.preset1()
}

private frequencyKey(evt) {
	//evt.deviceId ?: evt.value
	"lastActionTimeStamp"
}
