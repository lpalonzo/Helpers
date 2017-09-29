definition(
    name: "Romantic",
    namespace: "lpalonzo",
    author: "LP",
    description: "App under Development",
    category: "Convenience",
    iconUrl: "http://i2.kym-cdn.com/entries/icons/original/000/008/549/Naamloos-2.png",
    iconX2Url: "http://i2.kym-cdn.com/entries/icons/original/000/008/549/Naamloos-2.png"
)
import groovy.json.JsonSlurper

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
                    input new_code		, 	"enum", title: "Asociar a este código:", required: false,  image: "https://cdn3.iconfinder.com/data/icons/edition/100/keycode-512.png", description: "Seleccionar..",  options: ["Code 1", "Code 2", "Code 3", "Code 4", "Code 5", "App"] 
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
                        if (settings."notify_unlock_${x}" == true)
                        {
                        	input new_notify_who, 		"bool", title: "Notificar quien llega?", hideWhenEmpty: !(settings."notify_unlock_${x}" == true), required: false, submitOnChange: true, image: "https://cdn3.iconfinder.com/data/icons/aviation-2/505/Aviation-37-128.png"      	           	
                    	}
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
                                        input new_turnoffdelay, "number", title: "Retrasar la accion este tiempo:", required:false, description: "Opcional(Minutos)", image: "https://cdn3.iconfinder.com/data/icons/transfers/100/239345-reload_time_delay-512.png"
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
            if (controlAccess)
            {
            	input "controlModes" , "bool", title: "Control de Modos",  hideWhenEmpty: !controlAccess, required: false, submitOnChange: true, image: "https://d13yacurqjgara.cloudfront.net/users/39315/screenshots/1732030/dribbble-dms-icons.png"      	           	       
           		input "controlAlarm" , "bool", title: "Control de Alarma", hideWhenEmpty: !controlAccess, required: false, submitOnChange: true, image: "https://maxcdn.icons8.com/Color/PNG/512/City/fire_alarm-512.png"      	           	       
       		}
        }
        
        section() 
        {
        	paragraph image: "http://icons.iconarchive.com/icons/webalys/kameleon.pics/512/Burglar-icon.png",
            title: "Control de Intrusion",
            required: false,
            ""                    
            input	"intruderSW",  "capability.switch", title: "Interruptor de Alerta", description: "Seleccionar..", required: false, multiple: false, submitOnChange: true, image: "http://contactcarelifeline.co.uk/wp-content/themes/contactcare/img/logos/icon_button_color.png"
        	if (intruderSW != null)
            {
            	input	"alarmLights", "capability.light",  title: "Luz de Alerta:", multiple: true, hideWhenEmpty: !(intruderSW != null), required: false, submitOnChange: true, description: "Seleccionar..", image: "https://cdn0.iconfinder.com/data/icons/flat-vector-1/100/14-Alert-512.png"
            }            
        }
        section()
        {
        	paragraph image: "http://es.seaicons.com/wp-content/uploads/2016/03/Misc-Settings-icon.png",
            title: "Otros Ajustes",
            required: false,
            ""   
            input "midday"  	 , "time", title: "MedioDia"  , description: "Seleccionar..", required: false, submitOnChange: true, image: "https://cdn4.iconfinder.com/data/icons/time-line/512/daytime-512.png"
            input "midnight"	 , "time", title: "MediaNoche", description: "Seleccionar..", required: false, submitOnChange: true, image: "https://cdn4.iconfinder.com/data/icons/time-line/512/night_time-512.png"			
        	input "alarmLateTime", "time", title: "Alarma Nocturna", description: "Iniciar desde esta hora..", required: controlModes || controlAlarm, submitOnChange: true, image: "https://cdn2.iconfinder.com/data/icons/business-and-finance-related-hand-gestures/256/time_timepiece_late_lateness_rush_watch_clock-512.png"
        	input "entryLight"	 ,  "capability.switch", title: "Luz de Entrada", description: "Encender esta luz al llegar..", required: false, multiple: true, submitOnChange: true, image: "https://www.shareicon.net/data/512x512/2016/09/01/822230_tool_512x512.png"

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
	log.info "Tap something else dumbass!"
    
}

def lockHandler(evt)
{
	log.info "HomeTap Started: $app.label (LockHandler)"
    
    def counter 		= number_of_scenarios as Integer
    def thisGuy     	= evt.device as String
    def thisHappen		= evt.descriptionText  as String
    def wasManual		= false
   	def lockData 		= ""
    def thisCode 		= 0
    
    if( evt.data != null)
    {
        lockData = new JsonSlurper().parseText(evt.data)
        thisCode = lockData.usedCode as Integer
    }
    
    
    if (thisHappen.contains("manually")) 	wasManual = true //Unlocked manually
    else 									wasManual = false //Unlocked with code or app
    
    
    for (int x = 1; x <= counter; x++) 
    { 
    	def locks 			= settings."lock_${x}"
        
        if (locks != null)
        {
			 if ( (locks as String) == thisGuy)
             {
             	log.info "Event = $thisGuy is $thisHappen"
                Action_Lock(x, wasManual, thisCode)
             }
        
        }
        
        
   }
    
    
    log.info "HomeTap Completed: $app.label (LockHandler)"
}

def arrivalHandler(evt)
{
	log.info "HomeTap Started: $app.label (ArrivalHandler)"
    
    def currentTime  	= now()
    def riseTime 	 	= getSunriseAndSunset().sunrise.time
	def setTime  		= getSunriseAndSunset().sunset.time 
    
    def timeStart 		= new Date(setTime).format("HH:mm", location.timeZone)
    def timeStop  		= new Date(riseTime).format("HH:mm", location.timeZone)
    
    def counter 		= number_of_users as Integer
    def thisGuy     	= evt.device as String
    def result			= 0
    def name			= ""
    def arrivals		= false
    
    if (timeOfDayIsBetween(timeStart, timeStop, new Date(), location.timeZone))
    {
    	//Arrival after sunset
        if (entryLight != null)
        {
            for (light in entryLight)
            {
                if (light.hasCommand("setLevel"))	light.setLevel(80)
                light.on()
            }
        }
    }
    
    for (int x = 1; x <= counter; x++) 
    { 
    	def presence	= settings."presence_${x}"
        
        if (presence != null)
        {
			 if ( (presence as String) == thisGuy)
             {
                name 		= settings."name_${x}"
                arrivals 	= settings."arrival_${x}"
                if (name == null) name = "N/A"
                if (arrivals)	  sendPush("$name ha llegado!")
             }
             
             if (settings."presence_${x}".currentPresence == "present") 
             {
             	result++
             }
        }
    }
    
    if (result == 1 && controlAccess)
    {
    	//first to arrive
        log.warn "First person to arrive!"
    }
    
    log.info "HomeTap Completed: $app.label (ArrivalHandler)"
}

def departureHandler(evt)
{
	log.info "HomeTap Started: $app.label (DepartureHandler)"
    
    def counter 		= number_of_users as Integer
    def thisGuy     	= evt.device as String
    def result			= true
    def name			= ""
    def departures		= false
    
    for (int x = 1; x <= counter; x++) 
    { 
    	def presence	= settings."presence_${x}"
        
        if (presence != null)
        {
			 if ( (presence as String) == thisGuy)
             {
                name 		= settings."name_${x}"
                departures 	= settings."departures_${x}"
                if (name == null) name = "N/A"
                if (departures)	  sendPush("Adiós $name!")
             }
             
             if (settings."presence_${x}".currentPresence == "present") 
             {
             	result = false
             }
        }
    }
    
    if (result && controlAccess)
    {
    	//last to leave
        log.warn "Nobody´s home, activate security!"
        ControlSecurity(result)
    }
    
    
    log.info "HomeTap Completed: $app.label (DepartureHandler)"
}

def alarmHandler(evt)
{
	log.info "HomeTap Started: $app.label (AlarmHandler)"
    
    def currentState = location.currentState("alarmSystemStatus")?.value
    if (currentState == "away" || currentState == "stay")
    {
    	if (alarmLights != null)
        {
            if (alarmLights.hasCommand("setLevel")) alarmLights.setLevel(100)
            if (alarmLights.hasCommand("setHue"))   alarmLights.setHue(100)
            
            for (int j = 0; j < 10; j++)
            {
                
                alarmLights.on()
                pause(600)
                alarmLights.off()
                pause(600)
            }
        }
    }
    
    log.info "HomeTap Completed: $app.label (AlarmHandler)"
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  HANDLERS FOR EACH DEVICE
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def Action_Lock(Number index, Boolean wasManual, Number Code )	
{
	def thisLock 		= settings."lock_${index}"
	def noti_unlock		= settings."notify_unlock_${index}"
    def noti_who		= settings."notify_who_${index}"
    def autoLocks		= settings."autolock_${index}"
    def horarios		= settings."schedules_${index}"
          
    log.info "Code used: $Code"
    log.info "Manual: $wasManual"
    log.info "Avisar Unlock: $noti_unlock"
	log.info "Avisar Quien: $noti_who"
	log.info "Autolock: $autoLocks secs"
    
    //Control of modes and alarm if required
    ControlSecurity(false)
	
    if (autoLocks != null && autoLocks > 0)
    {
    	runIn(autoLocks, "Delay_Autolock", [data: [index_scenario: index]]) 
    }
    if (noti_unlock) 
    {
        if (wasManual) sendPush ("$thisLock fue abierta")
        else if (noti_who)	
        {
            def thisUser = findUser(Code)
        	sendPush("Hola $thisUser!")       
        }
    }    
    
    //Handler para horarios --------------------------------------------
    log.info "Horarios = $horarios"
    
    def counter = horarios as Integer
    for (int x = 1; x <= counter; x++) 
    { 
    	def fromtime	 		= settings."fromtime_${index}_${x}"       
    	def totime 				= settings."totime_${index}_${x}"
       		
        def huecolor		    = settings."huecolor_${index}_${x}"       
        def huelux				= settings."huelux_${index}_${x}"
       
        
        def turnon				= settings."turnon_${index}_${x}"
        def turnoff				= settings."turnoff_${index}_${x}"
        def turnoffdelay		= settings."turnoffdelay_${index}_${x}"
        
        def sound				= settings."sound_${index}_${x}"

        
        
        if (fromtime != null && totime != null)
        {
        	
            if( CheckSchedule(index, x) )
            {
            	//Current Time is valid for actions
                if (huecolor != null) 							ActionColor(index, x)
                if (huelux 	 != null) 							ActionLux(  index, x)
                if (sound	 != null) 							ActionSound(index, x)
                if (turnon	 != null) 							settings."turnon_${index}_${x}"*.on()
                if (turnoff  != null && turnoffdelay == null) 	settings."turnoff_${index}_${x}"*.off()
                if (turnoff  != null && turnoffdelay != null) 	runIn(turnoffdelay*60, "Delay_TurnOff", [data: [index_scenario: index, index_horario:x]])              
            }
        
        }
        
           
    }

}

def CheckSchedule(Number index_scenario, Number index_horario)
{
	def result = false
    def riseTime = getSunriseAndSunset().sunrise.time
	def setTime  = getSunriseAndSunset().sunset.time 
    def timeStart = ""
    def timeStop  = ""

    def fromtime	 		= settings."fromtime_${index_scenario}_${index_horario}"
	def fromothertime		= settings."fromothertime_${index_scenario}_${index_horario}"
    def fromoffsetvalue		= settings."fromoffsetvalue_${index_scenario}_${index_horario}"
    def fromoffsetdir		= settings."fromoffsetdir_${index_scenario}_${index_horario}"
       
    def totime 				= settings."totime_${index_scenario}_${index_horario}"
	def toothertime			= settings."toothertime_${index_scenario}_${index_horario}"
    def tooffsetvalue		= settings."tooffsetvalue_${index_scenario}_${index_horario}"
    def tooffsetdir			= settings."tooffsetdir_${index_scenario}_${index_horario}"
    
    if (fromtime != null && totime != null)
    {
    		//Time to Start
     		switch (fromtime)
            {
            	case "Amanecer":
                  				if (fromoffsetvalue != null)
            					{
            							if (fromoffsetdir == "Adelantar")
                                        {
                                            timeStart = new Date(riseTime - (fromoffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else if (fromoffsetdir == "Retrasar")
                                        {
                                            timeStart = new Date(riseTime + (fromoffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else
                                            timeStart = new Date(riseTime).format("HH:mm", location.timeZone)
            					}
                                else
                                    timeStart = new Date(riseTime).format("HH:mm", location.timeZone)
                				break
                                
                                
                case "Mediodia":
                				if (fromoffsetvalue != null)
            					{
            							if (fromoffsetdir == "Adelantar")
                                        {
                                            timeStart = midday - (fromoffsetvalue*60*1000 )
                                        }
                                        else if (fromoffsetdir == "Retrasar")
                                        {
                                            timeStart = midday + (fromoffsetvalue*60*1000 )
                                        }
                                        else
                                            timeStart = midday
            					}
                                else
                                    timeStart = midday
                				break
                                
                                
                case "Atardecer":
                				if (fromoffsetvalue != null)
            					{
            							if (fromoffsetdir == "Adelantar")
                                        {
                                            timeStart = new Date(setTime - (fromoffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else if (fromoffsetdir == "Retrasar")
                                        {
                                            timeStart = new Date(setTime + (fromoffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else
                                            timeStart = new Date(setTime).format("HH:mm", location.timeZone)
            					}
                                else
                                    timeStart = new Date(setTime).format("HH:mm", location.timeZone)
                				break
                                
                                
                case "Medianoche":
                				if (fromoffsetvalue != null)
            					{
            							if (fromoffsetdir == "Adelantar")
                                        {
                                            timeStart = midnight - (fromoffsetvalue*60*1000 )
                                        }
                                        else if (fromoffsetdir == "Retrasar")
                                        {
                                            timeStart = midnight + (fromoffsetvalue*60*1000 )
                                        }
                                        else
                                            timeStart = midnight
            					}
                                else
                                    timeStart = midnight
                				break
                                
                                
                case "Especificar":
                				if (fromothertime != null)	timeStart = fromothertime
                				break
                                
                                
                default:
                				break
            
            }       
        
        	//Time to Finish
            switch (totime)
            {
            	case "Amanecer":
                  				if (tooffsetvalue != null)
            					{
            							if (tooffsetdir == "Adelantar")
                                        {
                                            timeStop = new Date(riseTime - (tooffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else if (tooffsetdir == "Retrasar")
                                        {
                                            timeStop = new Date(riseTime + (tooffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else
                                            timeStop = new Date(riseTime).format("HH:mm", location.timeZone)
            					}
                                else
                                    timeStop = new Date(riseTime).format("HH:mm", location.timeZone)
                				break
                                
                                
                case "Mediodia":
                				if (tooffsetvalue != null)
            					{
            							if (tooffsetdir == "Adelantar")
                                        {
                                            timeStop = midday - (tooffsetvalue*60*1000 )
                                        }
                                        else if (tooffsetdir == "Retrasar")
                                        {
                                            timeStop = midday + (tooffsetvalue*60*1000 )
                                        }
                                        else
                                            timeStop = midday
            					}
                                else
                                    timeStop = midday
                				break
                                
                                
                case "Atardecer":
                				if (tooffsetvalue != null)
            					{
            							if (tooffsetdir == "Adelantar")
                                        {
                                            timeStop = new Date(setTime - (tooffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else if (tooffsetdir == "Retrasar")
                                        {
                                            timeStop = new Date(setTime + (tooffsetvalue*60*1000 )).format("HH:mm", location.timeZone)
                                        }
                                        else
                                            timeStop = new Date(setTime).format("HH:mm", location.timeZone)
            					}
                                else
                                    timeStop = new Date(setTime).format("HH:mm", location.timeZone)
                				break
                                
                                
                case "Medianoche":
                				if (tooffsetvalue != null)
            					{
            							if (tooffsetdir == "Adelantar")
                                        {
                                            timeStop = midnight - (tooffsetvalue*60*1000 )
                                        }
                                        else if (tooffsetdir == "Retrasar")
                                        {
                                            timeStop = midnight + (tooffsetvalue*60*1000 )
                                        }
                                        else
                                            timeStop = midnight
            					}
                                else
                                    timeStop = midnight
                				break
                                
                                
                case "Especificar":
                				if (toothertime != null)	timeStop = toothertime
                				break
                                
                                
                default:
                				break
            
            }          
     
     		result =  timeOfDayIsBetween(timeStart, timeStop, new Date(), location.timeZone)
   }
    
    def newStart = ""
    def newEnd   = ""
    
    if (timeStart.contains("-"))	newStart = Date.parse( "yyyy-MM-dd'T'HH:mm:ss.SSSX", timeStart ).format( 'HH:mm', location.timeZone )
    else							newStart = timeStart
    
    if (timeStop.contains("-"))		newEnd = Date.parse( "yyyy-MM-dd'T'HH:mm:ss.SSSX", timeStop ).format( 'HH:mm', location.timeZone )
    else							newEnd = timeStop
    
    def value = newStart + "-" + newEnd
    
	if (result) log.warn "schedule$index_horario =  $result ($value)" 
    else  log.error "schedule$index_horario = $result ($value)" 
	result
}

def ActionColor(Number index_scenario, Number index_horario)
{
     def hueColor           = 0
     def hueBrightness      = 0
     def hueSaturation      = 0
     
     def new_level_of_light = settings."huecolorlevel_${index_scenario}_${index_horario}"
     def new_color_of_light = settings."huetone_${index_scenario}_${index_horario}"
     def new_satur_of_light = settings."huesat_${index_scenario}_${index_horario}"
     def new_quest_of_asian = false
         
     if (new_level_of_light != null) 								hueBrightness = getBrightnessValue(new_level_of_light)
     else															hueBrightness = 80
     
     if (new_color_of_light != null && new_quest_of_asian != null) 	hueColor      = getColorValue(new_color_of_light, new_quest_of_asian)
     else															hueColor	  = 23
     
     if (new_color_of_light != null && new_satur_of_light != null) 	hueSaturation = getSaturationValue(new_color_of_light, getBrightnessValue(new_satur_of_light))
     else															hueSaturation = 90
     
     
	 settings."huecolor_${index_scenario}_${index_horario}"*.setLevel(hueBrightness)
     settings."huecolor_${index_scenario}_${index_horario}"*.setSaturation(hueSaturation)
     settings."huecolor_${index_scenario}_${index_horario}"*.setHue(hueColor)
     settings."huecolor_${index_scenario}_${index_horario}"*.on()

}

def ActionLux(Number index_scenario, Number index_horario)
{
     def hueBrightness      = 0
    
     def new_level_of_light = settings."hueluxlevel_${index_scenario}_${index_horario}"

         
     if (new_level_of_light != null) 	hueBrightness = getBrightnessValue(new_level_of_light)
     else								hueBrightness = 80
     
    
	 settings."huelux_${index_scenario}_${index_horario}"*.setLevel(hueBrightness)
     settings."huelux_${index_scenario}_${index_horario}"*.on()

}

def ActionSound(Number index_scenario, Number index_horario)
{
   	def soundaction			= settings."soundaction_${index_scenario}_${index_horario}"
   	def soundvolume			= settings."soundvolume_${index_scenario}_${index_horario}" 
    def options = [:]
	
    if (soundvolume != null) 
    {
		settings."sound_${index}_${x}"*.setLevel(soundvolume as Integer)
		options.delay = 1000
	}
    
    if (soundaction != null)
    {
    	switch(soundaction)
        {
                case "Turn On & Play":
                                    options ? settings."sound_${index}_${x}"*.on(options) : settings."sound_${index}_${x}"*.on()
                                    break
                case "Turn Off":
                                    options ? settings."sound_${index}_${x}"*.off(options) : settings."sound_${index}_${x}"*.off()
                                    break
                case "Toggle Play/Pause":
                                    def currentStatus = settings."sound_${index}_${x}"*.currentValue("playpause")
                                    if (currentStatus == "play") {
                                        options ? settings."sound_${index}_${x}"*.pause(options) : settings."sound_${index}_${x}"*.pause()
                                    }
                                    else if (currentStatus == "pause") {
                                        options ? settings."sound_${index}_${x}"*.play(options) : settings."sound_${index}_${x}"*.play()
                                    }
                                    break
                case "Skip to Next Track":
                                    options ? settings."sound_${index}_${x}"*.nextTrack(options) : settings."sound_${index}_${x}"*.nextTrack()
                                    break
                case "Skip to Beginning/Previous Track":
                                    options ? settings."sound_${index}_${x}"*.previousTrack(options) : settings."sound_${index}_${x}"*.previousTrack()
                                    break
                case "Play Preset 1":
                                    options ? settings."sound_${index}_${x}"*.preset1(options) : settings."sound_${index}_${x}"*.preset1()
                                    break
                case "Play Preset 2":
                                    options ? settings."sound_${index}_${x}"*.preset2(options) : settings."sound_${index}_${x}"*.preset2()
                                    break 
                case "Play Preset 3":
                                    options ? settings."sound_${index}_${x}"*.preset3(options) : settings."sound_${index}_${x}"*.preset3()
                                    break
                case "Play Preset 4":
                                    options ? settings."sound_${index}_${x}"*.preset4(options) : settings."sound_${index}_${x}"*.preset4()
                                    break
                case "Play Preset 5":
                                    options ? settings."sound_${index}_${x}"*.preset5(options) : settings."sound_${index}_${x}"*.preset5()
                                    break
                case "Play Preset 6":
                                    options ? settings."sound_${index}_${x}"*.preset6(options) : settings."sound_${index}_${x}"*.preset6()
                                    break
                case "Select Song..":
                                    options ? settings."sound_${index}_${x}"*.playTrack(options) : settings."sound_${index}_${x}"*.playTrack(state.selectedSong)
                                    break
                case "Change to Aux..":
                                    options ? settings."sound_${index}_${x}"*.aux(options) : settings."sound_${index}_${x}"*.aux()
                                    //runIn(2, ChangeToAux )
                                    break
                case "Join":
                                    options ? settings."sound_${index}_${x}"*.everywhereJoin(options) : settings."sound_${index}_${x}"*.everywhereJoin()
                                    break
                case "Leave":
                                    options ? settings."sound_${index}_${x}"*.everywhereLeave(options) : settings."sound_${index}_${x}"*.everywhereLeave()
                                    break
                default:
                    				break        
       
        }       
    
    }

}

def Delay_TurnOff (data)
{
    settings."turnoff_${data.index_scenario}_${data.index_horario}"*.off()
}

def Delay_Autolock (data)
{
	def lock = settings."lock_${data.index_scenario}"
    settings."lock_${data.index_scenario}".lock()
    log.warn "$lock se ha cerrado!"
}

def ControlSecurity (Boolean nobodyAtHome)
{
	def currentTime  = now()
    def riseTime 	 = getSunriseAndSunset().sunrise.time
    def late   		 = timeToday(alarmLateTime, location?.timeZone).time
    
    if (controlAccess)
    {
    	if (controlModes)
        {
        	if (nobodyAtHome)
            {
            	if (location.mode != "away")	
                {
                	setLocationMode("Away")
                    log.warn "Mode change to Away"
                }
            }
            else
            {
            	if (currentTime > late && currentTime < riseTime) 
                { 
                	setLocationMode("Night")
                    log.warn "Mode change to Armed"
                }
                else	
                {	
                	setLocationMode("Home")
                    log.warn "Mode change to Home"
                }                
            }        
        }
    
    	if (controlAlarm)
        {
        	if (nobodyAtHome)
            {
            	sendLocationEvent(name: "alarmSystemStatus", value: "away")
                log.warn "Alarm is Armed(Away)"
            }
            else
            {
                if (currentTime > late && currentTime < riseTime) 
                { 
                	sendLocationEvent(name: "alarmSystemStatus", value: "stay")
                    log.warn "Alarm is Armed(Home)"
                }
                else
                {
                	sendLocationEvent(name: "alarmSystemStatus", value: "off")
                    log.warn "Alarm is Disarmed"
                }
            }
        
        }
    
    }

}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS FOR COLOR SETTINGS
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
int getColorValue (String color_name, Boolean is_chinese)
{
	def color = 0

    
    switch (color_name)
    {
		case "Random":
        	color = new Random().nextInt(90) + 10
        	break
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
        	if (is_chinese) color = 16
			else color = 22
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

int getSaturationValue (String color_name, int selected_value )
{
    def saturation = 100
    
    switch (color_name)
    {
		case "Random":
        	saturation = new Random().nextInt(70) + 30
            break
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
        	saturation = selected_value
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


//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	HELPERS FOR USER
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def findUser(Number code)
{
	def user 	 = ""
    def thisCode = "Code "
    def userCode = ""
    
    if (code == 0)	thisCode = "App"
    else            thisCode += code
              
    def counter	 =	number_of_users as Integer
    
    for (int x = 1; x <= counter; x++) 
    {
		userCode = settings."code_${x}"
        if (userCode == thisCode) user = settings."name_${x}"
	}
    
    if (user == "")	user = "N/A"
    
    user
}