package com.fandango.files;

//import com.github.tomakehurst.wiremock.common.Notifier;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.StringReader;

public class POSNotifier {
}

/*

public class POSNotifier implements Notifier {

    final static Logger LOG = LoggerFactory.getLogger("com.fandango.commerce.pos.amc.client");

    public POSNotifier() {
    }

    @Override
    public void info(String message) {
        if (message.contains("bodyFileName")){
            LOG.info(message);
            //LOG.info(parseCode(message, "bodyFileName"));
        }
    }

    @Override
    public void error(String message) {
        //LOG.error(message);
    }

    @Override
    public void error(String message, Throwable t) {
        //LOG.error(message, t);
    }

    private String parseCode(String text, String field){
        try{
            JsonReader jsonReader = new JsonReader(new StringReader(text));
            jsonReader.setLenient(true);

            JsonObject jsonObject = new JsonParser().parse(jsonReader).getAsJsonObject();
            return jsonObject.get(field).getAsString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
*/