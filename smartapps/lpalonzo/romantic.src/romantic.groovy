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
  input "luz",  "capability.colorControl", title: "Luz No. $i:", multiple: true, required: false, submitOnChange: true, description: "Seleccionar..", image: "https://image.ibb.co/bJywHS/rgb_led.png"               
  //input "rutine", "enum", title: "Cuando esta rutina se ejecute:", description: "Seleccionar..", required: false, options: actions, submitOnChange: true, image: "https://image.ibb.co/g5c8P7/performance_management_512.png"                

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
    
    //Events for App pressing
    subscribe(app, appTouch)
    
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  EVENT HANDLERS
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def appTouch(evt)
{   
	log.info "Tap something else dumbass!"
    for (device in luz)
    {
        def thisColor = device.currentValue("hue")
        def thisLevel = device.currentValue("level")
        def thisSatur = device.currentValue("saturation")
        
        log.warn "$device.name color:$thisColor, level:$thisLevel, Saturation: $thisSatur"
 
    }	

}

