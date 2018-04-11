package com.sqli.challenge.entities;

public final class Capsule extends Product
{
  public Capsule(String name, int quantity, double price)
  {
    super(name, quantity, price);
  }

  @Override
  public String groupingByIdentifier()
  {
    return "Capsules";
  }

  @Override
  public Product add(Product product)
  {
    return forAdd(this, product, Capsule::new);
  }

  @Override
  public Product remove(Product product)
  {
    return forRemoval(this, product, Capsule::new);
  }
}
