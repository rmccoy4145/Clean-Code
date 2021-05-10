package com.b.simple.design.business.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;
import org.junit.jupiter.api.Assertions;

public class CustomerBOTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies()
			throws DifferentCurrenciesException {

            Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO), 
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO)};
            
            Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);
            
		List<Product> products = createProductsWithAmounts(amounts);
                
                Amount actual = customerBO.getCustomerProductsSum(products);

		assertCurrency(expected, actual);
	}
        
	@Test
	public void testCustomerProductSum_TwoProductsDifferentCurrencies()
			throws DifferentCurrenciesException {

            Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO), 
                new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE)};
            
            
		List<Product> products = createProductsWithAmounts(amounts);

		Assertions.assertThrows(DifferentCurrenciesException.class, () ->
                {
                    customerBO.getCustomerProductsSum(products);
                });
	}


	@Test
	public void testCustomerProductSum2() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<Product>();

		Amount actual = customerBO.getCustomerProductsSum(products);
                final Amount expected = new AmountImpl(new BigDecimal("0"), Currency.EURO);		
                
                assertCurrency(expected, actual);
	}

        
        
            private void assertCurrency(Amount expected, Amount actual)
    {
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getValue(), actual.getValue());
    }

    private List<Product> createProductsWithAmounts(Amount[] amounts)
    {
        int productId = 100;
        List<Product> products = new ArrayList<Product>();
        for (Amount amount : amounts)
        {
          products.add(new ProductImpl(productId, "Product " + productId, ProductType.BANK_GUARANTEE, amount)); 
          productId++;
        }
        return products;
    }
        
}