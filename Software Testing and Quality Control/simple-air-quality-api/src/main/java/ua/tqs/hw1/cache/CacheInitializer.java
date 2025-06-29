package ua.tqs.hw1.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ua.tqs.hw1.data.Cache;
import ua.tqs.hw1.utils.ConfigUtils;

@Component
public class CacheInitializer implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(CacheInitializer.class);
    private final Cache cache;

    @Autowired
    public CacheInitializer(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Setting up the Cache Cleanup Task...");
        long cleanupInterval = Long.parseLong(ConfigUtils.getPropertyFromConfig("cache_ttl"));
        CacheCleanupTask cleanupTask = new CacheCleanupTask(cache, cleanupInterval); // Run cleanup every cache_ttl milliseconds
        logger.info("Set up Cache Cleanup Task successfully...");
        logger.info("Initializing new thread...");
        Thread cleanupThread = new Thread(cleanupTask);
        cleanupThread.setDaemon(true); // Set as daemon thread to automatically terminate when the application exits
        logger.info("Starting cache clean up thread...");
        cleanupThread.start();
    }
}