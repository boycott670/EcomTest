package com.sqli.challenge.validators;

import java.util.List;

import com.sqli.challenge.EcommerceFacade;

public interface DomainRulesValidator
{
  List<String> validate (final EcommerceFacade facade);
}
