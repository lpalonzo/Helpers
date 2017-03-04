definition(
    name: "Emergency",
    namespace: "lpalonzo",
    author: "LP",
    description: "Blink Emergency Red Lights for defined number of seconds",
    category: "Convenience",
    iconUrl: "https://cdn0.iconfinder.com/data/icons/medical_realvista/400/emergency.png",
    iconX2Url: "https://cdn0.iconfinder.com/data/icons/medical_realvista/400/emergency.png"
)

preferences 
{

		section("Controlar estas luces:")
        {
			input "switches", "capability.colorControl", multiple: true
		}
        section("Cuantos ciclos durara la alerta?")
        {
            input "cycles", "number", title: "Numero de ciclos?", required: true
        }
        section("Agregar una sirena?")
        {
        	input "bose", "capability.musicPlayer", title: "Altavoz/Sirena a utilizar", required: true
        }
        section("Mas opciones", hideable: true, hidden: true) 
        {
			input "volume", "number", title: "Cuanto volumen?", description: "0-100%", required: false
		}
}

def installed() 
{
	initialize()
}

def updated() 
{
	unsubscribe()
	initialize()
}

def initialize() 
{
    subscribe(app, appTouch)
    subscribe(location, "alarmSystemStatus", alarmHandler)
    subscribe(location, "routineExecuted", routineChanged)
}

// TODO: implement event handlers

def appTouch(evt)
{   
	Alarm()

}

def alarmHandler(evt)
{
	def name = evt.descriptionText
    if (name == 'Intrusion Detected')
    {
    	Alarm()
    }
    
}

def routineChanged(evt)
{
   def name = evt.displayName
   if (name == 'Code Red')
   {
		Alarm()
   }

}

def Alarm()
{
	def doFlash = true
	def onFor = onFor ?: 500
	def offFor = offFor ?: 500
	def numFlashes = numFlashes ?: 15

	if (state.lastActivated) 
    {
		def elapsed = now() - state.lastActivated
		def sequenceTime = (numFlashes + 1) * (onFor + offFor)
		doFlash = elapsed > sequenceTime
	}

	if (doFlash) 
    {
		state.lastActivated = now()
		def delay = 0L
        switches.off()
        if (bose)
        {
        	SetAlarm()
        }
        
		numFlashes.times 
        {
			switches.on(delay: delay)
			delay += onFor
     		switches.off(delay: delay)
			delay += offFor
		}
        switches.off()
        DefuseAlarm()
 	}
}
    



def EmergencyLightSetup()
{
	switches.setHue(100)
    switches.setSaturation(100)
    switches.setLevel(100)
}

def SetAlarm()
{
    def options = [:]
	if (volume) 
    {
		bose.setLevel(volume as Integer)
		options.delay = 1000
	}
	options ? bose.preset6(options) : bose.preset6()

}

def DefuseAlarm()
{
    options ? bose.off(options) : bose.off()
}

