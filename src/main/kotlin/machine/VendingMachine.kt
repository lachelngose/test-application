package org.example.machine

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VendingMachine {
    private val log: Logger = LoggerFactory.getLogger(VendingMachine::class.java)

    private var insertedCash: Int = 0
    private var selectedPaymentMethod: PaymentMethod? = null

    private val availableCash = listOf(100, 500, 1000, 5000, 10000)

    fun displayAvailableDrinks() {
        log.info("구매 가능한 음료 목록:")
        Drinks.entries.forEach { drink ->
            log.info("${drink.displayName}: ${drink.price}원")
        }
    }

    fun displayPaymentMethods() {
        log.info("사용 가능한 결제 수단:")
        PaymentMethod.entries.forEach { method ->
            log.info(method.name)
        }
    }

    // 결제 방법 선택
    fun selectPaymentMethod(paymentMethod: PaymentMethod) {
        selectedPaymentMethod = paymentMethod
        when (paymentMethod) {
            PaymentMethod.CARD -> log.info("카드 결제를 선택하셨습니다.")
            PaymentMethod.CASH -> log.info("현금 결제를 선택하셨습니다.")
        }
    }

    // 현금 삽입
    fun insertCash(amount: Int) {
        if (amount <= 0) {
            log.error("유효하지 않은 금액: 현금은 0 또는 음수가 될 수 없습니다.")
            return
        }

        if (amount in availableCash) {
            insertedCash += amount
            log.info("현금 삽입: $amount 원 (총액: $insertedCash 원)")
        } else {
            log.error("유효하지 않은 금액: $amount 원. 유효한 지폐 단위 ${availableCash}로 다시 시도해주세요.")
        }
    }

    // 음료 선택
    fun selectDrink(drink: Drinks) {
        if (selectedPaymentMethod == null) {
            log.error("먼저 결제 방법을 선택해주세요.")
            return
        }

        when (selectedPaymentMethod) {
            PaymentMethod.CASH -> processCashPayment(drink)
            PaymentMethod.CARD -> processCardPayment(drink)
            else -> log.error("알 수 없는 결제 방법입니다.")
        }
    }

    // 현금 결제 처리
    private fun processCashPayment(drink: Drinks) {
        if (insertedCash >= drink.price) {
            log.info("${drink.displayName}을(를) ${drink.price} 원에 제공합니다.")
            val change = insertedCash - drink.price
            if (change > 0) {
                log.info("잔돈 반환: $change 원")
            }
            resetTransaction()
        } else {
            val required = drink.price - insertedCash
            log.warn("현금이 부족합니다. $required 원을 추가로 넣어주세요.")
            refund()
        }
    }

    // 카드 결제 처리
    private fun processCardPayment(drink: Drinks) {
        log.info("${drink.displayName}을(를) ${drink.price} 원에 카드 결제를 진행합니다.")
        log.info("결제가 완료되었습니다. ${drink.displayName}을(를) 제공합니다.")
        resetTransaction()
    }

    // 환불 처리
    private fun refund() {
        if (insertedCash > 0) {
            log.info("환불 처리: $insertedCash 원 반환")
            insertedCash = 0
        }
        resetTransaction()
    }

    // 트랜잭션 리셋
    private fun resetTransaction() {
        insertedCash = 0
        selectedPaymentMethod = null
        log.info("거래가 완료되었습니다. 다음 손님을 받을 준비가 되었습니다.")
    }
}
