package ii_collections

// Return the set of customers who ordered the specified product
fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> =
    this.customers.filter { it.orderedProducts.contains(product) }.toSet()

// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? =
        this.orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }

// Return the number of times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int =
        this.customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }

