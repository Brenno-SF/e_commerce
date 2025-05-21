package com.bsf.e_commerce.services;

import com.bsf.e_commerce.config.MercadoPagoInitializer;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.resources.preference.Preference;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    public String createPreference(List<PreferenceItemRequest> itens) throws Exception {
        MercadoPagoInitializer.initialize();

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://seusite.com/sucesso")
                .failure("https://seusite.com/falha")
                .pending("https://seusite.com/pendente")
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(itens)
                .backUrls(backUrls)
                .autoReturn("approved")
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return preference.getInitPoint();
    }
}

