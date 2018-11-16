/* 
  Grove Water Sensor sketch 
     Written by ScottC 5th August 2014
     Arduino IDE version 1.0.5
     Website: http://arduinobasics.blogspot.com
     Description: Use Grove Water Sensor to detect leaks, floods, spills, rain etc.
     Credits: This sketch was inspired by this website:
              http://www.seeedstudio.com/wiki/Grove_-_Water_Sensor     
 ------------------------------------------------------------- */
#define Grove_Water_Sensor 8     //Attach Water sensor to Arduino Digital Pin 8
#define Grove_Piezo_Buzzer 12    //Attach Piezo Buzzer to Arduino Digital Pin 12
#define LED 13                   //Attach an LED to Digital Pin 13 (or use onboard LED)
#include <LiquidCrystal.h>
unsigned long time=millis();
// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to
const int rs = 13, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
void setup(){
 pinMode(Grove_Water_Sensor, INPUT);     //The Water Sensor is an Input
 pinMode(Grove_Piezo_Buzzer, OUTPUT);    //The Piezo Buzzer is an Output
        pinMode(LED, OUTPUT);   
        lcd.begin(16, 2);
  // Print a message to the LCD.
  lcd.print("Status ok");
        //The LED is an Output
}

void loop(){
        /* The water sensor will switch LOW when water is detected.
           Get the Arduino to illuminate the LED and activate the buzzer
           when water is detected, and switch both off when no water is present */
 while(true){
 if(digitalRead(Grove_Water_Sensor) == LOW){
        delay(20*1000);
        if(digitalRead(Grove_Water_Sensor) == LOW){
        lcd.clear();
        lcd.print("Danger!");
        digitalWrite(Grove_Piezo_Buzzer, HIGH);
        delay(2*1000);
        digitalWrite(Grove_Piezo_Buzzer, LOW); 
                break;
        }
        else{
                lcd.clear();
                lcd.print("Status ok");
                lcd.setCursor(0,1);
                //delay(2);
                lcd.print("Normal");
                delay(100);              
                lcd.setCursor(0,0);
                digitalWrite(Grove_Piezo_Buzzer, LOW);
                digitalWrite(LED,LOW);
                
          
          }
                //digitalWrite(Grove_Piezo_Buzzer, LOW);
                //delay(40);
        }
        else{  
                lcd.clear();
                lcd.print("Status ok");
                lcd.setCursor(0,1);
                lcd.print("Normal"); 
                delay(100);             
                digitalWrite(Grove_Piezo_Buzzer, LOW);
                digitalWrite(LED,LOW);
        }
 }
}
