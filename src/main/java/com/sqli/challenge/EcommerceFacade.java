package com.sqli.challenge;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.sqli.challenge.entities.Machine;
import com.sqli.challenge.entities.Product;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter = new DefaultCartContentPresenter();
  private final Map<? super String, Product> products = new HashMap<>();
  
  private void addProduct (final Product product)
  {
    products.merge(
        product.getName(),
        product,
        (productFromMap, productFromMerge) -> productFromMap.add(product));
  }
  
  private void removeProduct (final Product product)
  {
    products.computeIfPresent(
        product.getName(),
        (productNameToRemove, productToRemove) -> productToRemove.remove(product));
  }
  
  public void addMachine (final String name, final int quantity, final int price)
  {
    addProduct(new Machine(name, quantity, price));
  }
  
  public void removeMachine (final String name, final int quantity)
  {
    removeProduct(new Machine(name, quantity, 0));
  }
  
  public String cartContent ()
  {
    return cartContentPresenter.presentCartContent(
        products.values()
          .stream()
          .collect(Collectors.groupingBy(Product::groupingByIdentifier)),
        Comparator.comparing(Product::getName));
  }
}
