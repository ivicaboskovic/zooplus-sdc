package com.zooplus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zooplus.persistence.model.ConversionQuery;

public class ConversionQueryValidator implements Validator {
	

	@Override
	public boolean supports(Class<?> paramClass) {
		return ConversionQuery.class.equals(paramClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ConversionQuery conversionQuery = (ConversionQuery)object;
		if (conversionQuery.getCurrencyTo()!=null && conversionQuery.getCurrencyFrom()!=null){
			if (conversionQuery.getCurrencyTo().equals(conversionQuery.getCurrencyFrom())){
				errors.rejectValue("currencyTo", "convert.currency.same");
			}
		}
	}
}