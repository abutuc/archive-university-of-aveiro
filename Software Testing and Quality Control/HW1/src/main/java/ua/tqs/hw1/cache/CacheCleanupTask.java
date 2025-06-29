package ua.tqs.hw1.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.tqs.hw1.data.Cache;

public class CacheCleanupTask implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(CacheCleanupTask.class);

    private final Cache cache;
    private final long cleanupInterval;

    public CacheCleanupTask(Cache cache, long cleanupInterval) {
        this.cache = cache;
        this.cleanupInterval = cleanupInterval;
    }

    @Override
    public void run() {
        logger.info("Starting infinite cleanup loop...");
        while (true) {
            try {
                Thread.sleep(cleanupInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info("Starting cleaning expired cache entries...");
            cache.cleanExpiredEntries();
            logger.info("Cache cleaning process completed successfully!");


        }
    }
}

