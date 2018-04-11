package com.sqli.challenge.entities;

public final class Capsule extends Product
{
  public Capsule(String name, int quantity, double price)
  {
    super(name, quantity, price);
  }

  private Capsule(String name, int quantity)
  {
    super(name, quantity);
  }

  public static Capsule forRemoval (final String name, final int quantity)
  {
    return new Capsule(name, quantity);
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
