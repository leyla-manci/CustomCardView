package lyl.manci.customcreditcard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.view_credit_card.view.*
import kotlinx.android.synthetic.main.view_credit_card_back.view.*
import kotlinx.android.synthetic.main.view_credit_card_front.view.*


/**       Code with ❤  ´• ل •`   ❤
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
▬     Created by Leyla Akmancı                ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬    ▬
▬     leyla.manci@gmail.com                           ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬     ▬
▬     10/08/2020 - 19:05        ▬
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
 */
class CreditCardCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(
    context,
    attrs,
    defStyleAttr
) {
    val CARD_NUMBER_TOTAL_SYMBOLS = 19 // size of pattern 0000-0000-0000-0000
    val CARD_NUMBER_TOTAL_DIGITS = 16 // max numbers of digits in pattern: 0000 x 4
    val CARD_NUMBER_DIVIDER_MODULO =
        5 // means divider position is every 5th symbol beginning with 1
    val CARD_NUMBER_DIVIDER_POSITION =
        CARD_NUMBER_DIVIDER_MODULO - 1 // means divider position is every 4th symbol beginning with 0
    val CARD_NUMBER_DIVIDER = '-'

    val CARD_DATE_TOTAL_SYMBOLS = 5 // size of pattern MM/YY
    val CARD_DATE_TOTAL_DIGITS = 4 // max numbers of digits in pattern: MM + YY
    val CARD_DATE_DIVIDER_MODULO = 3 // means divider position is every 3rd symbol beginning with 1
    val CARD_DATE_DIVIDER_POSITION =
        CARD_DATE_DIVIDER_MODULO - 1 // means divider position is every 2nd symbol beginning with 0
    val CARD_DATE_DIVIDER = '/'
    val CARD_CVC_TOTAL_SYMBOLS = 3

    init {

        LayoutInflater.from(context).inflate(R.layout.view_credit_card, this)

        btnCvc.setOnClickListener {
            incCreditCardFront.animate().rotation(10F).translationX(-1000F).alpha(0.0F).duration =
                700
            incCreditCardBack.animate().translationY(0F).alpha(1.0F).duration = 1000
        }

        btnFront.setOnClickListener {
            incCreditCardFront.animate().rotation(0F).translationX(0F).alpha(1.0F).duration = 1000
            incCreditCardBack.animate().translationY(0F).alpha(0.0F).duration = 700
        }
    }
}