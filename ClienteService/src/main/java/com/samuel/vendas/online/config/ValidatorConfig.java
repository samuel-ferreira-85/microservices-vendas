package com.samuel.vendas.online.config;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {
	
	@Bean
    public Validator validatorFactory () {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    return bean;
    }
}
