This project implements a two-player Connect Four game using a client–server networking model written in Java, with a graphical user interface built using JavaFX.
Two computers communicate over a wireless network to establish a basic distributed system, allowing players to participate in a synchronized, turn-based game.
The system demonstrates fundamental concepts in computer networking, concurrency, object-oriented design, and event-driven GUI programming.

Server: Maintains the game state, validates moves, enforces turn order, broadcasts updates to clients.
Client: Sends player input, to server, receives games states, and updates board.

Connection is created through Java sockets, consists ServerSocket and Sockets for the clients.
Uses multithreadin to maintain responsiveness, and handle networking operations.

Has basic error handling for invalid moves, network interuptions, and client disconnections.

The Java-based server–client Connect Four game provides a functional example of a distributed interactive application. 
By combining socket programming, concurrency, and JavaFX-based visualization, the project highlights how fundamental networking principles can be applied to real-world software systems.
The modular design allows for future enhancements such as AI opponents, matchmaking, or support for additional players.
