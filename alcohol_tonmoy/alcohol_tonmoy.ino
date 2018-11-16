#include <LiquidCrystal.h>

const int AOUTpin=0;//the AOUT pin of the alcohol sensor goes into analog pin A0 of the arduino
const int DOUTpin=10;//the DOUT pin of the alcohol sensor goes into digital pin D8 of the arduino
const int buzzer=9;//buzzer
const int relay=6;//relay
int limit;
int value;

// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);



void setup(void) {  
  Serial.begin(115200);//sets the baud rate
  lcd.begin(16, 2);
  lcd.setCursor(0,0);
  pinMode(DOUTpin, INPUT);//sets the pin as an input to the arduino
  pinMode(buzzer, OUTPUT);//sets the pin as an output of the arduino
  pinMode(relay, OUTPUT);//sets the pin as an output of the arduino
  


}

  void loop(void) {





value= analogRead(AOUTpin);//reads the analaog value from the alcohol sensor's AOUT pin
limit= digitalRead(DOUTpin);//reads the digital value from the alcohol sensor's DOUT pin
Serial.print("Alcohol value: ");
Serial.println(value);//prints the alcohol value
Serial.print("Limit: ");
Serial.print(limit);//prints the limit reached as either LOW or HIGH (above or underneath)
delay(100);
if (true){
  lcd.clear();
  lcd.print("Danger");
  lcd.setCursor(0,1);
  digitalWrite(buzzer,HIGH);
  digitalWrite(relay,LOW);
  unsigned long  starttime = millis();
  unsigned long endtime = starttime;
  while((endtime - starttime) <=(600000)) // do this loop for up to 1000mS
  {
    //lcd.clear();
    lcd.setCursor(0,1);
    unsigned long t=(endtime -starttime)/1000;
    lcd.print(t/60);
    lcd.setCursor(3,1);
    lcd.print(":");
    lcd.setCursor(5,1);
    lcd.print((t%60));
    endtime = millis();
  }
  
}
else{
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("Normal");
  digitalWrite(buzzer,LOW);
  digitalWrite(relay,HIGH);
  //delay(1000);
}

    
}

