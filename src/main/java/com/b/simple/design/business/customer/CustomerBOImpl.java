package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

public class CustomerBOImpl implements CustomerBO {
    private static final Currency DEFAULT_CURENCY = Currency.EURO;    
    
	@Override
	public Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException {
            
		if (products.isEmpty())
			return new AmountImpl(BigDecimal.ZERO, DEFAULT_CURENCY);

                if(!doAllProductsHaveTheSameCurreny(products)) throw new DifferentCurrenciesException();
              
                BigDecimal totalSumOfProducts = sumOfAllProducts(products);               
		
		Currency firstProductCurrency = products.get(0).getAmount().getCurrency();
		return new AmountImpl(totalSumOfProducts, firstProductCurrency);
	}

    private BigDecimal sumOfAllProducts(List<Product> products)
    {
        return products.stream().map((product) -> product.getAmount().getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean doAllProductsHaveTheSameCurreny(List<Product> products)
    {
        Currency firstProductCurrency = products.get(0).getAmount().getCurrency();
        return products.stream().allMatch((product) ->
        {
                return product.getAmount()
                    .getCurrency().equals(firstProductCurrency);       
        });
    }

}