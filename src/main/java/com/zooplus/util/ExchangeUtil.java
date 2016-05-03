package com.zooplus.util;

import com.zooplus.controller.dto.ExchangeResponse;

public class ExchangeUtil {

	
	public static final Double calculateRate(ExchangeResponse response, String codeFrom, String codeTo){
		if (codeFrom.equals(response.getBase())){
			return response.getRates().get(codeTo);
		}else if (codeTo.equals(response.getBase())){
			return 1 / response.getRates().get(codeFrom);
		}else{
			return response.getRates().get(codeFrom)/response.getRates().get(codeTo);
		}
	}
}
