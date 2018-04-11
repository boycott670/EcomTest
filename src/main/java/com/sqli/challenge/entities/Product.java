package com.sqli.challenge.entities;

import com.sqli.challenge.utils.ProductConstructor;

public abstract class Product
{
  final String name;
  final int quantity;
  final double price;

  Product(String name, int quantity, double price)
  {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }
  
  Product(String name, int quantity)
  {
    this(name, quantity, 0);
  }

  final Product forAdd (final Product first, final Product second, final ProductConstructor productConstructor)
  {
    return productConstructor.call(first.getName(), first.getQuantity() + second.getQuantity(), first.getPrice());
  }
  
  final Product forRemoval (final Product first, final Product second, final ProductConstructor productConstructor)
  {
    final int newQuantity = first.getQuantity() - second.getQuantity();
    
    return newQuantity <= 0 ? null : productConstructor.call(first.getName(), newQuantity, first.getPrice());
  }
  
  public final double getTotalPrice ()
  {
    return price * quantity;
  }

  public abstract String groupingByIdentifier ();
  public abstract Product add (final Product product);
  public abstract Product remove (final Product product);

  public String getName()
  {
    return name;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public double getPrice()
  {
    return price;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (name == null)
    {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
