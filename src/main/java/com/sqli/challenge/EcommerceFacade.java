package com.sqli.challenge;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.sqli.challenge.entities.Capsule;
import com.sqli.challenge.entities.Machine;
import com.sqli.challenge.entities.Product;
import com.sqli.challenge.entities.Voucher;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;
import com.sqli.challenge.presenters.DefaultSummaryPresenter;
import com.sqli.challenge.presenters.SummaryPresenter;
import com.sqli.challenge.validators.CapsulePackagingRulesValidator;
import com.sqli.challenge.validators.DomainRulesValidator;
import com.sqli.challenge.validators.EmptyCartValidator;
import com.sqli.challenge.validators.VoucherCodeValidator;
import com.sqli.challenge.validators.VoucherRequiresMachinePurchaseValidator;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter = new DefaultCartContentPresenter();
  private final SummaryPresenter summaryPresenter = new DefaultSummaryPresenter();

  private Voucher voucher;
  private final Map<? super String, Product> cart = new HashMap<>();

  private Collection<? extends DomainRulesValidator> validators = Arrays.asList(new EmptyCartValidator(),
      new CapsulePackagingRulesValidator(), new VoucherCodeValidator(), new VoucherRequiresMachinePurchaseValidator());

  private Collection<? extends String> validationErrors;

  private void addProduct(final Product product)
  {
    cart.merge(product.getName(), product, (productFromMap, productFromMerge) -> productFromMap.add(product));
  }

  private void removeProduct(final Product product)
  {
    cart.computeIfPresent(product.getName(), (productNameToRemove, productToRemove) -> productToRemove.remove(product));
  }

  public void addMachine(final String name, final int quantity, final int price)
  {
    addProduct(new Machine(name, quantity, price));
  }

  public void removeMachine(final String name, final int quantity)
  {
    removeProduct(Machine.forRemoval(name, quantity));
  }

  public void addCapsule(final String name, final int quantity, final int price)
  {
    addProduct(new Capsule(name, quantity, price));
  }

  public void removeCapsule(final String name, final int quantity)
  {
    removeProduct(Capsule.forRemoval(name, quantity));
  }

  public String cartContent()
  {
    return cartContentPresenter.presentCartContent(getCartContent());
  }

  public String summary()
  {
    return summaryPresenter.presentSummary(getCartContent());
  }

  public Collection<Product> getCartContent()
  {
    return cart.values();
  }

  public EcommerceFacade order()
  {
    validationErrors = validators.stream().map(validator -> validator.validate(this)).flatMap(Collection::stream)
        .collect(Collectors.toList());

    return this;
  }

  public boolean hasErrors()
  {
    return !validationErrors.isEmpty();
  }

  public String errors()
  {
    return String.join("\n", validationErrors);
  }

  public void voucher(final String voucherCode)
  {
    voucher = new Voucher(voucherCode);
  }

  public Voucher getVoucher()
  {
    return voucher;
  }
}
