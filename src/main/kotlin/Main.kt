package org.example

import org.example.machine.Drinks
import org.example.machine.PaymentMethod
import org.example.machine.VendingMachine

fun main() {
    val vendingMachine = VendingMachine()

    println("\n--- 테스트 1: 현금 결제 ---")
    vendingMachine.selectPaymentMethod(PaymentMethod.CASH)
    vendingMachine.insertCash(500)
    vendingMachine.insertCash(500)
    vendingMachine.selectDrink(Drinks.COKE) // 금액 부족

    println("\n--- 테스트 2: 추가 현금 투입 후 결제 ---")
    vendingMachine.insertCash(100)
    vendingMachine.selectDrink(Drinks.COKE) // 결제 성공

    println("\n--- 테스트 3: 카드 결제 ---")
    vendingMachine.selectPaymentMethod(PaymentMethod.CARD)
    vendingMachine.selectDrink(Drinks.WATER) // 카드 결제

    println("\n--- 테스트 4: 잘못된 순서로 결제 시도 ---")
    vendingMachine.selectDrink(Drinks.COFFEE) // 결제 방법 미선택 오류
}
