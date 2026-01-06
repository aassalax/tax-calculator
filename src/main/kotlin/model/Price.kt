package com.aassalax.model

import java.math.BigDecimal
import java.math.RoundingMode

@JvmInline
value class Price private constructor(val amount: BigDecimal){
    init {
        require(amount >= BigDecimal.ZERO) { "Price must be positive" }
    }

    fun asMoney(): BigDecimal =
        amount.setScale(2, RoundingMode.HALF_UP)

    operator fun plus(other: Price): Price =
        Price(this.amount.add(other.amount))

    operator fun times(times: Int): Price =
        Price(this.amount.multiply(times.toBigDecimal()))

    override fun toString(): String {
        return "$amount"
    }

    companion object {
        fun of(amount: BigDecimal): Price =
            Price(amount)

        fun from(value: String): Price =
            Price(BigDecimal(value))

        fun from(value: Double): Price =
            Price(BigDecimal.valueOf(value))

        fun from(value: Price): Price = Price(value.asMoney())
    }
}