tcp-http-test-tools
===================

Variety of Java test tools that:

    (a) hoards TCP connections
    (b) connect/disconnects via TCP ('tickle')
    (c) forced 404 http request with random text
    (d) trash talk via a TCP socket


connectionhoarder
=================

Attempts to hold on to x number of TCP connections and see server behaviour.

Usage: java ConnectionHoarder [hostname] [port] [numberOfThreads] [timeToLeaveConnectionOpen]


connectiontickler
=================

Tickles the connection by connecting and disconnecting to sockets after X milliseconds.

Usage: java ConnectionTickler [hostname] [port] [sleep]

filenotfoundrequester
=====================

Connects via HTTP and request for a random URI (random string from text file), to force the HTTP server to return 404.

Usage: java FileNotFoundRequester [hostname] [port] [number of threads] [text file]


trashtalker
===========

Connects to a socket and sends random text to a server.

Usage: java TrashTalker [hostname] [port] [number of threads] [text file]"
