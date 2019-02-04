# gerencesy
A application to management activities, like a Trello.

![Example Gerencesy running](https://i.imgur.com/VbFUxwz.gif)

# Getting started 

# Database
PostgreSQL: create a database.

# Back-end - gerencesy folder:
In "project-defaults.yml" file, change the line "connection-url" with your database name:
- connection-url: jdbc:postgresql://localhost:5432/(your database name).

And set your username and password:
- user-name: (user-name)
- password: (password)

In gerencesy folder, execute the follow command:
- mvn install wildfly-swarm:run

# Front-end - app folder:
After it, in app folder, execute the follows commands:
- npm install
- gulp vendor
- gulp dev

The project will open in your browser.

