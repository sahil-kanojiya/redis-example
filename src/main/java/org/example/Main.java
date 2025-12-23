package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            log.info("Redis instance server info:\n{}", jedis.info("server"));

//            by default database 0 is used
//            jedis.select(0);

//            get and set configs via java client
//            jedis.configGet("*");
//            jedis.configSet()

//            setting one key and value
            jedis.set("user.name", "Sahil Kanojiya");

//            1) Key Management
//            get all keys
            Set<String> allKeys = jedis.keys("*");
            log.info("All keys: {}", allKeys);

//            check key exists or not
            boolean isExists = jedis.exists("user.name");
            log.info("Key with name \"user.name\" exists: {}", isExists);

//            delete a key
            jedis.set("user.age", String.valueOf(20));
            log.info("Deleted key with name \"user.age\" {}", jedis.del("user.age"));

//            expire a key and check TTL
            jedis.set("user.age", String.valueOf(20));
            jedis.expire("user.age", 60);
            Thread.sleep(500);
            log.info("Key with name \"user.age\" will expire in {} seconds", jedis.ttl("user.age"));

//            2) String Commands
//            set value and get value
            jedis.set("lang", "java");
            log.info("Value of key \"language\": {}", jedis.get("lang"));

//            set multiple values and get multiple values
            jedis.mset("user.city", "surat", "user.country", "india");
            List<String> values = jedis.mget("user.city", "user.country");
            log.info("Values of multiple keys (user.city, user.country): {}", values);

//            increment and decrement a value
            jedis.set("page.views", "10");
            jedis.incr("page.views");
            jedis.incr("page.views");
            jedis.decr("page.views");
            log.info("Current value of key \"page.views\": {}", jedis.get("page.views"));

//            3) List Commands (Ordered Lists)
//            left push and list range (use -1 to print start to length)
            jedis.lpush("chars", "c");
            jedis.lpush("chars", "b");
            jedis.lpush("chars", "a");
            log.info("List after lpush: {}", jedis.lrange("chars", 0, -1));

//            right push and list range
            jedis.rpush("chars", "d");
            jedis.rpush("chars", "e");
            log.info("List after rpush: {}", jedis.lrange("chars", 0, -1));

//            lpop and rpop
            log.info("List before lpop and rpop: {}", jedis.lrange("chars", 0, -1));
            log.info("lpop removed: {}", jedis.lpop("chars"));
            log.info("rpop removed: {}", jedis.rpop("chars"));
            log.info("List after lpop and rpop: {}", jedis.lrange("chars", 0, -1));

//            list range inclusive
            List<String> range = jedis.lrange("chars", 0, 2);
            log.info("lrange index start:0 end:2 : {}", range);

//            4) Set Commands (Unordered Unique Values)
//            set add
            log.info("Adding members into a set");
            jedis.sadd("user.names", "sahil");
            jedis.sadd("user.names", "ankit");
            jedis.sadd("user.names", "parth");

//            set remove
            log.info("Member \"ankit\" removed from the set {}", jedis.srem("user.names", "ankit"));

//            set all members
            Set<String> allMembers = jedis.smembers("user.names");
            log.info("All members of a set: {}", allMembers);

//            set is member
            log.info("\"sahil\" is member of set: {}", jedis.sismember("user.names", "sahil"));

//            5) Hash Commands (Key-Field-Value Pairs)
//            hash set
            log.info("Create hash with key user and name,age fields");
            jedis.hset("user", "name", "sahil");
            jedis.hset("user", "age", "20");

//            hash get
            log.info("Value of hash with key \"user\" with field \"name\" is {}", jedis.hget("user", "name"));

//            hash get all
            log.info("Hash with key \"user\" structure: {}", jedis.hgetAll("user"));

//            hash delete
            log.info("Delete a hash field with key \"user\" and field \"age\"  {}", jedis.hdel("user", "age"));

//            6) Sorted Set Commands (Elements with Scores)
//           sorted set add
            log.info("Adding elements to sorted set");
            jedis.zadd("leaderboard", 100, "sahil");
            jedis.zadd("leaderboard", 150, "ankit");
            jedis.zadd("leaderboard", 120, "parth");

//            sorted ser range
            log.info("zrange index start:0  end:-1 : {}", jedis.zrange("leaderboard", 0, -1));

//            sorted set rank
            log.info("Rank of element \"sahil\": {}", jedis.zrank("leaderboard", "sahil"));

//            sorted set remove
            log.info("Removed element \"parth\" {}", jedis.zrem("leaderboard", "parth"));

//            7) Pub Sub
//            subscriber (runs in a separate thread)
//                    jedis.subscribe(new JedisPubSub() {
//                        public void onMessage(String channel, String message) {
//                            System.out.printf("Message from channel: %s message: %s%n", channel, message);
//                        }
//                    }, "chatroom");

//            jedis.publish("chatroom", "Hello");

//            8) Miscellaneous / Utility
//            DBSIZE TIME

        } catch (RuntimeException ex) {
            log.error("Cannot connect to Redis", ex);
        } catch (InterruptedException ex) {
            log.error("Exception occurred:", ex);
        }
    }
}