package com.sqli.challenge.validators;

import java.util.List;
import java.util.stream.Collectors;

import com.sqli.challenge.EcommerceFacade;
import com.sqli.challenge.entities.Capsule;
import com.sqli.challenge.entities.Product;

public class CapsulePackagingRulesValidator implements DomainRulesValidator
{
  @Override
  public List<String> validate(EcommerceFacade facade)
  {
    return facade.getCartContent()
      .stream()
      .filter(product -> product instanceof Capsule)
      .map(Capsule.class::cast)
      .filter(capsule -> capsule.getQuantity() % 5 != 0)
      .map(Product::getName)
      .map(name -> String.format("%s: Invalid Quantity, must be a multiple of 5", name))
      .collect(Collectors.toList());
  }
}
