package es.develex.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("es.develex.infrastructure")
@EnableRedisRepositories(basePackages = "es.develex.domain.repository")
public class RedisConfiguration {
    @Value("${redis.client.host}")
    public String host;

    @Value("${redis.client.port}")
    public int port;

    @Value("${redis.client.password}")
    public String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(this.host, this.port);
        return new JedisConnectionFactory(standaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }
}
