definition(
    name: "newTap",
    namespace: "lpalonzo",
    author: "LP",
    description: "Crea eventos y elige que acciones ocurren..",
    category: "Convenience",
    parent: "lpalonzo:OneTap",
    iconUrl: "https://cdn.iconscout.com/public/images/icon/premium/png-128/smart-home-3a965e9b98c3a41c-128x128.png",
    iconX2Url: "https://cdn.iconscout.com/public/images/icon/premium/png-128/smart-home-3a965e9b98c3a41c-128x128.png",
    iconX3Url: "https://cdn.iconscout.com/public/images/icon/premium/png-128/smart-home-3a965e9b98c3a41c-128x128.png",
    oauth: [displayName: "OneTap", displayLink: ""]
)

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	PREFERENCES FOR THE APP
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
preferences 
{
    page(name: "Eventos"  , uninstall: true , nextPage: "Acciones")
    page(name: "Acciones" , uninstall: true , nextPage: "LastPage")
    page(name: "LastPage" , uninstall: true , install:  true      )
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	BOTTOM PAGE
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def LastPage()
{
	dynamicPage(name: "LastPage") 
    {
    	section("Guardar Tap como:") 
    	{
		 	label title: "Nombre:", required: true, description: "Ingresar"
            icon  title: "Icono",    required: true
		}
        
    }
}


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	TRIGGER EVENTS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def Eventos() 
{
	dynamicPage(name: "Eventos") 
    {
		section() 
        {
        	paragraph image: "http://www.healthylivingforlife.org/assets/files/spotlight/58dcaa07ac403ca69788a3a78f21ebc3.png",
            title: "Eventos",
            required: false,
            "Cualquiera de los eventos seleccionados ejecutara las acciones a seleccionar despues"
    	}
        
        def anythingSet = anythingSet()
		if (anythingSet) 
        {
			section("Cambios en Dispositivos:")
            {
		        ifSet "motion", "capability.motionSensor", title: "Si algo se mueve:", required: false,  multiple: true
                ifSet "motionStop", "capability.motionSensor", title: "Si algo se detiene:", required: false,  multiple: true
                ifSet "locks",  "capability.lock", title: "Si estas puertas se abren:", required: false, multiple: true
				ifSet "contact", "capability.contactSensor", title: "Al abrir el contacto:", required: false, multiple: true
				ifSet "contactClosed", "capability.contactSensor", title: "Al cerrar el contacto:", required: false, multiple: true
				ifSet "acceleration", "capability.accelerationSensor", title: "Si algo se acelera:", required: false, multiple: true
				ifSet "mySwitch", "capability.switch", title: "Al encender el switch:", required: false, multiple: true
				ifSet "mySwitchOff", "capability.switch", title: "Al apagar el switch:", required: false, multiple: true
				ifSet "arrivalPresence", "capability.presenceSensor", title: "Llegada de:", required: false, multiple: true
				ifSet "departurePresence", "capability.presenceSensor", title: "Salida de:", required: false, multiple: true
				ifSet "smoke", "capability.smokeDetector", title: "Alerta de Humo:", required: false, multiple: true
				ifSet "water", "capability.waterSensor", title: "Alerta de Fuga:", required: false, multiple: true
				ifSet "button1", "capability.button", title: "Botonazo de:", required:false, multiple:true //remove from production
                if (button1)
                {
                	input "button_pressed", "enum", title: "Boton a utilizar:", required: button1, description: "Seleccionar..", options: ["1","2","3","4"], multiple: true, defaultValue = 1
                    input "button_state",   "enum", title: "Que funcion se usará:", required: button1, description: "Seleccionar..", options: ["Presionar", "Presionar y Mantener"], multiple: false
                }
				ifSet "triggerModes", "mode", title: "Si cambia el modo a:", required: false, multiple: true
				ifSet "timeOfDay", "time", title: "En un momento especifico del dia", required: false
			}
		}
		section(anythingSet ? "Eventos adicionales" : "Cambios en Dispositivos:", hideable: anythingSet, hidden: true)
        {
			ifUnset "motion", "capability.motionSensor", title: "Si algo se mueve:",  required: false, multiple: true
            ifUnset "motionStop", "capability.motionSensor", title: "Si algo se detiene:", required: false,  multiple: true
            ifUnset "locks",  "capability.lock", title: "Si estas puertas se abren:", required: false, multiple: true
			ifUnset "contact", "capability.contactSensor", title: "Al abrir el contacto:", required: false, multiple: true
			ifUnset "contactClosed", "capability.contactSensor", title: "Al cerrar el contacto::", required: false, multiple: true
			ifUnset "acceleration", "capability.accelerationSensor", title: "Si algo se acelera:", required: false, multiple: true
			ifUnset "mySwitch", "capability.switch", title: "Al encender el switch:", required: false, multiple: true
			ifUnset "mySwitchOff", "capability.switch", title: "Al apagar el switch:", required: false, multiple: true
			ifUnset "arrivalPresence", "capability.presenceSensor", title: "Llegada de:", required: false, multiple: true
			ifUnset "departurePresence", "capability.presenceSensor", title: "Salida de:", required: false, multiple: true
			ifUnset "smoke", "capability.smokeDetector", title: "Alerta de Humo:", required: false, multiple: true
			ifUnset "water", "capability.waterSensor", title: "Alerta de Fuga:", required: false, multiple: true
			ifUnset "button1", "capability.button", title: "Botonazo de:", required:false, multiple:true //remove from production
            if (button1)
            {
               	input "button_pressed", "enum", title: "Boton a utilizar:", required: button1, description: "Seleccionar..", options: ["1","2","3","4"], multiple: true
                input "button_state",   "enum", title: "Que funcion se usará:", required: button1, description: "Seleccionar..", options: ["Presionar", "Presionar y Mantener"], multiple: false
            }
			ifUnset "triggerModes", "mode", title: "Si cambia el modo a:", required: false, multiple: true
			ifUnset "timeOfDay", "time", title: "En un momento especifico del dia", required: false
		}
        
        
            def actions = location.helloHome?.getPhrases()*.label // get the available routines
            if (actions) 
            {
            	actions.sort()   // sort them alphabetically
                section("Ejecutar con la siguiente rutina:") 
                {
	                input "rutine", "enum", title: "Seleccione una rutina", required: false, options: actions
                }
            }
            section("Ejecutar si la alarma cambia a:")
            {
            	input "trigAlarmStatus", "enum", title: "Ejecutar si estado cambia a:", required: false, options: ["Armed (Away)", "Armed(Home)", "Disarmed"] 
            }
            section("Solo durante cierto horario:")
			{
				input "starting", "time", title: "Desde", required: false, submitOnChange: true
				input "ending", "time", title: "Hasta", required: starting, hideWhenEmpty: !starting
			}
            section("Otras opciones:", hideable: true, hidden: true) 
            {
				input "frequency", "decimal", title: "Tiempo minimo entre las acciones:", description: "Minutes", required: false
				input "days", "enum", title: "Ejecutar solo ciertos dias de la semana:", multiple: true, required: false,
				options: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
				input "modes", "mode", title: "Ejecutar solo cuando el modo actual es:", multiple: true, required: false
				input "oncePerDay", "bool", title: "Ejecutar solo una vez al dia:", required: false, defaultValue: false
			}
	}
}


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	AVAILABLE ACTIONS FOR THE SMARTAPP
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def Acciones() 
{
	dynamicPage(name: "Acciones") 
    {
    	section() 
        {
        	paragraph image: "http://www.marrakechguidetours.com/wp-content/uploads/todotalks-568x330.jpg",
            title: "Acciones",
            required: false,
            "Selecciona las acciones que realizara la aplicacion cuando se ejecute"
    	}
        section("Efectos de luz:")
        {
        	input "action_number_of_lights", "enum", title: "Cuantas luces se van a manejar?", required: false, submitOnChange: true, defaultValue: "0", description: "Seleccionar..", options: ["1","2","3","4","5"]
            if (action_number_of_lights)
            {
            	def number_of_lights = action_number_of_lights as Integer
                def name_of_light        = "action_lights_"
                def level_of_light       = "action_lights_lvl_"
                def color_of_light       = "action_lights_color_"
                def flashes_of_light     = "action_lights_flashes_"
                def question_flashes     = "action_question_flashes_"
                def new_name_of_light    = ""
                def new_level_of_light   = ""
                def new_color_of_light   = ""
                def new_flashes_of_light = ""
                def new_question_flashes = ""
                for (int i = 1; i <= number_of_lights; i++) 
                {
                    new_name_of_light    = name_of_light    + (i as String)
                    new_level_of_light   = level_of_light   + (i as String)
                    new_color_of_light   = color_of_light   + (i as String)
                    new_flashes_of_light = flashes_of_light + (i as String)
                    new_question_flashes = question_flashes + (i as String)
                    
                   	input new_name_of_light,  "capability.colorControl", title: "Luz No. $i:", multiple: true, required: action_number_of_lights, submitOnChange: true, description: "Seleccionar.."
 					input new_level_of_light, "enum", title: "$i. Nivel de Brillo:", required: action_number_of_lights, description: "0-100%", multiple: false, hideWhenEmpty: !new_name_of_light, options: [
                    "10%", "20%","30%","40%","50%","60%","70%","80%","90%","100%"] 
                   	input new_color_of_light, "enum", title: "$i. Color:", required: action_number_of_lights, multiple:false, hideWhenEmpty: !new_name_of_light, options: [
					"White", "Daylight", "Soft White", "Warm White", "Red","Green","Blue","Yellow","Orange","Purple","Pink"]
                    input new_question_flashes, "bool", title: "Modo Alerta:", required: false, submitOnChange: true, defaultValue: false
                    if (settings."action_question_flashes_${i}") {  input new_flashes_of_light, "number", title: "Cuantos Ciclos?", defaultValue: 5, description: "Debe ser menor a 10..", required: false}
                }
            }
        }
        section("Encender estos dispositivos:")
        {
        	input "action_switches_on", "capability.switch", title: "Encender a:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar.."
            if (action_switches_on) { input "question_switches_on", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, defaultValue: false }
            if (question_switches_on)  {  input "delay_switches_on", "number", title: "Apagar dentro de:", required: false, description: "Segundos"    }
        }
      	section("Apagar estos dispositivos:")
        {
        	input "action_switches_off", "capability.switch", title: "Apagar a:",  multiple: true, required: false, submitOnChange: true, description: "Seleccionar.."
            input "question_switches_off", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, hideWhenEmpty: !action_switches_off, defaultValue: false
            if (question_switches_off) {  input "delay_switches_off", "number", title: "Encender dentro de:", required: false, description: "Segundos" }
        }
        section("Abrir estas puertas:")
        {
        	input "action_lock_open", "capability.lock", title: "Abrir a:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar.."
            input "question_lock_open", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, hideWhenEmpty: !action_lock_open, defaultValue: false
            if (question_lock_open) {  input "delay_lock_open", "number", title: "Cerrar despues de:", required: false, description: "Segundos" }
        }
        section("Cerrar estas puertas:")
        {
        	input "action_lock_close", "capability.lock", title: "Cerrar a:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar.."
            input "question_lock_close", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, hideWhenEmpty: !action_lock_close, defaultValue: false
            if (question_lock_close) {  input "delay_lock_close", "number", title: "Abrir despues de:", required: false, description: "Segundos" }
        }
        section("Apertura de válvulas:")
        {
        	input "action_valve_open", "capability.valve", title: "Abrir a:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar.."
            input "question_valve_open", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, hideWhenEmpty: !action_valve_open, defaultValue: false
            if (question_valve_open) {  input "delay_valve_open", "number", title: "Cerrar despues de:", required: false, description: "Segundos" }
        }
        section("Cierre de válvulas:")
        {
        	input "action_valve_close", "capability.valve", title: "Cerrar a:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar.."
            input "question_valve_close", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, hideWhenEmpty: !action_valve_close, defaultValue: false
            if (question_valve_close) {  input "delay_valve_close", "number", title: "Abrir despues de:", required: false, description: "Segundos" }
        }
        section("Hacer esto con el sonido:")
        {
        	input "bose", "capability.musicPlayer", title: "Altavoz a utilizar", required: false, submitOnChange: true, description: "Seleccionar.."
            input "question_bose", "bool", title: "Opciones Adicionales:", required: false, submitOnChange: true, hideWhenEmpty: !bose, defaultValue: false
            if (question_bose) 
            { 
                input "actionType", "enum", title: "Que accion se va realizar?", required: false, defaultValue: "Turn On & Play", description: "Seleccione una accion..", options: [
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
                input "volume", "number", title: "Nivel de volumen", description: "0-100%", required: false
                input "delay_bose", "number", title: "Silenciar despues de:", required: false, description: "Segundos"
            
            }
       }
       
    	def available_rutines = location.helloHome?.getPhrases()*.label // get the available routines
        if (available_rutines) 
            {
            	available_rutines.sort()   // sort them alphabetically
                section("Ejecutar la siguiente rutina:") 
                {
	                input "action_callRoutine", "enum", title: "Seleccione una rutina", required: false, options: available_rutines
                }
        }
       section("Cambiar Alarma a este estado:")
       {
       		input "action_newAlarmStatus", "enum", title: "Cambiar a:", description: "Seleccionar..", required: false, options: ["Armed (Away)", "Armed(Home)", "Disarmed"] 
       }
       section("Acciones de video:")
       {
       		input "camera", "capability.imageCapture", required: false, multiple:false
       }
       section("Notificaciones")
       {
            input "action_notificationType", "enum", title: "Seleccione el tipo de aviso:", required: false, description: "Seleccionar.." , submitOnChange: true, options: ["None", "Push", "SMS", "Both"]
            if (action_notificationType == "SMS"  || action_notificationType == "Both") {  input "phone", "phone", title: "Telefono", required: (action_notificationType == "SMS"  || action_notificationType == "Both"), description: "Ingresar.." }
            if (action_notificationType == "Push" || action_notificationType == "Both") {  input "recipients", "contact", title: "Avisar a:", required: (action_notificationType == "Push" || action_notificationType == "Both")  }
            if (action_notificationType != "None") { input "msg", "text", title: "Mensaje", required: false, description: "Ingresar..", defaultValue: "Se ejecuto la rutina.." }
       }
    	
        
     }   
    
}


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	DEFAULT FUNCTIONS FOR SMARTAPP
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def installed() 
{
	subscribeToEvents()
}

def updated()
{
	unsubscribe()
	unschedule()
	subscribeToEvents()
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	EVENT SUSCRIPTIONS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def subscribeToEvents() 
{
	subscribe(app, eventHandler)
	subscribe(contact, "contact.open", eventHandler)
	subscribe(contactClosed, "contact.closed", eventHandler)
	subscribe(acceleration, "acceleration.active", eventHandler)
	subscribe(motion, "motion.active", eventHandler)
    subscribe(motionStop, "motion.inactive", eventHandler)
	subscribe(mySwitch, "switch.on", eventHandler)
	subscribe(mySwitchOff, "switch.off", eventHandler)
	subscribe(arrivalPresence, "presence.present", eventHandler)
	subscribe(departurePresence, "presence.not present", eventHandler)
	subscribe(smoke, "smoke.detected", eventHandler)
	subscribe(smoke, "smoke.tested", eventHandler)
	subscribe(smoke, "carbonMonoxide.detected", eventHandler)
	subscribe(water, "water.wet", eventHandler)
	subscribe(button1, "button", buttonHandler)
	subscribe(location, "routineExecuted", routineChanged)
    subscribe(location, "alarmSystemStatus", alarmHandler)
    subscribe(locks, "lock", lockHandler)

	if (triggerModes)
    {
		subscribe(location, modeChangeHandler)
	}

	if (timeOfDay) 
    {
		schedule(timeOfDay, scheduledTimeHandler)
	}
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	MAIN EVENT HANDLER
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def eventHandler(evt) 
{
	if (allOk) 
    {
		def lastTime = state[frequencyKey(evt)]
		if (oncePerDayOk(lastTime)) 
        {
			if (frequency) 
            {
				if (lastTime == null || now() - lastTime >= frequency * 60000)
                {
					takeAction(evt)
				}
				else 
                {
					log.debug "Not taking action because $frequency minutes have not elapsed since last action"
				}
			}
			else
            {
				takeAction(evt)
			}
		}
		else 
        {
			log.debug "Not taking action because it was already taken today"
		}
	}
}



//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	MAIN ACTION HANDLER
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
private takeAction(evt) 
{
	if (frequency || oncePerDay) 
    {
		state[frequencyKey(evt)] = now()
	}
    
    if (action_number_of_lights) { Action_LightEffects()  }
    if (action_switches_on     ) { Action_Switches_On()   }
	if (action_switches_off    ) { Action_Switches_Off()  }
	if (action_lock_open       ) { Action_Locks_Open()    }
    if (action_lock_close      ) { Action_Locks_Close()   }
    if (action_valve_open      ) { Action_Valves_Open()   }
    if (action_valve_close     ) { Action_Valves_Close()  }
    if (bose                   ) { Action_Sounds()        }
    if (action_newAlarmStatus  ) { Action_AlarmSystem()   }
    if (action_callRoutine     ) { Action_CallRoutine()   }
    if (action_notificationType) { Action_Notifications() }

}



//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS EVENT HANDLER TRIGGERS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def routineChanged(evt)
{
   def name = evt.displayName
   if (name == rutine)
   {
		takeAction(evt)
   }

}

def alarmHandler(evt)
{
	def selectedState = ""
    def newAlarmState = evt.value

    switch (trigAlarmStatus)
    {
    	
        case "Armed (Away)":
        		selectedState = "away"
                break
        case "Armed(Home)":
           		selectedState = "stay"
                break   
        case "Disarmed":
                selectedState = "off"
                break
        default: 
        		break
    }
    
    if (newAlarmState == selectedState)
    {
    	takeAction(evt)
    }
    
}

def modeChangeHandler(evt) {
	log.trace "modeChangeHandler $evt.name: $evt.value ($triggerModes)"
	if (evt.value in triggerModes) {
		eventHandler(evt)
	}
}

def scheduledTimeHandler() 
{
	eventHandler(null)
}


def lockHandler(evt)
{
	    
    log.debug "Event Name: ${evt.value}"
    log.debug "Event Description: ${evt.description}"
    log.debug "Event Text: ${evt.descriptionText}"
    
    if(evt.value == "unlocked") 
    {
    	if (evt.descriptionText == "Lock was unlocked with code 1")
        {
        	log.debug "Pedri abrio la puerta"
        }
        if (evt.descriptionText == "Lock was unlocked with code 2")
        {
        	log.debug "Papi abrio la puerta"
        }
   
        takeAction(evt)
    }
}

def buttonHandler(evt)
{
	def is_held = 0
    
    if (button_state == "Presionar y Mantener")
    {
    	is_held = 1
    }

    switch(evt.jsonData?.buttonNumber) 
    {
    	case 1:
        	if (button_pressed == "1" )
            {
                if ((evt.value == "held") && is_held )
            	{
  					takeAction(evt)
            	}
            	else 
            	{
					if (!is_held) { takeAction(evt) }
            	}
        	}
            break


        case 2:
        	if (button_pressed == "2" )
            {
                if ((evt.value == "held") && is_held )
            	{
  					takeAction(evt)
            	}
            	else 
            	{
					if (!is_held) { takeAction(evt) }
            	}
        	}
            break
        
        case 3:
        	if (button_pressed == "3" )
            {
                if ((evt.value == "held") && is_held )
            	{
					takeAction(evt)  
            	}
            	else 
            	{
					if (!is_held) { takeAction(evt) }
            	}
        	}
            break
        
        case 4:
        	if (button_pressed == "4" )
            {
                if ((evt.value == "held") && is_held )
            	{
					takeAction(evt)  
            	}
            	else 
            	{
					if (!is_held) { takeAction(evt) }
            	}
        	}
            break
        
        default:
        	break
    }
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS FOR DYNAMIC PAGE 
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
private anythingSet() {
	for (name in ["motion","contact","contactClosed","acceleration","mySwitch","mySwitchOff","arrivalPresence","departurePresence","smoke","water","locks", "button1","triggerModes","timeOfDay"]) {
		if (settings[name]) {
			return true
		}
	}
	return false
}

private ifUnset(Map options, String name, String capability) {
	if (!settings[name]) {
		input(options, name, capability)
	}
}

private ifSet(Map options, String name, String capability) {
	if (settings[name]) {
		input(options, name, capability)
	}
}


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS FOR TIMING CONDITIONS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
private getAllOk() {
	modeOk && daysOk && timeOk
}

private getModeOk() {
	def result = !modes || modes.contains(location.mode)
	log.trace "modeOk = $result"
	result
}

private getDaysOk() {
	def result = true
	if (days) {
		def df = new java.text.SimpleDateFormat("EEEE")
		if (location.timeZone) {
			df.setTimeZone(location.timeZone)
		}
		else {
			df.setTimeZone(TimeZone.getTimeZone("America/Guatemala"))
		}
		def day = df.format(new Date())
		result = days.contains(day)
	}
	result
}

private getTimeOk() 
{
	def result = true
	if (starting && ending) 
    {
		def currTime = now()
		def start = timeToday(starting, location?.timeZone).time
		def stop = timeToday(ending, location?.timeZone).time
		result = start < stop ? currTime >= start && currTime <= stop : currTime <= stop || currTime >= start
	}
	result
}

private hhmm(time, fmt = "h:mm a")
{
	def t = timeToday(time, location.timeZone)
	def f = new java.text.SimpleDateFormat(fmt)
	f.setTimeZone(location.timeZone ?: timeZone(time))
	f.format(t)
}

private timeIntervalLabel()
{
	(starting && ending) ? hhmm(starting) + "-" + hhmm(ending, "h:mm a z") : ""
}


private frequencyKey(evt) {
	//evt.deviceId ?: evt.value
	"lastActionTimeStamp"
}

private dayString(Date date) {
	def df = new java.text.SimpleDateFormat("yyyy-MM-dd")
	if (location.timeZone) {
		df.setTimeZone(location.timeZone)
	}
	else {
		df.setTimeZone(TimeZone.getTimeZone("America/Guatemala"))
	}
	df.format(date)
}

private oncePerDayOk(Long lastTime) 
{
	def result = lastTime ? dayString(new Date()) != dayString(new Date(lastTime)) : true
	log.trace "oncePerDayOk = $result - $lastTime"
	result
}



//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS FOR COLOR SETTINGS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
int getColorValue (String color_name)
{
	def color = 0
    def saturation = 80
    
    switch (color_name)
    {
		case "White":
			color = 52
			break;
		case "Daylight":
			color = 53
			break;
		case "Soft White":
			color = 23
			break;
		case "Warm White":
			color = 22
			break;
		case "Blue":
			color = 70
			break;
		case "Green":
			color = 39
			break;
		case "Yellow":
			color = 25
			break;
		case "Orange":
			color = 10
			break;
		case "Purple":
			color = 75
			break;
		case "Pink":
			color = 83
			break;
		case "Red":
			color = 100
			break;
        default:
        	break
                
    }
    
    color

}

int getSaturationValue (String color_name)
{
    def saturation = 100
    
    switch (color_name)
    {
		case "White":
			saturation = 19
			break;
		case "Daylight":
			saturation = 91
			break;
		case "Soft White":
			saturation = 56
			break;
		case "Warm White":
			saturation = 48 //80
			break;
        default:
        	break
                
    }
    
    saturation

}

int getBrightnessValue (String percentage)
{
	def brightness = 0
    
    switch (percentage)
    {
		case "10%":
			brightness = 10
			break;
		case "20%":
			brightness = 20
			break;
		case "30%":
			brightness = 30
			break;
		case "40%":
			brightness = 40
			break;
		case "50%":
			brightness = 50
			break;
		case "60%":
			brightness = 60
			break;
		case "70%":
			brightness = 70
			break;
		case "80%":
			brightness = 80
			break;
		case "90%":
			brightness = 90
			break;
		case "100%":
			brightness = 100
			break;
        default:
        	break
                
    }
    
    brightness

}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	DEVICE EVENT HANDLERS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def Action_Sounds()
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
			break
	}
    
    if (delay_bose)
    {
    	runIn(delay_bose, StopSound )
    }

}


def Action_Switches_On()
{
	turnOnSwitches_On()
    
    if (delay_switches_on)
    {
    	runIn(delay_switches_on, turnOffSwitches_On )
    }

}

def Action_Switches_Off()
{
	turnOffSwitches_Off()
    
    if (delay_switches_off)
    {
    	runIn(delay_switches_off, turnOnSwitches_Off )
    }

}

def Action_Locks_Open()
{
	UnlockDoors_Open()
    
    if (delay_lock_open)
    {
    	runIn(delay_lock_open, LockDoors_Open )
    }
}

def Action_Locks_Close()
{
	LockDoors_Close()
    
    if (delay_lock_close)
    {
    	runIn(delay_lock_close, UnlockDoors_Close )
    }
}

def Action_Valves_Open() 
{
	OpenValves_Open()
    
    if (delay_valve_open)
    {
    	runIn(delay_valve_open, CloseValves_Open )
    }
}

def Action_Valves_Close()
{
	CloseValves_Close()
    
    if (delay_valve_close)
    {
    	runIn(delay_valve_close, OpenValves_Close )
    }

}


def Action_CallRoutine()
{
	location.helloHome?.execute(action_callRoutine)
}

def Action_AlarmSystem()
{
	def newState = ""
    switch(action_newAlarmStatus)
    {
        case "Armed (Away)":
        		newState = "away"
                break
        case "Armed(Home)":
           		newState = "stay"
                break   
        case "Disarmed":
                newState = "off"
                break
        default: 
        		break
    }
    
    if (newState != "")
    {
    	sendLocationEvent(name: "alarmSystemStatus", value: newState)
    }
}

def Action_Notifications()
{
	switch (action_notificationType)
    {
    	case "Both":
        			if (phone)      
                    { 
                    	sendSms (phone, msg) 
                    	log.debug "SMS was sent to $phone"
                    }
        			if (recipients && location.contactBookEnabled) 
                    { 
                    	//sendNotificationToContacts(msg, recipients)
                        sendPush(msg)
                    	log.debug "Push was sent to $recipients"
                    }
        			break
        case "SMS":
        			if (phone)      
                    { 
                    	sendSms (phone, msg)
                    	log.debug "Only SMS was sent to $phone"
                    }
        			break
        case "Push":
        			if (recipients && location.contactBookEnabled) 
                    { 
                    	//sendNotificationToContacts(msg, recipients) 
                        sendPush(msg)
                    	log.debug "Only Push was sent to $recipients"
                    }
        			break
        default:
        			log.debug "Notification requested, but do nothing"
                    break
    }

}


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS 
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Turn off the sound
def StopSound()
{
	def cmd = [:]
	cmd ? bose.off(cmd) : bose.off()
}

// Switches to be turned on
def turnOnSwitches_On() 
{
	action_switches_on.on() 
}

def turnOffSwitches_On() 
{
	action_switches_on.off() 
}
//-------------------------------

// Switches to be turned off
def turnOnSwitches_Off() 
{
	action_switches_off.on() 
}

def turnOffSwitches_Off() 
{
	action_switches_off.off() 
}
//--------------------------------

// Locks to be open
def UnlockDoors_Open() 
{
	action_lock_open.unlock()
}

def LockDoors_Open() 
{
	action_lock_open.lock()
}
//--------------------------------

// Locks to be close
def UnlockDoors_Close() 
{
	action_lock_close.unlock()
}

def LockDoors_Close() 
{
	action_lock_close.lock()
}
//--------------------------------

// Valves to be open
def OpenValves_Open()
{
	action_valve_open.open()
}

def CloseValves_Open()
{
	action_valve_open.close()
}
//--------------------------------

// Valves to be close
def OpenValves_Close()
{
	action_valve_close.open()
}

def CloseValves_Close()
{
	action_valve_close.close()
}

//--------------------------------

// Lights to be controlled
def Action_LightEffects()
{
     //Variables for naming groups
     def new_level_of_light = 0
     def new_color_of_light = ""
     def new_flash_of_light = 0
     def new_quest_of_light = 0
     def hueColor           = 0
     def hueBrightness      = 0
     def hueSaturation      = 0
     
     log.debug "Numer of Lights : $action_number_of_lights"
     
     def counter = action_number_of_lights as Integer
     for (int i = 1; i <= counter; i++) 
     {
			new_level_of_light = settings."action_lights_lvl_${i}"
            new_color_of_light = settings."action_lights_color_${i}"
            new_quest_of_light = settings."action_question_flashes_${i}"
            new_flash_of_light = settings."action_lights_flashes_${i}"
       
            hueBrightness = getBrightnessValue(new_level_of_light)
            hueColor      = getColorValue(new_color_of_light)
            hueSaturation = getSaturationValue(new_color_of_light)
       
            if (!new_quest_of_light)
            {
                log.debug "Level of light $i : $new_level_of_light"
                log.debug "Color of light $i : $new_color_of_light value as $hueColor"

                settings."action_lights_${i}"*.setLevel(hueBrightness)
                settings."action_lights_${i}"*.setSaturation(hueSaturation)
                settings."action_lights_${i}"*.setHue(hueColor)
                settings."action_lights_${i}"*.on()
            }
            else
            {

                log.trace "Now flash the lights"
                settings."action_lights_${i}"*.setLevel(hueBrightness)
                settings."action_lights_${i}"*.setSaturation(hueSaturation)
                settings."action_lights_${i}"*.setHue(hueColor)
                
                for (int j = 0; j < new_flash_of_light; j++)
                {
                    settings."action_lights_${i}"*.on()
                    pause(600)
                    settings."action_lights_${i}"*.off()
                    pause(600)

                }


        	}
     }
     
                  

}