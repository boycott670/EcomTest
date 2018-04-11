package com.sqli.challenge.validators;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.sqli.challenge.EcommerceFacade;
import com.sqli.challenge.entities.Machine;
import com.sqli.challenge.entities.Product;

public final class VoucherRequiresMachinePurchaseValidator implements DomainRulesValidator
{
  @Override
  public List<String> validate(EcommerceFacade facade)
  {
    final Supplier<? extends Boolean> containsOnlyMachines = () -> facade.getCartContent()
        .stream()
        .map(Product::getClass)
        .allMatch(Machine.class::isAssignableFrom);
    
    if (facade.getVoucher() != null && facade.getVoucher().getCode() != null && !containsOnlyMachines.get())
    {
      return Arrays.asList("Voucher requires machine purchase");
    }
    
    return Arrays.asList();
  }
}
