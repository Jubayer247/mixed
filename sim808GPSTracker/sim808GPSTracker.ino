#include <SoftwareSerial.h>
#include <TinyGPS.h>
#include <LiquidCrystal.h>
SoftwareSerial ss(7,8);

#define GSM_PORT ss

#include "sim808.h"
const int AOUTpin=0;//the AOUT pin of the alcohol sensor goes into analog pin A0 of the arduino
const int DOUTpin=10;//the DOUT pin of the alcohol sensor goes into digital pin D8 of the arduino
const int buzzer=9;//buzzer
const int relay=6;//relay
const int power=13;//car start button
int limit;// any presence of alcohol
int value;// intensity of alcohol 
boolean carStarted=false;
boolean smsSent=false;
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);



void setup() {
    // put your setup code here, to run once:
    ss.begin(9600);
    Serial.begin(115200);
    Serial.println("Starting...");
    lcd.begin(16, 2);
    lcd.setCursor(0,0);
    lcd.print("Loading...");
    pinMode(DOUTpin, INPUT);//sets the pin as an input to the arduino
    pinMode(buzzer, OUTPUT);//sets the pin as an output of the arduino
    pinMode(relay, OUTPUT);//sets the pin as an output of the arduino
    pinMode(power,INPUT);
    sim808_setup();
}



void sendPositionReport(unsigned long now) {
    float flat, flon;
    unsigned long age;
    GSM_PORT.print("AT+CMGF=1\r"); 
    delay(400);
    GSM_PORT.print("AT+CMGS=\"");
    GSM_PORT.print("01796074402");//01827095630
    GSM_PORT.println("\"");
    GSM_PORT.println("A car has been turned off in this location, emergency assistence needed ");
    delay(300);
    GSM_PORT.print("http://maps.google.com/maps?q=loc:");
    GSM_PORT.print(lat);
    GSM_PORT.print(",");
    GSM_PORT.print(lon);
    GSM_PORT.println("\"");
    delay(200);
    GSM_PORT.println((char)26); // End AT command with a ^Z, ASCII code 26
    delay(2000);
    flushGSM(now);
    delay(500);
    smsSent=true;
}




void loop() {
    lcd.setCursor(0,1);
    lcd.print("Monitoring");
    delay(200);
    value= analogRead(AOUTpin);//reads the analaog value from the alcohol sensor's AOUT pin
    limit= digitalRead(DOUTpin);//reads the digital value from the alcohol sensor's DOUT pin
    Serial.print("Alcohol value: ");
    Serial.println(value);//prints the alcohol value
    Serial.print("Limit: ");
    Serial.print(limit);//prints the limit reached as either LOW or HIGH (above or underneath)
    delay(100);
    
    if((digitalRead(power)==HIGH&&!carStarted)||(carStarted && value>240)){
        lcd.clear();
        if(!carStarted){
            lcd.print("Trying to start");
        }
        delay(2000);
        unsigned long  starttime = millis();
        unsigned long endtime = starttime;
        if (value>240){
            carStarted=false;
            lcd.clear();
            lcd.print("Danger");
            lcd.setCursor(0,1);
            digitalWrite(buzzer,HIGH);
            digitalWrite(relay,LOW);
            starttime = millis();
            endtime = starttime;
            smsSent=false;
            delay(2000);
            digitalWrite(buzzer,LOW);
            while((endtime - starttime) <=(60000)) // do this loop for up to 1000mS
            {

////////////////////////////////////////////////////////////////start of sms block ///////////////////////////////////////////////////////////////////////////
             if(!smsSent){
                            unsigned long now = millis();
                            boolean gotGPS = false;
                            if ( actionState == AS_IDLE ) {
                                if ( fixStatus > 0 && now > lastActionTime + 10000 ) {
                      
                                      sendPositionReport(now);
                      
                                      lastActionTime = now;
                                      httpResult = 0;
                                      actionState = AS_WAITING_FOR_RESPONSE;
                                   }
                            }
                            else {
                            // waiting on response - abort if taking too long
                            if ( now > lastActionTime + 15000 ) {
                                actionState = AS_IDLE;
                                parseState = PS_DETECT_MSG_TYPE;
                                resetBuffer();
                            }
                          }
                          sim808_loop();
                      }
////////////////////////////////////////////////////////////////////end of sms block///////////////////////////////////////////////////////////////////
              
              //lcd.clear();
              lcd.setCursor(0,1);
              unsigned long t=(endtime -starttime)/1000;
              lcd.print(t/60);
              lcd.setCursor(3,1);
              lcd.print(":");
              lcd.setCursor(5,1);
              lcd.print((t%60));
              endtime = millis();
              carStarted=false;
            }
      }
      else{
          lcd.clear();
          lcd.setCursor(0,0);
          lcd.print("Normal");
          digitalWrite(buzzer,LOW);
          digitalWrite(relay,HIGH);
          carStarted=true;
           delay(1000);
      }
  }

  else {
    lcd.clear();
    lcd.setCursor(0,0);
    if(carStarted){
        lcd.print("Car started");
    }
    else{
      lcd.print("ENGINE OFF");
      }
 }
  
}


