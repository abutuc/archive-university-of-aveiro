# Growmate

## Context

Over the last few years, a growing number of people have begun taking up the hobby of indoor plant care and growing, with the numbers of “plant parents” rising considerably in the western countries after the Covid-19 pandemic, and interest for gardening surging within the millennial’s generation. Alongside this trend, the use of apps for management of daily life activities and tasks has also seen a sharp increase, with people using apps to plan their to dos for the day.
However, the vast diversity of different plants, varieties, and species, as well as the different optimal conditions for the growth of each of them, can be quite overwhelming, not only for newbies of this hobby, but also for people with a large collection of plants already present in their homes.

Thus, we believe that an application that allows the management of indoor plants, serving as a catalogue for information about their conditions and parameters, as a planner for the tasks required to maintain them, and supporting the integration with other plant monitorization systems, like sensors, could be of interest to the market segments interested in the indoor plant gardening activity, from the most experienced users to the least.

As such, we developed the proof of concept for a mobile application with these characteristics, with this report providing the description for the entire software development process, from the early inception and requirements gathering phase, to the code development and usability testing. Though not all of the functionalities and use cases we envisioned for the perfect plant care app were implemented, we believe this project serves as a very solid stepping stone for a reliable and interesting product. Usability tests conducted on the final product at the end of the implementation cycle validated the design of the application, as well as the good flow between the different components and screen of the pages, and the usefulness of the implemented features. Future work is recommended regarding the testing of the system, enrichment of the supporting plant catalogue, and implementation of additional features, such as pest and disease detection.

## Functionalities

### User Registration and Login

<image src="images/Picture1.png" alt="Picture1" height="500"/>
<image src="images/Picture2.png" alt="Picture2" height="500"/>

### Plant Inventory 

<image src="images/Picture3.png" alt="Picture4" height="500"/>
<image src="images/Picture4.png" alt="Picture3" height="500"/>

### Divisions

<image src="images/Picture5.jpg" alt="Picture5" height="500"/>
<image src="images/Picture6.png" alt="Picture6" height="500"/>

### Sensors

<image src="images/Picture7.png" alt="Picture7" height="500"/>

### Tasks

<image src="images/Picture8.png" alt="Picture8" height="500"/>

### Plant Page

<image src="images/Picture9.png" alt="Picture9" height="500"/>
<image src="images/Picture10.png" alt="Picture10" height="500"/>

### Profile Page

<image src="images/Picture11.png" alt="Picture11" height="500"/>

### Discover New Plants

<image src="images/Picture12.png" alt="Picture12" height="500"/>
<image src="images/Picture13.png" alt="Picture13" height="500"/>

## Architecture 

![architecture.png](images%2Farchitecture.png)

## How to Run

To run the application locally, follow these steps:

- Setting up and running the API
    1. Clone the GrowMate’s repository.
    2. Install Docker and Docker Compose.
    3. On the main folder terminal run “docker compose up”.
- Setting up the emulator and running the app
    1. Install Node.js and npm
    2. On the “mobileapp” folder terminal run “npm install” and “npm start”
    3. Running the app:
         - On emulator:
             1. Install Android Studio and create a virtual device (https://developer.android.com/studio/install)
             2. For better compatibility, create a Pixel_3a_API_30_x86 device
             3. Enter the option “a” on the terminal running “npm start” to open the emulator and the app
         - On Expo Go app:
             1. Install Expo Go app on your phone
             2. Enter the option “c” on the terminal running “npm start” to print a QR Code
             3. Scan the QR Code with your phone
- You can enter existing profiles with the following credentials:
     - For a Premium account:
        - Email: john.doe@example.com
        - Password: password123
  - For a Non-Premium account:
      - Email: john.doe.poor@example.com
      - Password: password123


## Important Links

- [Promotional Website, including reports and presentations](https://sensing-my-home.github.io/Project-Website/)
- [Documentation Website](https://sensing-my-home.github.io/Documentation/)
- [API Documentation](https://documenter.getpostman.com/view/24060738/2s93m8xKtK)

## Development Team

- [André Butuc](https://github.com/abutuc) (andrebutuc@ua.pt)
- [Artur Correia](https://github.com/afarturc) (art.afo@ua.pt)
- [Bruna Simões](https://github.com/Brums21) (brunams21@ua.pt)
- [Daniel Carvalho](https://github.com/danielfcarvalho) (dl.carvalho@ua.pt)
