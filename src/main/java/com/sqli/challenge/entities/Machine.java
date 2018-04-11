package com.sqli.challenge.entities;

public final class Machine extends Product
{
  public Machine(String name, int quantity, double price)
  {
    super(name, quantity, price);
  }
  
  private Machine(String name, int quantity)
  {
    super(name, quantity);
  }

  public static Machine forRemoval (final String name, final int quantity)
  {
    return new Machine(name, quantity);
  }

  @Override
  public String groupingByIdentifier()
  {
    return "Machines";
  }

  @Override
  public Product add(Product product)
  {
    return forAdd(this, product, Machine::new);
  }

  @Override
  public Product remove(Product product)
  {
    return forRemoval(this, product, Machine::new);
  }
}
