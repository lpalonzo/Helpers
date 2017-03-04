definition(
    name: "Empieza la Fiesta",
    namespace: "lpalonzo",
    author: "LP",
    description: "Escenario para animarse un poco",
    category: "Convenience",
    iconUrl: "http://www.freeiconspng.com/uploads/celebration-icon-png-12.png",
    iconX2Url: "http://www.freeiconspng.com/uploads/celebration-icon-png-12.png"
)

preferences 
{
	page(name: "Principal", title: "Ajusta el tono y el ambiente", install: true, uninstall: true)
	{
		section ("Que luces vamos a modificar?") 
        {
			input "light1", "capability.colorControl", multiple: false
            input "light2", "capability.colorControl", multiple: false
            input "light3", "capability.colorControl", multiple: false
		}
        section("Escoger los colores a utilizar:")
        {
            input "color1", "enum", title: "Que Color?", required: true, multiple:false, options: ["Red","Green","Blue","Yellow","Orange","Purple","Pink"]
            input "color2", "enum", title: "Que Color?", required: true, multiple:false, options: ["Red","Green","Blue","Yellow","Orange","Purple","Pink"]
            input "color3", "enum", title: "Que Color?", required: true, multiple:false, options: ["Red","Green","Blue","Yellow","Orange","Purple","Pink"]
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
   if (name == 'The Dance')
   {
   		PlayTrack()
        SetupLights()
   }

}


def SetupLights()
{
	def hueColor1 = 0
    def hueColor2 = 0
    def hueColor3 = 0
    def saturation = 99
    
    switch (color1)
    {
		case "Blue":
			hueColor1 = 70
			break;
		case "Green":
			hueColor1 = 39
			break;
		case "Yellow":
			hueColor1 = 25
			break;
		case "Orange":
			hueColor1 = 10
			break;
		case "Purple":
			hueColor1 = 75
			break;
		case "Pink":
			hueColor1 = 83
			break;
		case "Red":
			hueColor1 = 100
			break;
                
    }
    
    switch (color2)
    {
		case "Blue":
			hueColor2 = 70
			break;
		case "Green":
			hueColor2 = 39
			break;
		case "Yellow":
			hueColor2 = 25
			break;
		case "Orange":
			hueColor2 = 10
			break;
		case "Purple":
			hueColor2 = 75
			break;
		case "Pink":
			hueColor2 = 83
			break;
		case "Red":
			hueColor2 = 100
			break;
                
    }
    
    switch (color3)
    {
		case "Blue":
			hueColor3 = 70
			break;
		case "Green":
			hueColor3 = 39
			break;
		case "Yellow":
			hueColor3 = 25
			break;
		case "Orange":
			hueColor3 = 10
			break;
		case "Purple":
			hueColor3 = 75
			break;
		case "Pink":
			hueColor3 = 83
			break;
		case "Red":
			hueColor3 = 100
			break;
                
    }

    light1.setLevel(brillo)
	light1.setSaturation(saturation)
	light1.setHue(hueColor1)
    light1.on()
    
    light2.setLevel(brillo)
	light2.setSaturation(saturation)
	light2.setHue(hueColor2)
    light2.on()
    
    light3.setLevel(brillo)
	light3.setSaturation(saturation)
	light3.setHue(hueColor3)
    light3.on()
    
    
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
