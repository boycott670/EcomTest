package com.sqli.challenge.presenters;

import java.util.Collection;

import com.sqli.challenge.entities.Product;

public interface CartContentPresenter
{
  String presentCartContent (final Collection<? extends Product> products);
}
