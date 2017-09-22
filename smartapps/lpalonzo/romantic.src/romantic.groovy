definition(
    name: "Romantic",
    namespace: "lpalonzo",
    author: "LP",
    description: "Escenario para arrimarlo",
    category: "Convenience",
    iconUrl: "http://i2.kym-cdn.com/entries/icons/original/000/008/549/Naamloos-2.png",
    iconX2Url: "http://i2.kym-cdn.com/entries/icons/original/000/008/549/Naamloos-2.png"
)

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  PREFERENCES
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

preferences 
{
 	page(name: "MainPage"      , uninstall: true  , nextPage: "Guardar"    )
    page(name: "Guardar"       , uninstall: true  , install:  true         )
    page(name: "Usuarios"      , uninstall: false )
    page(name: "Cerraduras"    , uninstall: false )
    page(name: "Seguridad"     , uninstall: false )
}


def MainPage()
{
	dynamicPage(name: "MainPage") 
    {
    	section() 
        {
        	paragraph image: "https://www.infineon.com/iot-security-ebrochure/images/default/icon_verstaendnis.png",
            title: "HomeTap",
            required: false,
            "Control y seguridad para tu hogar"
    	}
        
        section("Categorias") 
        {
        		href(name: "Users" , title: "Usuarios"  ,  description: null  , page: "Usuarios"  , image: "https://cdn.pixabay.com/photo/2016/04/15/18/05/computer-1331579_960_720.png" 		)
                href(name: "Chapas", title: "Cerraduras",  description: null  , page: "Cerraduras", image: "http://design.orionoptics.co.uk/Resources/Lock-icon.png"							)
                href(name: "Securi", title: "Seguridad" ,  description: null  , page: "Seguridad" , image: "https://cdn4.iconfinder.com/data/icons/free-large-boss-icon-set/512/Policeman.png" 	)

        }
    
    }
}


def Usuarios()
{
	dynamicPage(name: "Usuarios") 
    {
    	section() 
        {
        	paragraph image: "https://cdn.pixabay.com/photo/2016/04/15/18/05/computer-1331579_960_720.png",
            title: "Usuarios",
            required: false,
            "Control de usuarios de la casa"
    	}
    	
        section("Agregar usuarios") 
        {
		   input "number_of_users", "enum", title: "Cuantos usuarios deseas crear?", required: false, submitOnChange: true, description: "Seleccionar..", options: ["1","2","3","4","5"], image: "https://i.pinimg.com/originals/72/76/89/7276893013bfdc5d07f01508f95e20d4.jpg"      
        }
        
        if (number_of_users != null)
        {
        	//Variable setup 
            def name			=	"name_"
            def code			=	"code_"
            def presence		=	"presence_"
            def arrivals		=	"arrival_"
            def departures		=	"departures_"
            
            def new_name		=	""
            def new_code		=	""
            def new_presence	=	""
            def	new_arrivals	=	""
            def	new_departures	=	""
        
        	def counter			=	number_of_users as Integer
            for (int x = 1; x <= counter; x++) 
            {
                new_name			= name			+ (x as String)
                new_code			= code			+ (x as String)
                new_presence		= presence		+ (x as String)
                new_arrivals		= arrivals		+ (x as String)
                new_departures		= departures	+ (x as String)   
                
                section() 
                {
                    paragraph image: "http://download.seaicons.com/icons/custom-icon-design/flatastic-11/512/User-blue-icon.png",
                    title: "Usuario No. ${x}",
                    required: false, ""
                    
                    input new_name		,	"text", title: "Nombre:",  required: false, description: "Ingresar..", image: "https://cdn3.iconfinder.com/data/icons/business-office-2/512/card_employer_badge_identification-512.png"
                    input new_code		, 	"enum", title: "Asociar a este código:", required: false,  image: "https://cdn3.iconfinder.com/data/icons/edition/100/keycode-512.png", description: "Seleccionar..",  options: ["Code 1", "Code 2", "Code 3", "Code 4", "Code 5", "App", "Manual"] 
               		input new_presence	,	"capability.presenceSensor", title: "Asociar a este dispositivo:", required: false, multiple: false, submitOnChange: true, description: "Seleccionar..", image: "http://icon-park.com/imagefiles/location_map_pin_purple5.png"	 
                    input new_arrivals	,	"bool", title: "Notificar cuando llegue?"   , required: false, submitOnChange: true, image: "http://flyavp.com/wp-content/themes/AVP/library/images/Arrivals_blue.png"      	           	
		            input new_departures,	"bool", title: "Notificar cuando se marche?", required: false, submitOnChange: true, image: "https://userscontent2.emaze.com/images/d5437e72-fe68-4010-b4e8-1ace56fd27c7/e7ff4de3-f191-425f-b5a1-acfba7e3793c.png"      	           	       
               
               }//User
            
            }//For
        }//user not null
        

        
    }//dynamic page
}//Usuarios
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def Cerraduras()
{
	dynamicPage(name: "Cerraduras") 
    {
    	section() 
        {
        	paragraph image: "http://design.orionoptics.co.uk/Resources/Lock-icon.png",
            title: "Cerraduras",
            required: false,
            "Control de cerraduras de la casa"
            
            input "number_of_scenarios", "enum", title: "Cuantos escenarios deseas crear?", required: false, submitOnChange: true, description: "Seleccionar..", options: ["1","2","3","4","5"], image: "https://png.icons8.com/color/1600/hashtag-activity-grid"
      
    	}        
       
        if (number_of_scenarios != null)
        {
        	//Variable setup for dynamic smartapps -----------------------
            def lock				= "lock_"
            def notify_unlock		= "notify_unlock_"
            def notify_who			= "notify_who_"
            def autolock			= "autolock_"
            def schedules  			= "schedules_"
            
            def fromtime			= "fromtime_"
            def fromothertime       = "fromothertime_"
            def fromoffsetvalue		= "fromoffsetvalue_"
            def fromoffsetdir		= "fromoffsetdir_"
            
            def totime				= "totime_" 
            def toothertime			= "toothertime_"
            def tooffsetvalue		= "tooffsetvalue_"
            def tooffsetdir			= "tooffsetdir_"
            
       		def huecolor		    = "huecolor_"
            def huelux				= "huelux_"
            def hueluxlevel			= "hueluxlevel_"
            def huecolorlevel		= "huecolorlevel_"
            def huesat				= "huesat_"
            def huetone				= "huetone_"
            def turnon				= "turnon_"
            def turnoff				= "turnoff_"
            def turnoffdelay		= "turnoffdelay_"
            def sound				= "sound_"
            def soundaction			= "soundaction_"
            def soundvolume			= "soundvolume_"
            
            def new_lock			= ""
            def new_notify_unlock	= ""
            def new_notify_who		= ""
            def new_autolock		= ""
            def new_schedules		= ""
            
            def new_fromtime		= ""
            def new_fromothertime   = ""
            def new_fromoffsetvalue	= ""
            def new_fromoffsetdir	= ""
            
            def new_totime			= ""
            def new_toothertime		= ""
            def new_tooffsetvalue	= ""
            def new_tooffsetdir		= ""
            
        	def new_huecolor		= ""
			def new_huecolorlevel	= ""
            def new_huesat			= ""
            def new_huetone			= ""
            def new_huelux			= ""
            def new_hueluxlevel		= ""
            def new_turnon			= ""
            def new_turnoff			= ""
            def new_turnoffdelay	= ""
            def new_sound			= ""
            def new_soundaction		= ""
            def new_soundvolume		= ""
            
			def counter 			= number_of_scenarios as Integer
            for (int x = 1; x <= counter; x++) 
            {
                new_lock			= lock				+ (x as String)
                new_notify_unlock	= notify_unlock		+ (x as String)
                new_notify_who		= notify_who		+ (x as String)
                new_autolock		= autolock			+ (x as String)
                new_schedules		= schedules			+ (x as String)
                
                section() 
                {
                    paragraph image: "https://png.icons8.com/hash/color/1600",
                    title: "Escenario No. ${x}",
                    required: false, ""
                }
                section("Controlar esta cerradura:")
                {	
         			input new_lock,  "capability.lock", title: "Cerradura", required: false, multiple: false, submitOnChange: true, description: "Seleccionar..", image: "https://cdn1.iconfinder.com/data/icons/hotel-25/24/handle_door_lock_key_hole-512.png"
        			if (settings."lock_${x}" != null)
                    {                    	   
                  		input new_autolock,    		"number", title: "Autobloqueo", required:false, hideWhenEmpty: !(settings."lock_${x}" != null), description: "Cerrar puerta despues de..(segundos)", image: "https://cdn2.iconfinder.com/data/icons/apple-inspire-white/100/Apple-11-128.png"
                    	input new_notify_unlock,	"bool", title: "Notificar cuando alguien llegue?", required: false, submitOnChange: true, image: "https://cdn3.iconfinder.com/data/icons/web-icons-1/64/Unlock-512.png"
                        input new_notify_who, 		"bool", title: "Notificar quien llega?", required: false, submitOnChange: true, image: "https://cdn3.iconfinder.com/data/icons/aviation-2/505/Aviation-37-128.png"      	           	
                    	input new_schedules, "enum", title: "Cuántos horarios diferentes a automatizar?", required: false, submitOnChange: true, description: "Seleccionar..", options: ["1","2","3","4","5"], image: "https://icon-icons.com/icons2/516/PNG/512/calendar_clock_schedule_icon-icons.com_51085.png"
                    }                               
                }
                             
          		if (settings."schedules_${x}" != null  &&  settings."lock_${x}" != null)
                {
                   	def counter2 			= settings."schedules_${x}" as Integer
                   	for (int i = 1; i <= counter2; i++) 
                   	{
                              new_fromtime			= fromtime			+ (x as String) + "_" + (i as String)
            				  new_fromothertime   	= fromothertime		+ (x as String) + "_" + (i as String)
            				  new_fromoffsetvalue	= fromoffsetvalue	+ (x as String) + "_" + (i as String)
            				  new_fromoffsetdir		= fromoffsetdir		+ (x as String) + "_" + (i as String)
                              
                              new_totime			= totime			+ (x as String) + "_" + (i as String)
                              new_toothertime		= toothertime		+ (x as String) + "_" + (i as String)
                              new_tooffsetvalue		= tooffsetvalue		+ (x as String) + "_" + (i as String)
                              new_tooffsetdir		= tooffsetdir		+ (x as String) + "_" + (i as String)
                              
                              new_huecolor			= huecolor			+ (x as String) + "_" + (i as String)
                              new_huelux			= huelux			+ (x as String) + "_" + (i as String)
                              new_huecolorlevel		= huecolorlevel		+ (x as String) + "_" + (i as String)
                              new_hueluxlevel		= hueluxlevel		+ (x as String) + "_" + (i as String)
                              new_huesat			= huesat			+ (x as String) + "_" + (i as String)
                              new_huetone			= huetone			+ (x as String) + "_" + (i as String)
                              new_turnon			= turnon			+ (x as String) + "_" + (i as String)
                              new_turnoff			= turnoff			+ (x as String) + "_" + (i as String)
                              new_turnoffdelay		= turnoffdelay		+ (x as String) + "_" + (i as String)
                              new_sound				= sound				+ (x as String) + "_" + (i as String)
                              new_soundaction		= soundaction		+ (x as String) + "_" + (i as String)
                              new_soundvolume		= soundvolume		+ (x as String) + "_" + (i as String)

                              section("Acciones para Horario No. ${i}:")
                              {	          
                                    //Inicio
                                    input new_fromtime, "enum", title: "Inicio", required: false, multiple: false, submitOnChange: true, options: ["Amanecer", "Mediodia", "Atardecer", "Medianoche", "Especificar"], description: "Seleccionar..", image: "http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-8/256/Flag-green-icon.png"
                                    if ( settings."fromtime_${x}_${i}" != null  &&  settings."fromtime_${x}_${i}" != "Especificar")
                                    {
                                    	input new_fromoffsetvalue,  "number", title: "Opcional:", required: false, submitOnChange:true, description: "Desfazar estos minutos", image: "https://cdn3.iconfinder.com/data/icons/flat-icons-big-sized/64/equaliser-512.png"
       									input new_fromoffsetdir  ,  "enum", title: "Opcional:", description: "Retrasar o Adelantar el inicio", required: ( settings."fromoffsetvalue_${x}_${i}" != null), submitOnChange:true, options: ["Retrasar","Adelantar"], image: "https://icon-icons.com/icons2/37/PNG/512/more_less_3931.png"                                    
                                    }
                                    else if (settings."fromtime_${x}_${i}" == "Especificar")
                                    {
                                    	input new_fromothertime, "time", title: "Inicio", description: "Seleccionar..", required: false, submitOnChange: true, image: "http://icons.iconarchive.com/icons/paomedia/small-n-flat/512/clock-icon.png"
                                    }
                                    
                                    //Fin
                                    input new_totime, "enum", title: "Fin", required: false, multiple: false, submitOnChange: true, options: ["Amanecer", "Mediodia", "Atardecer", "Medianoche", "Especificar"], description: "Seleccionar..", image: "http://www.free-icons-download.net/images/red-flag-icon-94794.png"
                                    if ( settings."totime_${x}_${i}" != null  &&  settings."totime_${x}_${i}" != "Especificar")
                                    {
                                    	input new_tooffsetvalue,  "number", title: "Opcional:", required: false, submitOnChange:true, description: "Desfazar estos minutos", image: "https://cdn3.iconfinder.com/data/icons/flat-icons-big-sized/64/equaliser-512.png"
       									input new_tooffsetdir  ,  "enum", title: "Opcional:", description: "Retrasar o Adelantar el final", required: ( settings."tooffsetvalue_${x}_${i}" != null), submitOnChange:true, options: ["Retrasar","Adelantar"], image: "https://icon-icons.com/icons2/37/PNG/512/more_less_3931.png"                                    
                                    }
                                    else if (settings."totime_${x}_${i}" == "Especificar")
                                    {
                                    	input new_toothertime, "time", title: "Fin", required: false, description: "Seleccionar..", submitOnChange: true, image: "http://icons.iconarchive.com/icons/paomedia/small-n-flat/512/clock-icon.png"
                                    }                                    
                                    
                                    //Luces de Colores 
                                    input new_huecolor,  "capability.colorControl", title: "Luces de Colores", multiple: true, required: false, submitOnChange: true, description: "Animá el ambiente con tu llegada!", image: "https://thelampster.com/wp-content/uploads/2015/10/rgb_led.png"                  
                                    if  (settings."huecolor_${x}_${i}" != null)
                                    {
                                        input new_huetone, "enum", title: "Color", submitOnChange: true, description: "Seleccionar tonalidad..", required: (settings."huecolor_${x}_${i}" != null), multiple:false, hideWhenEmpty: !(settings."huecolor_${x}_${i}" != null), options: ["Random", "White", "Daylight", "Soft White", "Warm White", "Red","Green","Blue","Yellow","Orange","Purple","Pink"], image: "http://www.free-icons-download.net/images/color-palette-icon-9236.png"
                                        def selected_color = settings."huetone_${x}_${i}"
                                        if (saturationNeeded(selected_color)) 
                                        { 
                                            input new_huesat, "enum", title: "Saturacion", required: saturationNeeded(selected_color), description: "0-100%", multiple: false, hideWhenEmpty: !saturationNeeded(selected_color), options: ["10%", "20%","30%","40%","50%","60%","70%","80%","90%","100%"], image: "https://lh3.googleusercontent.com/ZVwrnYprLJaTz3Lx3R7p6dPMSz-sfRg-QfUp73Auit_kAQsHzYNwApZaOMKaNE34TiM=w300"
                                        }                                                             	
                                        input new_huecolorlevel, "enum", title: "Brillo:", required:(settings."huecolor_${x}_${i}" != null) , description: "0-100%", multiple: false, hideWhenEmpty: !(settings."huecolor_${x}_${i}" != null), options: ["10%", "20%","30%","40%","50%","60%","70%","80%","90%","100%"], image: "https://lh3.googleusercontent.com/SQ3udXqvxWK4TOnC8UhGnw4jE93M6NyT3IMePjjechN_Y0LV6xeQhEp9ONVNUYX9cTUw=w300"                                
                                    }

                                    //Luces de Dimmerizables 
                                    input new_huelux,  "capability.light",          title: "Luces Dimmerizables", multiple: true, required: false, submitOnChange: true, description: "Seleccionar..", image: "http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/light-bulb-icon.png"                        
                                    if ((settings."huelux_${x}_${i}" != null) )
                                    {
                                        input new_hueluxlevel, "enum", title: "Brillo:", required:((settings."huelux_${x}_${i}" != null)), description: "0-100%", multiple: false, hideWhenEmpty: !((settings."huelux_${x}_${i}" != null) ), options: ["10%", "20%","30%","40%","50%","60%","70%","80%","90%","100%"], image: "http://www.myiconfinder.com/uploads/iconsets/256-256-ce5953b003b6ac4a7f4c5beaa9a08b5d.png"                           
                                    }

                                    //Encender Dispositivos
                                    input new_turnon,  "capability.switch", title: "Encender estos dispositivos",  multiple: true, required: false, submitOnChange: true, description: "Seleccionar..", image: "https://cdn0.iconfinder.com/data/icons/superuser-web-kit/512/680460-button_switch_power_option_control_lever_on-512.png"

                                    //Apagar Dispositivos
                                    input new_turnoff, "capability.switch", title: "Apagar estos dispositivos"  ,  multiple: true, required: false, submitOnChange: true, description: "Seleccionar..", image: "https://cdn0.iconfinder.com/data/icons/superuser-web-kit/512/680455-button_switch_power_option_control_lever_off-512.png"
                                    if (settings."turnoff_${x}_${i}" != null ) 
                                    {  
                                        input new_turnoffdelay, "number", title: "Retrasar la accion este tiempo:", required:false, description: "Opcional(Segundos)", image: "https://cdn3.iconfinder.com/data/icons/transfers/100/239345-reload_time_delay-512.png"
                                    }

                                    //Altavoces                     
                                    input new_sound, "capability.musicPlayer", title: "Altavoz", required: false, submitOnChange: true, description: "Seleccionar..", image: "http://vignette4.wikia.nocookie.net/criminal-case-grimsborough/images/1/1f/Speaker-icon.png/revision/latest?cb=20140611110935"
                                    if (settings."sound_${x}_${i}" != null) 
                                    { 
                                        input new_soundaction, "enum", title: "Que accion se va realizar?", required: (settings."sound_${x}_${i}" != null), hideWhenEmpty: !(settings."sound_${x}_${i}" != null), submitOnChange: true, multiple: false, description: "Seleccione una accion.." , image: "https://cdn4.iconfinder.com/data/icons/classic-icons-1/512/013.png", options: [
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
                                            "Play Preset 6",
                                            "Change to Aux..",
                                            "Join", 
                                            "Leave"]

                                        input new_soundvolume, "number", title: "Volumen", description: "0-100%", required: (settings."sound_${x}_${i}" != null), hideWhenEmpty: !(settings."sound_${x}_${i}" != null), image: "http://susannebaumann.de/wp-content/uploads/2016/01/audio-volume-medium.png"           
                                    }
                                } //Acciones disponibles
                     }//for2
                 } //schedules
             }//for      
        }//numberofscenarios
    }//dynamicpage
}//cerraduras
//-----------------------------------------------------------------------------------------------------



                

                


def Seguridad()
{
	dynamicPage(name: "Seguridad") 
    {
    	section() 
        {
        	paragraph image: "https://cdn4.iconfinder.com/data/icons/free-large-boss-icon-set/512/Policeman.png",
            title: "Seguridad",
            required: false,
            "Control de Seguridad de la casa"
    	}
    	
        section()
        {
        	paragraph image: "https://cdn4.iconfinder.com/data/icons/basic-office-icons/512/gears.png",
            title: "Configuraciones",
            required: false,
            ""
            
            input "controlAccess", "bool", title: "Control de Acceso (Alguien viene/Todos se van)", required: false, submitOnChange: true, image: "https://cdn0.iconfinder.com/data/icons/public-signs-2/80/Public_signs-04-512.png"      	           	       
            input "controlModes" , "bool", title: "Control de Modos", required: false, submitOnChange: true, image: "https://d13yacurqjgara.cloudfront.net/users/39315/screenshots/1732030/dribbble-dms-icons.png"      	           	       
           	input "controlAlarm" , "bool", title: "Control de Alarma", required: false, submitOnChange: true, image: "https://maxcdn.icons8.com/Color/PNG/512/City/fire_alarm-512.png"      	           	       
       
        }
        
        section() 
        {
        	paragraph image: "http://icons.iconarchive.com/icons/webalys/kameleon.pics/512/Burglar-icon.png",
            title: "Control de Intrusion",
            required: false,
            ""                    
            input	"intruderSW",  "capability.switch", title: "Interruptor de Alerta", description: "Seleccionar..", required: false, multiple: false, submitOnChange: true, image: "http://contactcarelifeline.co.uk/wp-content/themes/contactcare/img/logos/icon_button_color.png"
        	input	"alarmLights", "capability.light",  title: "Luz de Alerta:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar..", image: "https://cdn0.iconfinder.com/data/icons/flat-vector-1/100/14-Alert-512.png"
                        
        }
    }
}

def Guardar()
{
	dynamicPage(name: "Guardar") 
    {
    	section() 
        {
        	paragraph image: "http://icons.iconarchive.com/icons/custom-icon-design/flatastic-9/512/Save-as-icon.png",
            title: "Guardar",
            required: false,
            "Guardar como"
    	}
    	
        section("Guardar app como") 
        {
        	label title: "Nombre:", required: true, description: "Ingresar", image: "https://cdn2.iconfinder.com/data/icons/pretty-office-10/128/Student-id-128.png"
        }
    }
}



//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  SMARTAPP DEFAULT FUNCTIONS
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def installed() 
{
	suscribeAll()
}

def updated() 
{
	unsubscribe()
	suscribeAll()
}

def suscribeAll()
{
	//Eventos for Door Unlock
	subscribe(lock_1, 		"lock.unlocked",			lockHandler)
    subscribe(lock_2, 		"lock.unlocked",			lockHandler)
    subscribe(lock_3, 		"lock.unlocked",			lockHandler)
    subscribe(lock_4, 		"lock.unlocked",			lockHandler)
    subscribe(lock_5, 		"lock.unlocked",			lockHandler)
    
    //Events for Presence (Arrivals)
 	subscribe(presence_1, 	"presence.present", 		arrivalHandler)
    subscribe(presence_2, 	"presence.present", 		arrivalHandler)
    subscribe(presence_3, 	"presence.present", 		arrivalHandler)
    subscribe(presence_4, 	"presence.present", 		arrivalHandler)
    subscribe(presence_5, 	"presence.present", 		arrivalHandler)
    
    //Events for Presence (Departures)    
    subscribe(presence_1, 	"presence.not present", 	departureHandler)
    subscribe(presence_2, 	"presence.not present", 	departureHandler)
    subscribe(presence_3, 	"presence.not present", 	departureHandler)
    subscribe(presence_4, 	"presence.not present", 	departureHandler)
    subscribe(presence_5, 	"presence.not present", 	departureHandler)
    
    //Events for Intruder Alarm
    subscribe(intruderSW, 	"switch.on", 				alarmHandler)
    
    //Events for App pressing
    subscribe(app, appTouch)
    
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  EVENT HANDLERS
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def appTouch(evt)
{   
	def counter = number_of_scenarios as Integer
    for (int i = 1; i<= counter; i++)
    {
    	def device = settings."lock_${i}"
        def schedu = settings."schedules_${i}"
        
        if (device != null) log.info "Lock ${i}: $device"
        if (schedu != null) log.warn "Schedules ${i}: $schedu"
        
        def counter2 = schedu as Integer
        for (int x = 1; x<= counter; x++)
    	{
        	def tipoHorario = settings."fromtime_${i}_${x}"
            if (tipoHorario != null) log.error "tipo: $tipoHorario"
    	}
    }
    
}

def lockHandler(evt)
{
	log.info "HomeTap Started: $app.label (LockHandler)"
    def counter 	= number_of_scenarios as Integer
    def thisGuy     = evt.device as String
    def thisHappen	= evt.value  as String
    
    for (int x = 1; x <= counter; x++) 
    { 
    	def locks 			= settings."lock_${x}"
        
        if (locks != null)
        {
			 if ( (locks as String) == thisGuy)
             {
             	log.info "Event = $thisGuy is $thisHappen"
                Action_Lock(x)
             }
        
        }
        
        
   }
    
    
    log.info "HomeTap Completed: $app.label (LockHandler)"
}

def arrivalHandler(evt)
{
	log.info "HomeTap Started: $app.label (ArrivalHandler)"
    
    
    
    
    log.info "HomeTap Completed: $app.label (ArrivalHandler)"
}

def departureHandler(evt)
{
	log.info "HomeTap Started: $app.label (DepartureHandler)"
    
    
    
    
    log.info "HomeTap Completed: $app.label (DepartureHandler)"
}

def alarmHandler(evt)
{
	log.info "HomeTap Started: $app.label (AlarmHandler)"
    
    
    
    
    log.info "HomeTap Completed: $app.label (AlarmHandler)"
}





//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  HELPERS
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def saturationNeeded(String color)
{
	def result = false
    for (name in ["Red","Green","Blue","Yellow","Orange","Purple","Pink"]) 
    {
		if (color == name) 
        {
        	result = true
        }
	}
    result    
}

def Action_Lock(Number index )	
{
	def thisLock 		= settings."lock_${index}"
	def noti_unlock		= settings."notify_unlock_${index}"
    def noti_who		= settings."notify_who_${index}"
    def autoLocks		= settings."autolock_${index}"
    def horarios		= settings."schedules_${index}"
    
    log.warn "Lock at Scenario: $index"
    log.warn "ThisLock = $thisLock"
    log.warn "ThisLock = $noti_unlock"
	log.warn "ThisLock = $noti_who"
	log.warn "ThisLock = $autoLocks"
	log.warn "ThisLock = $horarios"

}