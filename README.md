# TravelBirdWeather

 SDK version
 
The Minimun sdk version i had to keep 18 so as to support for UiAutomator.Please Use the devices having API version 18(Jellybean) or above(6.0 mashamllow).

# Network calls 
I have used retrofit for the network calls

# Ui Testing 

I have used espresso and UiAutomator.(I could use espresso alone but things became worst for me as i have used PlaceAutoComPleteFragment)
for getting city names.(My bad).The limitation was i could not access the views of the Place fragment even multiWondow API with espresso 
couldn't help.

#Client Architecture used : MVP.

#Unit Test :

Junit and Mockito to mock the objects.
