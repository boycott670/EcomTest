package com.sqli.challenge.utils;

import com.sqli.challenge.entities.Product;

@FunctionalInterface
public interface ProductConstructor
{
  Product call (final String name, final int quantity, final double price);
}
