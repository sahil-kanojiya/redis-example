# Simple Redis example 

---

## Steps to use
1) Start docker compose --it will start redis on default port 6379
2) Start redis insight --to analyze data
3) Run the main class

---

## Content

Redis is an open-source, in-memory data structure store that is used as a database, cache, and message broker. Let’s break that down in simpler terms:

1) In-memory: Redis stores data in RAM instead of on a hard disk. This makes it extremely fast for reading and writing data, often much faster than traditional databases.

2) Data structures: Redis doesn’t just store plain key-value pairs—it supports advanced data structures like:
    * Strings
    * Lists
    * Sets
    * Hashes
    * Sorted sets
    * Bitmaps, HyperLogLogs, and Streams

3) Use cases: Redis is very versatile. Common uses include:
    * Caching: Store frequently accessed data to make applications faster.
    * Real-time analytics: Track things like live user activity or counters.
    * Message broker / Pub-Sub: Redis can be used to send messages between services in real time.
    * Session storage: Store user sessions for web apps.

4) Persistence: Even though it’s in-memory, Redis can save data to disk periodically or append every write operation to a file, so data isn’t lost if the server restarts.

5) Speed: Because it keeps everything in memory, operations are extremely fast—often in microseconds.

Think of Redis as a superfast, flexible dictionary that lives in memory, capable of doing a lot more than just storing simple key-value pairs.

INFO

Bind and Protected Mode - By default, Redis listens on 127.0.0.1 (localhost) If your app runs on a different server, bind to your server IP

Security Settings - require password, disable commands

Persistence - RDB (snapshotting) and AOF (append only file)

Memory Management - Max memory and Eviction policy

Logging

Performance Tuning - Databases by default 16 from 0 to 15 default 0 is used for any operation and Threaded I/O

High Availability (Optional but recommended)
Replication: Master → Slave setup.
Sentinel: Automatic failover if the master fails.
Cluster: For horizontal scaling across multiple nodes.
