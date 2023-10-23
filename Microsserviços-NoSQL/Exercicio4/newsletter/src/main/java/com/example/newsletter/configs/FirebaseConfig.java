package com.example.newsletter.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${firebase.credential-file-name}")
    private String credentialFileName;

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
    @Bean
    public FirebaseApp firebaseApp(GoogleCredentials credentials) {
        var options = FirebaseOptions
                .builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }
    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        var classPathResource = new ClassPathResource(credentialFileName).getInputStream();
        return GoogleCredentials.fromStream(classPathResource);
    }
}
