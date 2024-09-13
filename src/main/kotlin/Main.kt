package org.example

import org.example.machine.Drinks
import org.example.machine.PaymentMethod
import org.example.machine.VendingMachine

fun main() {
    val vendingMachine = VendingMachine()

    println("=== 정상적인 상황 ===")
    vendingMachine.displayAvailableDrinks()
    vendingMachine.displayPaymentMethods()
    vendingMachine.selectPaymentMethod(PaymentMethod.CASH)
    vendingMachine.insertCash(5000)
    vendingMachine.selectDrink(Drinks.COKE)

    println("\n=== 금액 부족 예외 상황 ===")
    vendingMachine.selectPaymentMethod(PaymentMethod.CASH)
    vendingMachine.insertCash(500)
    vendingMachine.selectDrink(Drinks.COKE)

    println("\n=== 카드 결제 상황 ===")
    vendingMachine.selectPaymentMethod(PaymentMethod.CARD)
    vendingMachine.selectDrink(Drinks.WATER)
}
