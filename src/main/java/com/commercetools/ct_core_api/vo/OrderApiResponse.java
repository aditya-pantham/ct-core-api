package com.commercetools.ct_core_api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties
public class OrderApiResponse {
    private String type;
    private String id;
    private String orderNumber;
    private String customerId;
    private String customerEmail;
    private TotalPrice totalPrice;
    private TaxedPrice taxedPrice;
    private String country;
    private TaxedShippingPrice taxedShippingPrice;
    private String orderState;
    private String shipmentState;
    private String paymentState;
    private ArrayList<Object> syncInfo;
    private ArrayList<Object> returnInfo;
    private String taxMode;
    private String inventoryMode;
    private String taxRoundingMode;
    private String taxCalculationMode;
    private String origin;
    private String shippingMode;
    private ShippingInfo shippingInfo;
    private ShippingAddress shippingAddress;
    private ArrayList<Object> shipping;
    private ArrayList<LineItem> lineItems;
    private ArrayList<Object> customLineItems;
    private boolean transactionFee;
    private ArrayList<Object> discountCodes;
    private ArrayList<Object> directDiscounts;
    private Cart cart;
    private ArrayList<Object> itemShippingAddresses;
    private ArrayList<Object> refusedGifts;
    private Store store;
    private BusinessUnit businessUnit;
}

    @Getter
    @Setter
    class Amount {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class BusinessUnit {
        private String typeId;
        private String key;
    }

    @Getter
    @Setter
    class Cart {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class Channel {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class Discount {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class DiscountedAmount {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class DiscountedPrice {
        private Value value;
        private ArrayList<IncludedDiscount> includedDiscounts;
    }

    @Getter
    @Setter
    class DistributionChannel {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class FreeAbove {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class Image {
        private String url;
    }

    @Getter
    @Setter
    class IncludedDiscount {
        private Discount discount;
        private DiscountedAmount discountedAmount;
    }

    @Getter
    @Setter
    class LastModifiedBy {
        private boolean isPlatformClient;
    }

    @Getter
    @Setter
    class LineItem {
        private String id;
        private String productId;
        private String productKey;
        private Name name;
        private ProductType productType;
        private Variant variant;
        private Price price;
        private int quantity;
        private ArrayList<Object> discountedPricePerQuantity;
        private SupplyChannel supplyChannel;
        private DistributionChannel distributionChannel;
        private TaxRate taxRate;
        private ArrayList<Object> perMethodTaxRate;
        private Date addedAt;
        private Date lastModifiedAt;
        private ArrayList<State> state;
        private String priceMode;
        private String lineItemMode;
        private TotalPrice totalPrice;
        private TaxedPrice taxedPrice;
        private ArrayList<Object> taxedPricePortions;
    }

    @Getter
    @Setter
    class Price {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
        private String id;
        private Value value;
        private String key;
        private Channel channel;
    }

    @Getter
    @Setter
    class ProductType {
        private String typeId;
        private String id;
        private int version;
    }

    @Getter
    @Setter
    class ShippingAddress {
        private String streetName;
        private String streetNumber;
        private String additionalStreetInfo;
        private String postalCode;
        private String city;
        private String region;
        private String state;
        private String country;
        private String company;
        private String building;
        private String apartment;
        private String key;
    }

    @Getter
    @Setter
    class ShippingInfo {
        private String shippingMethodName;
        private Price price;
        private ShippingRate shippingRate;
        private TaxRate taxRate;
        private TaxCategory taxCategory;
        private ArrayList<Object> deliveries;
        private ShippingMethod shippingMethod;
        private DiscountedPrice discountedPrice;
        private TaxedPrice taxedPrice;
        private String shippingMethodState;
    }

    @Getter
    @Setter
    class ShippingMethod {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class ShippingRate {
        private Price price;
        private FreeAbove freeAbove;
    }

    @Getter
    @Setter
    class State {
        private int quantity;
        private State state;
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class Store {
        private String typeId;
        private String key;
    }

    @Getter
    @Setter
    class SupplyChannel {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class TaxCategory {
        private String typeId;
        private String id;
    }

    @Getter
    @Setter
    class TaxedPrice {
        private TotalNet totalNet;
        private TotalGross totalGross;
        private ArrayList<TaxPortion> taxPortions;
        private TotalTax totalTax;
    }

    @Getter
    @Setter
    class TaxedShippingPrice {
        private TotalNet totalNet;
        private TotalGross totalGross;
        private ArrayList<TaxPortion> taxPortions;
        private TotalTax totalTax;
    }

    @Getter
    @Setter
    class TaxPortion {
        private double rate;
        private Amount amount;
        private String name;
    }

    @Getter
    @Setter
    class TaxRate {
        private String name;
        private double amount;
        private boolean includedInPrice;
        private String country;
        private String id;
        private String key;
        private ArrayList<Object> subRates;
    }

    @Getter
    @Setter
    class TotalGross {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class TotalNet {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class TotalPrice {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class TotalTax {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class Value {
        private String type;
        private String currencyCode;
        private int centAmount;
        private int fractionDigits;
    }

    @Getter
    @Setter
    class Variant {
        private int id;
        private String sku;
    }