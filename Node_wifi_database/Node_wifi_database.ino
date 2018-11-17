#include <ESP8266WiFi.h>
#include <SPI.h>
#include <MFRC522.h>
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>
#include <ESP8266HTTPClient.h>

#define RST_PIN  D3
#define SS_PIN   D4

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance


/* Set these to your desired credentials. */
const char *ssid = "Firefly";  //ENTER YOUR WIFI SETTINGS
const char *password = "nothingwrong";

//Web/Server address to read/write from 
const char *host = "http://notify247.epizy.com";   //http://notify247.epizy.com website or IP address of server

//=======================================================================
//                    Power on setup
//=======================================================================

String data;
void setup()
{
  pinMode(D2,OUTPUT);
  delay(1000);
  Serial.begin(115200);
  WiFi.mode(WIFI_OFF);        //Prevents reconnection issue (taking too long to connect)
  delay(1000);
  WiFi.mode(WIFI_STA);        //This line hides the viewing of ESP as wifi hotspot
  
  WiFi.begin(ssid, password);     //Connect to your WiFi router
  Serial.println("");

  Serial.print("Connecting");
  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  //If connection successful show IP address in serial monitor
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());  //IP address assigned to your ESP                                         // Initialize serial communications with the PC
  SPI.begin();                                                  // Init SPI bus
  mfrc522.PCD_Init();                                              // Init MFRC522 card
  Serial.println(F("Read personal data on a MIFARE PICC:"));    //shows in serial that it is ready to read
}


void loop()
{
// Prepare key - all keys are set to FFFFFFFFFFFFh at chip delivery from the factory.
  MFRC522::MIFARE_Key key;
  for (byte i = 0; i < 6; i++) key.keyByte[i] = 0xFF;

  //some variables we need
  byte block;
  byte len;
  MFRC522::StatusCode status;

  //-------------------------------------------

  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }

  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }

  Serial.println(F("**Card Detected:**"));

  //-------------------------------------------

  //-------------------------------------------
String s="";
  //-------------------------------------------
  for(int i=0;i<4;i++){
      s=s+mfrc522.uid.uidByte[i];
    }
Serial.println(s);
  


  delay(1000); //change value if you want to read cards faster

  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();

 //////////////////////////////////////http///////////////////////////////
  Serial.printf("\n[Connecting to %s ... ", host);
  HTTPClient http;    //Declare object of class HTTPClient
  String postData;
 //Post Data
  postData = "request=" + s;
  
  http.begin("http://notify247.epizy.com/info.php");              //Specify request destination
  http.addHeader("Content-Type", "application/x-www-form-urlencoded");    //Specify content-type header
  http.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ");
  http.addHeader("Cookie", "__test=7f04e0c131a1ac373076b95b181ad0b3; expires=Fri, 01-Jan-38 5:55:55 GMT; path=/");
  int httpCode = http.POST(postData);   //Send the request
  String payload = http.getString();    //Get the response payload

  Serial.println(httpCode);   //Print HTTP return code
  Serial.println(payload);    //Print request response payload

  http.end();  //Close connection
  digitalWrite(D2,HIGH);
  delay(1000);
  digitalWrite(D2,LOW);
  delay(5000);
}


