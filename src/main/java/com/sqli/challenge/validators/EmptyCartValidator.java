package com.sqli.challenge.validators;

import java.util.Arrays;
import java.util.List;

import com.sqli.challenge.EcommerceFacade;

public final class EmptyCartValidator implements DomainRulesValidator
{
  @Override
  public List<String> validate(EcommerceFacade facade)
  {
    return Arrays.asList(facade.getCartContent().isEmpty() ? new String[] { "Empty Cart" } : new String[0]);
  }
}
