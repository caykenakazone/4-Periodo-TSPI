package com.iftm.cliente;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        while (i < 100) {
            Thread requestThread = new Thread(() -> {
                criarRequisicao();
            });
            requestThread.start();
            i++;
        }
    }

    public static void criarRequisicao() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String urlOtimista = "http://localhost:8080/api/v1/contas/otimista";
            String urlPessimista = "http://localhost:8080/api/v1/contas/pessimista";

            HttpPost postOtimista = new HttpPost(urlOtimista);
            HttpPost postPessimista = new HttpPost(urlPessimista);

            String jsonRequest1 = "{" +
                    "\"numeroConta\":\"" + 2 +
                    "\",\"valor\":\"" + 100 + "\"}";

            String jsonRequest2 = "{" +
                    "\"numeroConta\":\"" + 2 +
                    "\",\"valor\":\"" + 100 + "\"}";

            StringEntity entity1 = new StringEntity(jsonRequest1);
            StringEntity entity2 = new StringEntity(jsonRequest2);
            postOtimista.setEntity(entity1);
            postOtimista.setHeader("Content-type", "application/json");

            postPessimista.setEntity(entity2);
            postPessimista.setHeader("Content-type", "application/json");

            CloseableHttpResponse respostaOtimista = httpClient.execute(postOtimista);
            CloseableHttpResponse respostaPessimista = httpClient.execute(postPessimista);
            String responseOtimista = EntityUtils.toString(respostaOtimista.getEntity());
            String responsePessimista = EntityUtils.toString(respostaPessimista.getEntity());
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}