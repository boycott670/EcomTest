package com.sqli.challenge.entities;

public final class Machine extends Product
{
  public Machine(String name, int quantity, double price)
  {
    super(name, quantity, price);
  }

  @Override
  public String groupingByIdentifier()
  {
    return "Machines";
  }

  @Override
  public Product add(Product product)
  {
    return new Machine(name, quantity + product.getQuantity(), price);
  }

  @Override
  public Product remove(Product product)
  {
    final int newQuantity = quantity - product.getQuantity();
    
    return newQuantity <= 0 ? null : new Machine(name, newQuantity, price);
  }
}
