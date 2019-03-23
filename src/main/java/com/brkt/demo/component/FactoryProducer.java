package com.brkt.demo.component;


import com.brkt.demo.enumaretion.EnumFactory;
import org.springframework.stereotype.Component;

@Component
public class FactoryProducer {

    public static ServiceFactory getFactory(EnumFactory factory) {
        if(factory == null){
            return null;
        }

        if(factory == EnumFactory.CLIENT){
            return new ClientFactory();

        }else if(factory == EnumFactory.SERVER){
            return new ServerFactory();
        }

        return null;
    }
}
