## Redis Commands

### To access redis-cli in a running container
```terminaloutput
PS C:\projects\redis-example> docker exec -it 49c3912e0416 redis-cli
127.0.0.1:6379>
``` 

1. Setup & Server Commands
    ```text
    redis-server → Start Redis server
    
    redis-cli → Open Redis command-line interface
    exit -> to quit cli
    
    PING → Check if server is running (PONG if alive)
    returns pong
    
    INFO → Get server info and stats
    returns server info
    
    CONFIG GET <parameter> → Get a config value
    * config get * --to get all config with key and value
    
    CONFIG SET <parameter> <value> → Set a config value temporarily
    
    FLUSHDB → Delete all keys in the current database
    
    FLUSHALL → Delete all keys in all databases
    
    SELECT <db-number> → Switch between databases (0–15 by default)
    ```

2. Key Management
    ```text
    KEYS * → List all keys (for small datasets)
   
    EXISTS key → Check if a key exists
   
    DEL key → Delete a key
   
    RENAME key newkey → Rename a key
   
    EXPIRE key seconds → Set a timeout for a key
   
    TTL key → Check remaining time to live
    ```
   
3. String Commands (Simple Key-Value)
    ```text
    SET key value → Store a value
    expiration can set using EX 60 (expire in 60 seconds)
    
    GET key → Retrieve a value
    
    MSET key1 value1 key2 value2 → Set multiple keys at once
    
    MGET key1 key2 → Get multiple keys at once
    
    INCR key → Increment a numeric value by 1
    
    DECR key → Decrement a numeric value by 1
    ```
   
4. List Commands (Ordered Lists)
    ```text
    LPUSH key value → Add element to the start of a list
    
    RPUSH key value → Add element to the end of a list
    
    LPOP key → Remove and return first element
    
    RPOP key → Remove and return last element
    
    LRANGE key start end → Get elements in a range
    here range is inclusive start=0 end=2 will return 3 elements 0,1,2 indexes
    ```
   
5. Set Commands (Unordered Unique Values)
    ```text
    SADD key value → Add element
    
    SREM key value → Remove element
    
    SMEMBERS key → Get all elements
    
    SISMEMBER key value → Check if element exists
    ```

6. Hash Commands (Key-Field-Value Pairs)
    ```text
    HSET key field value → Add or update a field
    
    HGET key field → Get value of a field
    
    HGETALL key → Get all fields and values
    
    HDEL key field → Delete a field
    ```
   
7. Sorted Set Commands (Elements with Scores)
    ```text
    ZADD key score value → Add element with a score
    
    ZRANGE key start end [WITHSCORES] → Get elements in a range
    
    ZRANK key value → Get rank of an element
    
    ZREM key value → Remove element
    ```
   
8. Pub/Sub (Messaging)
    ```text
    PUBLISH channel message → Send a message
    
    SUBSCRIBE channel → Subscribe to a channel
    
    UNSUBSCRIBE channel → Unsubscribe from a channel
    ```
   
9. Miscellaneous / Utility
    ```text
    DBSIZE → Get number of keys in current DB
    
    TIME → Get server time
    
    SAVE → Persist data to disk immediately
    
    BGSAVE → Save data asynchronously
    
    MONITOR → See all commands received by Redis in real-time
    ```