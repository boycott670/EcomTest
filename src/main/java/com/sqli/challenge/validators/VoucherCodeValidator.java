package com.sqli.challenge.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.sqli.challenge.EcommerceFacade;
import com.sqli.challenge.entities.Voucher;

public final class VoucherCodeValidator implements DomainRulesValidator
{
  @Override
  public List<String> validate(EcommerceFacade facade)
  {
    return Optional.ofNullable(facade.getVoucher())
      .map(Voucher::getCode)
      .filter(Pattern.compile("^\\d+$").asPredicate().negate())
      .map(__ -> "Invalid voucher code")
      .map(Arrays::asList)
      .orElseGet(Arrays::asList);
  }
}
