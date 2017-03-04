definition(
    name: "Romantic",
    namespace: "lpalonzo",
    author: "LP",
    description: "Escenario para arrimarlo",
    category: "Convenience",
    iconUrl: "http://i2.kym-cdn.com/entries/icons/original/000/008/549/Naamloos-2.png",
    iconX2Url: "http://i2.kym-cdn.com/entries/icons/original/000/008/549/Naamloos-2.png"
)

preferences 
{
	page(name: "Principal", title: "Ajusta el tono y el ambiente", install: true, uninstall: true)
	{
		section ("Que luces vamos a modificar?") 
        {
			input "lights", "capability.colorControl", multiple: true
		}
        section("Escoger la tonalidad utilizar:")
        {
            input "color", "enum", title: "Que Color?", required: true, multiple:false, options: [
					"White", "Daylight", "Soft White", "Warm White", "Red","Green","Blue","Yellow","Orange","Purple","Pink"]
        }
        section("Nivel de brillo?")
        {
        	input "brillo", "enum", title: "Brillo" , options: [20, 30, 33, 40, 50, 60, 80, 90, 100], description: "Escoga el porcentaje:", required: true
        }
        section("Fuente de Sonido")
        {
        	input "bose", "capability.musicPlayer", title: "Altavoz a utilizar", required: true
        }
		section("Pista a tocar:")
        {
			input "actionType", "enum", title: "Que hago?", required: true, defaultValue: "play", options: [
				"Turn On & Play",
                "Tocar Pista 1",
                "Tocar Pista 2",
                "Tocar Pista 3",
                "Tocar Pista 4",
                "Tocar Pista 5",
                "Tocar Pista 6"
			]
		}
        section("Mas opciones", hideable: true, hidden: true) 
        {
			input "volume", "number", title: "Cuanto volumen?", description: "0-100%", required: false
            input "switches", "capability.switch", title: "Desea apagar algun disposito?", required: false, multiple: true

		}
        
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
    subscribe(location, "routineExecuted", routineChanged)
}



def appTouch(evt)
{   
	SetupLights()
}

def routineChanged(evt)
{
   def name = evt.displayName
   if (name == 'The Mood')
   {
   		if (switches)
        {
        	switches.off()
        }
        
        PlayTrack()
        SetupLights()
   }

}


def SetupLights()
{
	def hueColor = 0
    def saturation = 80
    
    switch (color)
    {
		case "White":
			hueColor = 52
			saturation = 19
			break;
		case "Daylight":
			hueColor = 53
			saturation = 91
			break;
		case "Soft White":
			hueColor = 23
			saturation = 56
			break;
		case "Warm White":
			hueColor = 22
			saturation = 48 //80
			break;
		case "Blue":
			hueColor = 70
			break;
		case "Green":
			hueColor = 39
			break;
		case "Yellow":
			hueColor = 25
			break;
		case "Orange":
			hueColor = 10
			break;
		case "Purple":
			hueColor = 75
			break;
		case "Pink":
			hueColor = 83
			break;
		case "Red":
			hueColor = 100
			break;
                
    }

    lights.setLevel(35)
	lights.setSaturation(saturation)
	lights.setHue(hueColor)
    lights.on()
}

def PlayTrack()
{
    def options = [:]
	if (volume) 
    {
		bose.setLevel(volume as Integer)
		options.delay = 1000
	}

	switch (actionType) 
    {
		case "Turn On & Play":
			options ? bose.on(options) : bose.on()
			break
        case "Tocar Pista 1":
			options ? bose.preset1(options) : bose.preset1()
			break
        case "Tocar Pista 2":
			options ? bose.preset2(options) : bose.preset2()
			break 
        case "Tocar Pista 3":
			options ? bose.preset3(options) : bose.preset3()
			break
        case "Tocar Pista 4":
			options ? bose.preset4(options) : bose.preset4()
			break
        case "Tocar Pista 5":
			options ? bose.preset5(options) : bose.preset5()
			break
        case "Tocar Pista 6":
			options ? bose.preset6(options) : bose.preset6()
			break
		default:
			break
	}

}

