# WeatherTop

An MVC-based Java/[Play](https://www.playframework.com/) web app for managing WeatherTop Inc. weather stations. 
Users can register, log in, add/delete stations and readings, update account settings, as well as view readings and
account/station analytics. Styled using [Fomantic-UI](https://fomantic-ui.com/) and deployed on 
[Heroku](https://www.heroku.com/) using an [ElephantSQL]() database.

This web app was created as part of an assignment for SETU's Computer Science HDip course. I also created [a version of this app using Node.js and Express](https://github.com/eoinfennessy/weathertop-node) which includes extra functionality such as integration with the OpenWeatherMap API and a more extensive analytics section that uses Frappe Charts and a Leaflet map. You can [visit the deployed app here](https://the-return-of-weathertop.glitch.me/).

## Getting Started
### Prerequisites
- [Java 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Play 1.6.0](https://www.playframework.com/releases)

### Installing
To get a copy of the project running on your system, navigate to the project directory in a command prompt/shell and 
run the following:
```
play run
```
This will load the application and start a local server on port 9000.

## Deployment
### Prerequisites
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)
- [An ElephantSQL Account](https://customer.elephantsql.com/signup)
- [A Heroku Account](https://signup.heroku.com/)

### Setting up and ElephantSQL Database
1. After [creating a new ElephantSQL database instance](https://www.elephantsql.com/docs/index.html), copy the URL to the
database to the clipboard.
2. Open **app/conf/application.conf** and find the **db=** setting. Set this to the URL you copied in the previous step.

### Deploying on Heroku
To deploy this app on Heroku, log in to Heroku in a command prompt/shell, then navigate to your project's directory and
 run the following to create a new Play application on Heroku:
```
heroku create --stack heroku-18 --buildpack https://github.com/heroku/heroku-buildpack-play
```
You can then [follow the steps outlined here](https://devcenter.heroku.com/articles/git#create-a-heroku-remote) to 
deploy the project using Git and the Heroku CLI.

## Author
- **Eoin Fennessy** - [GitHub](https://github.com/eoinfennessy)
