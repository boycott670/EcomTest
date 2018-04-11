package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.sqli.challenge.entities.Product;

public final class DefaultSummaryPresenter implements SummaryPresenter
{
  @Override
  public String presentSummary(Collection<? extends Product> products)
  {
    final Map<? extends String, ? extends Integer> sumOfQuantitiesByGroupingType = products.stream()
      .collect(Collectors.groupingBy(Product::groupingByIdentifier, Collectors.summingInt(Product::getQuantity)));
    
    final Map<? extends String, ? extends Double> sumOfTotalPricesByGroupingType = products.stream()
        .collect(Collectors.groupingBy(Product::groupingByIdentifier, Collectors.summingDouble(Product::getTotalPrice)));
    
    final double sumOfTotalPrices = sumOfTotalPricesByGroupingType.values()
        .stream()
        .mapToDouble(Double::doubleValue)
        .sum();
    
    final StringBuilder cartSummary = new StringBuilder();
    
    sumOfQuantitiesByGroupingType.keySet()
      .stream()
      .sorted()
      .forEach(groupingType ->
      {
        cartSummary.append(String.format("%s\n", groupingType));
        cartSummary.append(String.format("\tQuantity: %d\tPrice: %.0f\n",
            sumOfQuantitiesByGroupingType.get(groupingType),
            sumOfTotalPricesByGroupingType.get(groupingType)));
      });

    cartSummary.append(String.format("Total Price: %.0f\n", sumOfTotalPrices));
    
    return cartSummary.toString();
  }
}
