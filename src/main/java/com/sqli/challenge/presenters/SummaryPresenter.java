package com.sqli.challenge.presenters;

import java.util.Collection;

import com.sqli.challenge.entities.Product;

public interface SummaryPresenter
{
  String presentSummary (final Collection<? extends Product> products);
}
