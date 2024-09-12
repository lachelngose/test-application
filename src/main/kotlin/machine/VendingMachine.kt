package org.example.machine

// 자판기 클래스
class VendingMachine {
    private var insertedCash: Int = 0
    private var selectedPaymentMethod: PaymentMethod? = null

    fun insertCash(amount: Int) {
        if (amount in listOf(100, 500, 1000, 5000, 10000)) {
            insertedCash += amount
            println("Inserted cash: $amount 원 (Total: $insertedCash 원)")
        } else {
            println("Invalid cash amount: $amount 원")
        }
    }

    fun selectPaymentMethod(paymentMethod: PaymentMethod) {
        selectedPaymentMethod = paymentMethod
        when (paymentMethod) {
            PaymentMethod.CARD -> println("Card payment selected.")
            PaymentMethod.CASH -> println("Cash payment selected.")
        }
    }

    fun selectDrink(drink: Drinks) {
        if (selectedPaymentMethod == null) {
            println("Please select a payment method first.")
            return
        }

        when (selectedPaymentMethod) {
            PaymentMethod.CASH -> processCashPayment(drink)
            PaymentMethod.CARD -> processCardPayment(drink)
            else -> println("Unknown payment method.")
        }
    }

    private fun processCashPayment(drink: Drinks) {
        if (insertedCash >= drink.price) {
            println("Dispensing ${drink.name} for ${drink.price} 원")
            val change = insertedCash - drink.price
            if (change > 0) {
                println("Returning change: $change 원")
            }
            resetTransaction()
        } else {
            val required = drink.price - insertedCash
            println("Insufficient cash. Please insert $required 원 more.")
        }
    }

    private fun processCardPayment(drink: Drinks) {
        println("Processing card payment for ${drink.name}. Price: ${drink.price} 원")
        println("Payment successful. Dispensing ${drink.name}")
        resetTransaction()
    }

    private fun resetTransaction() {
        insertedCash = 0
        selectedPaymentMethod = null
        println("Transaction complete. Ready for next customer.")
    }
}
