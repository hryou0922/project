package com.swjtu.trans.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.swjtu.lang.LANG;
import com.swjtu.trans.AbstractTranslator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class GoogleTranslator extends AbstractTranslator {
    private static final String url = "https://translate.google.cn/translate_a/single";

    public GoogleTranslator(){
        super(url);
    }


    @Override
    public void setLangSupport() {
        langMap.put(LANG.ZH, "zh-CN");
        langMap.put(LANG.EN, "en");
        langMap.put(LANG.JP, "ja");
        langMap.put(LANG.KOR, "ko");
        langMap.put(LANG.FRA, "fr");
        langMap.put(LANG.RU, "ru");
        langMap.put(LANG.DE, "de");
    }

    @Override
    public void setFormData(LANG from, LANG to, String text) {
        formData.put("client", "t");
        formData.put("sl", langMap.get(from));
        formData.put("tl", langMap.get(to));
        formData.put("hl", "zh-CN");
        formData.put("dt", "at");
        formData.put("dt", "bd");
        formData.put("dt", "ex");
        formData.put("dt", "ld");
        formData.put("dt", "md");
        formData.put("dt", "qca");
        formData.put("dt", "rw");
        formData.put("dt", "rm");
        formData.put("dt", "ss");
        formData.put("dt", "t");
        formData.put("ie", "UTF-8");
        formData.put("oe", "UTF-8");
        formData.put("source", "btn");
        formData.put("ssel", "0");
        formData.put("tsel", "0");
        formData.put("kc", "0");
        formData.put("tk", token(text));
        formData.put("q", text);
    }

    @Override
    public String query() throws Exception {
        URIBuilder uri = new URIBuilder(url);
        for (String key : formData.keySet()) {
            String value = formData.get(key);
            uri.addParameter(key, value);
        }
        HttpUriRequest request = new HttpGet(uri.toString());
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        System.out.println(entity.getContentLength());
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println("-" + result);
//        EntityUtils.consume(entity);
//        response.getEntity().getContent().close();
//        response.close();

        return result;
    }

    @Override
    public String parses(String text) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // text: -[[["因为获取中的检查在排队之前被调用，所以新获取的线程可能会先于其他被阻塞和排队的线程。","Because checks in acquire are invoked before enqueuing, a newly acquiring thread may ahead of others that are blocked and queued.",null,null,3],["但是，如果需要，您可以通过内部调用一个或多个检查方法来定义tryAcquire和/或tryAcquireShared}以禁用barging，从而提供一个\u003cem\u003e公平的\u003c/ em\u003e FIFO采集顺序","However, you can, if desired, define {@code tryAcquire} and/or {@code tryAcquireShared} to disable barging by internally invoking one or more of the inspection methods, thereby providing a \u003cem\u003efair\u003c/em\u003e FIFO acquisition order",null,null,3],["。",".",null,null,0]],null,"en"]

        // 可能返回多个记录
        StringBuilder sb = new StringBuilder();
        JsonNode jsonNode = mapper.readTree(text).get(0);
        if(jsonNode instanceof ArrayNode){
            ArrayNode arrayNode = (ArrayNode)jsonNode;
            for(int i = 0; i < arrayNode.size(); i++){
                ArrayNode arrayNode1Inner = (ArrayNode)arrayNode.get(i);
                sb.append(arrayNode1Inner.get(0).toString());
            }
        }
        return sb.toString();


    }

    private String token(String text) {
        String tk = "";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            FileReader reader = new FileReader(getJsFile("Google.js"));
            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;
                tk = String.valueOf(invoke.invokeFunction("token", text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}
