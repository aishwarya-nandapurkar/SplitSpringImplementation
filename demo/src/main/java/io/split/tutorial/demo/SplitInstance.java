package io.split.tutorial.demo;

import java.io.IOException;
import java.net.URISyntaxException;

import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;

public class SplitInstance {
    private static SplitInstance single_instance = null;
 
	SplitClientConfig config = SplitClientConfig.builder()
            .setBlockUntilReadyTimeout(20000)
            .build();
    public  SplitFactory splitFactory;
 
 
    private SplitInstance()
    {
    	try {
			splitFactory = SplitFactoryBuilder.build("cf7kbi6b5slr3qr7lvr4mlh9kg1mkoql4f7f", config); // API key : Server-side
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
