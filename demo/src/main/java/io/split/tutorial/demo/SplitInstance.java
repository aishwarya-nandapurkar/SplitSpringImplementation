package io.split.tutorial.demo;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;
import io.split.storages.enums.OperationMode;
import io.split.storages.enums.StorageMode;
import pluggable.CustomStorageWrapper;
import redis.RedisInstance;

public class SplitInstance {
    private static SplitInstance single_instance = null;
    final static Logger logger = LoggerFactory.getLogger(SplitInstance.class);
    
    /**
     * 
    
    // Building the Redis storage wrapper with some configurations of choice.
    CustomStorageWrapper redis = RedisInstance.builder()
                    .host("localhost")
                    .port(6379)
                    .timeout(1000)
                    .prefix("aish_test:")
                    .build();
    
    
    // Building the SDK config with the Redis wrapper referenced.
    SplitClientConfig splitConfig = SplitClientConfig.builder()
    						.setBlockUntilReadyTimeout(10000)
                            .customStorageWrapper(redis)
                            .operationMode(OperationMode.CONSUMER)
                            .storageMode(StorageMode.REDIS)                        
                            .build();
    
     */
    
    
      //Original implementation for just using SDK to connect to CDN
     
	SplitClientConfig splitConfig = SplitClientConfig.builder()
            .setBlockUntilReadyTimeout(10000).enableDebug().streamingEnabled(true).featuresRefreshRate(10)
            .build();
    public  SplitFactory splitFactory;
 
 
    private SplitInstance()
    {
    	try {
    		//cf7kbi6b5slr3qr7lvr4mlh9kg1mkoql4f7f
			splitFactory = SplitFactoryBuilder.build("cf7kbi6b5slr3qr7lvr4mlh9kg1mkoql4f7f", splitConfig); // API key : Server-side
			logger.debug("Logging Splitfactory via lslf4j logger");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
    }
 
    public static SplitInstance getInstance()
    {
        if (single_instance == null)
            single_instance = new SplitInstance();
 
        return single_instance;
    }
}
