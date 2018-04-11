package com.sqli.challenge;

import java.util.HashMap;
import java.util.Map;

import com.sqli.challenge.entities.Capsule;
import com.sqli.challenge.entities.Machine;
import com.sqli.challenge.entities.Product;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;
import com.sqli.challenge.presenters.DefaultSummaryPresenter;
import com.sqli.challenge.presenters.SummaryPresenter;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter = new DefaultCartContentPresenter();
  private final SummaryPresenter summaryPresenter = new DefaultSummaryPresenter();
  
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
    removeProduct(Machine.forRemoval(name, quantity));
  }

  public void addCapsule (final String name, final int quantity, final int price)
  {
    addProduct(new Capsule(name, quantity, price));
  }
  
  public void removeCapsule (final String name, final int quantity)
  {
    removeProduct(Capsule.forRemoval(name, quantity));
  }
  
  public String cartContent ()
  {
    return cartContentPresenter.presentCartContent(products.values());
  }
  
  public String summary ()
  {
    return summaryPresenter.presentSummary(products.values());
  }
}
