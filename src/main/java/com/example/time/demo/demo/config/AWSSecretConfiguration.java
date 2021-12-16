package com.example.time.demo.demo.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;


@Component
public class AWSSecretConfiguration implements ApplicationListener<ApplicationPreparedEvent> {
    private static final String AWS_SM_REGION = "eu-central-1";
    private static final String AWS_SECRETE_NAME = "MyAppDemoTimeSecret2";
    private final static String SPRING_DATASOURCE_HOST = "spring.datasource.url";
    private final static String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    private final static String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
    private static final String KEY_VALUE_FROM_SECRET_STRING_HOST = "HOSTNAME";
    private static final String KEY_VALUE_FROM_SECRET_STRING_USERNAME = "USERNAME";
    private static final String KEY_VALUE_FROM_SECRET_STRING_PASSWORD = "PASSWORD";

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        // Get username and password from AWS Secret Manager using secret nam

        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put(SPRING_DATASOURCE_HOST, getString(getSecret(AWS_SECRETE_NAME).secret, KEY_VALUE_FROM_SECRET_STRING_HOST));
        props.put(SPRING_DATASOURCE_USERNAME, getString(getSecret(AWS_SECRETE_NAME).secret, KEY_VALUE_FROM_SECRET_STRING_USERNAME));
        props.put(SPRING_DATASOURCE_PASSWORD, getString(getSecret(AWS_SECRETE_NAME).secret, KEY_VALUE_FROM_SECRET_STRING_PASSWORD));
        environment.getPropertySources().addFirst(new PropertiesPropertySource("aws.secret.manager", props));
    }

    private DecodedSecret getSecret(String secretName) {
        var secretsClient = SecretsManagerClient.builder()
                .region(Region.of(AWS_SM_REGION))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();

        var getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        DecodedSecret secret = new DecodedSecret();
        try {
            var getSecretValueResponse = secretsClient.getSecretValue(getSecretValueRequest);

            if (getSecretValueResponse != null && getSecretValueResponse.secretString() != null)
                secret.secret = getSecretValueResponse.secretString();
            else
                secret.decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResponse.secretBinary().asByteBuffer()).array());
        } catch (SecretsManagerException e) {
        }
        return secret;
    }

    private String getString(@NotNull String json, String keyName) {
        try {
            var secrets = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
            });
            return secrets.get(keyName);
        } catch (IOException e) {
            return null;
        }
    }

    private static class DecodedSecret {
        public String secret;
        public String decodedBinarySecret;
    }
}
