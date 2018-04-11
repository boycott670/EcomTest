package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.sqli.challenge.entities.Product;

public final class DefaultCartContentPresenter implements CartContentPresenter
{
  @Override
  public String presentCartContent(Collection<? extends Product> products)
  {
    final StringBuilder cartContent = new StringBuilder();
    
    final Map<? extends String, ? extends Collection<? extends Product>> byGroupingIdentifier = products.stream()
      .collect(Collectors.groupingBy(
          Product::groupingByIdentifier,
          TreeMap::new,
          Collectors.toList()));
    
    for (final Entry<? extends String, ? extends Collection<? extends Product>> productsByGroupingIdentifier : byGroupingIdentifier.entrySet())
    {
      cartContent.append(String.format("%s\n", productsByGroupingIdentifier.getKey()));
      
      productsByGroupingIdentifier.getValue()
        .stream()
        .sorted(Comparator.comparing(Product::getName))
        .forEach(product ->
        {
          cartContent.append(String.format("\tName: %s", product.getName()));
          cartContent.append(String.format("\tQuantity: %d", product.getQuantity()));
          cartContent.append(String.format("\tPrice: %.0f\n", product.getTotalPrice()));
        });
    }
    
    return cartContent.toString();
  }
}
