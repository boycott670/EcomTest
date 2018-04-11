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
    return null;
  }

  @Override
  public Product remove(Product product)
  {
    return null;
  }
}
